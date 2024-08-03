package org.mounanga.accounting.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.CreateLiabilityCommand;
import org.mounanga.accounting.commands.command.DeleteLiabilityCommand;
import org.mounanga.accounting.commands.command.UpdateLiabilityCommand;
import org.mounanga.accounting.commands.util.EventFactory;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;
import org.mounanga.accounting.common.event.LiabilityCreatedEvent;
import org.mounanga.accounting.common.event.LiabilityDeletedEvent;
import org.mounanga.accounting.common.event.LiabilityUpdatedEvent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Aggregate
@Slf4j
@Getter
public class LiabilityAggregate {

    @AggregateIdentifier
    private String liabilityId;
    private String name;
    private String description;
    private LiabilityType type;
    private BigDecimal amount;
    private LocalDate issueDate;
    private LiabilityCategory category;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public LiabilityAggregate() {
        super();
    }

    @CommandHandler
    public LiabilityAggregate(CreateLiabilityCommand command) {
        log.info("CreateLiabilityCommand handled");
        LiabilityCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull LiabilityCreatedEvent event) {
        log.info("LiabilityCreatedEvent handled");
        this.liabilityId = event.getId();
        this.createdBy = event.getEventBy();
        this.createdDate = event.getEventDateTime();
        setItems(event.getName(), event.getDescription(), event.getType(), event.getAmount(), event.getIssueDate(), event.getCategory());
    }

    @CommandHandler
    public void handle(@NotNull UpdateLiabilityCommand command) {
        log.info("UpdateLiabilityCommand handled");
        LiabilityUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull LiabilityUpdatedEvent event) {
        log.info("LiabilityUpdatedEvent handled");
        this.liabilityId = event.getId();
        this.lastModifiedBy = event.getEventBy();
        this.lastModifiedDate = event.getEventDateTime();
        setItems(event.getName(), event.getDescription(), event.getType(), event.getAmount(), event.getIssueDate(), event.getCategory());
    }

    @CommandHandler
    public void handle(DeleteLiabilityCommand command) {
        log.info("DeleteLiabilityCommand handled");
        LiabilityDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull LiabilityDeletedEvent event) {
        log.info("LiabilityDeletedEvent handled");
        this.liabilityId = event.getId();
        this.lastModifiedBy = event.getEventBy();
        this.lastModifiedDate = event.getEventDateTime();
        setItems(null, null, null, null, null, null);
    }

    private void setItems(String name, String description, LiabilityType type, BigDecimal amount, LocalDate issueDate, LiabilityCategory category) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.issueDate = issueDate;
        this.category = category;
    }
}
