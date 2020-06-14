package com.sarika.trainbooking.model;

import com.sarika.trainbooking.enums.CoachType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "coach_type")
    private CoachType coachType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="coach_berth", joinColumns={@JoinColumn(name="coach_id")}, inverseJoinColumns=@JoinColumn(name="berth_id"))
    private List<Berth> berths;
}
