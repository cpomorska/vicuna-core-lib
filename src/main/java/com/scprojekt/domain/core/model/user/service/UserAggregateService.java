package com.scprojekt.domain.core.model.user.service;

import com.scprojekt.domain.core.model.user.aggregate.UserAggregate;
import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Domain service for user management operations using the UserAggregate.
 * This service provides methods for user-specific business operations.
 */
public interface UserAggregateService extends BaseService<UserAggregate> {

    /**
     * Gets a user aggregate by its ID
     * 
     * @param id The user ID
     * @return The user aggregate
     */
    UserAggregate getById(long id);
    
    /**
     * Gets a user aggregate by its UUID
     * 
     * @param uuid The user UUID
     * @return The user aggregate
     */
    UserAggregate getByUuid(UUID uuid);
    
    /**
     * Creates a new user in the system
     * 
     * @param userAggregate The user aggregate to create
     * @return The UUID of the created user
     */
    UuidResponse create(UserAggregate userAggregate);
    
    /**
     * Updates an existing user in the system
     * 
     * @param userAggregate The user aggregate to update
     */
    void update(UserAggregate userAggregate);
    
    /**
     * Removes a user from the system
     * 
     * @param userAggregate The user aggregate to remove
     */
    void remove(UserAggregate userAggregate);
    
    /**
     * Finds all users with the specified types
     * 
     * @param typeList The list of user types to search for
     * @return A list of matching user aggregates
     */
    List<UserAggregate> findAllByType(List<UserType> typeList);
    
    /**
     * Finds all users with the specified name
     * 
     * @param name The name to search for
     * @return A list of matching user aggregates
     */
    List<UserAggregate> findAllByName(String name);
    
    /**
     * Finds all users with the specified description
     * 
     * @param description The description to search for
     * @return A list of matching user aggregates
     */
    List<UserAggregate> findAllByDescription(String description);

    /**
     * Registers a new user in the system
     * 
     * @param username The username
     * @param description The user description
     * @param password The plain text password (will be hashed)
     * @param userTypes The user types
     * @return The UUID of the created user
     */
    UuidResponse registerUser(String username, String description, String password, List<UserType> userTypes);

    /**
     * Authenticates a user with username and password
     * 
     * @param username The username
     * @param password The plain text password
     * @return The authenticated user aggregate if successful, empty otherwise
     */
    Optional<UserAggregate> authenticateUser(String username, String password);

    /**
     * Changes a user's password
     * 
     * @param userId The user ID
     * @param currentPassword The current password for verification
     * @param newPassword The new password
     * @return true if the password was changed successfully
     */
    boolean changePassword(UUID userId, String currentPassword, String newPassword);

    /**
     * Disables a user account
     * 
     * @param userId The user ID
     */
    void disableUser(UUID userId);

    /**
     * Enables a previously disabled user account
     * 
     * @param userId The user ID
     */
    void enableUser(UUID userId);

    /**
     * Adds a user type to a user
     * 
     * @param userId The user ID
     * @param userType The user type to add
     */
    void addUserType(UUID userId, UserType userType);

    /**
     * Removes a user type from a user
     * 
     * @param userId The user ID
     * @param userType The user type to remove
     */
    void removeUserType(UUID userId, UserType userType);

    /**
     * Finds users by a partial username match
     * 
     * @param usernamePattern The username pattern to match
     * @return A list of matching user aggregates
     */
    List<UserAggregate> findUsersByUsernamePattern(String usernamePattern);
}