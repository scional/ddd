package org.nico.ddd.shared.publishsubscribe;

public interface EventDispatcher {
  <E extends Event> void dispatch(E event);
}
