package com.accenture.SmartOffice.dao.repository;

import com.accenture.SmartOffice.dao.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
