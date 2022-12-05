package com.scprojekt.domain.service;

import com.scprojekt.domain.model.user.User;
import com.scprojekt.domain.model.user.UserRepository;
import com.scprojekt.domain.model.user.UserService;
import com.scprojekt.domain.model.user.UserType;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(long id) {
        return userRepository.findByIdInRepository(id);
    }

    @Override
    public User getUser(UUID userNumber) {
        return userRepository.findByUUID(userNumber);
    }

    @Override
    public UUID createUser(User user) {
        userRepository.createEntity(user);
        return user.getUserNumber().getUuid();
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateEntity(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.removeEntity(user);
    }

    @Override
    public List<User> findAllUsersByType(UserType type) {
        return userRepository.findByType(type);
    }

    @Override
    public List<User> findAllUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findAllUserByDescription(String description) {
        return userRepository.findByDescription(description);
    }
}
