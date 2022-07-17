package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.meet_room.MeetRoomBookedPeriodsDto;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.stereotype.Component;

@Component
public class MeetRoomMapper {

    public MeetRoomDto toDto(MeetRoom model) {
        MeetRoomDto meetRoomDto = new MeetRoomDto();
        meetRoomDto.setName(model.getName());
        meetRoomDto.setName(model.getName());

        return meetRoomDto;
    }

    public MeetRoomBookedPeriodsDto toBookedPeriodsDto(MeetRoom model) {
        MeetRoomBookedPeriodsDto meetRoomBookedPeriodsDto = new MeetRoomBookedPeriodsDto();
        meetRoomBookedPeriodsDto.setId(model.getId());
        meetRoomBookedPeriodsDto.setName(model.getName());

        return meetRoomBookedPeriodsDto;
    }
}
