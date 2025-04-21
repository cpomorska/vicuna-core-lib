package com.scprojekt.domain.core.shared.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base class for all entities in the system.
 * Provides common fields and behavior for tracking entity lifecycle.
 */
@MappedSuperclass
@Getter
public class BaseEntity {

    private LocalDateTime entityDates;

    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        LocalDateTime now = LocalDateTime.now();
        this.entityDates = now;

        if (this.createdAt == null) {
            this.createdAt = now;
        }

        this.modifiedAt = now;
    }

    @JsonIgnore
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @Setter
    private String modifiedFrom;

    @JsonIgnore
    @Column(columnDefinition = "boolean default true")
    private Boolean enabled = true;

    /**
     * Enables this entity
     */
    public void enable() {
        this.enabled = true;
    }

    /**
     * Disables this entity
     */
    public void disable() {
        this.enabled = false;
    }

    /**
     * Sets the enabled status of this entity
     * 
     * @param enabled The enabled status
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Checks if this entity is enabled
     * 
     * @return true if the entity is enabled
     */
    public boolean isEnabled() {
        return enabled != null && enabled;
    }
}
