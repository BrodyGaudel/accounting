package org.mounanga.accounting.queires.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BalanceSheetResponseDTO {
    private boolean isBalanced;
    private BigDecimal  totalAssets;
    private BigDecimal totalLiabilities;
    private BigDecimal totalDifferences;
    private String description;
    private LocalDateTime dateTime;
    private List<AssetResponseDTO> assets;
    private List<LiabilityResponseDTO> liabilities;
}
