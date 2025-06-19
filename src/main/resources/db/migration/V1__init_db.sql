create TABLE booking
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    review_id      BIGINT                NULL,
    starting_time  datetime              NULL,
    ending_time    datetime              NULL,
    total_distance BIGINT                NOT NULL,
    booking_status ENUM('SCHEDULED',
                        'CANCELLED',
                        'CAB_ARRIVED',
                        'ASSIGINING_DRIVER',
                        'IN_RIDE',
                        'COMPLETED')     NULL,
    driver_id      BIGINT                NULL,
    passenger_id   BIGINT                NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

create TABLE driver
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    name           VARCHAR(255)          NULL,
    licence_number VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

create TABLE driver_review
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    created_at            datetime              NOT NULL,
    updated_at            datetime              NOT NULL,
    driver_review_content VARCHAR(255)          NULL,
    CONSTRAINT pk_driverreview PRIMARY KEY (id)
);

create TABLE passenger
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    name       VARCHAR(255)          NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

create TABLE passenger_review
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    created_at               datetime              NOT NULL,
    updated_at               datetime              NOT NULL,
    passenger_review_content VARCHAR(255)          NULL,
    CONSTRAINT pk_passengerreview PRIMARY KEY (id)
);

create TABLE review
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    content    VARCHAR(255)          NOT NULL,
    rating     DOUBLE                NOT NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

alter table driver
    add CONSTRAINT uc_driver_licencenumber UNIQUE (licence_number);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

alter table booking
    add CONSTRAINT FK_BOOKING_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);