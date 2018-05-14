package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User p WHERE LOWER(p.login) = LOWER(:userName)")
    User findByUserName(@Param("userName") String userName);
}
