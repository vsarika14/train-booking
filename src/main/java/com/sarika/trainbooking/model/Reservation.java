package com.sarika.trainbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pnr;

    //    @Column(name = "train_id")
    //    private Integer trainId;

    @OneToOne
    @JoinColumn(name = "train_id")
    private Train train;

    private Double amount;
    private String status;

    @Column(name = "travel_date")
    private Date travelDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "pnr")})
    private List<BookingDetails> bookingDetails;
}
