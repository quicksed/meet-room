package com.nordclan.test_project.service;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;

import java.security.Principal;

public interface BookingService {

    void createBooking(BookingCreateDto bookingCreateDto, Principal principal);

    void joinToBooking(BookingDto bookingDto, Principal principal);

    void updateBooking(BookingUpdateDto bookingUpdateDto, Principal principal);

    void deleteBooking(Long bookingId, Principal principal);
}
