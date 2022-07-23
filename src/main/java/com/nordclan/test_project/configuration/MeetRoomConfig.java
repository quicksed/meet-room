package com.nordclan.test_project.configuration;

import com.nordclan.test_project.entity.MeetRoom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeetRoomConfig {

    @Bean
    public MeetRoom mainMeetRoom() {
        return new MeetRoom(1L, "Главная");
    }
}
