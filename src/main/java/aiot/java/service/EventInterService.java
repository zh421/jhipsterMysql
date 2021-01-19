package aiot.java.service;

import aiot.java.domain.EventInter;
import aiot.java.repository.EventInterRepository;
import aiot.java.service.dto.EventInterDTO;
import aiot.java.service.mapper.EventInterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventInter}.
 */
@Service
@Transactional
public class EventInterService {

    private final Logger log = LoggerFactory.getLogger(EventInterService.class);

    private final EventInterRepository eventInterRepository;

    private final EventInterMapper eventInterMapper;

    public EventInterService(EventInterRepository eventInterRepository, EventInterMapper eventInterMapper) {
        this.eventInterRepository = eventInterRepository;
        this.eventInterMapper = eventInterMapper;
    }

    /**
     * Save a eventInter.
     *
     * @param eventInterDTO the entity to save.
     * @return the persisted entity.
     */
    public EventInterDTO save(EventInterDTO eventInterDTO) {
        log.debug("Request to save EventInter : {}", eventInterDTO);
        EventInter eventInter = eventInterMapper.toEntity(eventInterDTO);
        eventInter = eventInterRepository.save(eventInter);
        return eventInterMapper.toDto(eventInter);
    }

    /**
     * Get all the eventInters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EventInterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventInters");
        return eventInterRepository.findAll(pageable)
            .map(eventInterMapper::toDto);
    }

    /**
     * Get one eventInter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EventInterDTO> findOne(Long id) {
        log.debug("Request to get EventInter : {}", id);
        return eventInterRepository.findById(id)
            .map(eventInterMapper::toDto);
    }

    /**
     * Delete the eventInter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EventInter : {}", id);
        eventInterRepository.deleteById(id);
    }
}
