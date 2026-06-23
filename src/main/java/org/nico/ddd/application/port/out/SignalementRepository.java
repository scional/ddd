package org.nico.ddd.application.port.out;

import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.signalement.Signalement;

public interface SignalementRepository {
  void sauver(Signalement signalement);

  long compter(AvisId identifiant);
}
