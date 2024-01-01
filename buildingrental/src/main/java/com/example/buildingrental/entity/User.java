package com.example.buildingrental.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username", unique = true, nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    //password_hash
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
    private String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    @Pattern(regexp = "^(\\+84|0)[1-9]\\d{8}$", message = "Invalid Vietnamese phone number")
    private String phoneNumber;
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$", message = "Invalid first name")
    @Size(max = 50, message = "Name must be at most 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$", message = "Invalid last name")
    @Size(max = 50, message = "Name must be at most 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "day_of_birth", nullable = false)
    private Date dayOfBirth;
    @Column(name = "active")
    private Boolean isActive;
    @Column(name = "avatar_path")
    private String avatarPath;
    @Column(name = "registration_date")
    private Timestamp registration_date;
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    //generate relationship
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public void addRole(Role role) {
        if(roles == null) roles = new HashSet<>();
        roles.add(role);
    }
}
