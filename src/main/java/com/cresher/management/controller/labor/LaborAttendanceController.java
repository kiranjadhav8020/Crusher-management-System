package com.cresher.management.controller.labor;

import com.cresher.management.entity.LaborRecord;
import com.cresher.management.exception.ResourceNotFoundException;
import com.cresher.management.model.Labor;
import com.cresher.management.model.LaborRecordDTO;
import com.cresher.management.service.labor.LaborAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/labor-attendance")
public class LaborAttendanceController {


    @Autowired
    private LaborAttendanceService service;

    // ✅ Save Attendance
    @PostMapping("/save")
    public ResponseEntity<LaborRecord> saveAttendance(@RequestBody LaborRecordDTO attendanceDTO) {

        Labor labor = service.findLaborById(attendanceDTO.getLaborId())
                .orElseThrow(() -> new ResourceNotFoundException("Labor not found with ID: " + attendanceDTO.getLaborId()));

        // Create a new LaborRecord entity and set values
        LaborRecord attendance = new LaborRecord();
        attendance.setLabor(labor);
        attendance.setAttendanceDate(attendanceDTO.getAttendanceDate());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setWorkingHours(attendanceDTO.getWorkingHours());
        attendance.setNotes(attendanceDTO.getNotes());
        attendance.setSalaryAmount(attendanceDTO.getSalaryAmount());
        attendance.setPaymentDate(attendanceDTO.getPaymentDate());
        attendance.setPaymentStatus(attendanceDTO.getPaymentStatus());

        LaborRecord savedAttendance = service.saveAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    // ✅ Get All Attendance Records
    @GetMapping("/all")
    public ResponseEntity<List<LaborRecord>> getAllAttendance() {
        List<LaborRecord> records = service.getAllAttendance();
        return ResponseEntity.ok(records);
    }

    // ✅ Get Attendance by Date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<LaborRecord>> getAttendanceByDate(@PathVariable String date) {
        List<LaborRecord> records = service.getAttendanceByDate(LocalDate.parse(date));
        return ResponseEntity.ok(records);
    }

    // ✅ Get Weekly Attendance (All)
    @GetMapping("/weekly")
    public ResponseEntity<List<LaborRecord>> getWeeklyAttendance() {
        return ResponseEntity.ok(service.getWeeklyAttendance());
    }
    // ✅ Update Attendance Record
    @PutMapping("/update/{id}")
    public ResponseEntity<LaborRecord> updateAttendance(@PathVariable Long id, @RequestBody LaborRecord updatedAttendance) {
        LaborRecord updatedRecord = service.updateAttendance(id, updatedAttendance);
        return ResponseEntity.ok(updatedRecord);
    }

    // ✅ Delete Attendance Record
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        service.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record with ID " + id + " has been deleted!");
    }

    // ✅ Get Weekly Attendance by Laborer Name
    @GetMapping("/weekly/{laborerName}")
    public ResponseEntity<List<LaborRecord>> getWeeklyAttendance(@PathVariable String laborerName) {
        return ResponseEntity.ok(service.getWeeklyAttendance(laborerName));
    }

    // ✅ Get Monthly Attendance by Laborer Name
    @GetMapping("/monthly/{laborerName}/{year}/{month}")
    public ResponseEntity<List<LaborRecord>> getMonthlyAttendance(
            @PathVariable String laborerName, @PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok(service.getMonthlyAttendance(laborerName, year, month));
    }

    // ✅ Get Attendance by Date Range
    @GetMapping("/range/{laborerName}/{startDate}/{endDate}")
    public ResponseEntity<List<LaborRecord>> getAttendanceByDateRange(
            @PathVariable String laborerName, @PathVariable String startDate, @PathVariable String endDate) {
        List<LaborRecord> records = service.getLaborAttendanceByDateRange(
                laborerName, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(records);
    }

    // ✅ Get Salary by Date Range
    @GetMapping("/salary/range/{laborerName}/{startDate}/{endDate}")
    public ResponseEntity<Map<String, Object>> getSalaryByDateRange(
            @PathVariable String laborerName, @PathVariable String startDate, @PathVariable String endDate) {
        Map<String, Object> salaryData = service.getSalaryByDateRange(
                laborerName, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(salaryData);
    }

    // ✅ Get Weekly Payment
    @GetMapping("/weekly-payment/{id}/{startDate}/{endDate}")
    public ResponseEntity<Map<String, Object>> getWeeklyPayment(
            @PathVariable Long id, @PathVariable String startDate, @PathVariable String endDate) {
        Map<String, Object> paymentData = service.getWeeklyPayment(id, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(paymentData);
    }


}
