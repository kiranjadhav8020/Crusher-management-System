package com.cresher.management.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "material_exports")
public class MaterialExport {

    @Id
    private String id;

    private LocalDate exportDate;
    private String materialName;
    private Double quantityExported;
    private String buyerDetails;
    private Double price;
}
