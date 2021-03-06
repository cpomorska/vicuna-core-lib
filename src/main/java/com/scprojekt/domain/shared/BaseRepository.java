package com.scprojekt.domain.shared;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll();
    T findById(long id);
    void createEntity(T entity);
    void removeEntity(T entity);
    void updateEntity(T entity);
}