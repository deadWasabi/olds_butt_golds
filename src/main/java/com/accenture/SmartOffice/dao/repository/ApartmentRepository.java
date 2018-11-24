package com.accenture.SmartOffice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.accenture.SmartOffice.dao.entity.Apartment;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT p FROM Apartment p WHERE p.owner = (SELECT u from User u WHERE LOWER(u.login) = LOWER(:ownerName))")
    List<Apartment> getApartmentsByUserName(@Param("ownerName")String ownerName);
}
