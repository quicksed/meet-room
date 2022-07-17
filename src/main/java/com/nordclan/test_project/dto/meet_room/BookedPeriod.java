package com.nordclan.test_project.dto.meet_room;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookedPeriod {

    LocalDateTime timeFrom;

    LocalDateTime timeTo;
}
