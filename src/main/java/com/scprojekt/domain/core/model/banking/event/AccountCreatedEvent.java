package com.scprojekt.domain.core.model.banking.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;


@Getter
public class AccountCreatedEvent extends DomainEvent {

    private final UUID accountId;

    private final Long customerId;

    public AccountCreatedEvent(UUID accountId, Long customerId) {
        super();
        this.accountId = accountId;
        this.customerId = customerId;
    }
}