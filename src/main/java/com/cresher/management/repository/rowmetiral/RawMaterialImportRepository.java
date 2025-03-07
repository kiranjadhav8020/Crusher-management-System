package com.cresher.management.repository.rowmetiral;

import com.cresher.management.entity.RawMaterialImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RawMaterialImportRepository extends JpaRepository<RawMaterialImport, Long> {

    List<RawMaterialImport> findByImportDateBetween(LocalDate startDate, LocalDate endDate);
}
