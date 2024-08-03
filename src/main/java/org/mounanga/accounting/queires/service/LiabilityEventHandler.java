package org.mounanga.accounting.queires.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.common.event.LiabilityCreatedEvent;
import org.mounanga.accounting.common.event.LiabilityDeletedEvent;
import org.mounanga.accounting.common.event.LiabilityUpdatedEvent;
import org.mounanga.accounting.queires.entity.Liability;
import org.mounanga.accounting.queires.exception.LiabilityNotFoundException;
import org.mounanga.accounting.queires.repository.LiabilityRepository;
import org.mounanga.accounting.queires.util.EntityFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class LiabilityEventHandler {

    private final LiabilityRepository liabilityRepository;

    public LiabilityEventHandler(LiabilityRepository liabilityRepository) {
        this.liabilityRepository = liabilityRepository;
    }

    @EventHandler
    public Liability on(LiabilityCreatedEvent event){
        log.info("LiabilityCreatedEvent received");
        Liability liability = EntityFactory.create(event);
        Liability savedLiability = liabilityRepository.save(liability);
        log.info("Liability saved");
        return savedLiability;
    }

    @EventHandler
    public Liability on(@NotNull LiabilityUpdatedEvent event){
        log.info("LiabilityUpdatedEvent received");
        Liability oldLiability = liabilityRepository.findById(event.getId())
                .orElseThrow( () -> new LiabilityNotFoundException("liability not found"));
        Liability newLiability = EntityFactory.create(event);
        newLiability.setCreatedBy(oldLiability.getCreatedBy());
        newLiability.setCreatedDate(oldLiability.getCreatedDate());
        Liability updatedLiability = liabilityRepository.save(newLiability);
        log.info("Liability updated");
        return updatedLiability;
    }

    @EventHandler
    public void on(@NotNull LiabilityDeletedEvent event){
        log.info("LiabilityDeletedEvent received");
        Liability existingLiability = liabilityRepository.findById(event.getId())
                .orElseThrow( () -> new LiabilityNotFoundException("liability not found"));
        liabilityRepository.deleteById(existingLiability.getId());
    }

}
