package com.nordclan.test_project.helper.date;

import java.time.DateTimeException;

public enum DayOfWeekOnRussian {

    Понедельник,
    Вторник,
    Среда,
    Четверг,
    Пятница,
    Суббота,
    Воскресенье;

    private static final DayOfWeekOnRussian[] ENUMS = DayOfWeekOnRussian.values();

    public int getValue() {
        return ordinal() + 1;
    }

    public static DayOfWeekOnRussian of(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new DateTimeException("Invalid value for DayOfWeekOnRussian: " + dayOfWeek);
        }
        return ENUMS[dayOfWeek - 1];
    }
}
