package aiot.java.service;

import aiot.java.domain.DeviceCode;
import aiot.java.repository.DeviceCodeRepository;
import aiot.java.service.dto.DeviceCodeDTO;
import aiot.java.service.mapper.DeviceCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DeviceCode}.
 */
@Service
@Transactional
public class DeviceCodeService {

    private final Logger log = LoggerFactory.getLogger(DeviceCodeService.class);

    private final DeviceCodeRepository deviceCodeRepository;

    private final DeviceCodeMapper deviceCodeMapper;

    public DeviceCodeService(DeviceCodeRepository deviceCodeRepository, DeviceCodeMapper deviceCodeMapper) {
        this.deviceCodeRepository = deviceCodeRepository;
        this.deviceCodeMapper = deviceCodeMapper;
    }

    /**
     * Save a deviceCode.
     *
     * @param deviceCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public DeviceCodeDTO save(DeviceCodeDTO deviceCodeDTO) {
        log.debug("Request to save DeviceCode : {}", deviceCodeDTO);
        DeviceCode deviceCode = deviceCodeMapper.toEntity(deviceCodeDTO);
        deviceCode = deviceCodeRepository.save(deviceCode);
        return deviceCodeMapper.toDto(deviceCode);
    }

    /**
     * Get all the deviceCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DeviceCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeviceCodes");
        return deviceCodeRepository.findAll(pageable)
            .map(deviceCodeMapper::toDto);
    }

    /**
     * Get one deviceCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeviceCodeDTO> findOne(Long id) {
        log.debug("Request to get DeviceCode : {}", id);
        return deviceCodeRepository.findById(id)
            .map(deviceCodeMapper::toDto);
    }

    /**
     * Delete the deviceCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DeviceCode : {}", id);
        deviceCodeRepository.deleteById(id);
    }
}
