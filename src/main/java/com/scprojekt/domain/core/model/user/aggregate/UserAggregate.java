package com.scprojekt.domain.core.model.user.aggregate;

import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserHash;
import com.scprojekt.domain.core.model.user.entity.UserNumber;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.event.UserCreatedEvent;
import com.scprojekt.domain.core.model.user.event.UserDisabledEvent;
import com.scprojekt.domain.core.model.user.event.UserUpdatedEvent;
import com.scprojekt.domain.core.model.user.exception.UserCreationException;
import com.scprojekt.domain.core.shared.aggregate.BaseAggregate;
import com.scprojekt.domain.core.shared.event.DomainEventPublisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * UserAggregate is the aggregate root for the User domain.
 * It encapsulates the User entity and enforces all invariants and business rules related to users.
 * 
 * This class follows the DDD aggregate pattern, ensuring that all changes to the User and its
 * associated value objects go through the aggregate root, maintaining consistency.
 */
public class UserAggregate implements BaseAggregate {
    
    private final User user;
    
    /**
     * Private constructor to enforce creation through factory methods
     * 
     * @param user The user entity
     */
    private UserAggregate(User user) {
        this.user = user;
    }
    
    /**
     * Creates a new user aggregate with the given parameters
     *
     * @param userName The username
     * @param userDescription The user description
     * @param userTypes The user types
     * @param password The plain text password
     * @return A new UserAggregate instance
     * @throws UserCreationException if validation fails
     */
    public static UserAggregate createUser(String userName, String userDescription, List<UserType> userTypes, String password) {
        // Create user hash from password
        UserHash userHash = UserHash.fromPassword(password);
        
        // Create the user
        User user = new User();
        user.setUserName(userName);
        user.setUserDescription(userDescription);
        user.setUserTypes(new ArrayList<>(userTypes));
        user.setUserHash(userHash);
        user.setUserNumber(new UserNumber(UUID.randomUUID()));
        user.setEnabled(true);
        
        // Create the aggregate
        UserAggregate aggregate = new UserAggregate(user);
        
        // Validate the user
        aggregate.validate();
        
        // Publish domain event
        DomainEventPublisher.publish(new UserCreatedEvent(user.getUserNumber().getUuid(), userName));
        
        return aggregate;
    }
    
    /**
     * Creates a user aggregate from an existing user entity
     * 
     * @param user The user entity
     * @return A new UserAggregate instance
     */
    public static UserAggregate fromUser(User user) {
        return new UserAggregate(user);
    }
    
    /**
     * Gets the underlying user entity
     * 
     * @return The user entity
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Gets the user's unique identifier
     * 
     * @return The user's UUID
     */
    public UUID getUserId() {
        return user.getUserNumber().getUuid();
    }
    
    /**
     * Updates the user with new information
     *
     * @param newUserName The new username
     * @param newUserDescription The new user description
     * @param newUserTypes The new user types
     */
    public void updateUserInfo(String newUserName, String newUserDescription, List<UserType> newUserTypes) {
        user.setUserName(newUserName);
        user.setUserDescription(newUserDescription);
        user.setUserTypes(newUserTypes);
        
        // Validate the updated user
        validate();
        
        // Publish domain event
        DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserId(), newUserName));
    }
    
    /**
     * Changes the user's password
     *
     * @param currentPassword The current password for verification
     * @param newPassword The new password
     * @return true if the password was changed successfully
     */
    public boolean changePassword(String currentPassword, String newPassword) {
        // Verify current password
        if (!user.getUserHash().validatePassword(currentPassword)) {
            return false;
        }
        
        // Change password
        UserHash newUserHash = UserHash.fromPassword(newPassword);
        user.setUserHash(newUserHash);
        
        // Publish domain event
        DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserId(), user.getUserName()));
        
        return true;
    }
    
    /**
     * Disables the user
     */
    public void disable() {
        user.setEnabled(false);
        
        // Publish domain event
        DomainEventPublisher.publish(new UserDisabledEvent(this.getUserId(), user.getUserName()));
    }
    
    /**
     * Enables the user
     */
    public void enable() {
        user.setEnabled(true);
        
        // Publish domain event
        DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserId(), user.getUserName()));
    }
    
    /**
     * Adds a user type to this user
     *
     * @param userType The user type to add
     */
    public void addUserType(UserType userType) {
        if (userType != null && !user.getUserTypes().contains(userType)) {
            List<UserType> userTypes = new ArrayList<>(user.getUserTypes());
            userTypes.add(userType);
            user.setUserTypes(userTypes);
            
            // Publish domain event
            DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserId(), user.getUserName()));
        }
    }
    
    /**
     * Removes a user type from this user
     *
     * @param userType The user type to remove
     */
    public void removeUserType(UserType userType) {
        if (userType != null && user.getUserTypes().size() > 1) {
            List<UserType> userTypes = new ArrayList<>(user.getUserTypes());
            userTypes.remove(userType);
            user.setUserTypes(userTypes);
            
            // Publish domain event
            DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserId(), user.getUserName()));
        }
    }
    
    /**
     * Gets an unmodifiable view of the user types
     *
     * @return Unmodifiable list of user types
     */
    public List<UserType> getUserTypes() {
        return Collections.unmodifiableList(user.getUserTypes());
    }
    
    /**
     * Checks if the user has a specific user type
     * 
     * @param userType The user type to check
     * @return true if the user has the specified type
     */
    public boolean hasUserType(UserType userType) {
        return user.getUserTypes().contains(userType);
    }
    
    /**
     * Validates that the user meets all business rules
     *
     * @throws UserCreationException if validation fails
     */
    private void validate() {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new UserCreationException("Username cannot be empty");
        }
        
        if (user.getUserDescription() == null || user.getUserDescription().trim().isEmpty()) {
            throw new UserCreationException("User description cannot be empty");
        }
        
        if (user.getUserTypes() == null || user.getUserTypes().isEmpty()) {
            throw new UserCreationException("User must have at least one user type");
        }
        
        if (user.getUserHash() == null) {
            throw new UserCreationException("User hash cannot be null");
        }
    }
    
    /**
     * Checks if the user is enabled
     * 
     * @return true if the user is enabled
     */
    public boolean isEnabled() {
        return user.isEnabled();
    }
    
    /**
     * Gets the username
     * 
     * @return The username
     */
    public String getUserName() {
        return user.getUserName();
    }
    
    /**
     * Gets the user description
     * 
     * @return The user description
     */
    public String getUserDescription() {
        return user.getUserDescription();
    }
    
    /**
     * Validates a password against the user's hash
     * 
     * @param password The password to validate
     * @return true if the password is valid
     */
    public boolean validatePassword(String password) {
        return user.getUserHash().validatePassword(password);
    }
}