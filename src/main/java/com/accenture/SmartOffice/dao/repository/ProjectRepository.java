package com.accenture.SmartOffice.dao.repository;

import com.accenture.SmartOffice.dao.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
