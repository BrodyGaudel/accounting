package org.mounanga.accounting.queires.query.liability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mounanga.accounting.common.enums.LiabilityCategory;

@AllArgsConstructor
@Getter
public class GetLiabilitiesByCategoryQuery {
    private LiabilityCategory category;
    private int page;
    private int pageSize;
}
