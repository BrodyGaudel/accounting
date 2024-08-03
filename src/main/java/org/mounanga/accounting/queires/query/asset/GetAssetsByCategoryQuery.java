package org.mounanga.accounting.queires.query.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mounanga.accounting.common.enums.AssetCategory;

@AllArgsConstructor
@Getter
public class GetAssetsByCategoryQuery {
    private AssetCategory category;
    private int page;
    private int pageSize;
}
