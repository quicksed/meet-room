package com.nordclan.test_project.repository;

import com.nordclan.test_project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    Set<Booking> findByTimeFromAndTimeTo(LocalDateTime timeFrom, LocalDateTime timeTo);

    @Modifying
    @Query("INSERT INTO booking_employee(booking_id, employee_id) " +
            "VALUES (?1, ?2) " +
            "WHERE booking_id = ?1")
    Booking joinEmployeeToBooking(Long bookingId, Long employeeId);
}
