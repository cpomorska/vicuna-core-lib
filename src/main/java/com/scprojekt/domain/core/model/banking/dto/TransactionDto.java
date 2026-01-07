package com.scprojekt.domain.core.model.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Transaction information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    
    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String transactionReference;
    private LocalDateTime processedAt;
    private BigDecimal balanceAfter;
    private String status;
    private String channel;
    private String externalReference;
    private UUID createdBy;
    private UUID authorizedBy;
    private String notes;
}
