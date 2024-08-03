package org.mounanga.accounting.commands.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Getter
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;
    private final String commandBy;
    private final LocalDateTime commandDateTime;

    public BaseCommand(T id, String commandBy, LocalDateTime commandDateTime) {
        this.id = id;
        this.commandBy = commandBy;
        this.commandDateTime = commandDateTime;
    }
}
