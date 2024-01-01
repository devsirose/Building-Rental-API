package com.example.buildingrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(name = "code", unique = true, nullable = false)
    @Max(value = 10, message = "Code must be at most 10 characters")
    private String code;
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$", message = "Invalid role name")
    private String name;
    @Column(name = "description", nullable = true)
    @Max(value = 255, message = "description must be at most 255 characters")
    private String description;
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
