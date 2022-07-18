package com.nordclan.test_project.service.impl;

import com.nordclan.test_project.dto.booking.*;
import com.nordclan.test_project.dto.mapper.BookingMapper;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.MeetRoom;
import com.nordclan.test_project.exception.ResourceNotFoundException;
import com.nordclan.test_project.repository.BookingRepo;
import com.nordclan.test_project.repository.EmployeeRepo;
import com.nordclan.test_project.repository.MeetRoomRepo;
import com.nordclan.test_project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final EmployeeRepo employeeRepo;
    private final MeetRoomRepo meetRoomRepo;
    private final BookingMapper bookingMapper;

    @Override
    public void createBooking(BookingCreateDto bookingCreateDto, Principal principal) {
        Employee owner = employeeRepo.findByUsername(principal.getName());
        MeetRoom meetRoom = meetRoomRepo.findById(bookingCreateDto.getMeetRoomId())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Meet room with id '%s' not found", bookingCreateDto.getMeetRoomId())));

        bookingRepo.save(bookingMapper.createDtoToEntity(
                bookingCreateDto, owner, meetRoom
        ));
    }

    @Override
    public void joinToBooking(BookingDto bookingDto, Principal principal) {
        Employee employee = employeeRepo.findByUsername(principal.getName());
        Booking booking = bookingRepo.findById(bookingDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Booking with id '%s' not found", bookingDto.getId())));

        bookingRepo.joinEmployeeToBooking(booking.getId(), employee.getId());
    }

    @Override
    public void updateBooking(BookingUpdateDto bookingUpdateDto, Principal principal) {
        Employee employee = employeeRepo.findByUsername(principal.getName());
        Booking booking = bookingRepo.findById(bookingUpdateDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Booking with id '%s' not found", bookingUpdateDto.getId())));

        bookingRepo.save(bookingMapper.updateDtoToEntity(bookingUpdateDto));
    }

    @Override
    public void deleteBooking(Long bookingId, Principal principal) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Meet room with id '%s' not found", bookingId)));

        bookingRepo.delete(booking);
    }
}
