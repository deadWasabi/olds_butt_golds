package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingService;

import java.util.List;

public interface AccountingServiceRepository extends JpaRepository<AccountingService, Long> {

    @Query("SELECT p FROM AccountingService p WHERE p.accountingDevice = :deviceId")
    List<AccountingService> findByDeviceId(@Param("deviceId") Long deviceId);
}
