package com.nordclan.test_project.dto.meet_room;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MeetRoomBookedPeriodsDto {

    Long id;

    String name;

    Set<BookedPeriod> bookedPeriods;
}