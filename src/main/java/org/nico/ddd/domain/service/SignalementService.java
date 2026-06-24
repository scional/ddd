package org.nico.ddd.domain.service;

import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.signalement.Signalement;
import org.nico.ddd.domain.entity.signalement.SignalementId;
import org.nico.ddd.domain.port.in.PourSignalerAvis;
import org.nico.ddd.domain.port.out.AvisRepository;
import org.nico.ddd.domain.port.out.SignalementRepository;

public record SignalementService(
    SignalementRepository signalementRepository, AvisRepository avisRepository)
    implements PourSignalerAvis {

  public Signalement signaler(Avis avis) {
    long nombreDeSignalements = signalementRepository.compter(avis.getIdentifiant());
    if (nombreDeSignalements >= 10L) {
      avis = avis.cacher();
    }
    avisRepository.sauver(avis);
    Signalement signalement = new Signalement(new SignalementId(), avis.getIdentifiant());
    signalementRepository.sauver(signalement);
    return signalement;
  }
}
