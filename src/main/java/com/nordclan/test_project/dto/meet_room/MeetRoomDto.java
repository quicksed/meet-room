package com.nordclan.test_project.dto.meet_room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetRoomDto {

    Long id;

    String name;

    public MeetRoomDto() {}

    public MeetRoomDto(String name) {
        this.name = name;
    }
}
