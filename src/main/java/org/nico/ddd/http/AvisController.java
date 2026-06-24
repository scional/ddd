package org.nico.ddd.http;

import static java.util.Objects.requireNonNull;

import org.nico.ddd.application.service.AvisApplicationService;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avis")
class AvisController {

  private final AvisApplicationService avisApplicationService;

  AvisController(AvisApplicationService avisApplicationService) {
    this.avisApplicationService = requireNonNull(avisApplicationService);
  }

  @DeleteMapping("/{identifiant}")
  ResponseEntity<?> supprimerAvis(@PathVariable String identifiant) {
    avisApplicationService.supprimer(new AvisId(identifiant));
    return ResponseEntity.noContent().build();
  }
}
