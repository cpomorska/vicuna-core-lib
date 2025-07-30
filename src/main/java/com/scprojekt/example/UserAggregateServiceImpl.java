package com.scprojekt.example;

import com.scprojekt.domain.core.model.user.aggregate.UserAggregate;
import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import com.scprojekt.domain.core.model.user.service.UserAggregateService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the UserAggregateService that provides domain-specific user operations.
 * This service implements the business logic for user management using the UserAggregate.
 */
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserAggregateServiceImpl implements UserAggregateService {

    private final UserRepository userRepository;

    @Override
    public UserAggregate getById(long id) {
        User user = userRepository.findByIdInRepository(id);
        return user != null ? UserAggregate.fromUser(user) : null;
    }

    @Override
    public UserAggregate getByUuid(UUID uuid) {
        User user = userRepository.findByUUID(uuid);
        return user != null ? UserAggregate.fromUser(user) : null;
    }

    @Override
    public UuidResponse create(UserAggregate userAggregate) {
        User user = userAggregate.getUser();
        userRepository.createEntity(user);
        return new UuidResponse(user.getUserNumber().getUuid());
    }

    @Override
    public void update(UserAggregate userAggregate) {
        userRepository.updateEntity(userAggregate.getUser());
    }

    @Override
    public void remove(UserAggregate userAggregate) {
        userRepository.removeEntity(userAggregate.getUser());
    }

    @Override
    public List<UserAggregate> findAllByType(List<UserType> typeList) {
        return userRepository.findByType(typeList).stream()
                .map(UserAggregate::fromUser)
                .toList();
    }

    @Override
    public List<UserAggregate> findAllByName(String name) {
        return userRepository.findByName(name).stream()
                .map(UserAggregate::fromUser)
                .toList();
    }

    @Override
    public List<UserAggregate> findAllByDescription(String description) {
        return userRepository.findByDescription(description).stream()
                .map(UserAggregate::fromUser)
                .toList();
    }

    @Override
    public UuidResponse registerUser(String username, String description, String password, List<UserType> userTypes) {
        // Create the user aggregate
        UserAggregate userAggregate = UserAggregate.createUser(username, description, userTypes, password);

        // Persist the user
        userRepository.createEntity(userAggregate.getUser());

        return new UuidResponse(userAggregate.getUserId());
    }

    @Override
    public Optional<UserAggregate> authenticateUser(String username, String password) {
        // Find users by name
        List<User> users = userRepository.findByName(username);

        // Find the first user that matches the password
        return users.stream()
                .map(UserAggregate::fromUser)
                .filter(userAggregate -> userAggregate.validatePassword(password))
                .findFirst();
    }

    @Override
    public boolean changePassword(UUID userId, String currentPassword, String newPassword) {
        UserAggregate userAggregate = getByUuid(userId);
        if (userAggregate == null) {
            return false;
        }

        // Change password
        boolean changed = userAggregate.changePassword(currentPassword, newPassword);
        if (changed) {
            // Update user
            userRepository.updateEntity(userAggregate.getUser());
        }

        return changed;
    }

    @Override
    public void disableUser(UUID userId) {
        UserAggregate userAggregate = getByUuid(userId);
        if (userAggregate != null) {
            userAggregate.disable();
            userRepository.updateEntity(userAggregate.getUser());
        }
    }

    @Override
    public void enableUser(UUID userId) {
        UserAggregate userAggregate = getByUuid(userId);
        if (userAggregate != null) {
            userAggregate.enable();
            userRepository.updateEntity(userAggregate.getUser());
        }
    }

    @Override
    public void addUserType(UUID userId, UserType userType) {
        UserAggregate userAggregate = getByUuid(userId);
        if (userAggregate != null) {
            userAggregate.addUserType(userType);
            userRepository.updateEntity(userAggregate.getUser());
        }
    }

    @Override
    public void removeUserType(UUID userId, UserType userType) {
        UserAggregate userAggregate = getByUuid(userId);
        if (userAggregate != null) {
            userAggregate.removeUserType(userType);
            userRepository.updateEntity(userAggregate.getUser());
        }
    }

    @Override
    public List<UserAggregate> findUsersByUsernamePattern(String usernamePattern) {
        // Get all users and filter by pattern
        // In a real implementation, this would be done at the database level
        List<User> allUsers = userRepository.findAllInRepository();

        return allUsers.stream()
                .filter(user -> user.getUserName().contains(usernamePattern))
                .map(UserAggregate::fromUser)
                .toList();
    }
}
