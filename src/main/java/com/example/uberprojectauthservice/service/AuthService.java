package com.example.uberprojectauthservice.service;


import com.example.uberprojectauthservice.dto.PassengerDto;
import com.example.uberprojectauthservice.dto.PassengerSignupRequestDto;
import com.example.uberprojectauthservice.models.Passenger;
import com.example.uberprojectauthservice.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signUp(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger = Passenger.builder()
                                        .name(passengerSignupRequestDto.getName())
                                        .email(passengerSignupRequestDto.getEmail())
                                        .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword())) //Todo : Encrypt the password
                                        .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                                        .build();
        Passenger newPassenger = passengerRepository.save(passenger);

        return PassengerDto.toDto(newPassenger);

    }

}
