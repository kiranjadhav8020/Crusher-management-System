
package com.cresher.management.repository.labor;

import com.cresher.management.entity.LaborRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LaborAttendanceRepository extends JpaRepository<LaborRecord, Long> {
    List<LaborRecord> findByAttendanceDate(LocalDate date);

    List<LaborRecord> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    // Get attendance for a specific labor within a date range
    List<LaborRecord> findByLabor_LaborerNameAndAttendanceDateBetween(
            String laborerName, LocalDate startDate, LocalDate endDate);
    // Get weekly attendance for a specific labor
    List<LaborRecord> findByLabor_LaborerNameAndAttendanceDateBetweenOrderByAttendanceDateDesc(
            String laborerName, LocalDate startDate, LocalDate endDate);

    // Get salary records within a date range
    List<LaborRecord> findByLabor_LaborerNameAndPaymentDateBetween(String laborName, LocalDate startDate, LocalDate endDate);

    @Query("SELECT lr FROM LaborRecord lr WHERE lr.labor.id = :laborId AND lr.attendanceDate BETWEEN :startDate AND :endDate")
    List<LaborRecord> findRecordsByLaborAndDateRange(@Param("laborId") Long laborId,
                                                     @Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

    @Query("SELECT lr FROM LaborRecord lr WHERE lr.labor.laborerName = :laborName AND lr.attendanceDate BETWEEN :startDate AND :endDate")
    List<LaborRecord> findRecordsByLaborNameAndDateRange(@Param("laborName") String laborName,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

}
