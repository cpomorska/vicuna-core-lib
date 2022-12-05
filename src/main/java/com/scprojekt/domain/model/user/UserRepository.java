package com.scprojekt.domain.model.user;

import com.scprojekt.domain.shared.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User> {
    User findByUUID(UUID uuid);
    List<User> findByType(UserType type);
    List<User> findByName(String name);
    List<User> findByDescription(String description);
}
