package org.nico.ddd.domain.entity.avis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AvisTest {

  @Test
  void testUnAvisValideNePeutEtreRevalide() {
    Avis avisValide = new Avis().valider();
    assertThatThrownBy(avisValide::valider)
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("L'avis est déjà validé");
  }

  @Test
  void testUnAvisNouvellementCreeNePeutEtreDejaValide() {
    Avis avisNouvellementCree = new Avis();
    assertThat(avisNouvellementCree.isValide()).isFalse();
  }

  @Test
  void testUnAvisNouvellementCreePeutEtreCache() {
    assertThat(new Avis().cacher().isCache()).isTrue();
  }

  @Test
  void testUnAvisInvalideNePeutEtreRevalide() {
    Avis avisInvalide = new Avis().invalider();
    assertThatThrownBy(avisInvalide::valider)
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("L'avis est déjà invalidé");
  }
}
