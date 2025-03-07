package com.cresher.management.service.labor;


import com.cresher.management.entity.LaborRecord;
import com.cresher.management.model.Labor;
import com.cresher.management.repository.labor.LaborAttendanceRepository;
import com.cresher.management.repository.labor.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class LaborAttendanceService {

    @Autowired
    private LaborAttendanceRepository repository;

    @Autowired
    private LaborRepository laborRepository;

    public LaborRecord saveAttendance(LaborRecord attendance) {
        return repository.save(attendance);
    }

    public List<LaborRecord> getAllAttendance() {
        return repository.findAll();
    }

    public List<LaborRecord> getAttendanceByDate(LocalDate date) {
        return repository.findByAttendanceDate(date);
    }

    public List<LaborRecord> getWeeklyAttendance() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(6); // Last 7 days including today
        return repository.findByAttendanceDateBetween(startOfWeek, today);
    }


    public List<LaborRecord> getMonthlyAttendance(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return repository.findByAttendanceDateBetween(startDate, endDate);
    }

    public LaborRecord updateAttendance(Long id, LaborRecord updatedAttendance) {
        return repository.findById(id).map(attendance -> {
            attendance.setLabor(updatedAttendance.getLabor());
            attendance.setAttendanceDate(updatedAttendance.getAttendanceDate());
            attendance.setStatus(updatedAttendance.getStatus());
            attendance.setWorkingHours(updatedAttendance.getWorkingHours());
            //attendance.setAdditionalNotes(updatedAttendance.getAdditionalNotes());
            return repository.save(attendance);
        }).orElseThrow(() -> new RuntimeException("Attendance record not found for ID: " + id));
    }

    public void deleteAttendance(Long id) {
        repository.deleteById(id);
    }

    public List<LaborRecord> getLaborAttendanceByDateRange(String laborerName, LocalDate startDate, LocalDate endDate) {
        return repository.findByLabor_LaborerNameAndAttendanceDateBetween(laborerName, startDate, endDate);
    }

    public List<LaborRecord> getWeeklyAttendance(String laborerName) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(6); // Last 7 days
        return repository.findByLabor_LaborerNameAndAttendanceDateBetweenOrderByAttendanceDateDesc(laborerName, startOfWeek, today);
    }

    public List<LaborRecord> getMonthlyAttendance(String laborerName, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return repository.findByLabor_LaborerNameAndAttendanceDateBetween(laborerName, startDate, endDate);
    }

    public Map<String, Object> getSalaryByDateRange(String laborerName, LocalDate startDate, LocalDate endDate) {

        List<LaborRecord> records = repository.findRecordsByLaborNameAndDateRange(laborerName, startDate, endDate);

        List<Map<String, Object>> dailyPayments = new ArrayList<>();
        double totalPayment = 0.0;

        for (LaborRecord record : records) {
            if ("PRESENT".equalsIgnoreCase(record.getStatus())) { // Only count working days
                double dailySalary = record.getSalaryAmount() != null ? record.getSalaryAmount() : 0.0;
                totalPayment += dailySalary;

                Map<String, Object> dailyData = new HashMap<>();
                dailyData.put("date", record.getAttendanceDate());
                dailyData.put("workingHours", record.getWorkingHours());
                dailyData.put("salaryAmount", dailySalary);
                dailyPayments.add(dailyData);
            }
        }

        // Final response structure
        Map<String, Object> response = new HashMap<>();
        response.put("laborName", laborerName);
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("dailyPayments", dailyPayments);
        response.put("totalPayment", totalPayment);


        return response;

    }

    public Map<String, Object> getWeeklyPayment(Long laborerId, LocalDate startDate, LocalDate endDate) {

        List<LaborRecord> records = repository.findRecordsByLaborAndDateRange(laborerId, startDate, endDate);

        List<Map<String, Object>> dailyPayments = new ArrayList<>();
        double totalPayment = 0.0;

        for (LaborRecord record : records) {
            if ("PRESENT".equalsIgnoreCase(record.getStatus())) { // Only count working days
                double dailySalary = record.getSalaryAmount() != null ? record.getSalaryAmount() : 0.0;
                totalPayment += dailySalary;

                Map<String, Object> dailyData = new HashMap<>();
                dailyData.put("date", record.getAttendanceDate());
                dailyData.put("workingHours", record.getWorkingHours());
                dailyData.put("salaryAmount", dailySalary);
                dailyPayments.add(dailyData);
            }
        }

        // Final response structure
        Map<String, Object> response = new HashMap<>();
        response.put("laborId", laborerId);
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("dailyPayments", dailyPayments);
        response.put("totalPayment", totalPayment);


       return response;
    }

    public Optional<Labor> findLaborById(Long laborId) {

        return  laborRepository.findById(laborId);
    }
}
