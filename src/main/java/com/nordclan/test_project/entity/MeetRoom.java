package com.nordclan.test_project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class MeetRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "meetRoom")
    private Set<Booking> bookings;

    public MeetRoom() {}

    public MeetRoom(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
