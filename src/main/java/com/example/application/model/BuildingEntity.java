package com.example.application.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "building")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @Column(name = "structure")
    private String structure;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "direction")
    private String direction;
    @Column(name = "level")
    private String level;
    @Column(name = "rentprice")
    private Integer rentPrice;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "carfee")
    private String carFee;
    @Column(name = "motorbikefee")
    private String motorbikeFee;
    @Column(name = "overtimefee")
    private String overtimeFee;
    @Column(name = "waterfee")
    private String waterFee;
    @Column(name = "electricityfee")
    private String electricityFee;
    @Column(name = "deposit")
    private String deposit;
    @Column(name = "payment")
    private String payment;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "decorationtime")
    private String decorationTime;
    @Column(name = "brokeragefee")
    private Double brokerageFee;
    @Column(name = "note")
    private String note;
    @Column(name = "linkofbuilding")
    private String linkOfBuilding;
    @Column(name = "map")
    private String map;
    @Column(name = "image")
    private String image;
    @Column(name = "createddate")
    private Date createdDate;
    @Column(name = "modifieddate")
    private Date modifiedDate;
    @Column(name = "createdby")
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
    @Column(name = "district")
    private String district;
    @Column(name = "type")
    private String type;
    @ManyToMany(targetEntity = UserEntity.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = {@JoinColumn(name = "buildingid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "staffid", referencedColumnName = "id")})
    private List<UserEntity> staffs = new ArrayList<>();
    @OneToMany(mappedBy = "buildingEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

    public void addStaff(UserEntity userEntity) {
        if (staffs == null) {
            staffs = new ArrayList<>();
        }
        staffs.add(userEntity);
    }

    public void addRentArea(RentAreaEntity rentAreaEntity) {
        if (rentAreaEntities == null) {
            rentAreaEntities = new ArrayList<>();
        }
        rentAreaEntities.add(rentAreaEntity);
    }

    public void setRentArea(List<RentAreaEntity> rentAreaEntities) {
        this.rentAreaEntities = rentAreaEntities;
    }

    public void removeRentAreaEntities() {
        this.rentAreaEntities.clear();
    }
}
