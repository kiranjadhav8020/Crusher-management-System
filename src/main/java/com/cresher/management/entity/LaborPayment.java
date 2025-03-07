package com.cresher.management.entity;

import com.cresher.management.model.Labor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "labor_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LaborPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "labor_id", nullable = false)
    private Labor labor;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "payment_amount", nullable = false)
    private Double paymentAmount;

    @Column(name = "deducted_from_advance", nullable = false)
    private Double deductedFromAdvance;
}

