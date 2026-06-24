package org.nico.ddd.domain.entity.signalement;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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


  @Override
  public Optional<Signalement> rechercherParIdentifiantAvis(String avisId) {
    return tousLesSignalements.values().stream()
            .filter(signalement -> Objects.equals(signalement.identifiantAvis().identifiant(), avisId))
            .findAny();
  }

  @Override
  public void supprimer(Signalement signalement) {
    this.tousLesSignalements.remove(signalement.identifiant());
  }
}
