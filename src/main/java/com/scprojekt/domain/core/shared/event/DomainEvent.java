package com.scprojekt.domain.core.shared.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base class for all domain events in the system.
 * Domain events represent something that happened in the domain that domain experts care about.
 */
public abstract class DomainEvent {
    private final UUID eventId;
    private final LocalDateTime occurredOn;
    
    /**
     * Creates a new domain event with a unique ID and timestamp
     */
    protected DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
    }
    
    /**
     * Gets the unique identifier for this event
     * 
     * @return The event ID
     */
    public UUID getEventId() {
        return eventId;
    }
    
    /**
     * Gets the timestamp when this event occurred
     * 
     * @return The event timestamp
     */
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}