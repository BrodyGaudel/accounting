package org.mounanga.accounting.common.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseEvent<T> {
    private final T id;
    private final String eventBy;
    private final LocalDateTime eventDateTime;

    public BaseEvent(T id, String eventBy, LocalDateTime eventDateTime) {
        this.id = id;
        this.eventBy = eventBy;
        this.eventDateTime = eventDateTime;
    }
}
