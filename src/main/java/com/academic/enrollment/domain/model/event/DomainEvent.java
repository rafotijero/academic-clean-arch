package com.academic.enrollment.domain.model.event;

import java.time.LocalDateTime;

public interface DomainEvent {
    Long getEventId();
    LocalDateTime getOccurredOn();
    String getEventType();
}
