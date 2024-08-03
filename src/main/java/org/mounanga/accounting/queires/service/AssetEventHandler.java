package org.mounanga.accounting.queires.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.common.event.AssetCreatedEvent;
import org.mounanga.accounting.common.event.AssetDeletedEvent;
import org.mounanga.accounting.common.event.AssetUpdatedEvent;
import org.mounanga.accounting.queires.entity.Asset;
import org.mounanga.accounting.queires.exception.AssetNotFoundException;
import org.mounanga.accounting.queires.repository.AssetRepository;
import org.mounanga.accounting.queires.util.EntityFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class AssetEventHandler {

    private final AssetRepository assetRepository;

    public AssetEventHandler(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @EventHandler
    public Asset on(AssetCreatedEvent event){
        log.info("AssetCreatedEvent received");
        Asset asset = EntityFactory.create(event);
        Asset savedAsset = assetRepository.save(asset);
        log.info("Asset saved with id {}", savedAsset.getId());
        return savedAsset;
    }

    @EventHandler
    public Asset on(@NotNull AssetUpdatedEvent event){
        log.info("AssetUpdatedEvent received");
        Asset oldAsset = assetRepository.findById(event.getId())
                .orElseThrow(() -> new AssetNotFoundException("Asset not found"));
        Asset newAsset = EntityFactory.create(event);
        newAsset.setCreatedBy(oldAsset.getCreatedBy());
        newAsset.setCreatedDate(oldAsset.getCreatedDate());
        Asset updatedAsset = assetRepository.save(newAsset);
        log.info("Asset with id {} updated", updatedAsset.getId());
        return updatedAsset;
    }

    @EventHandler
    public void on(@NotNull AssetDeletedEvent event){
        log.info("AssetDeletedEvent received");
        Asset asset = assetRepository.findById(event.getId())
                .orElseThrow(() -> new AssetNotFoundException("Asset not found"));
        assetRepository.deleteById(asset.getId());
        log.info("Asset with id {} deleted", event.getId());
    }
}
