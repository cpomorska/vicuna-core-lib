package com.scprojekt.domain.core.shared.messaging;

import java.util.UUID;

public interface BaseConsumer<T> {
    UUID receiveEvent(T eventType);
}