package org.mounanga.accounting.common.event;

import java.time.LocalDateTime;

public class LiabilityDeletedEvent extends BaseEvent<String>{
    public LiabilityDeletedEvent(String id, String eventBy, LocalDateTime eventDateTime) {
        super(id, eventBy, eventDateTime);
    }
}
