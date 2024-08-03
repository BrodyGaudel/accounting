package org.mounanga.accounting.commands.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.*;
import org.mounanga.accounting.common.event.*;

public class EventFactory {

    private EventFactory() {
        super();
    }

    @NotNull
    @Contract("_ -> new")
    public static AssetCreatedEvent create(@NotNull final CreateAssetCommand command){
        return new AssetCreatedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime(),
                command.getName(),
                command.getDescription(),
                command.getType(),
                command.getValue(),
                command.getAcquisitionDate(),
                command.getLocation(),
                command.getCategory()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static AssetUpdatedEvent create(@NotNull final UpdateAssetCommand command){
        return new AssetUpdatedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime(),
                command.getName(),
                command.getDescription(),
                command.getType(),
                command.getValue(),
                command.getAcquisitionDate(),
                command.getLocation(),
                command.getCategory()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static AssetDeletedEvent create(@NotNull final DeleteAssetCommand command){
        return new AssetDeletedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static LiabilityCreatedEvent create(@NotNull final CreateLiabilityCommand command){
        return new LiabilityCreatedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime(),
                command.getName(),
                command.getDescription(),
                command.getType(),
                command.getAmount(),
                command.getIssueDate(),
                command.getCategory()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static LiabilityUpdatedEvent create(@NotNull final UpdateLiabilityCommand command){
        return new LiabilityUpdatedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime(),
                command.getName(),
                command.getDescription(),
                command.getType(),
                command.getAmount(),
                command.getIssueDate(),
                command.getCategory()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static LiabilityDeletedEvent create(@NotNull final DeleteLiabilityCommand command){
        return new LiabilityDeletedEvent(
                command.getId(),
                command.getCommandBy(),
                command.getCommandDateTime()
        );
    }
}
