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
@Document(collection = "labor_records")
public class LaborRecord {

    @Id
    private String id;

    @DBRef
    private Labor labor;

    private LocalDate attendanceDate;
    private String status; // PRESENT / ABSENT
    private Integer workingHours;
    private String notes;
    private Double salaryAmount;
    private LocalDate paymentDate;
    private String paymentStatus; // PAID / PENDING
}
