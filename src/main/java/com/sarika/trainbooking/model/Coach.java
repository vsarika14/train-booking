package com.sarika.trainbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coach implements Serializable {
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="coach_berth", joinColumns={@JoinColumn(name="coach_id"),@JoinColumn(name="train_id")}, inverseJoinColumns=@JoinColumn(name="berth_id"))
    private List<Berth> berths;
}
