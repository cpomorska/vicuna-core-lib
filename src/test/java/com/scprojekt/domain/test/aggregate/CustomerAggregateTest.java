package com.scprojekt.domain.test.aggregate;

import com.scprojekt.domain.core.model.customer.aggregate.CustomerAggregate;
import com.scprojekt.domain.core.model.customer.entity.Customer;
import com.scprojekt.domain.core.model.customer.entity.CustomerType;
import com.scprojekt.domain.core.model.customer.exception.CustomerCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the CustomerAggregate class.
 */
class CustomerAggregateTest {

    private CustomerType businessType;
    private CustomerType privateType;

    @BeforeEach
    void setUp() {
        // Create customer types
        businessType = new CustomerType();
        businessType.setCustomerTypeId(1L);
        businessType.setCustomerRoleType("BUSINESS");
        businessType.setCustomerTypeDescription("Business Customer");

        privateType = new CustomerType();
        privateType.setCustomerTypeId(2L);
        privateType.setCustomerRoleType("PRIVATE");
        privateType.setCustomerTypeDescription("Private Customer");
    }

    @Test
    void testCreateCustomer() {
        // Given
        String customerName = "Test Company";
        String customerSurename = "Ltd";

        // When
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer(customerName, customerSurename, businessType);

        // Then
        assertThat(customerAggregate).isNotNull();
        assertThat(customerAggregate.getCustomerName()).isEqualTo(customerName);
        assertThat(customerAggregate.getCustomerSurename()).isEqualTo(customerSurename);
        assertThat(customerAggregate.getCustomerType()).isEqualTo(businessType);
        assertThat(customerAggregate.isEnabled()).isTrue();
        assertThat(customerAggregate.getCustomerId()).isNotNull();
    }

    @Test
    void testCreateCustomerWithInvalidData() {
        // Test empty customer name
        final String emptyName = "";
        final String validSurename = "Ltd";

        // When/Then
        assertThatThrownBy(() -> CustomerAggregate.createCustomer(emptyName, validSurename, businessType))
                .isInstanceOf(CustomerCreationException.class)
                .hasMessageContaining("Customer name cannot be empty");

        // Test empty customer surename
        final String validName = "Test Company";
        final String emptySurename = "";

        // When/Then
        assertThatThrownBy(() -> CustomerAggregate.createCustomer(validName, emptySurename, businessType))
                .isInstanceOf(CustomerCreationException.class)
                .hasMessageContaining("Customer surename cannot be empty");

        // Test null customer type
        // When/Then
        assertThatThrownBy(() -> CustomerAggregate.createCustomer(validName, validSurename, null))
                .isInstanceOf(CustomerCreationException.class)
                .hasMessageContaining("Customer must have a customer type");
    }

    @Test
    void testUpdateCustomerInfo() {
        // Given
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer("Test Company", "Ltd", businessType);
        String newName = "Updated Company";
        String newSurename = "Inc";

        // When
        customerAggregate.updateCustomerInfo(newName, newSurename, privateType);

        // Then
        assertThat(customerAggregate.getCustomerName()).isEqualTo(newName);
        assertThat(customerAggregate.getCustomerSurename()).isEqualTo(newSurename);
        assertThat(customerAggregate.getCustomerType()).isEqualTo(privateType);
    }

    @Test
    void testUpdateAddress() {
        // Given
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer("Test Company", "Ltd", businessType);
        String street1 = "123 Main St";
        String street2 = "Suite 100";
        String street3 = "Building A";
        String zip = "12345";
        String city = "Test City";

        // When
        customerAggregate.updateAddress(street1, street2, street3, zip, city);

        // Then
        Customer customer = customerAggregate.getCustomer();
        assertThat(customer.getCustomerStreet1()).isEqualTo(street1);
        assertThat(customer.getCustomerStreet2()).isEqualTo(street2);
        assertThat(customer.getCustomerStreet3()).isEqualTo(street3);
        assertThat(customer.getCustomerZip()).isEqualTo(zip);
        assertThat(customer.getCustomerCity()).isEqualTo(city);
    }

    @Test
    void testUpdateTitleAndSalutation() {
        // Given
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer("Test Company", "Ltd", businessType);
        String title = "Dr.";
        String salutation = "Mr.";

        // When
        customerAggregate.updateTitleAndSalutation(title, salutation);

        // Then
        Customer customer = customerAggregate.getCustomer();
        assertThat(customer.getCustomerTitel()).isEqualTo(title);
        assertThat(customer.getCustomerSalutation()).isEqualTo(salutation);
    }

    @Test
    void testDisableAndEnableCustomer() {
        // Given
        CustomerAggregate customerAggregate = CustomerAggregate.createCustomer("Test Company", "Ltd", businessType);
        assertThat(customerAggregate.isEnabled()).isTrue();

        // When
        customerAggregate.disable();

        // Then
        assertThat(customerAggregate.isEnabled()).isFalse();

        // When
        customerAggregate.enable();

        // Then
        assertThat(customerAggregate.isEnabled()).isTrue();
    }

    @Test
    void testFromCustomer() {
        // Given
        CustomerAggregate originalAggregate = CustomerAggregate.createCustomer("Test Company", "Ltd", businessType);
        Customer customer = originalAggregate.getCustomer();

        // When
        CustomerAggregate newAggregate = CustomerAggregate.fromCustomer(customer);

        // Then
        assertThat(newAggregate).isNotNull();
        assertThat(newAggregate.getCustomerId()).isEqualTo(originalAggregate.getCustomerId());
        assertThat(newAggregate.getCustomerName()).isEqualTo(originalAggregate.getCustomerName());
        assertThat(newAggregate.getCustomerSurename()).isEqualTo(originalAggregate.getCustomerSurename());
        assertThat(newAggregate.getCustomerType()).isEqualTo(originalAggregate.getCustomerType());
        assertThat(newAggregate.isEnabled()).isEqualTo(originalAggregate.isEnabled());
    }
}