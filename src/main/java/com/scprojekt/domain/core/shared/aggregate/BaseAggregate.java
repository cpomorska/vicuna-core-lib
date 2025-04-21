package com.scprojekt.domain.core.shared.aggregate;

/**
 * This interface represents the base aggregate pattern, providing a blueprint for classes that want to participate in an aggregate entity.
 * An aggregate is a collection of domain objects that can be treated as a single unit. The BaseAggregate interface defines the contract for entities that wish to become part of such
 *  aggregates.
 *
 * <p>Implementations of this interface should provide behavior and state relevant to the aggregate, and may define methods for operations common to all aggregates.
 *
 * <p>Note: This is an abstract interface and cannot be instantiated directly. It must be implemented by concrete classes.
 */
public interface BaseAggregate {
}
