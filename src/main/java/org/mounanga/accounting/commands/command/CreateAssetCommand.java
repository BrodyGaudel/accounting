package org.mounanga.accounting.commands.command;

import lombok.Builder;
import lombok.Getter;
import org.mounanga.accounting.common.enums.AssetCategory;
import org.mounanga.accounting.common.enums.AssetType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateAssetCommand extends BaseCommand<String> {

    private final String name;
    private final String description;
    private final AssetType type;
    private final BigDecimal value;
    private final LocalDate acquisitionDate;
    private final String location;
    private final AssetCategory category;

    public CreateAssetCommand(String id, String commandBy, LocalDateTime commandDateTime, String name, String description, AssetType type, BigDecimal value, LocalDate acquisitionDate, String location, AssetCategory category) {
        super(id, commandBy, commandDateTime);
        this.name = name;
        this.description = description;
        this.type = type;
        this.value = value;
        this.acquisitionDate = acquisitionDate;
        this.location = location;
        this.category = category;
    }
}
