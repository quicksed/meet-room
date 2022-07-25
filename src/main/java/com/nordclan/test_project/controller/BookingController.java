package com.nordclan.test_project.controller;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.service.BookingService;
import com.nordclan.test_project.service.MeetRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final MeetRoomService meetRoomService;

    @GetMapping("/create")
    public String showCreateFrom(BookingCreateDto bookingCreateDto, Model model) {
        return "booking/create";
    }

    @PostMapping("/create")
    public String createBooking(@Valid BookingCreateDto bookingCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "booking/create";
        }
        model.addAttribute("username", getPrincipal().getUsername());
        bookingService.createBooking(bookingCreateDto);

        return "redirect:/";
    }

    @GetMapping("/description")
    public String showDescription(@RequestParam(value = "id", required = false) Long bookingId, Model model) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Параметр id должен быть заполнен");
        }
        model.addAttribute("username", getPrincipal().getUsername());
        model.addAttribute("booking", bookingService.findById(bookingId));

        return "booking/description";
    }

    @GetMapping("/update")
    public String showUpdateFrom(@RequestParam(value = "id", required = false) Long bookingId, Model model) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Параметр id должен быть заполнен");
        }
        model.addAttribute("username", getPrincipal().getUsername());
        model.addAttribute("bookingUpdateDto", bookingService.findById(bookingId));

        return "booking/update";
    }

    @PostMapping("/update")
    public String updateBooking(@Valid BookingUpdateDto bookingUpdateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("username", getPrincipal().getUsername());
            return "booking/update";
        }
        bookingService.updateBooking(bookingUpdateDto);

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinToBooking(@RequestParam(value = "id", required = false) Long bookingId) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Параметр id должен быть заполнен");
        }
        bookingService.joinToBooking(bookingId);

        return "redirect:/";
    }

    @GetMapping("/exit")
    public String exitFromBooking(@RequestParam(value = "id", required = false) Long bookingId) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Параметр id должен быть заполнен");
        }
        bookingService.exitFromBooking(bookingId);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBooking(@RequestParam(value = "id", required = false) Long bookingId) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Параметр id должен быть заполнен");
        }
        bookingService.deleteBooking(bookingId);

        return "redirect:/";
    }

    private UserDetails getPrincipal() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
