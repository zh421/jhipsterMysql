package aiot.java.service;

import aiot.java.domain.DevicePatternIntro;
import aiot.java.repository.DevicePatternIntroRepository;
import aiot.java.service.dto.DevicePatternIntroDTO;
import aiot.java.service.mapper.DevicePatternIntroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DevicePatternIntro}.
 */
@Service
@Transactional
public class DevicePatternIntroService {

    private final Logger log = LoggerFactory.getLogger(DevicePatternIntroService.class);

    private final DevicePatternIntroRepository devicePatternIntroRepository;

    private final DevicePatternIntroMapper devicePatternIntroMapper;

    public DevicePatternIntroService(DevicePatternIntroRepository devicePatternIntroRepository, DevicePatternIntroMapper devicePatternIntroMapper) {
        this.devicePatternIntroRepository = devicePatternIntroRepository;
        this.devicePatternIntroMapper = devicePatternIntroMapper;
    }

    /**
     * Save a devicePatternIntro.
     *
     * @param devicePatternIntroDTO the entity to save.
     * @return the persisted entity.
     */
    public DevicePatternIntroDTO save(DevicePatternIntroDTO devicePatternIntroDTO) {
        log.debug("Request to save DevicePatternIntro : {}", devicePatternIntroDTO);
        DevicePatternIntro devicePatternIntro = devicePatternIntroMapper.toEntity(devicePatternIntroDTO);
        devicePatternIntro = devicePatternIntroRepository.save(devicePatternIntro);
        return devicePatternIntroMapper.toDto(devicePatternIntro);
    }

    /**
     * Get all the devicePatternIntros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DevicePatternIntroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DevicePatternIntros");
        return devicePatternIntroRepository.findAll(pageable)
            .map(devicePatternIntroMapper::toDto);
    }

    /**
     * Get one devicePatternIntro by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DevicePatternIntroDTO> findOne(Long id) {
        log.debug("Request to get DevicePatternIntro : {}", id);
        return devicePatternIntroRepository.findById(id)
            .map(devicePatternIntroMapper::toDto);
    }

    /**
     * Delete the devicePatternIntro by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DevicePatternIntro : {}", id);
        devicePatternIntroRepository.deleteById(id);
    }
}
