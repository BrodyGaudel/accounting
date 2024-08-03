package org.mounanga.accounting.queires.util;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.queires.dto.AssetResponseDTO;
import org.mounanga.accounting.queires.dto.LiabilityResponseDTO;
import org.mounanga.accounting.queires.dto.MetaData;
import org.mounanga.accounting.queires.entity.Asset;
import org.mounanga.accounting.queires.entity.Liability;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class Mapper {

    private Mapper() {
        super();
    }

    @NotNull
    public static AssetResponseDTO fromAsset(@NotNull final Asset asset) {
        final AssetResponseDTO dto = new AssetResponseDTO();
        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setDescription(asset.getDescription());
        dto.setValue(asset.getValue());
        dto.setType(asset.getType());
        dto.setLocation(asset.getLocation());
        dto.setAcquisitionDate(asset.getAcquisitionDate());
        dto.setCategory(asset.getCategory());
        dto.setMetaData(getMetaData(asset.getCreatedBy(), asset.getCreatedDate(), asset.getLastModifiedBy(), asset.getLastModifiedDate()));
        return dto;
    }

    public static List<AssetResponseDTO> fromAssets(@NotNull final List<Asset> assets) {
        return assets.stream().map(Mapper::fromAsset).toList();
    }

    @NotNull
    public static LiabilityResponseDTO fromLiability(@NotNull final Liability liability) {
        final LiabilityResponseDTO dto = new LiabilityResponseDTO();
        dto.setId(liability.getId());
        dto.setName(liability.getName());
        dto.setDescription(liability.getDescription());
        dto.setAmount(liability.getAmount());
        dto.setCategory(liability.getCategory());
        dto.setType(liability.getType());
        dto.setIssueDate(liability.getIssueDate());
        dto.setMetaData(getMetaData(liability.getCreatedBy(), liability.getCreatedDate(), liability.getLastModifiedBy(), liability.getLastModifiedDate()));
        return dto;
    }

    public static List<LiabilityResponseDTO> fromLiabilities(@NotNull final List<Liability> liabilities) {
        return liabilities.stream().map(Mapper::fromLiability).toList();
    }


    @NotNull
    private static MetaData getMetaData(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        return new MetaData(createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }


}
