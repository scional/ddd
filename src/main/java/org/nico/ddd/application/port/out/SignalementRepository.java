package org.nico.ddd.application.port.out;

import java.util.Optional;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.signalement.Signalement;

public interface SignalementRepository {
  void sauver(Signalement signalement);

  long compter(AvisId identifiant);

  Optional<Signalement> rechercherParIdentifiantAvis(String avisId);

  void supprimer(Signalement signalement);
}
