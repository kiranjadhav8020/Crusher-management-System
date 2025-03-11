package com.cresher.management.entity;

import com.cresher.management.model.Labor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "labor_advance")
public class LaborAdvance {

    @Id
    private String id;

    @DBRef
    private Labor labor;

    private Double advanceAmount;
    private LocalDate dateGiven;
}
