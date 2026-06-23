package org.nico.ddd.domain.entity.avis;

import static java.util.Objects.requireNonNull;

public record Message(String texte) {

  public Message {
    if (requireNonNull(texte).isBlank()) {
      throw new IllegalArgumentException("Le texte du message ne peut être vide");
    }
  }
}
