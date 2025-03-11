package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborPayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LaborPaymentRepository extends MongoRepository<LaborPayment, String> {

    List<LaborPayment> findByLaborId(String laborId);
}
