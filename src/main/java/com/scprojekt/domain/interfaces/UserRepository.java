package com.scprojekt.domain.interfaces;

import com.scprojekt.domain.model.User;
import com.scprojekt.domain.model.UserType;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User> {
    User findByUUID(UUID uuid);
    List<User> findByType(UserType type);
    List<User> findByName(String name);
    List<User> findByDesription(String description);
}
