package org.mounanga.accounting.queires.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.queires.dto.AssetResponseDTO;
import org.mounanga.accounting.queires.entity.Asset;
import org.mounanga.accounting.queires.query.asset.*;
import org.mounanga.accounting.queires.repository.AssetRepository;
import org.mounanga.accounting.queires.util.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AssetQueryHandler {

    private final AssetRepository assetRepository;

    public AssetQueryHandler(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @QueryHandler
    public AssetResponseDTO handle(@NotNull GetAssetByIdQuery query){
        log.info("GetAssetByIdQuery handled");
        Asset asset = assetRepository.findById(query.getAssetId()).orElse(null);
        if(asset == null){
            log.warn("Asset with '{}' not found", query.getAssetId());
            return null;
        }
        log.info("Asset with '{}' found", query.getAssetId());
        return Mapper.fromAsset(asset);
    }

    @QueryHandler
    public List<AssetResponseDTO> handle(@NotNull GetAllAssetsQuery query){
        log.info("GetAllAssetsQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Asset> assets = assetRepository.findAll(pageable);
        log.info("Found {} assets", assets.getTotalElements());
        return Mapper.fromAssets(assets.getContent());
    }

    @QueryHandler
    public List<AssetResponseDTO> handle(@NotNull GetAssetsByCategoryQuery query){
        log.info("GetAssetsByCategoryQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Asset> assets = assetRepository.findByCategory(query.getCategory().getDisplayName(), pageable);
        log.info("Found {} assets with category {} ", assets.getTotalElements(), query.getCategory().getDisplayName());
        return Mapper.fromAssets(assets.getContent());
    }

    @QueryHandler
    public List<AssetResponseDTO> handle(@NotNull GetAssetsByTypeQuery query){
        log.info("GetAssetsByTypeQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Asset> assets = assetRepository.findByType(query.getAssetType().toString(),pageable);
        log.info("Found {} assets with type {} ", assets.getTotalElements(), query.getAssetType());
        return Mapper.fromAssets(assets.getContent());
    }

    @QueryHandler
    public List<AssetResponseDTO> handle(@NotNull GetAssetsByCategoryAndTypeQuery query){
        log.info("GetAssetsByCategoryAndTypeQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Asset> assets = assetRepository.findByCategoryAndType(query.getCategory().getDisplayName(), query.getAssetType().toString(),pageable);
        log.info("Found {} assets with category {} and type {} ", assets.getTotalElements(), query.getCategory().getDisplayName(), query.getAssetType());
        return Mapper.fromAssets(assets.getContent());
    }

    @QueryHandler
    public List<AssetResponseDTO> handle(@NotNull SearchAssetsQuery query){
        log.info("SearchAssetsQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Asset> assets = assetRepository.findByNameOrDescription("%"+query.getKeyword()+"%", pageable);
        log.info("Found {} assets with keyword {} ", assets.getTotalElements(), query.getKeyword());
        return Mapper.fromAssets(assets.getContent());
    }
}
