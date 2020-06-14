package com.sarika.trainbooking.model;

import com.sarika.trainbooking.enums.CoachType;
import com.sarika.trainbooking.enums.Gender;
import com.sarika.trainbooking.enums.BerthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Traveller {
    private String name;
    private Gender gender;
    private Integer age;
    private Preference preference;
}
