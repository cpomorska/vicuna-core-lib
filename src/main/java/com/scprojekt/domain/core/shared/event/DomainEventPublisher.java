package com.scprojekt.domain.core.shared.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Publisher for domain events using the observer pattern.
 * This class allows subscribers to register for specific event types
 * and publishes events to all interested subscribers.
 */
public class DomainEventPublisher {
    private static final ThreadLocal<DomainEventPublisher> instance = ThreadLocal.withInitial(DomainEventPublisher::new);
    
    private final List<DomainEventSubscriber<?>> subscribers;
    private boolean publishing;
    
    private DomainEventPublisher() {
        this.subscribers = new CopyOnWriteArrayList<>();
        this.publishing = false;
    }
    
    /**
     * Gets the thread-local instance of the publisher
     * 
     * @return The domain event publisher instance
     */
    public static DomainEventPublisher instance() {
        return instance.get();
    }
    
    /**
     * Resets the publisher, clearing all subscribers
     */
    public static void reset() {
        instance.remove();
    }
    
    /**
     * Publishes an event to all interested subscribers
     * 
     * @param event The event to publish
     * @param <T> The type of the event
     */
    public static <T extends DomainEvent> void publish(T event) {
        if (event != null) {
            instance().doPublish(event);
        }
    }
    
    /**
     * Subscribes to events of a specific type
     * 
     * @param eventType The class of events to subscribe to
     * @param handler The handler function for the events
     * @param <T> The type of the event
     */
    public static <T extends DomainEvent> void subscribe(Class<T> eventType, Consumer<T> handler) {
        instance().doSubscribe(new DomainEventSubscriber<>(eventType, handler));
    }
    
    private <T extends DomainEvent> void doPublish(T event) {
        if (!publishing) {
            try {
                publishing = true;
                
                Class<?> eventClass = event.getClass();
                
                subscribers.stream()
                    .filter(subscriber -> subscriber.subscribesTo(eventClass))
                    .forEach(subscriber -> subscriber.handleEvent(event));
                
            } finally {
                publishing = false;
            }
        }
    }
    
    private void doSubscribe(DomainEventSubscriber<?> subscriber) {
        if (!publishing) {
            subscribers.add(subscriber);
        }
    }
    
    /**
     * Inner class representing a subscriber to domain events
     * 
     * @param <T> The type of events this subscriber handles
     */
    private static class DomainEventSubscriber<T extends DomainEvent> {
        private final Class<T> eventType;
        private final Consumer<T> handler;
        
        public DomainEventSubscriber(Class<T> eventType, Consumer<T> handler) {
            this.eventType = eventType;
            this.handler = handler;
        }
        
        public boolean subscribesTo(Class<?> eventClass) {
            return eventType.isAssignableFrom(eventClass);
        }
        
        @SuppressWarnings("unchecked")
        public void handleEvent(DomainEvent event) {
            if (subscribesTo(event.getClass())) {
                handler.accept((T) event);
            }
        }
    }
}