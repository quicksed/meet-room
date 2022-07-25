package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingDayDto {

    String date;

    List<BookingDto> bookings;

    public BookingDayDto(String date, List<BookingDto> bookings) {
        this.date = date;
        this.bookings = bookings;
    }
}
