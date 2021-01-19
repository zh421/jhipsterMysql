package aiot.java.service;

import aiot.java.domain.EventStatcode;
import aiot.java.repository.EventStatcodeRepository;
import aiot.java.service.dto.EventStatcodeDTO;
import aiot.java.service.mapper.EventStatcodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventStatcode}.
 */
@Service
@Transactional
public class EventStatcodeService {

    private final Logger log = LoggerFactory.getLogger(EventStatcodeService.class);

    private final EventStatcodeRepository eventStatcodeRepository;

    private final EventStatcodeMapper eventStatcodeMapper;

    public EventStatcodeService(EventStatcodeRepository eventStatcodeRepository, EventStatcodeMapper eventStatcodeMapper) {
        this.eventStatcodeRepository = eventStatcodeRepository;
        this.eventStatcodeMapper = eventStatcodeMapper;
    }

    /**
     * Save a eventStatcode.
     *
     * @param eventStatcodeDTO the entity to save.
     * @return the persisted entity.
     */
    public EventStatcodeDTO save(EventStatcodeDTO eventStatcodeDTO) {
        log.debug("Request to save EventStatcode : {}", eventStatcodeDTO);
        EventStatcode eventStatcode = eventStatcodeMapper.toEntity(eventStatcodeDTO);
        eventStatcode = eventStatcodeRepository.save(eventStatcode);
        return eventStatcodeMapper.toDto(eventStatcode);
    }

    /**
     * Get all the eventStatcodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EventStatcodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventStatcodes");
        return eventStatcodeRepository.findAll(pageable)
            .map(eventStatcodeMapper::toDto);
    }

    /**
     * Get one eventStatcode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EventStatcodeDTO> findOne(Long id) {
        log.debug("Request to get EventStatcode : {}", id);
        return eventStatcodeRepository.findById(id)
            .map(eventStatcodeMapper::toDto);
    }

    /**
     * Delete the eventStatcode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EventStatcode : {}", id);
        eventStatcodeRepository.deleteById(id);
    }
}
