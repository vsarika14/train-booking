package com.sarika.trainbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityInfoResponse {
    private String coachType;
    private String berthType;
    private Integer count;
}
