package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.accenture.SmartOffice.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndHashPassword(@Param("login") String userName, @Param("hashPassword") String hashPassword);

    User findByLogin(@Param("login") String userName);
}
