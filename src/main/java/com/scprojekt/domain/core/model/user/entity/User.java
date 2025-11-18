package com.scprojekt.domain.core.model.user.entity;

import com.scprojekt.domain.core.model.user.event.UserCreatedEvent;
import com.scprojekt.domain.core.model.user.event.UserDisabledEvent;
import com.scprojekt.domain.core.model.user.event.UserUpdatedEvent;
import com.scprojekt.domain.core.model.user.exception.UserCreationException;
import com.scprojekt.domain.core.shared.database.BaseEntity;
import com.scprojekt.domain.core.shared.database.NoSQLInjection;
import com.scprojekt.domain.core.shared.event.DomainEventPublisher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "userid_seq", sequenceName = "USERID_SEQ", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL)
    @CollectionTable
    private List<UserType> userTypes = new ArrayList<>();

    @Embedded
    private UserNumber userNumber;

    @Embedded
    private UserHash userHash;

    @NoSQLInjection
    @Column(name = "username", nullable = false)
    private String userName;

    @NotNull
    @NoSQLInjection
    @Column(name = "description")
    private String userDescription;

    // Domain behavior methods

    /**
     * Creates a new user with the given parameters
     *
     * @param userName The username
     * @param userDescription The user description
     * @param userTypes The user types
     * @param userHash The user hash
     * @return A new User instance
     * @throws UserCreationException if validation fails
     */
    public static User createUser(String userName, String userDescription, List<UserType> userTypes, UserHash userHash) {
        User user = new User();
        user.setUserName(userName);
        user.setUserDescription(userDescription);
        user.setUserTypes(new ArrayList<>(userTypes));
        user.setUserHash(userHash);
        user.setUserNumber(new UserNumber(UUID.randomUUID()));
        user.setEnabled(true);

        // Validate the user
        user.validate();

        // Publish domain event
        DomainEventPublisher.publish(new UserCreatedEvent(user.getUserNumber().getUuid(), userName));

        return user;
    }

    /**
     * Updates the user with new information
     *
     * @param newUserName The new username
     * @param newUserDescription The new user description
     * @param newUserTypes The new user types
     */
    public void updateUserInfo(String newUserName, String newUserDescription, List<UserType> newUserTypes) {
        this.userName = newUserName;
        this.userDescription = newUserDescription;
        this.userTypes.clear();
        this.userTypes.addAll(newUserTypes);

        // Validate the updated user
        validate();

        // Publish domain event
        DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserNumber().getUuid(), newUserName));
    }

    /**
     * Changes the user's password
     *
     * @param newUserHash The new password hash
     */
    public void changePassword(UserHash newUserHash) {
        this.userHash = newUserHash;

        // Publish domain event
        DomainEventPublisher.publish(new UserUpdatedEvent(this.getUserNumber().getUuid(), this.userName));
    }

    /**
     * Disables the user
     */
    public void disable() {
        this.setEnabled(false);

        // Publish domain event
        DomainEventPublisher.publish(new UserDisabledEvent(this.getUserNumber().getUuid(), this.userName));
    }

    /**
     * Validates that the user meets all business rules
     *
     * @throws UserCreationException if validation fails
     */
    private void validate() {
        if (userName == null || userName.trim().isEmpty()) {
            throw new UserCreationException("Username cannot be empty");
        }

        if (userDescription == null || userDescription.trim().isEmpty()) {
            throw new UserCreationException("User description cannot be empty");
        }

        if (userTypes == null || userTypes.isEmpty()) {
            throw new UserCreationException("User must have at least one user type");
        }

        if (userHash == null) {
            throw new UserCreationException("User hash cannot be null");
        }
    }

    /**
     * Gets an unmodifiable view of the user types
     *
     * @return Unmodifiable list of user types
     */
    public List<UserType> getUserTypes() {
        return Collections.unmodifiableList(userTypes);
    }

    /**
     * Adds a user type to this user
     *
     * @param userType The user type to add
     */
    public void addUserType(UserType userType) {
        if (userType != null && !userTypes.contains(userType)) {
            userTypes.add(userType);
        }
    }

    /**
     * Removes a user type from this user
     *
     * @param userType The user type to remove
     */
    public void removeUserType(UserType userType) {
        if (userType != null && userTypes.size() > 1) {
            userTypes.remove(userType);
        }
    }

    /**
     * Checks if the user has a specific user type
     * 
     * @param userType The user type to check
     * @return true if the user has the specified type
     */
    public boolean hasUserType(UserType userType) {
        return userTypes.contains(userType);
    }
}
