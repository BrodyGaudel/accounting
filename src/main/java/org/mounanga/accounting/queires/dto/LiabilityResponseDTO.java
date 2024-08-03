package org.mounanga.accounting.queires.dto;

import lombok.*;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LiabilityResponseDTO {
    private String id;
    private String name;
    private String description;
    private LiabilityType type;
    private BigDecimal amount;
    private LocalDate issueDate;
    private LiabilityCategory category;
    private MetaData metaData;
}
