package com.scprojekt.domain.core.shared.database;

import java.util.List;
import java.util.Optional;

/**
 * Base repository interface for domain entities.
 * This interface defines common operations for all repositories.
 * 
 * @param <T> The type of entity this repository manages
 */
public interface BaseRepository<T> {
    /**
     * Finds all entities of type T
     * 
     * @return A list of all entities
     */
    List<T> findAllInRepository();

    /**
     * Finds an entity by its ID
     * 
     * @param id The entity ID
     * @return The entity with the given ID, or null if not found
     */
    T findByIdInRepository(long id);

    /**
     * Finds an entity by its ID and returns an Optional
     * 
     * @param id The entity ID
     * @return An Optional containing the entity, or empty if not found
     */
    Optional<T> findById(long id);

    /**
     * Saves a new entity
     * 
     * @param entity The entity to save
     */
    void createEntity(T entity);

    /**
     * Removes an entity
     * 
     * @param entity The entity to remove
     */
    void removeEntity(T entity);

    /**
     * Updates an existing entity
     * 
     * @param entity The entity to update
     */
    void updateEntity(T entity);

    /**
     * Saves an entity (creates if new, updates if existing)
     * 
     * @param entity The entity to save
     * @return The saved entity
     */
    T save(T entity);

    /**
     * Counts the total number of entities
     * 
     * @return The count of entities
     */
    long count();

    /**
     * Checks if an entity with the given ID exists
     * 
     * @param id The entity ID
     * @return true if an entity with the given ID exists
     */
    boolean existsById(long id);

    /**
     * Finds all active (enabled) entities
     * 
     * @return A list of all active entities
     */
    List<T> findAllActive();
}
