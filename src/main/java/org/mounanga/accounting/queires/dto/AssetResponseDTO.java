package org.mounanga.accounting.queires.dto;

import lombok.*;
import org.mounanga.accounting.common.enums.AssetCategory;
import org.mounanga.accounting.common.enums.AssetType;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AssetResponseDTO {
    private String id;
    private AssetType type;
    private AssetCategory category;
    private String name;
    private String description;
    private BigDecimal value;
    private LocalDate acquisitionDate;
    private String location;
    private MetaData metaData;
}
