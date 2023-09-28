package com.scprojekt.domain.test.shared;

import com.scprojekt.domain.core.shared.misc.BaseEventType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class BaseEventTypeTest {

    @Test
    void equalsEvent() {
        Map<String, BaseEventType> enumMap = new HashMap<>();
        EnumSet<BaseEventType> enumList = EnumSet.allOf(BaseEventType.class);
        enumList.forEach(e -> enumMap.put(e.getValue(),e));
        List<String> eventlist = enumList.stream().map(e -> e.getValue()).toList();

        enumList.forEach(e -> assertThat(e.equalsEvent(String.valueOf(enumMap.get(e.getValue())))).isTrue());
    }

    @Test
    void getValue() {
        EnumSet<BaseEventType> enumList = EnumSet.allOf(BaseEventType.class);
        List<String> eventlist = enumList.stream().map(e -> e.getValue()).toList();

        enumList.forEach(e -> assertThat(eventlist).contains(e.getValue()));
    }

    @Test
    void testToString() {
        EnumSet<BaseEventType> enumList = EnumSet.allOf(BaseEventType.class);
        List<String> eventlist = enumList.stream().map(e -> e.getValue()).toList();

        enumList.forEach(e -> assertThat(eventlist.contains(e.toString())));
    }

    @Test
    void values() {
        EnumSet<BaseEventType> enumList = EnumSet.allOf(BaseEventType.class);
        assertThat(BaseEventType.values()).hasSize(enumList.size());
    }

    @Test
    void valueOf() {
        Map<String, BaseEventType> enumMap = new HashMap<>();
        EnumSet<BaseEventType> enumList = EnumSet.allOf(BaseEventType.class);
        enumList.forEach(e -> enumMap.put(e.getValue(),e));
        List<String> eventlist = enumList.stream().map(e -> e.getValue()).toList();

        eventlist.forEach(e -> assertThat(BaseEventType.valueOf(e.toUpperCase())).isIn(enumList));
    }
}