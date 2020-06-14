package com.sarika.trainbooking.service;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.sarika.trainbooking.enums.BerthType;
import com.sarika.trainbooking.enums.CoachType;
import com.sarika.trainbooking.model.*;
import com.sarika.trainbooking.repository.BerthRepository;
import com.sarika.trainbooking.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    BerthRepository berthRepository;

    @Autowired
    TrainRepository trainRepository;

    @Override
    @Transactional
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest) throws Exception {
        //authenticate customer
        //validate reservation request
        //create order
        // step1: get berth availability for a date

        List<BerthAvailability> berthAvailabilities = berthRepository.getAvailability(
                reservationRequest.getTrainId(), reservationRequest.getTravelDate());

        // step2: do matching based on travellers preference

        matchAndUpdate(reservationRequest.getTravellers(), berthAvailabilities);

        // step3a: if not matched return error


        // step3b: if matched then update berth availability for that date


        // step4: compute the cost and create reservation


        return ReservationResponse.builder().build();
    }

    private void matchAndUpdate(List<Traveller> travellers, List<BerthAvailability> berthAvailabilities) throws Exception {

        Table<CoachType, BerthType, Integer> preferenceTable = HashBasedTable.create();

        for (Traveller traveller : travellers) {

            Integer count=preferenceTable.get(traveller.getPreference().getCoachType(),
                    traveller.getPreference().getBerthType());
            if(count!=null)
            {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        count+1);
            }
            else {
                preferenceTable.put(traveller.getPreference().getCoachType(),
                        traveller.getPreference().getBerthType(),
                        1);
            }


        }

        Table<String, String, Integer> availabilityTable = HashBasedTable.create();

        for (BerthAvailability berthAvailability : berthAvailabilities) {

            Integer count=availabilityTable.get(berthAvailability.getCoachType(),
                    berthAvailability.getBerthType());
            if(count!=null)
            {
                availabilityTable.put(berthAvailability.getCoachType(),
                        berthAvailability.getBerthType(),
                        count+1);
            }
            else {
                availabilityTable.put(berthAvailability.getCoachType(),
                        berthAvailability.getBerthType(),
                        berthAvailability.getCount());
            }
        }

        for (Table.Cell cell : preferenceTable.cellSet()) {
            if((Integer)cell.getValue() > (Integer)availabilityTable.get(cell.getRowKey()+"",cell.getColumnKey()+""))
            {
                throw new Exception("no match");
            }
        }

        for (Table.Cell cell : preferenceTable.cellSet()) {
            Optional<BerthAvailability> berthAvailabilityOptional = berthAvailabilities.stream().filter(x->
                    x.getCoachType().equals(cell.getRowKey()+"")&&x.getBerthType().equals(cell.getColumnKey()+"")).findFirst();
            if(berthAvailabilityOptional.isPresent())
            {
                BerthAvailability b= berthAvailabilityOptional.get();
                b.setCount(b.getCount()-1);
            }
        }

    }

}
