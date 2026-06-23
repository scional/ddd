package org.nico.ddd.domain.entity.avis;

public record Note(int valeur) {

  public Note {
    if (valeur < 0) {
      throw new IllegalArgumentException("Une note doit être supérieure ou égale à 0");
    }
    if (valeur > 5) {
      throw new IllegalArgumentException("Une note ne peut être supérieure à 5");
    }
  }
}
