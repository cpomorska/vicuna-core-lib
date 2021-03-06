package com.scprojekt.domain.model.user;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUser(long id);
    User getUser(UUID userNumber);
    UUID createUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    List<User> findAllUsersByType(UserType type);
    List<User> findAllUserByName(String name);
    List<User> findAllUserByDescription(String description);
}
