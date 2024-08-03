package org.mounanga.accounting.commands.command;

import lombok.Getter;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class UpdateLiabilityCommand extends BaseCommand<String> {
    private final String name;
    private final String description;
    private final LiabilityType type;
    private final BigDecimal amount;
    private final LocalDate issueDate;
    private final LiabilityCategory category;

    public UpdateLiabilityCommand(String id, String commandBy, LocalDateTime commandDateTime, String name, String description, LiabilityType type, BigDecimal amount, LocalDate issueDate, LiabilityCategory category) {
        super(id, commandBy, commandDateTime);
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.issueDate = issueDate;
        this.category = category;
    }

}
