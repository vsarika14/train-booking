package com.sarika.trainbooking.service;

import com.sarika.trainbooking.model.ReservationRequest;
import com.sarika.trainbooking.model.ReservationResponse;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {
    @Override
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest) {
        //authenticate customer
        //validate reservation request
        //create order
        return ReservationResponse.builder().build();
    }
}
