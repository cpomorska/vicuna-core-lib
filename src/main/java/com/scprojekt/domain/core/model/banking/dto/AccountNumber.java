package com.scprojekt.domain.core.model.banking.dto;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;

/**
 * Value object representing an account number with UUID
 */
@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountNumber {
    
    private String value;
    private UUID uuid;
    
    private AccountNumber(String value, UUID uuid) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (!isValidFormat(value)) {
            throw new IllegalArgumentException("Invalid account number format");
        }
        if (uuid == null) {
            throw new IllegalArgumentException("Account UUID cannot be null");
        }
        this.value = value;
        this.uuid = uuid;
    }
    
    /**
     * Generate a new account number with UUID
     */
    public static AccountNumber generate() {
        // Generate format: ACC-XXXXXXXX (8 random digits)
        Random random = new Random();
        StringBuilder sb = new StringBuilder("ACC-");
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return new AccountNumber(sb.toString(), UUID.randomUUID());
    }
    
    /**
     * Create from existing values
     */
    public static AccountNumber of(String value, UUID uuid) {
        return new AccountNumber(value, uuid);
    }
    
    private boolean isValidFormat(String value) {
        return value.matches("ACC-\\d{8}");
    }
}
