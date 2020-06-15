package com.sarika.trainbooking.service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.sarika.trainbooking.enums.BerthType;
import com.sarika.trainbooking.enums.CoachType;
import com.sarika.trainbooking.model.*;
import com.sarika.trainbooking.repository.BerthRepository;
import com.sarika.trainbooking.repository.ReservationRepository;
import com.sarika.trainbooking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    BerthRepository berthRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Override
    public List<AvailabilityInfoResponse> checkAvailability(int trainId) throws Exception {

        List<BerthAvailability> berthAvailabilities = berthRepository.getAvailability(trainId);
        List<AvailabilityInfoResponse> resultInfo = new ArrayList<>();

        for (BerthAvailability b : berthAvailabilities) {
            resultInfo.add(AvailabilityInfoResponse.builder()
                    .berthType(b.getBerthType())
                    .coachType(b.getCoachType()).count(b.getCount())
                    .build());
        }

        return resultInfo;
    }

    @Override
    public PNRInfoResponse getReservationDetails(int pnr) throws Exception{

        Reservation reservation = reservationRepository.find(pnr);

        List<BookingDetailsResponse> bookingDetailsResponses = new ArrayList<>();

        reservation.getBookingDetails().forEach(x->bookingDetailsResponses.add(BookingDetailsResponse.builder()
                                                .passenger(x.getPassenger()).status(reservation.getStatus())
                                                .id(x.getId()).build()));

        return PNRInfoResponse.builder().trainId(reservation.getTrain().getId())
                .travelDate(reservation.getTravelDate())
                .bookingDetails(bookingDetailsResponses)
                .build();
    }


    @Override
    @Transactional
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest) throws Exception {
        //TODO: authenticate customer
        //TODO: validate reservation request

        // step1: get berth availability for a date

        List<BerthAvailability> berthAvailabilities = berthRepository.getAvailability(
                reservationRequest.getTrainId());// reservationRequest.getTravelDate()

        // step2: do matching based on travellers preference
        // step3a: if not matched return error
        // step3b: if matched then update berth availability for that date

        Integer pnr = matchAndUpdate(reservationRequest.getTravellers(), berthAvailabilities, reservationRequest.getTrainId());

        // step4: compute the cost and create reservation
        ReservationResponse reservationResponse = computeCost(reservationRequest, pnr);
        //createReservation(reservationResponse, reservationRequest.getTravellers());

        return reservationResponse;
    }

    private ReservationResponse computeCost(ReservationRequest reservationRequest, Integer pnr) {
        List<TravellerFare> travellerFares = new ArrayList<>();
        Double totalAmount= Double.valueOf(0);
        for (Traveller traveller : reservationRequest.getTravellers()) {
            Double cost = trainRepository.getCostByTrainAndCoachType(reservationRequest.getTrainId(),
                    traveller.getPreference().getCoachType().toString()).getCost();
            int discountPercentage = getDiscountPercentage(traveller.getAge());
            Double discountedFare = cost-(cost*discountPercentage/100);
            travellerFares.add(TravellerFare.builder()
                    .name(traveller.getName())
                    .amountBeforeDiscount(cost)
                    .discountPercentageIfAny(discountPercentage)
                    .totalAmount(discountedFare)
                    .build());
            totalAmount+=discountedFare;
        }
        Reservation reservation= reservationRepository.find(pnr);
        reservation.setAmount(totalAmount);
        for(BookingDetails bookingDetails : reservation.getBookingDetails())
        {
            bookingDetails.setReservation(reservation);
        }
        return ReservationResponse.builder().travellerFares(travellerFares)
                .totalAmount(totalAmount).pnr(pnr).status(reservation.getStatus()).build();
    }

    private int getDiscountPercentage(Integer age) {
        if(age<12 || age>60)
            return 10;
        return 0;
    }

    private Integer matchAndUpdate(List<Traveller> travellers, List<BerthAvailability> berthAvailabilities, int trainId) throws Exception {

        Table<CoachType, BerthType, Integer> preferenceTable = HashBasedTable.create();
        Table<CoachType, BerthType, List<Traveller>> travellerTable = HashBasedTable.create();

        for (Traveller traveller : travellers) {

            Integer count = preferenceTable.get(traveller.getPreference().getCoachType(),
                    traveller.getPreference().getBerthType());
            if (count != null) {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        count + 1);
                travellerTable.get(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType()).add(traveller);
            } else {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        1);
                List<Traveller> travellerList = new ArrayList<>();
                travellerList.add(traveller);
                travellerTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        travellerList);
            }

        }

        Table<String, String, Integer> availabilityTable = HashBasedTable.create();

        for (BerthAvailability berthAvailability : berthAvailabilities) {

            Integer count = availabilityTable.get(berthAvailability.getCoachType(),
                    berthAvailability.getBerthType());
            if (count != null) {
                availabilityTable.put(berthAvailability.getCoachType(),
                        berthAvailability.getBerthType(),
                        count + 1);
            } else {
                availabilityTable.put(berthAvailability.getCoachType(),
                        berthAvailability.getBerthType(),
                        berthAvailability.getCount());
            }
        }

        for (Table.Cell cell : preferenceTable.cellSet()) {
            if ((Integer) cell.getValue() > (Integer) availabilityTable.get(cell.getRowKey() + "", cell.getColumnKey() + "")) {
                throw new Exception("no match");
            }
        }

        List<BookingDetails> bookingDetails=new ArrayList<>();
        for (Table.Cell cell : preferenceTable.cellSet()) {
            Optional<BerthAvailability> berthAvailabilityOptional = berthAvailabilities.stream().filter(x ->
                    x.getCoachType().equals(cell.getRowKey() + "") && x.getBerthType().equals(cell.getColumnKey() + "")).findFirst();
            if (berthAvailabilityOptional.isPresent()) {
                BerthAvailability b = berthAvailabilityOptional.get();
                b.setCount(b.getCount() - (Integer)cell.getValue());
                for(Traveller traveller:travellerTable.get(cell.getRowKey(),cell.getColumnKey())) {
                    Passenger p = Passenger.builder().name(traveller.getName())
                            .age(traveller.getAge()).gender(traveller.getGender()).build();
                    BookingDetails booking = BookingDetails.builder().passenger(p).status("CNF").build();
                    bookingDetails.add(booking);
                }
            }
        }
        Train train=trainRepository.find(trainId);
        Reservation r = Reservation.builder().bookingDetails(bookingDetails).status("CNF").train(train).build();
        reservationRepository.save(r);

        return r.getPnr();
    }

}
