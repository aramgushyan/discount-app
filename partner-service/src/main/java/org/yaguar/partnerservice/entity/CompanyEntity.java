package org.yaguar.partnerservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<UnitEntity> units = new ArrayList<>();

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false, unique = true)
    private String name;
}
