package com.cresher.management.model;

import com.cresher.management.entity.LaborAdvance;
import com.cresher.management.entity.LaborPayment;
import com.cresher.management.entity.LaborRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "labor")
public class Labor {

    @Id
    private String id;

    private String laborerName;
    private String phoneNumber;
    private String jobRole;
    private String address;
    private Double salary;
    private Double totalAdvance = 0.0; // Tracks total advance balance

    @DBRef(lazy = true)
    @JsonIgnore
    private List<LaborRecord> laborRecords;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<LaborAdvance> advances;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<LaborPayment> payments;
}
