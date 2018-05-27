package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceCost;

public interface ServiceCostRepository extends JpaRepository<ServiceCost, Long> {
}
