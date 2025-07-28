package com.scprojekt.domain.core.model.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Account information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    
    private String accountNumber;
    private UUID accountUuid;
    private String accountHolderName;
    private String accountTypeName;
    private BigDecimal balance;
    private String currency;
    private BigDecimal overdraftLimit;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime lastTransactionAt;
}
