package org.nico.ddd.shared.publishsubscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryEventDispatcher implements EventDispatcher, EventSubscriber {

  private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers =
      new HashMap<>();

  public <E extends Event> void subscribe(Class<E> eventType, EventHandler<E> consumer) {
    log.trace("Subscribing to {} by event handler {}", eventType, consumer);
    handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(consumer);
  }

  @SuppressWarnings("unchecked")
  public <E extends Event> void dispatch(E event) {
    log.info("Dispatching the event {}", event);
    if (handlers.containsKey(event.getClass())) {
      for (EventHandler<? extends Event> handler : handlers.get(event.getClass())) {
        ((EventHandler<E>) handler).handle(event);
      }
    } else {
      log.info("No event handlers for the event {}", event.getClass());
    }
  }
}

/*
public class StockMoveFailedIntegrationEventHandler implements EventHandler<StockMoveFailedIntegrationEvent> {
	private final NotifyUserStockMoveFailedService notifyUserStockMoveFailedService;

	public StockMoveFailedIntegrationEventHandler(EventSubscriber eventSubscriber, NotifyUserStockMoveFailedService notifyUserStockMoveFailedService) {
		this.notifyUserStockMoveFailedService = notifyUserStockMoveFailedService;
		eventSubscriber.subscribe(StockMoveFailedIntegrationEvent.class, this);
	}

	@Override
	public void handle(StockMoveFailedIntegrationEvent event) {
		NotifyUserStockMoveFailedCommand command = new NotifyUserStockMoveFailedCommand(
				event.userId(),
				event.fromAddress(),
				event.quantityToMove(),
				event.reason()
		);
		notifyUserStockMoveFailedService.execute(command);
	}
}

 */
