package org.mounanga.accounting.queires.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.accounting.queires.dto.BalanceSheetResponseDTO;
import org.mounanga.accounting.queires.query.GetBalanceSheetQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queries/accounting")
public class AccountingRestController {

    private final QueryGateway queryGateway;

    public AccountingRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/balance-sheet")
    public BalanceSheetResponseDTO getBalanceSheet() {
        GetBalanceSheetQuery query = new GetBalanceSheetQuery();
        ResponseType<BalanceSheetResponseDTO> responseType = ResponseTypes.instanceOf(BalanceSheetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}
