package com.sarika.trainbooking.model;

import com.sarika.trainbooking.enums.BerthType;
import com.sarika.trainbooking.enums.CoachType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Preference {
    private CoachType coachType;
    private BerthType berthType;
}
