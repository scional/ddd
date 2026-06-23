package org.nico.ddd.infrastructure;

import org.nico.ddd.application.port.in.PourSignalerAvis;
import org.nico.ddd.application.port.out.AvisRepository;
import org.nico.ddd.application.port.out.SignalementRepository;
import org.nico.ddd.application.service.SignalementApplicationService;
import org.nico.ddd.domain.service.SignalementService;
import org.nico.ddd.infrastructure.avis.AvisRepositoryFake;
import org.nico.ddd.infrastructure.signalement.SignalementRepositoryFake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {

  @Bean
  PourSignalerAvis signalementService(
      SignalementRepository signalementRepository, AvisRepository avisRepository) {
    return new SignalementService(signalementRepository, avisRepository);
  }

  @Bean
  SignalementRepository signalementRepository() {
    return new SignalementRepositoryFake();
  }

  @Bean
  AvisRepository avisRepository() {
    return new AvisRepositoryFake();
  }

  @Bean
  SignalementApplicationService signalementApplicationService(
      PourSignalerAvis signalementService, AvisRepository avisRepository) {
    return new SignalementApplicationService(signalementService, avisRepository);
  }
}
