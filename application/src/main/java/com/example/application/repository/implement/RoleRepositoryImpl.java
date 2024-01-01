package com.example.application.repository.implement;

import com.example.application.model.entity.Role;
import com.example.application.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private EntityManager entityManager;
    public RoleRepositoryImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("app_pu");
        entityManager = emf.createEntityManager();
    }

    @Override
    public Role save(Role entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Role findById(Long aLong) {
        return entityManager.find(Role.class, aLong);
    }

    @Override
    public Collection<Role> findAll() {
        //JPQL query
        String query = "SELECT u from Role u";
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery(query, Role.class);
        return roleTypedQuery.getResultList();
    }

    @Override
    public void remove(Role entity) {
        entityManager.remove(entity);
    }
}
