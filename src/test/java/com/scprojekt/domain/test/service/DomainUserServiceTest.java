package com.scprojekt.domain.test.service;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;
import com.scprojekt.domain.core.model.user.entity.UserNumber;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.example.DomainUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DomainUserServiceTest {

    private static final String TESTUSER = "Testuser";
    private static final String TESTROLE = "Testrole";
    private  static final String UUID_USER_1 = "586c2084-d545-4fac-b7d3-2319382df14f";
    private static final String UUID_USER_2 = "35fa10da-594a-4601-a7b7-0a707a3c1ce7";

    private DomainUserService domainUserService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        domainUserService = new DomainUserService(userRepository);
    }

    @Test
    void getUserById() {
        when(userRepository.findByIdInRepository(1)).thenReturn(createTestUser());
        User result = domainUserService.getById(1);
        assertThat(result.getUserId()).isEqualTo(1);
    }

    @Test
    void getUserByUUID() {
        UUID testUuid = java.util.UUID.fromString(UUID_USER_1);
        when(userRepository.findByUUID(testUuid)).thenReturn(createTestUser());
        User result = domainUserService.getByUuid(testUuid);
        assertThat(result.getUserNumber().getUuid()).isEqualTo(testUuid);
    }

    @Test
    void createUser() {
        UUID testUuid = java.util.UUID.fromString(UUID_USER_1);
        User testUser = createTestUser();
        UuidResponse result = domainUserService.create(testUser);
        assertThat(result.getUuid()).isNotNull().isEqualTo(testUuid);
    }

    @Test
    void updateUser() {
        UUID orgUuid = java.util.UUID.fromString(UUID_USER_1);
        UUID testUuid = java.util.UUID.fromString(UUID_USER_2);
        User testUser = createTestUser();
        when(userRepository.findByUUID(any())).thenReturn(testUser);

        assertThat(testUser.getUserNumber().getUuid()).isEqualTo(orgUuid);
        testUser.setUserNumber(new UserNumber(testUuid));

        domainUserService.update(testUser);
        User result = domainUserService.getByUuid(testUuid);
        assertThat(result.getUserNumber().getUuid()).isNotNull().isEqualTo(testUuid);
    }

    @Test
    void removeUser() {
        UUID testUuid = java.util.UUID.fromString(UUID_USER_1);
        User testUser = createTestUser();
        when(userRepository.findByUUID(any())).thenReturn(null);

        domainUserService.remove(testUser);
        User result = domainUserService.getByUuid(testUuid);
        assertThat(result).isNull();
    }

    @Test
    void findAllUsersByType() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        UserType expectedType = userList.get(0).getUserTypes().get(0);
        when(userRepository.findByType(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllByType(List.of(expectedType));
        assertThat(result.get(0)).isNotNull();
        assertThat(result.get(0).getUserTypes().get(0)).isEqualTo(expectedType);
    }

    @Test
    void findAllUserByName() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        when(userRepository.findByName(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllByName(TESTUSER);
        assertThat(result.get(0)).isNotNull();
        assertThat(result.get(0).getUserName()).isEqualTo(TESTUSER);
    }

    @Test
    void findAllUserByDescription() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        when(userRepository.findByDescription(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllByDescription(TESTUSER);
        assertThat(result.get(0)).isNotNull();
        assertThat(result.get(0).getUserDescription()).isEqualTo(TESTUSER);
    }

    private User createTestUser(){
        User user = new User();
        UserType userType = new UserType();
        List<UserType> userTypeList = new ArrayList<>();

        userType.setUserTypeId(1);
        userType.setUserRoleType(TESTROLE);
        userType.setUserTypeDescription(TESTUSER);
        userTypeList.add(userType);

        user.setUserId(1);
        user.setUserName(TESTUSER);
        user.setUserDescription(TESTUSER);
        user.setUserNumber(new UserNumber(java.util.UUID.fromString(UUID_USER_1)));
        user.setUserTypes(userTypeList);

        return user;
    }
}