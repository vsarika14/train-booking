package com.sarika.trainbooking.model;

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
    private Integer pnr;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

//    @Id
//    @Column(name = "traveller_id")
//    private Integer travellerId;

}
