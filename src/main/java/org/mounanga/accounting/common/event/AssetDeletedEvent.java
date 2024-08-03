package org.mounanga.accounting.common.event;

import java.time.LocalDateTime;

public class AssetDeletedEvent extends BaseEvent<String>{
    public AssetDeletedEvent(String id, String eventBy, LocalDateTime eventDateTime) {
        super(id, eventBy, eventDateTime);
    }
}
