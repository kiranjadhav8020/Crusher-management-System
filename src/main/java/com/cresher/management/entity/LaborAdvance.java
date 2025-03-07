package com.cresher.management.entity;

import com.cresher.management.model.Labor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "labor_advance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LaborAdvance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "labor_id", nullable = false)
    private Labor labor;

    @Column(name = "advance_amount", nullable = false)
    private Double advanceAmount;

    @Column(name = "date_given", nullable = false)
    private LocalDate dateGiven;
}

