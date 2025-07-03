package com.example.uberprojectauthservice.dto;

import com.example.uberprojectentityservice.models.Passenger;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerDto {

    private String id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String createdAt;

    public static PassengerDto toDto(Passenger passenger) {

        return PassengerDto.builder()
                .id(Long.toString(passenger.getId()))
                .name(passenger.getName())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .createdAt(String.valueOf(passenger.getCreatedAt()))
                .build();
    }

}

