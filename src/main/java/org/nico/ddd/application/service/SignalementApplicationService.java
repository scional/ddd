package org.nico.ddd.application.service;

import org.nico.ddd.application.port.in.PourSignalerAvis;
import org.nico.ddd.application.port.out.AvisRepository;
import org.nico.ddd.application.port.out.SignalementRepository;
import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.signalement.Signalement;

public record SignalementApplicationService(
    PourSignalerAvis signalementService,
    AvisRepository avisRepository,
    SignalementRepository signalementRepository) {

  public Signalement signaler(AvisId identifiantAvis) {
    Avis avis = avisRepository.rechercher(identifiantAvis).orElseThrow();
    return signalementService.signaler(avis);
  }

  public void supprimer(String avisId) {
    signalementRepository
        .rechercherParIdentifiantAvis(avisId)
        .ifPresent(signalementRepository::supprimer);
  }
}
