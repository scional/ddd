package org.nico.ddd.domain.entity.signalement;

import java.util.HashMap;
import java.util.Map;
import org.nico.ddd.application.port.out.SignalementRepository;
import org.nico.ddd.domain.entity.avis.AvisId;

public class SignalementRepositoryFake implements SignalementRepository {

  private final Map<SignalementId, Signalement> tousLesSignalements;

  public SignalementRepositoryFake() {
    this.tousLesSignalements = new HashMap<>();
  }

  @Override
  public void sauver(Signalement signalement) {
    tousLesSignalements.put(signalement.identifiant(), signalement);
  }

  @Override
  public long compter(AvisId identifiant) {
    return tousLesSignalements.values().stream()
        .map(Signalement::identifiantAvis)
        .filter(identifiant::equals)
        .count();
  }
}
