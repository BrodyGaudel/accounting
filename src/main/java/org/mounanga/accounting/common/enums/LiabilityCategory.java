package org.mounanga.accounting.common.enums;

import lombok.Getter;

@Getter
public enum LiabilityCategory {
    CURRENT_LIABILITIES("Current Liabilities"),
    LONG_TERM_LIABILITIES("Long-term Liabilities"),
    CONTINGENT_LIABILITIES("Contingent Liabilities"),
    FINANCIAL_LIABILITIES("Financial Liabilities");

    private final String displayName;

    LiabilityCategory(String displayName) {
        this.displayName = displayName;
    }

}
