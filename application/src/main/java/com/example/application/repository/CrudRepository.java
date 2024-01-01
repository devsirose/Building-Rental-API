package com.example.application.repository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public interface CrudRepository<T,Id> {
    public T save(T entity);
    public T findById(Id id);
    public Collection<T> findAll();
    public void remove(T entity);
}
