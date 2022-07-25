package com.nordclan.test_project.service.impl;

import com.nordclan.test_project.dto.booking.BookingCreateDto;
import com.nordclan.test_project.dto.booking.BookingDayDto;
import com.nordclan.test_project.dto.booking.BookingDto;
import com.nordclan.test_project.dto.booking.BookingUpdateDto;
import com.nordclan.test_project.dto.mapper.BookingMapper;
import com.nordclan.test_project.dto.meet_room.MeetRoomDto;
import com.nordclan.test_project.entity.Booking;
import com.nordclan.test_project.entity.Employee;
import com.nordclan.test_project.entity.MeetRoom;
import com.nordclan.test_project.exception.*;
import com.nordclan.test_project.helper.date.DayOfWeekOnRussian;
import com.nordclan.test_project.helper.date.Week;
import com.nordclan.test_project.repository.BookingRepo;
import com.nordclan.test_project.repository.EmployeeRepo;
import com.nordclan.test_project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final MeetRoom mainMeetRoom;
    private final BookingRepo bookingRepo;
    private final EmployeeRepo employeeRepo;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto findById(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Бронь с id '%s' не найдена", bookingId)));

        return bookingMapper.toDto(booking);
    }

    @Override
    public List<BookingDayDto> findBookingsByMeetRoomAndDateOnWeek(MeetRoomDto meetRoomDto, LocalDateTime dateInWeek) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Week week = new Week(dateInWeek);
        Timestamp start = Timestamp.valueOf(week.getStartOfWeek());
        Timestamp end = Timestamp.valueOf(week.getEndOfWeekDate());

        List<Booking> bookings = bookingRepo.findBookingsByPeriodAndMeetRoom(
                mainMeetRoom.getId(), week.getStartOfWeek(), week.getEndOfWeekDate());

        Employee employee = employeeRepo.findByUsername(getPrincipal().getUsername());

        List<BookingDayDto> bookingDayDtos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDateTime startOfCurrentDay = LocalDateTime.from(week.getStartOfWeek().plusDays(i));
            LocalDateTime endOfCurrentDay = LocalDateTime.from(week.getStartOfWeek().plusDays(i + 1));

            List<Booking> bookingsInDay = bookings.stream()
                    .filter(booking -> booking.getTimeFrom().isEqual(startOfCurrentDay) || booking.getTimeFrom().isAfter(startOfCurrentDay) &&
                            booking.getTimeFrom().isEqual(startOfCurrentDay) || booking.getTimeFrom().isBefore(endOfCurrentDay))
                    .collect(Collectors.toList());

            List<BookingDto> fullDaySchedule = new ArrayList<>();
            for (int numberOfBooking = 0; numberOfBooking < 48; numberOfBooking++) {
                LocalDateTime startOfBooking = startOfCurrentDay.plusMinutes(numberOfBooking * 30);
                LocalDateTime endOfBooking = startOfCurrentDay.plusMinutes(numberOfBooking * 30 + 30);

                List<Booking> currentBooking = bookingsInDay.stream()
                        .filter(booking -> booking.getTimeFrom().isEqual(startOfBooking) && booking.getTimeTo().isEqual(endOfBooking))
                        .collect(Collectors.toList());

                if (!currentBooking.isEmpty()) {
                    BookingDto booking = bookingMapper.toDto(currentBooking.get(0), employee);
                    fullDaySchedule.add(booking);
                } else {
                    fullDaySchedule.add(new BookingDto());
                }
            }

            DayOfWeekOnRussian nameOfDay = DayOfWeekOnRussian.of(startOfCurrentDay.getDayOfWeek().getValue());
            BookingDayDto day = new BookingDayDto(nameOfDay + "</br>" + dateFormatter.format(startOfCurrentDay), fullDaySchedule);

            bookingDayDtos.add(day);
        }

        return bookingDayDtos;
    }

    @Override
    public void createBooking(BookingCreateDto bookingCreateDto) throws PeriodAlreadyBookedException, IncorrectBookingPeriodException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Employee owner = employeeRepo.findByUsername(getPrincipal().getUsername());

        LocalDateTime dateFrom = LocalDateTime.parse(bookingCreateDto.getStringTimeFrom(), dateTimeFormatter);
        LocalDateTime dateTo = LocalDateTime.parse(bookingCreateDto.getStringTimeTo(), dateTimeFormatter);

        List<Booking> bookingsInSelectedPeriod = bookingRepo.findBookingsByPeriodAndMeetRoom(mainMeetRoom.getId(), dateFrom, dateTo);
        if (!bookingsInSelectedPeriod.isEmpty()) {
            throw new PeriodAlreadyBookedException();
        }

        long minutes = ChronoUnit.MINUTES.between(dateFrom, dateTo);
        if (minutes % 30 != 0 || minutes <= 0 || minutes > 1440) {
            throw new IncorrectBookingPeriodException();
        }

        long countOfBookings = minutes / 30;
        for (int i = 0; i < countOfBookings; i++) {
            LocalDateTime startOfBooking = LocalDateTime.from(dateFrom.plusMinutes(i * 30L));
            LocalDateTime endOfBooking = LocalDateTime.from(dateFrom.plusMinutes(i * 30L + 30L));

            bookingCreateDto.setTimeFrom(LocalDateTime.from(startOfBooking));
            bookingCreateDto.setTimeTo(LocalDateTime.from(endOfBooking));
            Booking booking = bookingMapper.createDtoToEntity(bookingCreateDto, owner, mainMeetRoom);
            Set<Employee> invitedEmployees = new HashSet<>();
            invitedEmployees.add(owner);
            booking.setEmployees(invitedEmployees);

            bookingRepo.save(booking);
        }
    }

    @Override
    public void joinToBooking(Long bookingId) throws AlreadyInBookingException, ResourceNotFoundException {
        Employee employee = employeeRepo.findByUsername(getPrincipal().getUsername());
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Бронь с id '%s' не найдена", bookingId)));

        if (booking.getEmployees().contains(employee)) {
            throw new AlreadyInBookingException();
        }

        bookingRepo.joinEmployeeToBooking(booking.getId(), employee.getId());
    }

    @Override
    public void exitFromBooking(Long bookingId) throws AlreadyInBookingException, ResourceNotFoundException {
        Employee employee = employeeRepo.findByUsername(getPrincipal().getUsername());
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Бронь с id '%s' не найдена", bookingId)));

        if (employee.equals(booking.getOwner())) {
            throw new OwnerExitException();
        }

        Set<Employee> employees = booking.getEmployees();
        employees.remove(employee);

        bookingRepo.save(booking);
    }

    @Override
    public void updateBooking(BookingUpdateDto bookingUpdateDto) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findByUsername(getPrincipal().getUsername());
        Booking booking = bookingRepo.findById(bookingUpdateDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Бронь с id '%s' не найдена", bookingUpdateDto.getId())));

        if (!booking.getOwner().equals(employee)) {
            throw new AccessDeniedException();
        }

        bookingRepo.save(bookingMapper.updateEntity(bookingUpdateDto, booking));
    }

    @Override
    public void deleteBooking(Long bookingId) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findByUsername(getPrincipal().getUsername());
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Бронь с id '%s' не найдена", bookingId)));

        if (!booking.getOwner().equals(employee)) {
            throw new AccessDeniedException();
        }

        bookingRepo.delete(booking);
    }

    private UserDetails getPrincipal() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
