package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateDto {

    @NotEmpty(message ="Поле должно быть заполнено")
    Long id;

    @NotEmpty(message ="Поле должно быть заполнено")
    String name;

    @NotEmpty(message ="Поле должно быть заполнено")
    String description;
}