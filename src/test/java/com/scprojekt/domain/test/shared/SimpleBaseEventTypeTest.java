package com.scprojekt.domain.test.shared;

import com.scprojekt.domain.core.shared.misc.BaseEventType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A simple test class to demonstrate testing in the project.
 */
class SimpleBaseEventTypeTest {

    @Test
    void testCreateEventType() {
        // Given
        BaseEventType eventType = BaseEventType.CREATE;
        
        // When
        String value = eventType.getValue();
        
        // Then
        assertThat(value).isEqualTo("Create");
    }
    
    @Test
    void testEqualsEvent() {
        // Given
        BaseEventType eventType = BaseEventType.UPDATE;
        
        // When
        boolean result = eventType.equalsEvent("Update");
        
        // Then
        assertThat(result).isTrue();
    }
    
    @Test
    void testToString() {
        // Given
        BaseEventType eventType = BaseEventType.DELETE;
        
        // When
        String result = eventType.toString();
        
        // Then
        assertThat(result).isEqualTo("Delete");
    }
}