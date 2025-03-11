package com.cresher.management.controller.labor;


import com.cresher.management.entity.LaborAdvance;
import com.cresher.management.entity.LaborPayment;
import com.cresher.management.model.Labor;
import com.cresher.management.service.labor.LaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/labor")
public class LaborController {

    @Autowired
    private LaborService laborService;

    // ✅ Add Labor
    @PostMapping("/add")
    public ResponseEntity<Labor> addLabor(@RequestBody Labor labor) {
        Labor savedLabor = laborService.addLabor(labor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLabor);
    }

    // ✅ Get All Labor
    @GetMapping("/all")
    public ResponseEntity<List<Labor>> getAllLabor() {
        List<Labor> laborList = laborService.getAllLabor();
        return ResponseEntity.ok(laborList);
    }

    // ✅ Get Labor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Labor> getLaborById(@PathVariable Long id) {
        return laborService.getLaborById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    // ✅ Update Labor
    @PutMapping("/update/{id}")
    public ResponseEntity<Labor> updateLabor(@PathVariable Long id, @RequestBody Labor updatedLabor) {
        Labor labor = laborService.updateLabor(id, updatedLabor);
        return ResponseEntity.ok(labor);
    }

    // ✅ Delete Labor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabor(@PathVariable Long id) {
        laborService.deleteLabor(id);
        return ResponseEntity.ok("Labor with ID " + id + " has been deleted!");
    }

    // ✅ API to Add Advance
    @PostMapping("/{laborId}/add-advance")
    public ResponseEntity<LaborAdvance> addAdvance(@PathVariable Long laborId, @RequestParam Double amount) {
        LaborAdvance advance = laborService.addAdvance(laborId, amount);
        return ResponseEntity.ok(advance);
    }

    // ✅ API to Make Payment
    @PostMapping("/{laborId}/make-payment")
    public ResponseEntity<LaborPayment> makePayment(@PathVariable Long laborId, @RequestParam Double amount) {
        LaborPayment payment = laborService.makePayment(laborId, amount);
        return ResponseEntity.ok(payment);
    }

    // ✅ API to Get Payment History
    @GetMapping("/{laborId}/payment-history")
    public ResponseEntity<List<LaborPayment>> getPaymentHistory(@PathVariable String laborId) {
        return ResponseEntity.ok(laborService.getPaymentHistory(laborId));
    }

    // ✅ API to Get Advance History
    @GetMapping("/{laborId}/advance-history")
    public ResponseEntity<List<LaborAdvance>> getAdvanceHistory(@PathVariable String laborId) {
        return ResponseEntity.ok(laborService.getAdvanceHistory(laborId));
    }
}
