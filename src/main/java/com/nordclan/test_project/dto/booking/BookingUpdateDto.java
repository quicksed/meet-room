package com.nordclan.test_project.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateDto {

    @NotNull(message ="Поле должно быть заполнено")
    Long id;

    @NotEmpty(message ="Поле должно быть заполнено")
    String name;

    @NotEmpty(message ="Поле должно быть заполнено")
    String description;
}