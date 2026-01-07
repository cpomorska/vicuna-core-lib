package com.scprojekt.example;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserHash;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import com.scprojekt.domain.core.model.user.service.UserService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the UserService that provides domain-specific user operations.
 * This service implements the business logic for user management.
 */
@Log
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(long id) {
        return userRepository.findByIdInRepository(id);
    }

    @Override
    public User getByUuid(UUID uuid) {
        return userRepository.findByUUID(uuid);
    }

    @Override
    public UuidResponse create(User user) {
        userRepository.createEntity(user);
        return new UuidResponse(user.getUserNumber().getUuid());
    }

    @Override
    public void update(User user) {
        userRepository.updateEntity(user);
    }

    @Override
    public void remove(User user) {
        userRepository.removeEntity(user);
    }

    @Override
    public List<User> findAllByType(List<UserType> types) {
        return userRepository.findByType(types);
    }

    @Override
    public List<User> findAllByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findAllByDescription(String description) {
        return userRepository.findByDescription(description);
    }

    @Override
    public UuidResponse registerUser(String username, String description, String password, List<UserType> userTypes) {
        // Create user hash from password
        UserHash userHash = UserHash.fromPassword(password);

        // Create the user using the factory method
        User user = User.createUser(username, description, userTypes, userHash);

        // Persist the user
        userRepository.createEntity(user);

        return new UuidResponse(user.getUserNumber().getUuid());
    }

    @Override
    public Optional<User> authenticateUser(String username, String password) {
        // Find users by name
        List<User> users = userRepository.findByName(username);

        // Find the first user that matches the password
        return users.stream()
                .filter(user -> user.getUserHash().validatePassword(password))
                .findFirst();
    }

    @Override
    public boolean changePassword(UUID userId, String currentPassword, String newPassword) {
        User user = userRepository.findByUUID(userId);
        if (user == null) {
            return false;
        }

        // Verify current password
        if (!user.getUserHash().validatePassword(currentPassword)) {
            return false;
        }

        // Change password
        UserHash newUserHash = UserHash.fromPassword(newPassword);
        user.changePassword(newUserHash);

        // Update user
        userRepository.updateEntity(user);

        return true;
    }

    @Override
    public void disableUser(UUID userId) {
        User user = userRepository.findByUUID(userId);
        if (user != null) {
            user.disable();
            userRepository.updateEntity(user);
        }
    }

    @Override
    public void enableUser(UUID userId) {
        User user = userRepository.findByUUID(userId);
        if (user != null) {
            user.enable();
            userRepository.updateEntity(user);
        }
    }

    @Override
    public void addUserType(UUID userId, UserType userType) {
        User user = userRepository.findByUUID(userId);
        if (user != null) {
            user.addUserType(userType);
            userRepository.updateEntity(user);
        }
    }

    @Override
    public void removeUserType(UUID userId, UserType userType) {
        User user = userRepository.findByUUID(userId);
        if (user != null) {
            user.removeUserType(userType);
            userRepository.updateEntity(user);
        }
    }

    @Override
    public List<User> findUsersByUsernamePattern(String usernamePattern) {
        // Get all users and filter by pattern
        // In a real implementation, this would be done at the database level
        List<User> allUsers = userRepository.findAllInRepository();

        return allUsers.stream()
                .filter(user -> user.getUserName().contains(usernamePattern))
                .toList();
    }
}
