package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.SmartOffice.dao.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
