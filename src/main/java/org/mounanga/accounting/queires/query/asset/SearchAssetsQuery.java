package org.mounanga.accounting.queires.query.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchAssetsQuery {
    private String keyword;
    private int page;
    private int pageSize;
}
