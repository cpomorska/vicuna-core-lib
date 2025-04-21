package com.scprojekt.domain.core.model.customer.service;

import com.scprojekt.domain.core.model.customer.aggregate.CustomerAggregate;
import com.scprojekt.domain.core.model.customer.entity.Customer;
import com.scprojekt.domain.core.model.customer.entity.CustomerType;
import com.scprojekt.domain.core.model.customer.repository.CustomerRepository;
import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the CustomerAggregateService that provides domain-specific customer operations.
 * This service implements the business logic for customer management using the CustomerAggregate.
 */
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DomainCustomerAggregateService implements CustomerAggregateService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerAggregate getById(long id) {
        Customer customer = customerRepository.findByIdInRepository(id);
        return customer != null ? CustomerAggregate.fromCustomer(customer) : null;
    }

    @Override
    public CustomerAggregate getByUuid(UUID uuid) {
        Customer customer = customerRepository.findByUUID(uuid);
        return customer != null ? CustomerAggregate.fromCustomer(customer) : null;
    }

    @Override
    public UuidResponse create(CustomerAggregate customerAggregate) {
        Customer customer = customerAggregate.getCustomer();
        customerRepository.createEntity(customer);
        return new UuidResponse(customer.getCustomerNumber().getUuid());
    }

    @Override
    public void update(CustomerAggregate customerAggregate) {
        customerRepository.updateEntity(customerAggregate.getCustomer());
    }

    @Override
    public void remove(CustomerAggregate customerAggregate) {
        customerRepository.removeEntity(customerAggregate.getCustomer());
    }

    @Override
    public List<CustomerAggregate> findByCustomerType(CustomerType customerType) {
        return customerRepository.findByType(customerType).stream()
                .map(CustomerAggregate::fromCustomer)
                .toList();
    }

    @Override
    public List<CustomerAggregate> findAllByName(String name) {
        return customerRepository.findByName(name).stream()
                .map(CustomerAggregate::fromCustomer)
                .toList();
    }

    @Override
    public UuidResponse registerCustomer(String customerName, String customerSurename, CustomerType customerType) {
        // Create the customer aggregate
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer(customerName, customerSurename, customerType);

        // Persist the customer
        customerRepository.createEntity(customerAggregate.getCustomer());

        return new UuidResponse(customerAggregate.getCustomerId());
    }

    @Override
    public void disableCustomer(UUID customerId) {
        CustomerAggregate customerAggregate = getByUuid(customerId);
        if (customerAggregate != null) {
            customerAggregate.disable();
            customerRepository.updateEntity(customerAggregate.getCustomer());
        }
    }

    @Override
    public void enableCustomer(UUID customerId) {
        CustomerAggregate customerAggregate = getByUuid(customerId);
        if (customerAggregate != null) {
            customerAggregate.enable();
            customerRepository.updateEntity(customerAggregate.getCustomer());
        }
    }

    @Override
    public void updateCustomerAddress(UUID customerId, String street1, String street2, String street3, String zip, String city) {
        CustomerAggregate customerAggregate = getByUuid(customerId);
        if (customerAggregate != null) {
            customerAggregate.updateAddress(street1, street2, street3, zip, city);
            customerRepository.updateEntity(customerAggregate.getCustomer());
        }
    }

    @Override
    public void updateCustomerTitleAndSalutation(UUID customerId, String title, String salutation) {
        CustomerAggregate customerAggregate = getByUuid(customerId);
        if (customerAggregate != null) {
            customerAggregate.updateTitleAndSalutation(title, salutation);
            customerRepository.updateEntity(customerAggregate.getCustomer());
        }
    }

    @Override
    public List<CustomerAggregate> findCustomersByNamePattern(String namePattern) {
        // Get all customers and filter by pattern
        // In a real implementation, this would be done at the database level
        List<Customer> allCustomers = customerRepository.findAllInRepository();

        return allCustomers.stream()
                .filter(customer -> customer.getCustomerName().contains(namePattern))
                .map(CustomerAggregate::fromCustomer)
                .toList();
    }
}