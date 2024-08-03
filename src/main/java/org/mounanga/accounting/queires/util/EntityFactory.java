package org.mounanga.accounting.queires.util;

import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.common.event.AssetCreatedEvent;
import org.mounanga.accounting.common.event.AssetUpdatedEvent;
import org.mounanga.accounting.common.event.LiabilityCreatedEvent;
import org.mounanga.accounting.common.event.LiabilityUpdatedEvent;
import org.mounanga.accounting.queires.entity.Asset;
import org.mounanga.accounting.queires.entity.Liability;

public class EntityFactory {

    private EntityFactory() {
        super();
    }

    public static Asset create(@NotNull final AssetCreatedEvent event){
        return Asset.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .value(event.getValue())
                .location(event.getLocation())
                .type(event.getType())
                .category(event.getCategory())
                .acquisitionDate(event.getAcquisitionDate())
                .createdBy(event.getEventBy())
                .createdDate(event.getEventDateTime())
                .build();

    }

    public static Asset create(@NotNull final AssetUpdatedEvent event){
        return Asset.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .value(event.getValue())
                .location(event.getLocation())
                .type(event.getType())
                .category(event.getCategory())
                .acquisitionDate(event.getAcquisitionDate())
                .lastModifiedBy(event.getEventBy())
                .lastModifiedDate(event.getEventDateTime())
                .build();

    }

    public static Liability create(@NotNull final LiabilityCreatedEvent event){
        return Liability.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .amount(event.getAmount())
                .issueDate(event.getIssueDate())
                .type(event.getType())
                .category(event.getCategory())
                .createdBy(event.getEventBy())
                .createdDate(event.getEventDateTime())
                .build();
    }

    public static Liability create(@NotNull final LiabilityUpdatedEvent event){
        return Liability.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .amount(event.getAmount())
                .issueDate(event.getIssueDate())
                .type(event.getType())
                .category(event.getCategory())
                .lastModifiedBy(event.getEventBy())
                .lastModifiedDate(event.getEventDateTime())
                .build();
    }
}
