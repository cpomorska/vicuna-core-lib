package com.scprojekt.domain.core.shared.database;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findAllInRepository();
    T findByIdInRepository(long id);
    void createEntity(T entity);
    void removeEntity(T entity);
    void updateEntity(T entity);
}