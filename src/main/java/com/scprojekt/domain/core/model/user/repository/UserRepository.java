package com.scprojekt.domain.core.model.user.repository;

import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User> {
    User findByUUID(UUID uuid);
    List<User> findByType(List<UserType> type);
    List<User> findByName(String name);
    List<User> findByDescription(String description);
}
