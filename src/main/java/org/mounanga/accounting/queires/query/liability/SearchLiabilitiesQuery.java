package org.mounanga.accounting.queires.query.liability;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchLiabilitiesQuery {
    private String keyword;
    private int page;
    private int pageSize;
}
