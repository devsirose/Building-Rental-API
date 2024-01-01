package com.example.application.service.impl;

import com.example.application.model.entity.Role;
import com.example.application.repository.RoleRepository;
import com.example.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Role findById(Long aLong) {
        return roleRepository.findById(aLong);
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void remove(Role entity) {
        roleRepository.remove(entity);
    }
}
