package com.example.application.service;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EntityService<T,Id>{
    public T save(T entity);
    public T findById(Id id);
    public Collection<T> findAll();
    public void remove(T entity);
}
