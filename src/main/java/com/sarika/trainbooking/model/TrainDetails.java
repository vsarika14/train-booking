package com.sarika.trainbooking.model;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "train_details")
public class TrainDetails {
    @Id
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String source;
    @NonNull
    private String destination;
    @NonNull
    private Double cost;
}
