package org.mounanga.accounting.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.DeleteAssetCommand;
import org.mounanga.accounting.commands.command.UpdateAssetCommand;
import org.mounanga.accounting.commands.exception.NegativeValueException;
import org.mounanga.accounting.commands.util.EventFactory;
import org.mounanga.accounting.commands.command.CreateAssetCommand;
import org.mounanga.accounting.common.enums.AssetCategory;
import org.mounanga.accounting.common.enums.AssetType;
import org.mounanga.accounting.common.event.AssetCreatedEvent;
import org.mounanga.accounting.common.event.AssetDeletedEvent;
import org.mounanga.accounting.common.event.AssetUpdatedEvent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Aggregate
@Slf4j
@Getter
public class AssetAggregate {
    @AggregateIdentifier
    private String assetId;
    private String name;
    private String description;
    private AssetType type;
    private BigDecimal value;
    private LocalDate acquisitionDate;
    private String location;
    private AssetCategory category;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;


    public AssetAggregate() {
        super();
    }

    @CommandHandler
    public AssetAggregate(CreateAssetCommand command) {
        log.info("CreateAssetCommand handled");
        if (command.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeValueException("The value of an asset cannot be less than zero.");
        }
        AssetCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull AssetCreatedEvent event) {
        log.info("AssetCreatedEvent handled");
        setItems(event.getName(), event.getDescription(), event.getType(), event.getValue(), event.getAcquisitionDate(), event.getLocation(), event.getCategory());
        this.assetId = event.getId();
        this.createdBy = event.getEventBy();
        this.createdDate = event.getEventDateTime();
    }

    @CommandHandler
    public void handle(@NotNull UpdateAssetCommand command) {
        log.info("UpdateAssetCommand handled");
        if (command.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeValueException("The value of an asset cannot be less than zero.");
        }
        AssetUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull AssetUpdatedEvent event) {
        log.info("AssetUpdatedEvent handled");
        setItems(event.getName(), event.getDescription(), event.getType(), event.getValue(), event.getAcquisitionDate(), event.getLocation(), event.getCategory());
        this.assetId = event.getId();
        this.lastModifiedBy = event.getEventBy();
        this.lastModifiedDate = event.getEventDateTime();
    }

    @CommandHandler
    public void handle(DeleteAssetCommand command) {
        log.info("DeleteAssetCommand handled");
        AssetDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull AssetDeletedEvent event) {
        log.info("AssetDeletedEvent handled");
        setItems(null,null,null,null,null,null, null);
        this.assetId = event.getId();
        this.lastModifiedBy = event.getEventBy();
        this.lastModifiedDate = event.getEventDateTime();
    }

    private void setItems(String name, String description, AssetType type, BigDecimal value, LocalDate acquisitionDate, String location, AssetCategory category) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.acquisitionDate = acquisitionDate;
        this.location = location;
        this.category = category;
    }
}
