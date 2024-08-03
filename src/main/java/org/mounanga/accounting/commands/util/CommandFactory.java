package org.mounanga.accounting.commands.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.*;
import org.mounanga.accounting.commands.dto.AssetRequestDTO;
import org.mounanga.accounting.commands.dto.LiabilityRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommandFactory {

    private CommandFactory() {
        super();
    }

    @NotNull
    @Contract("_, _ -> new")
    public static CreateAssetCommand create(@NotNull AssetRequestDTO dto, String order) {
        return new CreateAssetCommand(
                UUID.randomUUID().toString(),
                order,
                LocalDateTime.now(),
                dto.name(),
                dto.description(),
                dto.type(),
                dto.value(),
                dto.acquisitionDate(),
                dto.location(),
                dto.category()
        );
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static UpdateAssetCommand create(@NotNull AssetRequestDTO dto, @NotNull String id, String order) {
        return new UpdateAssetCommand(
                id,
                order,
                LocalDateTime.now(),
                dto.name(),
                dto.description(),
                dto.type(),
                dto.value(),
                dto.acquisitionDate(),
                dto.location(),
                dto.category()
        );
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static DeleteAssetCommand create(String id, String order, LocalDateTime dateTime) {
        return new DeleteAssetCommand(id,order,dateTime);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static CreateLiabilityCommand create(@NotNull LiabilityRequestDTO dto, String order) {
        return new CreateLiabilityCommand(
                UUID.randomUUID().toString(), order, LocalDateTime.now(),
                dto.name(), dto.description(),
                dto.type(), dto.amount(),
                dto.issueDate(), dto.category()
        );
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static UpdateLiabilityCommand create(@NotNull LiabilityRequestDTO dto, @NotNull String id, String order) {
        return new UpdateLiabilityCommand(
                id, order, LocalDateTime.now(),
                dto.name(), dto.description(),
                dto.type(), dto.amount(),
                dto.issueDate(), dto.category()
        );
    }

    @NotNull
    @Contract("_, _ -> new")
    public static DeleteLiabilityCommand create(String id, String order) {
        return new DeleteLiabilityCommand(id, order, LocalDateTime.now());
    }
}
