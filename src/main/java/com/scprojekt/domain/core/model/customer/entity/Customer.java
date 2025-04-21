package com.scprojekt.domain.core.model.customer.entity;

import com.scprojekt.domain.core.model.customer.event.CustomerCreatedEvent;
import com.scprojekt.domain.core.model.customer.event.CustomerDisabledEvent;
import com.scprojekt.domain.core.model.customer.event.CustomerUpdatedEvent;
import com.scprojekt.domain.core.model.customer.exception.CustomerCreationException;
import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import com.scprojekt.domain.core.shared.event.DomainEventPublisher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kunde")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kundenid")
    private
    long customerId;

    @Embedded
    private CustomerNumber customerNumber;

    @OneToOne(orphanRemoval = true, targetEntity = CustomerType.class, cascade = CascadeType.ALL)
    private CustomerType customerType;

    @NoSQLInjection
    @Column(name="kundentitel")
    private
    String customerTitel;

    @NoSQLInjection
    @Column(name="kundenanrede")
    private
    String customerSalutation;

    @NoSQLInjection
    @Column(name="kundenname")
    private
    String customerName;

    @NoSQLInjection
    @Column(name="kundenmittelname")
    private
    String customerMiddlename;

    @NoSQLInjection
    @Column(name="kundenvorname")
    private
    String customerSurename;

    @NoSQLInjection
    @Column(name="kundenstrasse1")
    private
    String customerStreet1;

    @NoSQLInjection
    @Column(name="kundenstrasse2")
    private
    String customerStreet2;

    @NoSQLInjection
    @Column(name="kundenstrasse3")
    private
    String customerStreet3;

    @NoSQLInjection
    @Column(name="kundenplz")

    private
    String customerZip;

    @NoSQLInjection
    @Column(name="kundenstadt")
    private
    String customerCity;

    /**
     * Creates a new customer with the given parameters
     *
     * @param customerName The customer name
     * @param customerSurename The customer surename
     * @param customerType The customer type
     * @return A new Customer instance
     * @throws CustomerCreationException if validation fails
     */
    public static Customer createCustomer(String customerName, String customerSurename, CustomerType customerType) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerSurename(customerSurename);
        customer.setCustomerType(customerType);
        customer.setCustomerNumber(new CustomerNumber(UUID.randomUUID()));
        customer.setEnabled(true);

        // Validate the customer
        customer.validate();

        // Publish domain event
        DomainEventPublisher.publish(new CustomerCreatedEvent(customer.getCustomerNumber().getUuid(), customerName));

        return customer;
    }

    /**
     * Updates the customer with new information
     *
     * @param newCustomerName The new customer name
     * @param newCustomerSurename The new customer surename
     * @param newCustomerType The new customer type
     */
    public void updateCustomerInfo(String newCustomerName, String newCustomerSurename, CustomerType newCustomerType) {
        this.customerName = newCustomerName;
        this.customerSurename = newCustomerSurename;
        this.customerType = newCustomerType;

        // Validate the updated customer
        validate();

        // Publish domain event
        DomainEventPublisher.publish(new CustomerUpdatedEvent(this.getCustomerNumber().getUuid(), newCustomerName));
    }

    /**
     * Disables the customer
     */
    @Override
    public void disable() {
        this.setEnabled(false);

        // Publish domain event
        DomainEventPublisher.publish(new CustomerDisabledEvent(this.getCustomerNumber().getUuid(), this.customerName));
    }

    /**
     * Validates that the customer meets all business rules
     *
     * @throws CustomerCreationException if validation fails
     */
    private void validate() {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new CustomerCreationException("Customer name cannot be empty");
        }

        if (customerSurename == null || customerSurename.trim().isEmpty()) {
            throw new CustomerCreationException("Customer surename cannot be empty");
        }

        if (customerType == null) {
            throw new CustomerCreationException("Customer must have a customer type");
        }

        if (customerNumber == null) {
            throw new CustomerCreationException("Customer number cannot be null");
        }
    }
}
