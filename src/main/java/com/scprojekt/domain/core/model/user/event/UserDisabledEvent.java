package com.scprojekt.domain.core.model.user.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain event that is published when a user is disabled.
 * This event can be used by other bounded contexts that need to react to user disabling.
 */
@Getter
public class UserDisabledEvent extends DomainEvent {
    /**
     *  Gets the ID of the disabled user
     */
    private final UUID userId;
    /**
     *  Gets the username of the disabled user
     */
    private final String userName;
    
    /**
     * Creates a new user disabled event
     * 
     * @param userId The ID of the disabled user
     * @param userName The username of the disabled user
     */
    public UserDisabledEvent(UUID userId, String userName) {
        super();
        this.userId = userId;
        this.userName = userName;
    }

}