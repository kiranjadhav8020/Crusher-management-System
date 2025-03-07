package com.cresher.management.controller.rowmetiral;


import com.cresher.management.entity.MaterialExport;
import com.cresher.management.service.rowmetiral.MaterialExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/material-export")
public class MaterialExportController {

    @Autowired
    private MaterialExportService service;

    @PostMapping("/add")
    public MaterialExport addMaterialExport(@RequestBody MaterialExport materialExport) {
        return service.addMaterialExport(materialExport);
    }

    @GetMapping("/all")
    public List<MaterialExport> getAllMaterialExports() {
        return service.getAllMaterialExports();
    }

    @GetMapping("/{id}")
    public MaterialExport getMaterialExportById(@PathVariable Long id) {
        return service.getMaterialExportById(id);
    }

    @PutMapping("/update/{id}")
    public MaterialExport updateMaterialExport(@PathVariable Long id, @RequestBody MaterialExport updatedRecord) {
        return service.updateMaterialExport(id, updatedRecord);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMaterialExport(@PathVariable Long id) {
        service.deleteMaterialExport(id);
        return "Export record with ID " + id + " deleted!";
    }

    @GetMapping("/summary/{startDate}/{endDate}")
    public List<MaterialExport> getMonthlySummary(@PathVariable String startDate, @PathVariable String endDate) {
        return service.getMonthlySummary(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
