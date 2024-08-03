package org.mounanga.accounting.queires.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.queires.dto.LiabilityResponseDTO;
import org.mounanga.accounting.queires.entity.Liability;
import org.mounanga.accounting.queires.query.liability.*;
import org.mounanga.accounting.queires.repository.LiabilityRepository;
import org.mounanga.accounting.queires.util.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LiabilityQueryHandler {

    private final LiabilityRepository liabilityRepository;

    public LiabilityQueryHandler(LiabilityRepository liabilityRepository) {
        this.liabilityRepository = liabilityRepository;
    }

    @QueryHandler
    public LiabilityResponseDTO handle(@NotNull GetLiabilityByIdQuery query){
        log.info("GetLiabilityByIdQuery handled");
        Liability liability = liabilityRepository.findById(query.getAssetId()).orElse(null);
        if(liability == null){
            log.warn("Liability with '{}' not found", query.getAssetId());
            return null;
        }
        log.info("Liability with '{}' found", query.getAssetId());
        return Mapper.fromLiability(liability);
    }

    @QueryHandler
    public List<LiabilityResponseDTO> handle(@NotNull GetAllLiabilitiesQuery query){
        log.info("GetAllLiabilitiesQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Liability> liabilities = liabilityRepository.findAll(pageable);
        log.info("Found {} liabilities", liabilities.getTotalElements());
        return Mapper.fromLiabilities(liabilities.getContent());
    }

    @QueryHandler
    public List<LiabilityResponseDTO> handle(@NotNull GetLiabilitiesByCategoryQuery query){
        log.info("GetLiabilitiesByCategoryQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Liability> liabilities = liabilityRepository.findByCategory(query.getCategory(), pageable);
        log.info("Found {} liabilities with category {} ", liabilities.getTotalElements(), query.getCategory().getDisplayName());
        return Mapper.fromLiabilities(liabilities.getContent());
    }

    @QueryHandler
    public List<LiabilityResponseDTO> handle(@NotNull GetLiabilitiesByTypeQuery query){
        log.info("GetLiabilitiesByTypeQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Liability> liabilities = liabilityRepository.findByType(query.getAssetType(),pageable);
        log.info("Found {} liabilities with type {} ", liabilities.getTotalElements(), query.getAssetType());
        return Mapper.fromLiabilities(liabilities.getContent());
    }

    @QueryHandler
    public List<LiabilityResponseDTO> handle(@NotNull GetLiabilitiesByCategoryAndTypeQuery query){
        log.info("GetLiabilitiesByCategoryAndTypeQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Liability> liabilities = liabilityRepository.findByCategoryAndType(query.getCategory(), query.getAssetType(),pageable);
        log.info("Found {} liabilities with category {} and type {} ", liabilities.getTotalElements(), query.getCategory().getDisplayName(), query.getAssetType());
        return Mapper.fromLiabilities(liabilities.getContent());
    }

    @QueryHandler
    public List<LiabilityResponseDTO> handle(@NotNull SearchLiabilitiesQuery query){
        log.info("SearchLiabilitiesQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        String keyword = "%" + query.getKeyword() + "%";
        Page<Liability> liabilities = liabilityRepository.findByNameOrDescription(keyword, pageable);
        log.info("Found {} liabilities with keyword {} ", liabilities.getTotalElements(), query.getKeyword());
        return Mapper.fromLiabilities(liabilities.getContent());
    }
}
