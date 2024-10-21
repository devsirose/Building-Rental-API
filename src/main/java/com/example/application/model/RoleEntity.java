package com.example.application.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String code;
    @Column(name = "createddate")
    private java.sql.Date createdDate;
    @Column(name = "modifieddate")
    private Date modifiedDate;
    @Column(name = "createdby")
    private String createdBy;
    @Column(name = "modifiedby")
    private String modifiedBy;

    @ManyToMany(mappedBy = "roleEntities", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @Fetch(FetchMode.JOIN)
    private List<UserEntity> userEntities;

    public void addUser(UserEntity userEntity) {
        if (userEntities == null) {
            userEntities = new ArrayList<>();
        }
        userEntities.add(userEntity);
    }
}
