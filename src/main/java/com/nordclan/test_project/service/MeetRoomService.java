package com.nordclan.test_project.service;

import com.nordclan.test_project.dto.meet_room.BookedPeriod;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.MeetRoom;

import java.time.LocalDateTime;
import java.util.Set;

public interface MeetRoomService {

    boolean isBooked(MeetRoomDto meetRoomDto, LocalDateTime timeFrom, LocalDateTime timeTo);

    Set<MeetRoom> getAllMeetRooms();

    Set<BookedPeriod> getBookedPeriodByMeetRoom(MeetRoomDto meetRoomDto);
}
