package com.accenture.SmartOffice.dao.repository;

import com.accenture.SmartOffice.dao.entity.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
    WorkSpace findByXPositionAndYPositionAndOfficeId(@Param("xPosition") Integer xPosition,
                                          @Param("yPosition") Integer yPosition,
                                          @Param("officeId") Long officeId);

    WorkSpace findByUserId(@Param("userId") Long userId);
}
