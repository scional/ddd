package org.nico.ddd.domain.entity.avis;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.nico.ddd.application.port.out.AvisRepository;

public class AvisRepositoryFake implements AvisRepository {

  private final Map<AvisId, Avis> tousLesAvis;

  public AvisRepositoryFake() {
    this.tousLesAvis = new HashMap<>();
  }

  @Override
  public void sauver(Avis avis) {
    tousLesAvis.put(avis.getIdentifiant(), avis);
  }

  @Override
  public Optional<Avis> rechercher(AvisId identifiant) {
    return Optional.ofNullable(tousLesAvis.get(identifiant));
  }

  @Override
  public void supprimer(AvisId identifiant) {
    tousLesAvis.remove(identifiant);
  }
}
