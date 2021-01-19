package aiot.java.service;

import aiot.java.domain.SensorCode;
import aiot.java.repository.SensorCodeRepository;
import aiot.java.service.dto.SensorCodeDTO;
import aiot.java.service.mapper.SensorCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SensorCode}.
 */
@Service
@Transactional
public class SensorCodeService {

    private final Logger log = LoggerFactory.getLogger(SensorCodeService.class);

    private final SensorCodeRepository sensorCodeRepository;

    private final SensorCodeMapper sensorCodeMapper;

    public SensorCodeService(SensorCodeRepository sensorCodeRepository, SensorCodeMapper sensorCodeMapper) {
        this.sensorCodeRepository = sensorCodeRepository;
        this.sensorCodeMapper = sensorCodeMapper;
    }

    /**
     * Save a sensorCode.
     *
     * @param sensorCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public SensorCodeDTO save(SensorCodeDTO sensorCodeDTO) {
        log.debug("Request to save SensorCode : {}", sensorCodeDTO);
        SensorCode sensorCode = sensorCodeMapper.toEntity(sensorCodeDTO);
        sensorCode = sensorCodeRepository.save(sensorCode);
        return sensorCodeMapper.toDto(sensorCode);
    }

    /**
     * Get all the sensorCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SensorCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SensorCodes");
        return sensorCodeRepository.findAll(pageable)
            .map(sensorCodeMapper::toDto);
    }

    /**
     * Get one sensorCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SensorCodeDTO> findOne(Long id) {
        log.debug("Request to get SensorCode : {}", id);
        return sensorCodeRepository.findById(id)
            .map(sensorCodeMapper::toDto);
    }

    /**
     * Delete the sensorCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SensorCode : {}", id);
        sensorCodeRepository.deleteById(id);
    }
}
