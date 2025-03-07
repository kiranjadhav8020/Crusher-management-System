package com.cresher.management.repository.rowmetiral;


import com.cresher.management.entity.MaterialExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialExportRepository extends JpaRepository<MaterialExport, Long> {

    List<MaterialExport> findByExportDateBetween(LocalDate startDate, LocalDate endDate);
}

