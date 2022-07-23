package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetRoomMapper {

    public MeetRoomDto toDto(MeetRoom model) {
        MeetRoomDto meetRoomDto = new MeetRoomDto();
        meetRoomDto.setName(model.getName());
        meetRoomDto.setName(model.getName());

        return meetRoomDto;
    }

    public List<MeetRoomDto> toDto(List<MeetRoom> model) {
        return model.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
