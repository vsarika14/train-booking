package com.sarika.trainbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "TrainCoach")
@Table(name = "train_coach")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainCoach implements Serializable {
    @Id
    @Column(name = "train_id")
    private Integer trainId;
    @Id
    @Column(name = "coach_id")
    private Integer coachId;
    @Column(name = "coach_count")
    private Integer coachCount;
    @Column(name = "coach_type")
    private String coachType;

    private Double cost;


}
