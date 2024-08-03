package org.mounanga.accounting.queires.query.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mounanga.accounting.common.enums.AssetType;

@AllArgsConstructor
@Getter
public class GetAssetsByTypeQuery {
    private AssetType assetType;
    private int page;
    private int pageSize;
}
