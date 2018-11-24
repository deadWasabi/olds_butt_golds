package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.accenture.SmartOffice.dao.entity.AccountingService;

import java.util.List;

public interface AccountingServiceRepository extends JpaRepository<AccountingService, Long> {

    @Query("SELECT p FROM AccountingService p WHERE p.accountingDevice = :deviceId")
    List<AccountingService> findByDeviceId(@Param("deviceId") Long deviceId);
}
