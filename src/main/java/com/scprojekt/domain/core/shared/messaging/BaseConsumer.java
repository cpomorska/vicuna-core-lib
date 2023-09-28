package com.scprojekt.domain.core.shared.messaging;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;

public interface BaseConsumer<T> {
    UuidResponse receiveEvent(T eventType);
}