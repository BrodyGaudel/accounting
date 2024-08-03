package org.mounanga.accounting.commands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;

import java.math.BigDecimal;
import java.time.LocalDate;


public record LiabilityRequestDTO(
        @NotBlank(message = "field 'name' is mandatory: it can not be blank")
        String name,

        @NotBlank(message = "field 'description' is mandatory: it can not be blank")
        String description,

        @NotNull(message = "field 'type' is mandatory: it can not be null")
        LiabilityType type,

        @NotNull(message = "field 'value' is mandatory: it can not be null")
        @Positive(message = "field 'value' must be positive")
        BigDecimal amount,

        @NotNull(message = "field 'acquisitionDate' is mandatory: it can not be null")
        @PastOrPresent(message = "issueDate date must be today or in the past")
        LocalDate issueDate,

        @NotNull(message = "field 'type' is mandatory: it can not be null")
        LiabilityCategory category
) {}
