package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.SmartOffice.dao.entity.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
