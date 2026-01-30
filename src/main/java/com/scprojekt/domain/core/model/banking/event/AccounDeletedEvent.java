package com.scprojekt.domain.core.model.banking.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;


/**
 * Event representing the deletion of an account in the domain.
 * This event is triggered when an account is successfully deleted.
 * It contains information about the account and its associated customer.
 */
@Getter
public class AccounDeletedEvent extends DomainEvent {

    private final UUID accountId;

    private final Long customerId;

    public AccounDeletedEvent(UUID accountId, Long customerId) {
        super();
        this.accountId = accountId;
        this.customerId = customerId;
    }

}