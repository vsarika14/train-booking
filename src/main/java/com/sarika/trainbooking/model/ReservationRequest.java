package com.sarika.trainbooking.model;

import com.sarika.trainbooking.enums.CustomerPreference;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ReservationRequest {
    List<Traveller> travellers;
    Integer trainId;
    CustomerPreference customerPreference;
    Date travelDate;
}
