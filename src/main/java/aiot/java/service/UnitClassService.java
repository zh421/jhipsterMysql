package aiot.java.service;

import aiot.java.domain.UnitClass;
import aiot.java.repository.UnitClassRepository;
import aiot.java.service.dto.UnitClassDTO;
import aiot.java.service.mapper.UnitClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnitClass}.
 */
@Service
@Transactional
public class UnitClassService {

    private final Logger log = LoggerFactory.getLogger(UnitClassService.class);

    private final UnitClassRepository unitClassRepository;

    private final UnitClassMapper unitClassMapper;

    public UnitClassService(UnitClassRepository unitClassRepository, UnitClassMapper unitClassMapper) {
        this.unitClassRepository = unitClassRepository;
        this.unitClassMapper = unitClassMapper;
    }

    /**
     * Save a unitClass.
     *
     * @param unitClassDTO the entity to save.
     * @return the persisted entity.
     */
    public UnitClassDTO save(UnitClassDTO unitClassDTO) {
        log.debug("Request to save UnitClass : {}", unitClassDTO);
        UnitClass unitClass = unitClassMapper.toEntity(unitClassDTO);
        unitClass = unitClassRepository.save(unitClass);
        return unitClassMapper.toDto(unitClass);
    }

    /**
     * Get all the unitClasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UnitClassDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnitClasses");
        return unitClassRepository.findAll(pageable)
            .map(unitClassMapper::toDto);
    }

    /**
     * Get one unitClass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UnitClassDTO> findOne(Long id) {
        log.debug("Request to get UnitClass : {}", id);
        return unitClassRepository.findById(id)
            .map(unitClassMapper::toDto);
    }

    /**
     * Delete the unitClass by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UnitClass : {}", id);
        unitClassRepository.deleteById(id);
    }
}
