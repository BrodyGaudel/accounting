package org.mounanga.accounting.queires.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.accounting.common.enums.LiabilityCategory;
import org.mounanga.accounting.common.enums.LiabilityType;
import org.mounanga.accounting.queires.dto.LiabilityResponseDTO;
import org.mounanga.accounting.queires.exception.LiabilityNotFoundException;
import org.mounanga.accounting.queires.query.liability.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/liabilities")
public class LiabilityQueryRestController {

    private final QueryGateway queryGateway;

    public LiabilityQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public LiabilityResponseDTO getById(@PathVariable String id) {
        GetLiabilityByIdQuery query = new GetLiabilityByIdQuery(id);
        ResponseType<LiabilityResponseDTO> responseType = ResponseTypes.instanceOf(LiabilityResponseDTO.class);
        LiabilityResponseDTO response = queryGateway.query(query, responseType).join();
        if(response == null) {
            throw new LiabilityNotFoundException("Could not find liability with id " + id);
        }
        return response;
    }

    @GetMapping("/list")
    public List<LiabilityResponseDTO> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        GetAllLiabilitiesQuery query = new GetAllLiabilitiesQuery(page, size);
        ResponseType<List<LiabilityResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(LiabilityResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/categories")
    public List<LiabilityResponseDTO> getByCategory(@RequestParam(name = "category") LiabilityCategory category,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {

        GetLiabilitiesByCategoryQuery query = new GetLiabilitiesByCategoryQuery(category, page, size);
        ResponseType<List<LiabilityResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(LiabilityResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/type")
    public List<LiabilityResponseDTO> getByType(@RequestParam(name = "type") LiabilityType type,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {

        GetLiabilitiesByTypeQuery query = new GetLiabilitiesByTypeQuery(type, page, size);
        ResponseType<List<LiabilityResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(LiabilityResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/all")
    public List<LiabilityResponseDTO> getByCategoryAndType(@RequestParam(name = "category") LiabilityCategory category,
                                                           @RequestParam(name = "type") LiabilityType type,
                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size) {

        GetLiabilitiesByCategoryAndTypeQuery query = new GetLiabilitiesByCategoryAndTypeQuery(category, type, page, size);
        ResponseType<List<LiabilityResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(LiabilityResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/search")
    public List<LiabilityResponseDTO> search(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size) {

        SearchLiabilitiesQuery query = new SearchLiabilitiesQuery(keyword, page, size);
        ResponseType<List<LiabilityResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(LiabilityResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

