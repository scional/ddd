package org.nico.ddd.moderation.application.event;

import static java.util.Objects.requireNonNull;

import org.nico.ddd.application.service.SignalementApplicationService;
import org.nico.ddd.domain.event.SuppressionAvisEvent;
import org.nico.ddd.shared.publishsubscribe.EventHandler;
import org.nico.ddd.shared.publishsubscribe.EventSubscriber;
import org.springframework.stereotype.Component;

@Component
public class SuppressionAvisEventHandler implements EventHandler<SuppressionAvisEvent> {

  private final SignalementApplicationService signalementService;

  public SuppressionAvisEventHandler(
      SignalementApplicationService signalementService, EventSubscriber eventSubscriber) {
    this.signalementService = requireNonNull(signalementService);
    requireNonNull(eventSubscriber).subscribe(SuppressionAvisEvent.class, this);
  }

  @Override
  public void handle(SuppressionAvisEvent event) {
    // Supprimer signalement
    signalementService.supprimer(event.identifiantAvis().identifiant());
  }
}
