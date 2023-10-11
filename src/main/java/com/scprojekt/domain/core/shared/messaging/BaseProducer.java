package com.scprojekt.domain.core.shared.messaging;

import java.util.UUID;

public interface BaseProducer<T> {
   UUID produceEvent(T eventType);
   Boolean storeEvent(T eventType);
   Boolean storeErrorEvent(T eventType);
}