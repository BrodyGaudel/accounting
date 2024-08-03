package org.mounanga.accounting.queires.query.liability;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllLiabilitiesQuery {
    private int page;
    private int pageSize;
}
