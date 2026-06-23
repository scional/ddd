package org.nico.ddd.domain.entity.avis;

import java.util.Optional;
import java.util.UUID;

public record AvisId(String identifiant) {

  public AvisId() {
    this(null);
  }
  
  public AvisId(String identifiant) {
    this.identifiant =
        Optional.ofNullable(identifiant)
            .filter(id -> !id.isBlank())
            .orElseGet(() -> UUID.randomUUID().toString());
  }
}
