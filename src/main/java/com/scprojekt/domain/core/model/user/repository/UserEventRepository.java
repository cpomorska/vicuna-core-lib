package com.scprojekt.domain.core.model.user.repository;

import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserEvent;
import com.scprojekt.domain.core.shared.database.BaseRepository;

import java.util.*;

interface UserEventRepository extends BaseRepository<UserEvent> {
    UserEvent findByUUID(UUID uuid);
    List<User> findAllToRemove();
}