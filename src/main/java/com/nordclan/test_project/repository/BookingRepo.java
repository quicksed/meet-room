package com.nordclan.test_project.repository;

import com.nordclan.test_project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking " +
            "WHERE booking.meet_room_id = ?1 AND booking.time_from >= ?2 AND booking.time_to <= ?3", nativeQuery = true)
    List<Booking> findBookingsByPeriodAndMeetRoom(Long meetRoomId, LocalDateTime timeFrom, LocalDateTime timeTo);

    @Modifying
    @Query(value = "INSERT INTO booking_employee(booking_id, employee_id) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    void joinEmployeeToBooking(Long bookingId, Long employeeId);
}
