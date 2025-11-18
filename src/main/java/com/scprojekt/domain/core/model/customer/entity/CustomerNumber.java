package com.scprojekt.domain.core.model.customer.entity;

import com.scprojekt.domain.core.model.customer.exception.CustomerCreationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Value object representing a unique customer number.
 * This class is immutable and validates that the UUID is not null.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class CustomerNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name="kundennummer", unique = true, updatable = false, length = 36)
    @NotEmpty(message = "kundennummer cannot be empty.")
    private UUID uuid;

    /**
     * Creates a new customer number with the given UUID
     * 
     * @param uuid The UUID for this customer number
     * @throws CustomerCreationException if the UUID is null
     */
    public CustomerNumber(UUID uuid) {
        validate(uuid);
        this.uuid = uuid;
    }

    /**
     * Creates a new random customer number
     * 
     * @return A new customer number with a random UUID
     */
    public static CustomerNumber createRandom() {
        return new CustomerNumber(UUID.randomUUID());
    }

    /**
     * Validates that the UUID is not null
     * 
     * @param uuid The UUID to validate
     * @throws CustomerCreationException if the UUID is null
     */
    private void validate(UUID uuid) {
        if (uuid == null) {
            throw new CustomerCreationException("Customer number UUID cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerNumber that = (CustomerNumber) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}