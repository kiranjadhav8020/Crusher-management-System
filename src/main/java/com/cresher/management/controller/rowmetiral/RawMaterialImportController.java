package com.cresher.management.controller.rowmetiral;


import com.cresher.management.entity.RawMaterialImport;
import com.cresher.management.service.rowmetiral.RawMaterialImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/raw-material")
public class RawMaterialImportController {

    @Autowired
    private RawMaterialImportService service;

    @PostMapping("/add")
    public RawMaterialImport addRawMaterialImport(@RequestBody RawMaterialImport rawMaterialImport) {
        return service.addRawMaterialImport(rawMaterialImport);
    }

    @GetMapping("/all")
    public List<RawMaterialImport> getAllRawMaterialImports() {
        return service.getAllRawMaterialImports();
    }

    @GetMapping("/{id}")
    public RawMaterialImport getRawMaterialImportById(@PathVariable Long id) {
        return service.getRawMaterialImportById(id);
    }

    @PutMapping("/update/{id}")
    public RawMaterialImport updateRawMaterialImport(@PathVariable Long id, @RequestBody RawMaterialImport updatedRecord) {
        return service.updateRawMaterialImport(id, updatedRecord);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRawMaterialImport(@PathVariable Long id) {
        service.deleteRawMaterialImport(id);
        return "Import record with ID " + id + " deleted!";
    }

    @GetMapping("/summary/{startDate}/{endDate}")
    public List<RawMaterialImport> getMonthlySummary(@PathVariable String startDate, @PathVariable String endDate) {
        return service.getMonthlySummary(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
