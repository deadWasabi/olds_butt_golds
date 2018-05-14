package ru.tvstu.AccountingSystemHousingServices.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tvstu.AccountingSystemHousingServices.dao.entity.Apartment;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT p FROM Apartment p WHERE LOWER(p.owner_id) = LOWER(SELECT u.id from User u LOWER(u.login) = LOWER(:ownerName))")
    List<Apartment> getApartmentsByUserName(@Param("ownerName")String ownerName);
}
