package com.nordclan.test_project.dto.booking;

import com.nordclan.test_project.dto.employee.EmployeeDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingDto {

    Long id;

    String name;

    String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime timeFrom;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime timeTo;

    Long meetRoomId;

    boolean isJoined;

    boolean isOwner;

    List<EmployeeDto> members;
}