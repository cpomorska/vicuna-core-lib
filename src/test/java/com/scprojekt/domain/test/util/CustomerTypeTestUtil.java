package com.scprojekt.domain.test.util;

import com.scprojekt.domain.core.model.customer.entity.CustomerType;

/**
 * Utility class for creating CustomerType objects with test data.
 */
public class CustomerTypeTestUtil {

    // Static constants for common customer types
    public static final CustomerType RETAIL = createCustomerType(1L, "RETAIL", "Retail customer");
    public static final CustomerType BUSINESS = createCustomerType(2L, "BUSINESS", "Business customer");
    public static final CustomerType ENTERPRISE = createCustomerType(3L, "ENTERPRISE", "Enterprise customer");
    public static final CustomerType GOVERNMENT = createCustomerType(4L, "GOVERNMENT", "Government customer");
    public static final CustomerType NONPROFIT = createCustomerType(5L, "NONPROFIT", "Non-profit organization");

    /**
     * Creates a CustomerType with the specified values.
     *
     * @param id The customer type ID
     * @param type The customer role type
     * @param description The customer type description
     * @return A new CustomerType instance
     */
    public static CustomerType createCustomerType(Long id, String type, String description) {
        CustomerType customerType = new CustomerType();
        customerType.setCustomerTypeId(id);
        customerType.setCustomerRoleType(type);
        customerType.setCustomerTypeDescription(description);
        return customerType;
    }

    /**
     * Creates a random CustomerType with default values.
     *
     * @return A new CustomerType instance with default values
     */
    public static CustomerType createRandomCustomerType() {
        return createCustomerType(
            (long) (Math.random() * 1000),
            "TYPE_" + Math.random(),
            "Description for random customer type"
        );
    }
}