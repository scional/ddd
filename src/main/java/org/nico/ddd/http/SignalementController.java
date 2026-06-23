package org.nico.ddd.http;

import static java.util.Objects.requireNonNull;

import java.net.URI;
import org.nico.ddd.application.service.SignalementApplicationService;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.signalement.Signalement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signalements")
class SignalementController {

  private final SignalementApplicationService signalementApplicationService;

  SignalementController(SignalementApplicationService signalementApplicationService) {
    this.signalementApplicationService = requireNonNull(signalementApplicationService);
  }

  @PostMapping
  ResponseEntity<?> creerSignalement(@RequestBody AvisId identifiant) {
    Signalement signalement = this.signalementApplicationService.signaler(identifiant);
    return ResponseEntity.created(
            URI.create(
                "http://localhost:8090/signalements/" + signalement.identifiant().identifiant()))
        .build();
  }
}
