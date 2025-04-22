package com.scprojekt.domain.core.model.customer.event;

import com.scprojekt.domain.core.shared.event.DomainEvent;
import lombok.Getter;

import java.util.UUID;

/**
 * Domain event that is published when a customer is disabled.
 * This event can be used by other bounded contexts that need to react to customer disabling.
 */
@Getter
public class CustomerDisabledEvent extends DomainEvent {
    /**
     * -- GETTER --
     *  Gets the ID of the disabled customer
     *
     * @return The customer ID
     */
    private final UUID customerId;
    /**
     * -- GETTER --
     *  Gets the name of the disabled customer
     *
     * @return The customer name
     */
    private final String customerName;
    
    /**
     * Creates a new customer disabled event
     * 
     * @param customerId The ID of the disabled customer
     * @param customerName The name of the disabled customer
     */
    public CustomerDisabledEvent(UUID customerId, String customerName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
    }

}