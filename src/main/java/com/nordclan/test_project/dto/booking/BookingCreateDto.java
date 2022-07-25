package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingCreateDto {

    @NotEmpty(message ="Поле должно быть заполнено")
    String name;

    @NotEmpty(message ="Поле должно быть заполнено")
    String description;

    LocalDateTime timeFrom;

    LocalDateTime timeTo;

    @NotEmpty(message ="Поле должно быть заполнено")
    String stringTimeFrom;

    @NotEmpty(message ="Поле должно быть заполнено")
    String stringTimeTo;

    Long meetRoomId;

    Long ownerId;
}
