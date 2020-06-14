package com.sarika.trainbooking.service;

import com.sarika.trainbooking.model.AvailabilityInfoResponse;
import com.sarika.trainbooking.model.BerthAvailability;
import com.sarika.trainbooking.model.ReservationRequest;
import com.sarika.trainbooking.model.ReservationResponse;

import java.util.List;

public interface IReservationService {
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest) throws Exception;

    List<AvailabilityInfoResponse> checkAvailability(int trainId) throws Exception;
}
