package org.nico.ddd.domain.entity.signalement;

import java.util.Optional;
import java.util.UUID;

public record SignalementId(String identifiant) {

  public SignalementId() {
    this(null);
  }

  public SignalementId(String identifiant) {
    this.identifiant =
        Optional.ofNullable(identifiant)
            .filter(id -> !id.isBlank())
            .orElseGet(() -> UUID.randomUUID().toString());
  }
}
