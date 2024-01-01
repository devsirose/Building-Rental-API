package com.example.application.model.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long userRoleId;

    @ManyToOne
    @JoinColumn(name = "roleid",
            referencedColumnName = "id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "userid",
            referencedColumnName = "id")
    private User user;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "modifiedby")
    private String modifiedBy;
}

