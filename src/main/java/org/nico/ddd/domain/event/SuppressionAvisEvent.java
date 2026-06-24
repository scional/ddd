package org.nico.ddd.domain.event;

import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.shared.publishsubscribe.Event;

public record SuppressionAvisEvent(AvisId identifiantAvis) implements Event {}
