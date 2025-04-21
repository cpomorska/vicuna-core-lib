package com.scprojekt.domain.test.aggregate;

import com.scprojekt.domain.core.model.user.aggregate.UserAggregate;
import com.scprojekt.domain.core.model.user.entity.User;
import com.scprojekt.domain.core.model.user.entity.UserType;
import com.scprojekt.domain.core.model.user.exception.UserCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the UserAggregate class.
 */
class UserAggregateTest {

    private UserType adminType;
    private UserType userType;
    private List<UserType> userTypes;

    @BeforeEach
    void setUp() {
        // Create user types
        adminType = new UserType();
        adminType.setUserTypeId(1L);
        adminType.setUserRoleType("ADMIN");
        adminType.setUserTypeDescription("Administrator");

        userType = new UserType();
        userType.setUserTypeId(2L);
        userType.setUserRoleType("USER");
        userType.setUserTypeDescription("Regular User");

        userTypes = new ArrayList<>();
        userTypes.add(adminType);
    }

    @Test
    void testCreateUser() {
        // Given
        String username = "testuser";
        String description = "Test User";
        String password = "password123";

        // When
        UserAggregate userAggregate = UserAggregate.createUser(username, description, userTypes, password);

        // Then
        assertThat(userAggregate).isNotNull();
        assertThat(userAggregate.getUserName()).isEqualTo(username);
        assertThat(userAggregate.getUserDescription()).isEqualTo(description);
        assertThat(userAggregate.getUserTypes()).hasSize(1);
        assertThat(userAggregate.getUserTypes().get(0)).isEqualTo(adminType);
        assertThat(userAggregate.isEnabled()).isTrue();
        assertThat(userAggregate.getUserId()).isNotNull();
        assertThat(userAggregate.validatePassword(password)).isTrue();
    }

    @Test
    void testCreateUserWithInvalidData() {
        // Test empty username
        final String emptyUsername = "";
        final String validDescription = "Test User";
        final String validPassword = "password123";

        // When/Then
        assertThatThrownBy(() -> UserAggregate.createUser(emptyUsername, validDescription, userTypes, validPassword))
                .isInstanceOf(UserCreationException.class)
                .hasMessageContaining("Username cannot be empty");

        // Test empty description
        final String validUsername = "testuser";
        final String emptyDescription = "";

        // When/Then
        assertThatThrownBy(() -> UserAggregate.createUser(validUsername, emptyDescription, userTypes, validPassword))
                .isInstanceOf(UserCreationException.class)
                .hasMessageContaining("User description cannot be empty");

        // Test empty user types
        final List<UserType> emptyTypes = new ArrayList<>();

        // When/Then
        assertThatThrownBy(() -> UserAggregate.createUser(validUsername, validDescription, emptyTypes, validPassword))
                .isInstanceOf(UserCreationException.class)
                .hasMessageContaining("User must have at least one user type");
    }

    @Test
    void testUpdateUserInfo() {
        // Given
        UserAggregate userAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, "password123");
        String newUsername = "updateduser";
        String newDescription = "Updated User";
        List<UserType> newUserTypes = new ArrayList<>();
        newUserTypes.add(adminType);
        newUserTypes.add(userType);

        // When
        userAggregate.updateUserInfo(newUsername, newDescription, newUserTypes);

        // Then
        assertThat(userAggregate.getUserName()).isEqualTo(newUsername);
        assertThat(userAggregate.getUserDescription()).isEqualTo(newDescription);
        assertThat(userAggregate.getUserTypes()).hasSize(2);
        assertThat(userAggregate.getUserTypes()).contains(adminType, userType);
    }

    @Test
    void testChangePassword() {
        // Given
        String originalPassword = "password123";
        String newPassword = "newpassword456";
        UserAggregate userAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, originalPassword);

        // When
        boolean result = userAggregate.changePassword(originalPassword, newPassword);

        // Then
        assertThat(result).isTrue();
        assertThat(userAggregate.validatePassword(originalPassword)).isFalse();
        assertThat(userAggregate.validatePassword(newPassword)).isTrue();
    }

    @Test
    void testChangePasswordWithIncorrectCurrentPassword() {
        // Given
        String originalPassword = "password123";
        String wrongPassword = "wrongpassword";
        String newPassword = "newpassword456";
        UserAggregate userAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, originalPassword);

        // When
        boolean result = userAggregate.changePassword(wrongPassword, newPassword);

        // Then
        assertThat(result).isFalse();
        assertThat(userAggregate.validatePassword(originalPassword)).isTrue();
        assertThat(userAggregate.validatePassword(newPassword)).isFalse();
    }

    @Test
    void testDisableAndEnableUser() {
        // Given
        UserAggregate userAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, "password123");
        assertThat(userAggregate.isEnabled()).isTrue();

        // When
        userAggregate.disable();

        // Then
        assertThat(userAggregate.isEnabled()).isFalse();

        // When
        userAggregate.enable();

        // Then
        assertThat(userAggregate.isEnabled()).isTrue();
    }

    @Test
    void testAddAndRemoveUserType() {
        // Given
        UserAggregate userAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, "password123");
        assertThat(userAggregate.getUserTypes()).hasSize(1);
        assertThat(userAggregate.hasUserType(adminType)).isTrue();
        assertThat(userAggregate.hasUserType(userType)).isFalse();

        // When
        userAggregate.addUserType(userType);

        // Then
        assertThat(userAggregate.getUserTypes()).hasSize(2);
        assertThat(userAggregate.hasUserType(userType)).isTrue();

        // When
        userAggregate.removeUserType(userType);

        // Then
        assertThat(userAggregate.getUserTypes()).hasSize(1);
        assertThat(userAggregate.hasUserType(userType)).isFalse();
    }

    @Test
    void testFromUser() {
        // Given
        UserAggregate originalAggregate = UserAggregate.createUser("testuser", "Test User", userTypes, "password123");
        User user = originalAggregate.getUser();

        // When
        UserAggregate newAggregate = UserAggregate.fromUser(user);

        // Then
        assertThat(newAggregate).isNotNull();
        assertThat(newAggregate.getUserId()).isEqualTo(originalAggregate.getUserId());
        assertThat(newAggregate.getUserName()).isEqualTo(originalAggregate.getUserName());
        assertThat(newAggregate.getUserDescription()).isEqualTo(originalAggregate.getUserDescription());
        assertThat(newAggregate.getUserTypes()).isEqualTo(originalAggregate.getUserTypes());
        assertThat(newAggregate.isEnabled()).isEqualTo(originalAggregate.isEnabled());
    }
}
