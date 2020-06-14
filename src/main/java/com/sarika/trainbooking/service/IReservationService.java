package com.sarika.trainbooking.service;

import com.sarika.trainbooking.model.ReservationRequest;
import com.sarika.trainbooking.model.ReservationResponse;

public interface IReservationService {
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest);
}
