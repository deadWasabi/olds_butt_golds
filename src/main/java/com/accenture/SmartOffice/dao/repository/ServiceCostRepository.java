package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.SmartOffice.dao.entity.ServiceCost;

public interface ServiceCostRepository extends JpaRepository<ServiceCost, Long> {
}
