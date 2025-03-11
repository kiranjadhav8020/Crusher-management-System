package com.cresher.management.service.rowmetiral;


import com.cresher.management.entity.MaterialExport;
import com.cresher.management.repository.rowmetiral.MaterialExportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialExportService {

    @Autowired
    private MaterialExportRepository repository;

    public MaterialExport addMaterialExport(MaterialExport materialExport) {

        return repository.save(materialExport);
    }

    public List<MaterialExport> getAllMaterialExports() {

        return repository.findAll();
    }

    public MaterialExport getMaterialExportById(Long id) {
        return repository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Export record not found with ID: " + id));
    }

    public MaterialExport updateMaterialExport(Long id, MaterialExport updatedRecord) {
        MaterialExport existingRecord = getMaterialExportById(id);
        existingRecord.setExportDate(updatedRecord.getExportDate());
        existingRecord.setMaterialName(updatedRecord.getMaterialName());
        existingRecord.setQuantityExported(updatedRecord.getQuantityExported());
        existingRecord.setBuyerDetails(updatedRecord.getBuyerDetails());
        existingRecord.setPrice(updatedRecord.getPrice());
        return repository.save(existingRecord);
    }

    public void deleteMaterialExport(Long id) {

        repository.deleteById(String.valueOf(id));
    }

    public List<MaterialExport> getMonthlySummary(LocalDate startDate, LocalDate endDate) {
        return repository.findByExportDateBetween(startDate, endDate);
    }
}

