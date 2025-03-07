package com.cresher.management.service.labor;


import com.cresher.management.entity.LaborAdvance;
import com.cresher.management.entity.LaborPayment;
import com.cresher.management.model.Labor;
import com.cresher.management.repository.labor.LaborAdvanceRepository;
import com.cresher.management.repository.labor.LaborPaymentRepository;
import com.cresher.management.repository.labor.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LaborService {

    @Autowired
    private LaborRepository laborRepository;

    @Autowired
    private LaborAdvanceRepository laborAdvanceRepository;

    @Autowired
    private LaborPaymentRepository laborPaymentRepository;

    public Labor addLabor(Labor labor) {
        return laborRepository.save(labor);
    }

    public List<Labor> getAllLabor() {
        return laborRepository.findAll();
    }

    public Optional<Labor> getLaborById(Long id) {
        return laborRepository.findById(id);
    }

    public Labor updateLabor(Long id, Labor updatedLabor) {
        return laborRepository.findById(id).map(labor -> {
            labor.setLaborerName(updatedLabor.getLaborerName());
            labor.setPhoneNumber(updatedLabor.getPhoneNumber());
            labor.setJobRole(updatedLabor.getJobRole());
            labor.setAddress(updatedLabor.getAddress());
            return laborRepository.save(labor);
        }).orElseThrow(() -> new RuntimeException("Labor not found with ID: " + id));
    }

    public void deleteLabor(Long id) {
        laborRepository.deleteById(id);
    }

    // ✅ 1. Add Advance
    public LaborAdvance addAdvance(Long laborId, Double advanceAmount) {
        Labor labor = laborRepository.findById(laborId)
                .orElseThrow(() -> new RuntimeException("Labor not found"));

        LaborAdvance advance = new LaborAdvance();
        advance.setLabor(labor);
        advance.setAdvanceAmount(advanceAmount);
        advance.setDateGiven(LocalDate.now());

        // Update labor's total advance
        labor.setTotalAdvance(labor.getTotalAdvance() + advanceAmount);

        laborAdvanceRepository.save(advance);
        laborRepository.save(labor);

        return advance;
    }

    // ✅ 2. Make Payment and Deduct from Advance
    public LaborPayment makePayment(Long laborId, Double paymentAmount) {
        Labor labor = laborRepository.findById(laborId)
                .orElseThrow(() -> new RuntimeException("Labor not found"));

        double deductedFromAdvance = 0.0;
        if (labor.getTotalAdvance() > 0) {
            if (labor.getTotalAdvance() >= paymentAmount) {
                deductedFromAdvance = paymentAmount;
                labor.setTotalAdvance(labor.getTotalAdvance() - paymentAmount);
                paymentAmount = 0.0;
            } else {
                deductedFromAdvance = labor.getTotalAdvance();
                paymentAmount -= labor.getTotalAdvance();
                labor.setTotalAdvance(0.0);
            }
        }

        LaborPayment payment = new LaborPayment();
        payment.setLabor(labor);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentAmount(paymentAmount);
        payment.setDeductedFromAdvance(deductedFromAdvance);

        laborPaymentRepository.save(payment);
        laborRepository.save(labor);

        return payment;
    }

    // ✅ 3. Get Payment History
    public List<LaborPayment> getPaymentHistory(Long laborId) {
        return laborPaymentRepository.findByLaborId(laborId);
    }

    // ✅ 4. Get Advance History
    public List<LaborAdvance> getAdvanceHistory(Long laborId) {
        return laborAdvanceRepository.findByLaborId(laborId);
    }
}

