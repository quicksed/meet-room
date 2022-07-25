package com.nordclan.test_project.service;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDayDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.exception.AlreadyInBookingException;
import com.nordclan.test_project.exception.IncorrectBookingPeriodException;
import com.nordclan.test_project.exception.PeriodAlreadyBookedException;
import com.nordclan.test_project.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingDto findById(Long bookingId);

    List<BookingDayDto> findBookingsByMeetRoomAndDateOnWeek(MeetRoomDto meetRoomDto, LocalDateTime dateTime);

    void joinToBooking(Long bookingId) throws AlreadyInBookingException, ResourceNotFoundException;

    void exitFromBooking(Long bookingId) throws AlreadyInBookingException, ResourceNotFoundException;

    void createBooking(BookingCreateDto bookingCreateDto) throws PeriodAlreadyBookedException, IncorrectBookingPeriodException, ResourceNotFoundException;

    void updateBooking(BookingUpdateDto bookingUpdateDto) throws ResourceNotFoundException;

    void deleteBooking(Long bookingId) throws ResourceNotFoundException;
}
