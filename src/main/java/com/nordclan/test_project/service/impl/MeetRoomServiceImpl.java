package com.nordclan.test_project.service.impl;

import com.nordclan.test_project.dto.meet_room.BookedPeriod;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.MeetRoom;
import com.nordclan.test_project.exception.ResourceNotFoundException;
import com.nordclan.test_project.repository.BookingRepo;
import com.nordclan.test_project.repository.MeetRoomRepo;
import com.nordclan.test_project.service.MeetRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class MeetRoomServiceImpl implements MeetRoomService {

    private final BookingRepo bookingRepo;
    private final MeetRoomRepo meetRoomRepo;

    @Override
    public boolean isBooked(MeetRoomDto meetRoomDto, LocalDateTime timeFrom, LocalDateTime timeTo) {
        MeetRoom meetRoom = meetRoomRepo.findByName(meetRoomDto.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Meet room with id '%s' not found", meetRoomDto.getId())));

        Set<Booking> listOfBookings = bookingRepo.findBookingsByPeriodAndMeetRoom(meetRoom.getId(), timeFrom, timeTo);

        return !listOfBookings.isEmpty();
    }

    @Override
    public Set<MeetRoom> getAllMeetRooms() {
        return null;
    }

    @Override
    public Set<BookedPeriod> getBookedPeriodByMeetRoom(MeetRoomDto meetRoomDto) {
        return null;
    }
}
