package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingCreateDto {

    String name;

    String description;

    LocalDateTime timeFrom;

    LocalDateTime timeTo;

    String stringTimeFrom;

    String stringTimeTo;

    Long meetRoomId;

    Long ownerId;
}
