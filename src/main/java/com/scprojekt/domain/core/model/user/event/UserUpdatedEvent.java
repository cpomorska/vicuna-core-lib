package com.scprojekt.domain.core.model.user.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;

import java.util.UUID;

/**
 * Domain event that is published when a user is updated.
 * This event can be used by other bounded contexts that need to react to user updates.
 */
public class UserUpdatedEvent extends DomainEvent {
    private final UUID userId;
    private final String userName;
    
    /**
     * Creates a new user updated event
     * 
     * @param userId The ID of the updated user
     * @param userName The username of the updated user
     */
    public UserUpdatedEvent(UUID userId, String userName) {
        super();
        this.userId = userId;
        this.userName = userName;
    }
    
    /**
     * Gets the ID of the updated user
     * 
     * @return The user ID
     */
    public UUID getUserId() {
        return userId;
    }
    
    /**
     * Gets the username of the updated user
     * 
     * @return The username
     */
    public String getUserName() {
        return userName;
    }
}