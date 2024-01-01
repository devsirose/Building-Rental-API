package com.example.buildingrental.repository;

import com.example.buildingrental.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select u from Role u where u.code = :code")
    public Role findRoleByCode(@Param("code") String code);

}
