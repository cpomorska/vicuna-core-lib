package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.model.user.exception.UserCreationException;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Value object representing a unique user number.
 * This class is immutable and validates that the UUID is not null.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class UserNumber implements Serializable {
    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name="benutzernummer", unique = true, updatable = false, length = 36)
    @NotEmpty(message = "benutzernummer cannot be empty.")
    private UUID uuid;

    /**
     * Creates a new user number with the given UUID
     * 
     * @param uuid The UUID for this user number
     * @throws UserCreationException if the UUID is null
     */
    public UserNumber(UUID uuid) {
        validate(uuid);
        this.uuid = uuid;
    }

    /**
     * Creates a new random user number
     * 
     * @return A new user number with a random UUID
     */
    public static UserNumber createRandom() {
        return new UserNumber(UUID.randomUUID());
    }

    /**
     * Validates that the UUID is not null
     * 
     * @param uuid The UUID to validate
     * @throws UserCreationException if the UUID is null
     */
    private void validate(UUID uuid) {
        if (uuid == null) {
            throw new UserCreationException("User number UUID cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNumber that = (UserNumber) o;
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
