package com.example.uberprojectauthservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseModel{



    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startingTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date EndingTime;

    private long totalDistance;

    @Enumerated(value = EnumType.STRING) //Stores enum as a string in database
    private BookingStatus bookingStatus;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

}