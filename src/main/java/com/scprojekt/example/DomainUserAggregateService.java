package com.scprojekt.example;

import com.scprojekt.domain.core.model.user.aggregate.UserAggregate;
import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import com.scprojekt.domain.core.model.user.service.UserAggregateService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Example class demonstrating how to use the UserAggregateService.
 * This class shows how to perform common user management operations using the aggregate pattern.
 */
@Log
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DomainUserAggregateService {

    private final UserAggregateService userService;
    
    /**
     * Creates a new instance of the example service with a repository
     * 
     * @param userRepository The user repository
     */
    public DomainUserAggregateService(UserRepository userRepository) {
        this.userService = new UserAggregateServiceImpl(userRepository);
    }
    
    /**
     * Registers a new user
     * 
     * @param username The username
     * @param description The user description
     * @param password The password
     * @param userTypes The user types
     * @return The UUID of the created user
     */
    public UUID registerUser(String username, String description, String password, List<UserType> userTypes) {
        UuidResponse response = userService.registerUser(username, description, password, userTypes);
        log.info("User registered with UUID: " + response.getUuid());
        return response.getUuid();
    }
    
    /**
     * Authenticates a user
     * 
     * @param username The username
     * @param password The password
     * @return true if authentication was successful
     */
    public boolean authenticateUser(String username, String password) {
        Optional<UserAggregate> userOpt = userService.authenticateUser(username, password);
        if (userOpt.isPresent()) {
            UserAggregate user = userOpt.get();
            log.info("User authenticated: " + user.getUserName());
            return true;
        }
        log.warning("Authentication failed for user: " + username);
        return false;
    }
    
    /**
     * Changes a user's password
     * 
     * @param userId The user ID
     * @param currentPassword The current password
     * @param newPassword The new password
     * @return true if the password was changed successfully
     */
    public boolean changePassword(UUID userId, String currentPassword, String newPassword) {
        boolean changed = userService.changePassword(userId, currentPassword, newPassword);
        if (changed) {
            log.info("Password changed for user with UUID: " + userId);
        } else {
            log.warning("Failed to change password for user with UUID: " + userId);
        }
        return changed;
    }
    
    /**
     * Disables a user account
     * 
     * @param userId The user ID
     */
    public void disableUser(UUID userId) {
        userService.disableUser(userId);
        log.info("User disabled: " + userId);
    }
    
    /**
     * Enables a user account
     * 
     * @param userId The user ID
     */
    public void enableUser(UUID userId) {
        userService.enableUser(userId);
        log.info("User enabled: " + userId);
    }
    
    /**
     * Adds a user type to a user
     * 
     * @param userId The user ID
     * @param userType The user type to add
     */
    public void addUserType(UUID userId, UserType userType) {
        userService.addUserType(userId, userType);
        log.info("User type added to user: " + userId);
    }
    
    /**
     * Removes a user type from a user
     * 
     * @param userId The user ID
     * @param userType The user type to remove
     */
    public void removeUserType(UUID userId, UserType userType) {
        userService.removeUserType(userId, userType);
        log.info("User type removed from user: " + userId);
    }
    
    /**
     * Finds users by username pattern
     * 
     * @param pattern The username pattern
     * @return A list of matching users
     */
    public List<UserAggregate> findUsersByUsernamePattern(String pattern) {
        List<UserAggregate> users = userService.findUsersByUsernamePattern(pattern);
        log.info("Found " + users.size() + " users matching pattern: " + pattern);
        return users;
    }
}