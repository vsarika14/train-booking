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

@Entity(name = "CoachBerth")
@Table(name = "coach_berth")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachBerth implements Serializable {
    @Id
    @Column(name = "coach_id")
    private Integer coachId;
    @Id
    @Column(name = "berth_id")
    private Integer berthId;
}
