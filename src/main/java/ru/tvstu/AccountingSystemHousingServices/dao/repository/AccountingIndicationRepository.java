package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.AccountingIndication;

import java.util.Date;
import java.util.List;

public interface AccountingIndicationRepository extends JpaRepository<AccountingIndication, Long> {

    @Query("SELECT p FROM AccountingIndication p WHERE p.accountingService = :serviceId AND p.accountingDate >= :startDate AND p.accountingDate <= :endDate")
    List<AccountingIndication> fingAccountingIndicationByServiceIdAndRangeDate(@Param("serviceId") Long serviceId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
