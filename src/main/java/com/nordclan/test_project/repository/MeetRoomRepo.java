package com.nordclan.test_project.repository;

import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetRoomRepo extends JpaRepository<MeetRoom, Long> {

    Optional<MeetRoom> findByName(String name);
}
