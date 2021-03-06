package com.sarika.trainbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravellerFare {
    private String name;
    private Double amountBeforeDiscount;
    private Integer discountPercentageIfAny;
    private Double totalAmount;
}
