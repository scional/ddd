package org.nico.ddd.application.service;

import static java.util.Objects.requireNonNull;

import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.event.SuppressionAvisEvent;
import org.nico.ddd.domain.port.out.AvisRepository;
import org.nico.ddd.shared.publishsubscribe.EventDispatcher;
import org.springframework.stereotype.Service;

@Service
public class AvisApplicationService {

  private final AvisRepository avisRepository;
  private final EventDispatcher eventDispatcher;

  public AvisApplicationService(AvisRepository avisRepository, EventDispatcher eventDispatcher) {
    this.avisRepository = requireNonNull(avisRepository);
    this.eventDispatcher = requireNonNull(eventDispatcher);
  }

  public void supprimer(AvisId identifiant) {
    avisRepository.supprimer(identifiant);
    publierEvenementSuppression(identifiant);
  }

  private void publierEvenementSuppression(AvisId identifiant) {
    eventDispatcher.dispatch(new SuppressionAvisEvent(identifiant));
  }
}
