package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.MeetRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final EmployeeMapper employeeMapper;

    public BookingDto toDto(Booking model) {
        BookingDto booking = new BookingDto();
        booking.setId(model.getId());
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());

        return booking;
    }

    public BookingDto toDto(Booking model, Employee employee) {
        BookingDto booking = new BookingDto();
        booking.setId(model.getId());
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());
        booking.setTimeFrom(model.getTimeFrom());
        booking.setTimeTo(model.getTimeTo());
        booking.setJoined(model.getEmployees().contains(employee));
        booking.setOwner(model.getOwner().equals(employee));
        booking.setMembers(employeeMapper.toDto(model.getEmployees()));

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

    public Booking updateEntity(BookingUpdateDto model, Booking booking) {
        booking.setName(model.getName());
        booking.setDescription(model.getDescription());

        return booking;
    }
}
