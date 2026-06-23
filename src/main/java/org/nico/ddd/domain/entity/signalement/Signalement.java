package org.nico.ddd.domain.entity.signalement;

import java.util.Optional;
import lombok.Builder;
import org.nico.ddd.domain.entity.avis.AvisId;

public record Signalement(SignalementId identifiant, AvisId identifiantAvis) {

  @Builder
  public Signalement(SignalementId identifiant, AvisId identifiantAvis) {
    if (estAvisInexistant(identifiantAvis)) {
      throw new IllegalArgumentException("Un signalement ne peut exister sans un avis");
    }
    this.identifiant = Optional.ofNullable(identifiant).orElseGet(SignalementId::new);
    this.identifiantAvis = identifiantAvis;
  }

  private boolean estAvisInexistant(AvisId identifiant) {
    return identifiant == null;
  }
}
