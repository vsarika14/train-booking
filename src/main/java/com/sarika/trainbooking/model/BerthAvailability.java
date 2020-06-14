package com.sarika.trainbooking.model;

import com.sarika.trainbooking.enums.BerthType;
import com.sarika.trainbooking.enums.CoachType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "BerthAvailability")
@Table(name = "berth_availability")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BerthAvailability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "train_id")
    private Integer trainId;
    @Column(name = "coach_type")
    private String coachType;
    @Column(name = "berth_type")
    private String berthType;
    private Integer count;
    @Column(name = "travel_date")
    private Date travelDate;

}
