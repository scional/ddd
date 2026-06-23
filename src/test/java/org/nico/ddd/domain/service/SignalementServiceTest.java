package org.nico.ddd.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.nico.ddd.application.port.out.AvisRepository;
import org.nico.ddd.application.port.out.SignalementRepository;
import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.avis.AvisRepositoryFake;
import org.nico.ddd.domain.entity.avis.Message;
import org.nico.ddd.domain.entity.avis.Note;
import org.nico.ddd.domain.entity.signalement.SignalementRepositoryFake;

class SignalementServiceTest {

  private static final int NOMBRE_MAXIMUM_DE_SIGNALEMENTS = 10;

  @SuppressWarnings("java:S8692")
  private static final LocalDateTime NOW = LocalDateTime.now();

  @Test
  void testUnAvisNonCacheQuiRecoitPlusDeDixSignalementsDevientCache() {
    // GIVEN
    Avis avisNonCache =
        Avis.builder()
            .identifiant(new AvisId())
            .note(new Note(1))
            .message(new Message("Mon avis"))
            .datePublication(NOW)
            .build();
    AvisRepository avisRepository = new AvisRepositoryFake();
    SignalementRepository signalementRepository = new SignalementRepositoryFake();
    avisRepository.sauver(avisNonCache);
    SignalementService signalementService =
        new SignalementService(signalementRepository, avisRepository);

    // WHEN
    for (int i = 0; i <= NOMBRE_MAXIMUM_DE_SIGNALEMENTS; i++) {
      signalementService.signaler(avisNonCache);
    }

    // THEN
    assertThat(avisRepository.rechercher(avisNonCache.getIdentifiant()))
        .map(Avis::isCache)
        .isPresent()
        .get()
        .isEqualTo(true);
  }
}
