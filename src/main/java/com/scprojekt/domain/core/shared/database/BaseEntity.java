package com.scprojekt.domain.core.shared.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date createdAt;

    @JsonIgnore
    @Temporal(value = TemporalType.TIMESTAMP)
    Date modifiedAt;

    @JsonIgnore
    String modifiedFrom;

    @JsonIgnore
    @Column(columnDefinition = "boolean default true")
    Boolean enabled;
}