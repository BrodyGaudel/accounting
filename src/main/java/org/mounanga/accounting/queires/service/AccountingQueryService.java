package org.mounanga.accounting.queires.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.queires.dto.BalanceSheetResponseDTO;
import org.mounanga.accounting.queires.query.GetBalanceSheetQuery;
import org.mounanga.accounting.queires.repository.AssetRepository;
import org.mounanga.accounting.queires.repository.LiabilityRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AccountingQueryService {

    private final AssetRepository assetRepository;
    private final LiabilityRepository liabilityRepository;

    public AccountingQueryService(AssetRepository assetRepository, LiabilityRepository liabilityRepository) {
        this.assetRepository = assetRepository;
        this.liabilityRepository = liabilityRepository;
    }

    @QueryHandler
    public BalanceSheetResponseDTO calculateBalanceSheet(GetBalanceSheetQuery query) {
        log.info("Calculating balance sheet {}", query);
        BigDecimal totalAssetBalance = assetRepository.getTotalAssetBalance();
        BigDecimal totalLiabilityBalance = liabilityRepository.getTotalLiabilityBalance();
        return getBalanceSheetResponseDTO(totalAssetBalance, totalLiabilityBalance);
    }


    private @NotNull BalanceSheetResponseDTO getBalanceSheetResponseDTO(@NotNull BigDecimal totalAssetBalance, BigDecimal totalLiabilityBalance) {
        int comparison = totalAssetBalance.compareTo(totalLiabilityBalance);
        BigDecimal difference = totalAssetBalance.subtract(totalLiabilityBalance);

        boolean isBalanced = (comparison == 0);
        String message = buildMessage(comparison);

        BalanceSheetResponseDTO response = new BalanceSheetResponseDTO();
        response.setBalanced(isBalanced);
        response.setDescription(message);
        response.setTotalAssets(totalAssetBalance);
        response.setTotalLiabilities(totalLiabilityBalance);
        response.setTotalDifferences(difference);
        response.setDateTime(LocalDateTime.now());
        log.info("Balance sheet response");
        return response;
    }

    @NotNull
    @Contract(pure = true)
    private String buildMessage(int comparison) {
        if (comparison == 0) {
            return "Total assets equal total liabilities: the balance sheet is balanced";
        }
        String comparisonMessage = comparison < 0 ? "are less than" : "are greater than";
        return String.format("Total assets %s total liabilities: the balance sheet is not balanced", comparisonMessage);
    }

}
