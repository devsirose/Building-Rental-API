package com.example.application.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Integer value;
    @Column(name = "createddate")
    private Date createdDate;
    @Column(name = "modifieddate")
    private Date modifiedDate;
    @Column(name = "createdby")
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;

    @ManyToOne(targetEntity = BuildingEntity.class,
            fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "buildingid", referencedColumnName = "id")
    private BuildingEntity buildingEntity;
}
