package com.cresher.management.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "material_exports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "export_date", nullable = false)
    private LocalDate exportDate;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @Column(name = "quantity_exported", nullable = false)
    private Double quantityExported;

    @Column(name = "buyer_details", nullable = false)
    private String buyerDetails;

    @Column(name = "price", nullable = false)
    private Double price;
}
