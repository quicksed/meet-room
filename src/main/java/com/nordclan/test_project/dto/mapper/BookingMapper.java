package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public BookingDto toDto(Booking model, Employee employee) {
        BookingDto booking = new BookingDto();
        booking.setId(model.getId());
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());
        booking.setJoined( model.getEmployees().contains(employee));

        return booking;
    }

    public List<BookingDto> toDto(List<Booking> model, Employee employee) {
        return model.stream()
                .map(booking -> toDto(booking, employee))
                .collect(Collectors.toList());
    }

    public Booking createDtoToEntity(BookingCreateDto model, Employee owner, MeetRoom meetRoom) {
        Booking booking = new Booking();
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());
        booking.setOwner(owner);
        booking.setMeetRoom(meetRoom);

        return booking;
    }

    public Booking updateDtoToEntity(BookingUpdateDto model) {
        Booking booking = new Booking();
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());

        return booking;
    }
}
