package com.scprojekt.example;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import com.scprojekt.domain.core.model.user.service.UserService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

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
}
