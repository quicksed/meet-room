package com.nordclan.test_project.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
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
    @JoinColumn(name = "owner_id")
    private Employee owner;

    @ManyToOne
    @JoinColumn(name = "meet_room_id")
    private MeetRoom meetRoom;

    @ManyToMany
    @JoinTable(name = "booking_employee",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @ToString.Exclude
    private Set<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return id != null && Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
