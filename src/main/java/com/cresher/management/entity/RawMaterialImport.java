package com.cresher.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "raw_material_imports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RawMaterialImport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "import_date", nullable = false)
    private LocalDate importDate;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @Column(name = "quantity_received", nullable = false)
    private Double quantityReceived;

    @Column(name = "supplier_details", nullable = false)
    private String supplierDetails;

    @Column(name = "price")
    private Double price;
}
