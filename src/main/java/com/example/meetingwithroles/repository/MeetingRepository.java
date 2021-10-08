package com.example.meetingwithroles.repository;

import com.example.meetingwithroles.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
