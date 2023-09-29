package com.scprojekt.domain.core.shared.service;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.UserType;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    T getById(long id);
    T getByUuid(UUID userNumber);
    UuidResponse create(T type);
    void update(T type);
    void remove(T type);
    List<T> findAllByType(List<UserType> typeList);
    List<T> findAllByName(String name);
    List<T> findAllByDescription(String description);
}
