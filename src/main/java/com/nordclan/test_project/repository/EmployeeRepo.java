package com.nordclan.test_project.repository;

import com.nordclan.test_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);

    @Query(value = "SELECT * FROM employee " +
            "JOIN employee_booking on employee.id = employee_id " +
            "WHERE booking_id = ?1", nativeQuery = true)
    List<Employee> findEmployeesByBookingId(Long bookingId);
}
