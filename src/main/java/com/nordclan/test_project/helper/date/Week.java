package com.nordclan.test_project.helper.date;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Week {

    private final LocalDateTime startOfWeek;
    private final LocalDateTime endOfWeekDate;

    public Week(LocalDateTime date) {
        this.startOfWeek = dateToStartOfWeek(date);
        this.endOfWeekDate = dateToEndOfWeek(date);
    }

    private LocalDateTime dateToStartOfWeek(LocalDateTime date) {
        return date.minusDays(date.getDayOfWeek().getValue() - 1).toLocalDate().atTime(LocalTime.MIN);
    }

    private LocalDateTime dateToEndOfWeek(LocalDateTime date) {
        return startOfWeek.plusDays(6).toLocalDate().atTime(LocalTime.MAX);
    }
}
