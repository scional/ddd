package org.nico.ddd.domain.entity.avis;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

public class Avis {

  @Getter private final AvisId identifiant;
  @Getter private final Message message;
  @Getter private final Note note;
  @Getter private final LocalDateTime datePublication;

  private Boolean valide;
  @Getter private boolean cache;

  Avis() {
    this(null, new Message("Placeholder"), new Note(0), LocalDateTime.now(ZoneId.systemDefault()));
  }

  @Builder
  public Avis(AvisId identifiant, Message message, Note note, LocalDateTime datePublication) {
    this.identifiant = Optional.ofNullable(identifiant).orElseGet(AvisId::new);
    this.message = requireNonNull(message);
    this.note = requireNonNull(note);
    this.datePublication = requireNonNull(datePublication);
  }

  public Avis valider() {
    Avis avis = new Avis(identifiant, message, note, datePublication);
    if (valide == null) {
      avis.valide = true;
      return avis;
    }
    if (valide) {
      throw new IllegalStateException("L'avis est déjà validé");
    }
    throw new IllegalStateException("L'avis est déjà invalidé");
  }

  public Avis invalider() {
    Avis avis = new Avis(identifiant, message, note, datePublication);
    avis.valide = false;
    return avis;
  }

  public boolean isValide() {
    if (valide == null) {
      return false;
    }
    return valide;
  }

  public Avis cacher() {
    Avis avis = new Avis(identifiant, message, note, datePublication);
    avis.cache = true;
    return avis;
  }
}
