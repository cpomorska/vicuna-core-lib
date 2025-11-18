/**
 * This package contains the User aggregate.
 * 
 * In Domain-Driven Design, an aggregate is a cluster of domain objects that can be treated as a single unit.
 * The User aggregate consists of the User entity (aggregate root), UserNumber and UserHash value objects,
 * and references to UserType entities.
 * 
 * The aggregate ensures that all invariants and business rules related to users are enforced within its boundary.
 */
package com.scprojekt.domain.core.model.user.aggregate;