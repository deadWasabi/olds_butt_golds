package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
