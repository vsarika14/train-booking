package com.sarika.trainbooking.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String source;
    @NonNull
    private String destination;
    private String stops;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="train_coach", joinColumns={@JoinColumn(name="train_id")}, inverseJoinColumns=@JoinColumn(name="coach_id"))
    private List<Coach> coaches;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumns({@JoinColumn(name = "train_id")})
//    private List<Reservation> reservations;
}
