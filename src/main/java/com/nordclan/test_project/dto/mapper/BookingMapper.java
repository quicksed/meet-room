package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public BookingDto toDto(Booking model) {
        BookingDto booking = new BookingDto();
        booking.setId(model.getId());
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());

        return booking;
    }

    public Set<BookingDto> toDto(Set<Booking> model) {
        return model.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
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
