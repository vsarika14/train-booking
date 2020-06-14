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

@Entity(name = "BookingDetails")
@Table(name = "booking_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails implements Serializable {
    @Column(name = "coach_id")
    private Integer coachId;
    @Column(name = "berth_id")
    private Integer berthId;
    private String status;
    @Id
    @Column(name = "traveller_id")
    private Integer travellerId;
    @Id
    private Integer pnr;
}
