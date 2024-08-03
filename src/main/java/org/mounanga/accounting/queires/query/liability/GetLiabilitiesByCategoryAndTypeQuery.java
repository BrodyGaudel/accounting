package org.mounanga.accounting.queires.query.liability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;

@AllArgsConstructor
@Getter
public class GetLiabilitiesByCategoryAndTypeQuery {
    private LiabilityCategory category;
    private LiabilityType assetType;
    private int page;
    private int pageSize;
}
