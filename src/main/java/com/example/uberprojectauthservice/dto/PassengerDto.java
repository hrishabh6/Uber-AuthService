package com.example.uberprojectauthservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerDto {

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String createdAt;


}

