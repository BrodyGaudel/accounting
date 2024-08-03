package org.mounanga.accounting.queires.query.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mounanga.accounting.common.enums.AssetCategory;
import org.mounanga.accounting.common.enums.AssetType;

@AllArgsConstructor
@Getter
public class GetAssetsByCategoryAndTypeQuery {
    private AssetCategory category;
    private AssetType assetType;
    private int page;
    private int pageSize;
}
