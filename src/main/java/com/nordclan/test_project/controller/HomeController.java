package com.nordclan.test_project.controller;

import com.nordclan.test_project.dto.booking.BookingDayDto;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.MeetRoom;
import com.nordclan.test_project.helper.date.Week;
import com.nordclan.test_project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class HomeController {

    private final MeetRoom mainMeetRoom;
    private final BookingService bookingService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String showHomePage(@RequestParam(value = "date", required = false) String date,
                               @RequestParam(value = "direction", required = false) String direction,
                               Model model, Principal principal) {
        if (principal == null) {
            return "login";
        }

        Week week;
        if (date != null && direction != null) {
            if (direction.equals("prev")) {
                week = new Week(LocalDateTime.parse(date).minusWeeks(1));
            } else if (direction.equals("next")) {
                week = new Week(LocalDateTime.parse(date).plusWeeks(1));
            } else {
                week = new Week(LocalDateTime.now());
            }
        } else {
            week = new Week(LocalDateTime.now());
        }

        List<BookingDayDto> bookingsByDay =
                bookingService.findBookingsByMeetRoomAndDateOnWeek(
                        new MeetRoomDto(mainMeetRoom.getName()),
                        week.getStartOfWeek());

        model.addAttribute("username", principal.getName());
        model.addAttribute("startOfWeek", week.getStartOfWeek());
        model.addAttribute("bookingsByDay", bookingsByDay);

        return "home";
    }
}

