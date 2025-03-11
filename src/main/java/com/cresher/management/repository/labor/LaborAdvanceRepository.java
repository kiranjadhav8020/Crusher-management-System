package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborAdvance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LaborAdvanceRepository extends MongoRepository<LaborAdvance, String> {
    List<LaborAdvance> findByLaborId(String laborId);
}
