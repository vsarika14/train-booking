package com.sarika.trainbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "BookingDetails")
@Table(name = "booking_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "coach_id")
    private Integer coachId;
    @Column(name = "berth_id")
    private Integer berthId;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pnr", nullable = false)
    @JsonIgnore
    private Reservation reservation;

}
