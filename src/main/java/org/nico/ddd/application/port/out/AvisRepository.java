package org.nico.ddd.application.port.out;

import java.util.Optional;
import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.avis.AvisId;

public interface AvisRepository {

  void sauver(Avis avis);

  Optional<Avis> rechercher(AvisId identifiant);

  void supprimer(AvisId identifiant);
}
