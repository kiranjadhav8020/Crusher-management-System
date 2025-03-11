package com.cresher.management.service.rowmetiral;

import com.cresher.management.entity.RawMaterialImport;
import com.cresher.management.repository.rowmetiral.RawMaterialImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RawMaterialImportService {

    @Autowired
    private RawMaterialImportRepository repository;

    public RawMaterialImport addRawMaterialImport(RawMaterialImport rawMaterialImport) {
        return repository.save(rawMaterialImport);
    }

    public List<RawMaterialImport> getAllRawMaterialImports() {

        return repository.findAll();
    }

    public RawMaterialImport getRawMaterialImportById(Long id) {
        return repository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Import record not found with ID: " + id));
    }

    public RawMaterialImport updateRawMaterialImport(Long id, RawMaterialImport updatedRecord) {
        RawMaterialImport existingRecord = getRawMaterialImportById(id);
        existingRecord.setImportDate(updatedRecord.getImportDate());
        existingRecord.setMaterialName(updatedRecord.getMaterialName());
        existingRecord.setQuantityReceived(updatedRecord.getQuantityReceived());
        existingRecord.setSupplierDetails(updatedRecord.getSupplierDetails());
        existingRecord.setPrice(updatedRecord.getPrice());
        return repository.save(existingRecord);
    }

    public void deleteRawMaterialImport(Long id) {

        repository.deleteById(String.valueOf(id));
    }

    public List<RawMaterialImport> getMonthlySummary(LocalDate startDate, LocalDate endDate) {
        return repository.findByImportDateBetween(startDate, endDate);
    }
}
