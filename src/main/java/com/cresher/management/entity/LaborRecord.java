package com.cresher.management.entity;

import com.cresher.management.model.Labor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "labor_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LaborRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "labor_id", nullable = false)
    private Labor labor;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "status", nullable = false)
    private String status; // PRESENT / ABSENT

    @Column(name = "working_hours", nullable = false)
    private Integer workingHours;

    @Column(name = "notes")
    private String notes;

    @Column(name = "salary_amount")
    private Double salaryAmount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_status")
    private String paymentStatus; // PAID / PENDING
}
