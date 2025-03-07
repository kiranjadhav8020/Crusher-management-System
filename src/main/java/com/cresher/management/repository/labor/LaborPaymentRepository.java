package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaborPaymentRepository extends JpaRepository<LaborPayment, Long> {
    List<LaborPayment> findByLaborId(Long laborId);
}
