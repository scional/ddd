package org.nico.ddd.shared.publishsubscribe;

public interface EventHandler<T extends Event> {
  void handle(T event);
}
