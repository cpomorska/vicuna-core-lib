package com.scprojekt.domain.core.model.customer.service;

import com.scprojekt.domain.core.model.customer.aggregate.CustomerAggregate;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.service.BaseService;

import java.util.List;

/**
 * Base service interface for customer operations.
 * This interface adapts the generic BaseService to work with CustomerAggregate.
 */
public interface CustomerBaseService extends BaseService<CustomerAggregate> {
    
    /**
     * Implementation of findAllByType that returns an empty list.
     * This method is required by BaseService but not used for customers.
     * 
     * @param typeList The list of user types to search for
     * @return An empty list of customer aggregates
     */
    @Override
    default List<CustomerAggregate> findAllByType(List<UserType> typeList) {
        // This method is not used for customers, but is required by BaseService
        return List.of();
    }
    
    /**
     * Implementation of findAllByDescription that returns an empty list.
     * This method is required by BaseService but not used for customers.
     * 
     * @param description The description to search for
     * @return An empty list of customer aggregates
     */
    @Override
    default List<CustomerAggregate> findAllByDescription(String description) {
        // This method is not used for customers, but is required by BaseService
        return List.of();
    }
}