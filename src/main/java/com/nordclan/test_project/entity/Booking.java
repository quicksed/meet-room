package com.nordclan.test_project.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime timeFrom;

    private LocalDateTime timeTo;

    @ManyToOne
    @JoinColumn(name = "owner_id", updatable = false)
    private Employee owner;

    @ManyToOne
    @JoinColumn(name = "meet_room_id", updatable = false)
    private MeetRoom meetRoom;

    @ManyToMany
    @JoinTable(name = "booking_employee",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private Set<Employee> employees;
}
