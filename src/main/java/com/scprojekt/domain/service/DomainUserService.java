package com.scprojekt.domain.service;

import com.scprojekt.domain.interfaces.UserRepository;
import com.scprojekt.domain.interfaces.UserService;
import com.scprojekt.domain.entities.User;
import com.scprojekt.domain.entities.UserType;

import java.util.List;
import java.util.UUID;

public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    public DomainUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUser(UUID userNumber) {
        return userRepository.findByUUID(userNumber);
    }

    @Override
    public UUID createUser(User user) {
        userRepository.createEntity(user);
        return user.getUserNumber();
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
        return userRepository.findByDesription(description);
    }
}
