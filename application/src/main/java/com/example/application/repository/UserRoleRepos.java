package com.example.application.repository;

import com.example.application.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepos extends JpaRepository<UserRole,Long> {
}
