ALTER TABLE review
    ADD booking_id BIGINT NULL;

ALTER TABLE review
    MODIFY booking_id BIGINT NOT NULL;

ALTER TABLE passenger_review
    ADD passenger_review_rating VARCHAR(255) NULL;

ALTER TABLE passenger_review
    MODIFY passenger_review_rating VARCHAR (255) NOT NULL;


ALTER TABLE review
    ADD CONSTRAINT uc_review_booking UNIQUE (booking_id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id);

ALTER TABLE booking
DROP
COLUMN booking_status;

ALTER TABLE booking
    ADD booking_status VARCHAR(255) NULL;

ALTER TABLE passenger_review
    MODIFY passenger_review_content VARCHAR (255) NOT NULL;

ALTER TABLE review
    MODIFY rating DOUBLE NULL;