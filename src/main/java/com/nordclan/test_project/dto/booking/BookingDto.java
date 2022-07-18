package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto {

    Long id;

    String name;

    String description;

    LocalDateTime timeFrom;

    LocalDateTime timeTo;

    Long meetRoomId;
}