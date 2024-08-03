package org.mounanga.accounting.common.enums;

import lombok.Getter;

@Getter
public enum AssetCategory {
    CURRENT_ASSETS("Current Assets"),
    FIXED_ASSETS("Fixed Assets"),
    INTANGIBLE_ASSETS("Intangible Assets"),
    FINANCIAL_ASSETS("Financial Assets"),
    OTHER_ASSETS("Other Assets");

    private final String displayName;

    AssetCategory(String displayName) {
        this.displayName = displayName;
    }

}
