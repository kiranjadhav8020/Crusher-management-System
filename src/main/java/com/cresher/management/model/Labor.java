package com.cresher.management.model;

import com.cresher.management.entity.LaborAdvance;
import com.cresher.management.entity.LaborPayment;
import com.cresher.management.entity.LaborRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "labor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Labor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "laborer_name", nullable = false)
    private String laborerName;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "job_role", nullable = false)
    private String jobRole;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "labor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LaborRecord> laborRecords;

    @Column(name = "total_advance", nullable = false)
    private Double totalAdvance = 0.0; // Tracks total advance balance

    @OneToMany(mappedBy = "labor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LaborAdvance> advances;

    @OneToMany(mappedBy = "labor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LaborPayment> payments;

}

