package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
