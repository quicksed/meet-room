package com.nordclan.test_project.service;

import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.exception.ResourceNotFoundException;

import java.util.List;

public interface MeetRoomService {

    MeetRoomDto findByName(String name) throws ResourceNotFoundException;

    List<MeetRoomDto> findAll();
}
