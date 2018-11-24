package com.accenture.SmartOffice.dao.repository;

import com.accenture.SmartOffice.dao.entity.MeetingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingUserRepository extends JpaRepository<MeetingUser, Long> {
}
