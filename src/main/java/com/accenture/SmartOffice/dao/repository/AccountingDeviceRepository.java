package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.accenture.SmartOffice.dao.entity.AccountingDevice;

import java.util.UUID;

public interface AccountingDeviceRepository extends JpaRepository<AccountingDevice, Long> {
    @Query("SELECT p FROM AccountingDevice p WHERE LOWER(p.serialNumber) = LOWER(:serialNumber)")
    AccountingDevice findBySerialNumber(@Param("serialNumber") String serialNumber);

    @Query("SELECT p FROM AccountingDevice p WHERE p.deviceUUID = :deviceUUID")
    AccountingDevice findByDeviceUUID(@Param("deviceUUID")UUID deviceUUID);
}
