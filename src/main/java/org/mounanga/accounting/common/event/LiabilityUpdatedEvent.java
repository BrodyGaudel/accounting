package org.mounanga.accounting.common.event;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class LiabilityUpdatedEvent extends BaseEvent<String>{

    private final String name;
    private final String description;
    private final LiabilityType type;
    private final BigDecimal amount;
    private final LocalDate issueDate;
    private final LiabilityCategory category;

    public LiabilityUpdatedEvent(String id, String eventBy, LocalDateTime eventDateTime, String name, String description, LiabilityType type, BigDecimal amount, LocalDate issueDate, LiabilityCategory category) {
        super(id, eventBy, eventDateTime);
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.issueDate = issueDate;
        this.category = category;
    }
}
