package com.cresher.management.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class LaborRecordDTO {

    private Long laborId; // This will be used to fetch the Labor entity
    private LocalDate attendanceDate;
    private String status;
    private Integer workingHours;
    private String notes;
    private Double salaryAmount;
    private LocalDate paymentDate;
    private String paymentStatus;
}

