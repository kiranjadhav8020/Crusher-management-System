package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LaborAttendanceRepository extends MongoRepository<LaborRecord, String> {

    List<LaborRecord> findByAttendanceDate(LocalDate date);

    List<LaborRecord> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    // Get attendance for a specific labor within a date range
    List<LaborRecord> findByLabor_LaborerNameAndAttendanceDateBetween(
            String laborerName, LocalDate startDate, LocalDate endDate);

    // Get weekly attendance for a specific labor
    List<LaborRecord> findByLabor_LaborerNameAndAttendanceDateBetweenOrderByAttendanceDateDesc(
            String laborerName, LocalDate startDate, LocalDate endDate);

    // Get salary records within a date range
    List<LaborRecord> findByLabor_LaborerNameAndPaymentDateBetween(
            String laborerName, LocalDate startDate, LocalDate endDate);

    // Custom query to get attendance based on laborId and date range
    @Query("{ 'labor.id': ?0, 'attendanceDate': { $gte: ?1, $lte: ?2 } }")
    List<LaborRecord> findRecordsByLaborAndDateRange(
            String laborId, LocalDate startDate, LocalDate endDate);

    // Custom query to get attendance based on laborName and date range
    @Query("{ 'labor.laborerName': ?0, 'attendanceDate': { $gte: ?1, $lte: ?2 } }")
    List<LaborRecord> findRecordsByLaborNameAndDateRange(
            String laborName, LocalDate startDate, LocalDate endDate);
}
