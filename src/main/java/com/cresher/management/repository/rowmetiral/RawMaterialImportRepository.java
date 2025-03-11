package com.cresher.management.repository.rowmetiral;

import com.cresher.management.entity.RawMaterialImport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RawMaterialImportRepository extends MongoRepository<RawMaterialImport, String> {

    List<RawMaterialImport> findByImportDateBetween(LocalDate startDate, LocalDate endDate);
}
