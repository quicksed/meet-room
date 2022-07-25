package com.nordclan.test_project.service.impl;

import com.nordclan.test_project.dto.mapper.MeetRoomMapper;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.MeetRoom;
import com.nordclan.test_project.exception.ResourceNotFoundException;
import com.nordclan.test_project.repository.BookingRepo;
import com.nordclan.test_project.repository.MeetRoomRepo;
import com.nordclan.test_project.service.MeetRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class MeetRoomServiceImpl implements MeetRoomService {

    private final BookingRepo bookingRepo;
    private final MeetRoomRepo meetRoomRepo;
    private final MeetRoomMapper meetRoomMapper;

    @Override
    public MeetRoomDto findByName(String name) throws ResourceNotFoundException {
        MeetRoom meetRoom = meetRoomRepo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Переговорная '%s' не найдена", name)));

        return meetRoomMapper.toDto(meetRoom);
    }

    @Override
    public List<MeetRoomDto> findAll() {
        return meetRoomMapper.toDto(meetRoomRepo.findAll());
    }
}
