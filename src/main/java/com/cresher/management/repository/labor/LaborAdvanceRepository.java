package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborAdvance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaborAdvanceRepository extends JpaRepository<LaborAdvance, Long> {
    List<LaborAdvance> findByLaborId(Long laborId);
}

