package com.example.application.repository.implement;

import com.example.application.model.entity.User;
import com.example.application.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class UserRepositoryImpl implements UserRepository {
//    @PersistenceContext(unitName = "app_pu")
    private EntityManager entityManager;

    public UserRepositoryImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("app_pu");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public User save(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        //JPQL
        String query = "SELECT u FROM User u";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(query,User.class);
        return userTypedQuery.getResultList();
    }

    @Override
    public void remove(User entity) {
        entityManager.remove(entity);
    }
}
