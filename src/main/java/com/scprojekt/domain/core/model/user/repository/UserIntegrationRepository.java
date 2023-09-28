package com.scprojekt.domain.core.model.user.repository;

import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.util.List;

interface UserIntegrationRepository extends BaseRepository<User> {
    User findByUUID(String uid);
    List<User> findByType(UserType userType);
    List<User> findByName(String userName);
    List<User> findByDescription(String userDescription);
}
