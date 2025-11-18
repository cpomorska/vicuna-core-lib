package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.model.user.exception.UserCreationException;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * Value object representing a user's password hash and salt.
 * This class is immutable and provides methods for password validation.
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Required by JPA, but protected to discourage direct use
@Table(name = "user_hash_table")
public class UserHash {
    @NotNull
    @NoSQLInjection
    @Column(name = "user_hash")
    private String hash;

    @NotNull
    @NoSQLInjection
    @Column(name = "user_salt")
    private String salt;

    /**
     * Creates a new user hash with the given hash and salt
     * 
     * @param hash The password hash
     * @param salt The salt used for hashing
     * @throws UserCreationException if the hash or salt is invalid
     */
    public UserHash(String hash, String salt) {
        validate(hash, salt);
        this.hash = hash;
        this.salt = salt;
    }

    /**
     * Creates a new user hash from a plain text password
     * 
     * @param password The plain text password
     * @return A new user hash with the hashed password and a random salt
     * @throws UserCreationException if the password is invalid or hashing fails
     */
    public static UserHash fromPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new UserCreationException("Password cannot be empty");
        }

        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[16];
            random.nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);

            // Hash the password with the salt
            String hash = hashPassword(password, salt);

            return new UserHash(hash, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new UserCreationException("Failed to hash password", e);
        }
    }

    /**
     * Validates a plain text password against this hash
     * 
     * @param password The plain text password to validate
     * @return true if the password matches this hash
     */
    public boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        try {
            String hashedPassword = hashPassword(password, salt);
            return hash.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    /**
     * Validates that the hash and salt are valid
     * 
     * @param hash The hash to validate
     * @param salt The salt to validate
     * @throws UserCreationException if the hash or salt is invalid
     */
    private void validate(String hash, String salt) {
        if (hash == null || hash.trim().isEmpty()) {
            throw new UserCreationException("Hash cannot be empty");
        }

        if (salt == null || salt.trim().isEmpty()) {
            throw new UserCreationException("Salt cannot be empty");
        }
    }

    /**
     * Hashes a password with a salt using SHA-256
     * 
     * @param password The password to hash
     * @param salt The salt to use
     * @return The hashed password
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHash userHash = (UserHash) o;
        return Objects.equals(hash, userHash.hash) && Objects.equals(salt, userHash.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, salt);
    }

    @Override
    public String toString() {
        return "UserHash{hash=********, salt=********}"; // Don't expose actual values in toString
    }
}
