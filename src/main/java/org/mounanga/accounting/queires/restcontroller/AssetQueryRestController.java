package org.mounanga.accounting.queires.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.accounting.common.enums.AssetCategory;
import org.mounanga.accounting.common.enums.AssetType;
import org.mounanga.accounting.queires.dto.AssetResponseDTO;
import org.mounanga.accounting.queires.exception.AssetNotFoundException;
import org.mounanga.accounting.queires.query.asset.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/assets")
public class AssetQueryRestController {

    private final QueryGateway queryGateway;

    public AssetQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public AssetResponseDTO getById(@PathVariable String id) {
        GetAssetByIdQuery query = new GetAssetByIdQuery(id);
        ResponseType<AssetResponseDTO> responseType = ResponseTypes.instanceOf(AssetResponseDTO.class);
        AssetResponseDTO response = queryGateway.query(query, responseType).join();
        if(response == null) {
            throw new AssetNotFoundException("Could not find asset with id " + id);
        }
        return response;
    }

    @GetMapping("/list")
    public List<AssetResponseDTO> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        GetAllAssetsQuery query = new GetAllAssetsQuery(page, size);
        ResponseType<List<AssetResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(AssetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/categories")
    public List<AssetResponseDTO> getByCategory(@RequestParam(name = "category") String category,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        GetAssetsByCategoryQuery query = new GetAssetsByCategoryQuery(AssetCategory.valueOf(category), page, size);
        ResponseType<List<AssetResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(AssetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/type")
    public List<AssetResponseDTO> getByType(@RequestParam(name = "type") String type,
                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "10") int size) {

        GetAssetsByTypeQuery query = new GetAssetsByTypeQuery(AssetType.valueOf(type), page, size);
        ResponseType<List<AssetResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(AssetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/all")
    public List<AssetResponseDTO> getByCategoryAndType(@RequestParam(name = "category") String category,
                                                       @RequestParam(name = "type") String type,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {

        GetAssetsByCategoryAndTypeQuery query = new GetAssetsByCategoryAndTypeQuery(AssetCategory.valueOf(category), AssetType.valueOf(type), page, size);
        ResponseType<List<AssetResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(AssetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }



    @GetMapping("/search")
    public List<AssetResponseDTO> search(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "10") int size) {

        SearchAssetsQuery query = new SearchAssetsQuery(keyword, page, size);
        ResponseType<List<AssetResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(AssetResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}
