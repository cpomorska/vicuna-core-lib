package com.scprojekt.domain.core.model.banking.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@ToString
@EqualsAndHashCode
public class TransactionProcessedEvent extends DomainEvent {
    private final String value;
    @NotNull
    private final String transactionId;
    private final String credit;
    private final BigDecimal amount;
    private final BigDecimal amount1;
    private final String description;
    private final Instant timestamp;

    public TransactionProcessedEvent(String value, @NotNull String transactionId, String credit, 
                                   BigDecimal amount, BigDecimal amount1, String description) {
        this.value = value;
        this.transactionId = transactionId;
        this.credit = credit;
        this.amount = amount;
        this.amount1 = amount1;
        this.description = description;
        this.timestamp = Instant.now();
    }
}