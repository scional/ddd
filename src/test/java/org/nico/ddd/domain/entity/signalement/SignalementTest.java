package org.nico.ddd.domain.entity.signalement;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.nico.ddd.domain.entity.avis.AvisId;

class SignalementTest {

  @Test
  void testUnSignalementNePeutExisterSansUnAvis() {
    AvisId identifiantAvisInexistant = null;

    assertThatThrownBy(
            () -> new Signalement(new SignalementId("signalement-1"), identifiantAvisInexistant))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Un signalement ne peut exister sans un avis");
  }
}
