package com.cresher.management.repository.rowmetiral;

import com.cresher.management.entity.MaterialExport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialExportRepository extends MongoRepository<MaterialExport, String> {

    List<MaterialExport> findByExportDateBetween(LocalDate startDate, LocalDate endDate);
}
