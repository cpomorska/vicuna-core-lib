package com.scprojekt.domain.core.model.user.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain event that is published when a new user is created.
 * This event can be used by other bounded contexts that need to react to user creation.
 */
@Getter
public class UserCreatedEvent extends DomainEvent {
    /**
     *  Gets the ID of the created user
     */
    private final UUID userId;
    /**
     *  Gets the username of the created user
     */
    private final String userName;
    
    /**
     * Creates a new user created event
     * 
     * @param userId The ID of the created user
     * @param userName The username of the created user
     */
    public UserCreatedEvent(UUID userId, String userName) {
        super();
        this.userId = userId;
        this.userName = userName;
    }

}