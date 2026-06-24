package org.nico.ddd.shared.publishsubscribe;

public interface EventSubscriber {
  <E extends Event> void subscribe(Class<E> eventType, EventHandler<E> consumer);
}
