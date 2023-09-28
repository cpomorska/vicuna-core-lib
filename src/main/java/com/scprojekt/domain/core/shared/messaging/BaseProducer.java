package com.scprojekt.domain.core.shared.messaging;

import com.scprojekt.domain.core.model.user.dto.UuidResponse;

public interface BaseProducer<T> {
   UuidResponse produceEvent(T eventType);
   Boolean storeEvent(T eventType);
   Boolean storeErrorEvent(T eventType);
}