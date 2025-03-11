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
@Document(collection = "labor_payment")
public class LaborPayment {

    @Id
    private String id;

    @DBRef
    private Labor labor;

    private LocalDate paymentDate;
    private Double paymentAmount;
    private Double deductedFromAdvance;
}
