package com.scprojekt.domain.core.shared.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    private LocalDateTime entityDates;

    @PrePersist
    @PreUpdate
    public void setCreatedAt() {
        this.entityDates = LocalDateTime.now();
        if (this.createdAt == null){
            this.createdAt = entityDates;
        }
    }

    @JsonIgnore
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false )
    LocalDateTime createdAt;

    @JsonIgnore
    @Temporal(value = TemporalType.TIMESTAMP)
    public LocalDateTime modifiedAt;

    @JsonIgnore
    public String modifiedFrom;

    @JsonIgnore
    @Column(columnDefinition = "boolean default true")
    Boolean enabled;
}