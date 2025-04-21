package com.scprojekt.domain.core.model.user.service;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserHash;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Domain service for user management operations.
 * This service provides methods for user-specific business operations.
 */
public interface UserService extends BaseService<User> {

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
     * @return The authenticated user if successful, empty otherwise
     */
    Optional<User> authenticateUser(String username, String password);

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
     * @return A list of matching users
     */
    List<User> findUsersByUsernamePattern(String usernamePattern);
}
