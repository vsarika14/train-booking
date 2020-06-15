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


        return PNRInfoResponse.builder().trainId(reservation.getTrain().getId())
                .travelDate(reservation.getTravelDate())
                .bookingDetails(reservation.getBookingDetails())
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

        matchAndUpdate(reservationRequest.getTravellers(), berthAvailabilities);

        // step4: compute the cost and create reservation
        ReservationResponse response = computeCost(reservationRequest);


        return response;
    }

    private ReservationResponse computeCost(ReservationRequest reservationRequest) {
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
        return ReservationResponse.builder().travellerFares(travellerFares).totalAmount(totalAmount).build();
    }

    private int getDiscountPercentage(Integer age) {
        if(age<12 || age>60)
            return 10;
        return 0;
    }

    private void matchAndUpdate(List<Traveller> travellers, List<BerthAvailability> berthAvailabilities) throws Exception {

        Table<CoachType, BerthType, Integer> preferenceTable = HashBasedTable.create();

        for (Traveller traveller : travellers) {

            Integer count = preferenceTable.get(traveller.getPreference().getCoachType(),
                    traveller.getPreference().getBerthType());
            if (count != null) {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        count + 1);
            } else {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        1);
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

        for (Table.Cell cell : preferenceTable.cellSet()) {
            Optional<BerthAvailability> berthAvailabilityOptional = berthAvailabilities.stream().filter(x ->
                    x.getCoachType().equals(cell.getRowKey() + "") && x.getBerthType().equals(cell.getColumnKey() + "")).findFirst();
            if (berthAvailabilityOptional.isPresent()) {
                BerthAvailability b = berthAvailabilityOptional.get();
                b.setCount(b.getCount() - (Integer)cell.getValue());
            }
        }

    }

}
