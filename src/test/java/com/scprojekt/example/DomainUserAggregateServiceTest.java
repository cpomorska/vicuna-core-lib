package com.scprojekt.example;

import com.scprojekt.domain.core.model.user.aggregate.UserAggregate;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DomainUserAggregateServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DomainUserAggregateService domainUserAggregateService;

    @Test
    void testAuthenticateUser_Success() {
        // Prepare test data
        String username = "testuser";
        String password = "password";

        UserType userType = new UserType();
        userType.setUserTypeId(1L);
        userType.setUserRoleType("USER");
        userType.setUserTypeDescription("Regular User");

        UserAggregate mockUser = UserAggregate.createUser("testuser", "Test User", List.of(userType), password);

        // Configure mock behavior
        when(userRepository.findByName(anyString())).thenReturn(List.of(mockUser.getUser()));

        // Execute method under test
        boolean result = domainUserAggregateService.authenticateUser(username, password);

        // Verify the outcome
        assertTrue(result);
    }

    @Test
    void testAuthenticateUser_Failure() {
        // Prepare test data
        String username = "testuser";
        String password = "wrongpassword";

        // Configure mock behavior
        when(userRepository.findByName(anyString())).thenReturn(new ArrayList<>());

        // Execute method under test
        boolean result = domainUserAggregateService.authenticateUser(username, password);

        // Verify the outcome
        assertFalse(result);
    }
}