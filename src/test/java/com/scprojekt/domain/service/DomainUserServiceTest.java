package com.scprojekt.domain.service;

import com.scprojekt.domain.model.user.UserNumber;
import com.scprojekt.domain.model.user.UserRepository;
import com.scprojekt.domain.model.user.User;
import com.scprojekt.domain.model.user.UserType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainUserServiceTest {

    private DomainUserService domainUserService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        domainUserService = new DomainUserService(userRepository);
    }

    @Test
    public void getUserById() {
        when(userRepository.findByIdInRepository(1)).thenReturn(createTestUser());
        User result = domainUserService.getUser(1);
        assertEquals(1, result.getUserId());
    }

    @Test
    public void getUserByUUID() {
        UUID testUuid = UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f");
        when(userRepository.findByUUID(testUuid)).thenReturn(createTestUser());
        User result = domainUserService.getUser(testUuid);
        assertEquals(testUuid, result.getUserNumber().getUuid());
    }

    @Test
    public void createUser() {
        UUID testUuid = UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f");
        User testUser = createTestUser();
        UUID result = domainUserService.createUser(testUser);
        assertNotNull("Result should not be null", result);
        assertEquals(testUuid,result);
    }

    @Test
    public void updateUser() {
        UUID orgUuid = UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f");
        UUID testUuid = UUID.fromString("35fa10da-594a-4601-a7b7-0a707a3c1ce7");
        User testUser = createTestUser();
        when(userRepository.findByUUID(any())).thenReturn(testUser);

        assertEquals(orgUuid,testUser.getUserNumber().getUuid());
        testUser.setUserNumber(new UserNumber(testUuid));

        domainUserService.updateUser(testUser);
        User result = domainUserService.getUser(testUuid);
        assertNotNull("Result should not be null", result);
        assertEquals(testUuid,result.getUserNumber().getUuid());
    }

    @Test
    public void removeUser() {
        UUID testUuid = UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f");
        User testUser = createTestUser();
        when(userRepository.findByUUID(any())).thenReturn(null);

        domainUserService.removeUser(testUser);
        User result = domainUserService.getUser(testUuid);
        assertNull("Result should be null", result);
    }

    @Test
    public void findAllUsersByType() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        UserType expectedType = userList.get(0).getUserType().get(0);
        when(userRepository.findByType(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllUsersByType(expectedType);
        assertNotNull(result.get(0));
        assertEquals(expectedType,result.get(0).getUserType().get(0));
    }

    @Test
    public void findAllUserByName() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        when(userRepository.findByName(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllUserByName("Testuser");
        assertNotNull(result.get(0));
        assertEquals("Testuser",result.get(0).getUserName());
    }

    @Test
    public void findAllUserByDescription() {
        List<User> userList = new ArrayList<>();
        userList.add(createTestUser());
        when(userRepository.findByDescription(any())).thenReturn(userList);

        List<User> result = domainUserService.findAllUserByDescription("Testuser");
        assertNotNull(result.get(0));
        assertEquals("Testuser",result.get(0).getUserDescription());
    }

    private User createTestUser(){
        User user = new User();
        UserType userType = new UserType();
        List<UserType> userTypeList = new ArrayList<>();

        userType.setUserTypeId(1);
        userType.setUserRoleType("testrole");
        userType.setUserTypeDescription("Testuser");
        userTypeList.add(userType);

        user.setUserId(1);
        user.setUserName("Testuser");
        user.setUserDescription("Testuser");
        user.setUserNumber(new UserNumber(UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f")));
        user.setUserType(userTypeList);

        return user;
    }
}