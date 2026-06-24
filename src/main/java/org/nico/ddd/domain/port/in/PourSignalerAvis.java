package org.nico.ddd.domain.port.in;

import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.signalement.Signalement;

public interface PourSignalerAvis {

  Signalement signaler(Avis avis);
}
