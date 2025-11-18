package com.scprojekt.domain.core.model.user.repository;

import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for User entities.
 * This repository provides domain-specific methods for user management.
 */
public interface UserRepository extends BaseRepository<User> {
    /**
     * Finds a user by UUID
     * 
     * @param uuid The user UUID
     * @return The user with the given UUID, or null if not found
     */
    User findByUUID(UUID uuid);

    /**
     * Finds a user by UUID and returns an Optional
     * 
     * @param uuid The user UUID
     * @return An Optional containing the user, or empty if not found
     */
    Optional<User> findByUUIDOptional(UUID uuid);

    /**
     * Finds users by type
     * 
     * @param types The user types to search for
     * @return A list of users with the given types
     */
    List<User> findByType(List<UserType> types);

    /**
     * Finds users by name
     * 
     * @param name The username to search for
     * @return A list of users with the given name
     */
    List<User> findByName(String name);

    /**
     * Finds users by description
     * 
     * @param description The description to search for
     * @return A list of users with the given description
     */
    List<User> findByDescription(String description);

    /**
     * Finds users by name pattern
     * 
     * @param pattern The username pattern to search for
     * @return A list of users with usernames matching the pattern
     */
    List<User> findByNamePattern(String pattern);

    /**
     * Finds users created after a specific date
     * 
     * @param date The date to search from
     * @return A list of users created after the given date
     */
    List<User> findByCreatedAfter(LocalDateTime date);

    /**
     * Finds users by type that are active
     * 
     * @param type The user type to search for
     * @return A list of active users with the given type
     */
    List<User> findActiveByType(UserType type);

    /**
     * Counts users by type
     * 
     * @param type The user type to count
     * @return The number of users with the given type
     */
    long countByType(UserType type);

    /**
     * Checks if a user with the given username exists
     * 
     * @param username The username to check
     * @return true if a user with the given username exists
     */
    boolean existsByName(String username);
}
