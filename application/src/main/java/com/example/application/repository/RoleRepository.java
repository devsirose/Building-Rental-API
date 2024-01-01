package com.example.application.repository;

import com.example.application.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
}
