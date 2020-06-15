package com.sarika.trainbooking.service;

import com.sarika.trainbooking.model.*;

import java.util.List;

public interface IReservationService {
    public ReservationResponse reserve(Integer customerId, ReservationRequest reservationRequest) throws Exception;

    List<AvailabilityInfoResponse> checkAvailability(int trainId) throws Exception;

    PNRInfoResponse getReservationDetails(int pnr) throws Exception;

}
