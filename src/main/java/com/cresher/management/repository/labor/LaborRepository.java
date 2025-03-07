package com.cresher.management.repository.labor;

import com.cresher.management.model.Labor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborRepository extends JpaRepository<Labor, Long> {

}