package com.accenture.SmartOffice.dao.repository;

import com.accenture.SmartOffice.dao.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MeetingRepository extends JpaRepository<com.accenture.SmartOffice.dao.entity.Meeting, Long> {

    @Query(value = "Select * FROM meetings m where m.room_id = ?0 AND (m.start_time > ?1 AND m.end_time > ?2) OR (m.start_time < ?1 AND m.end_time > ?2) OR (m.start_time < ?1 AND m.end_time < ?2)"
                   + "OR (m.start_time > ?1 AND m.end_time < ?2)", nativeQuery = true)
    Meeting findByMeetingByIdAndTimeLines(Long id, LocalDate startTime, LocalDate endTime);
}
