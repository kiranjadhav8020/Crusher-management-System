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
@Document(collection = "raw_material_imports")  // Specify the collection name
public class RawMaterialImport {

    @Id
    private String id;  // MongoDB uses String for Id

    private LocalDate importDate;

    private String materialName;

    private Double quantityReceived;

    private String supplierDetails;

    private Double price;
}
