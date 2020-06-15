package com.sarika.trainbooking.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReservationResponse {
    List<TravellerFare> travellerFares;
    Double totalAmount;
    int pnr;
    String status;
}
