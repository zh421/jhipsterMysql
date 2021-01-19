package aiot.java.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import aiot.java.domain.DeviceCode;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.DeviceCodeRepository;
import aiot.java.service.dto.DeviceCodeCriteria;
import aiot.java.service.dto.DeviceCodeDTO;
import aiot.java.service.mapper.DeviceCodeMapper;

/**
 * Service for executing complex queries for {@link DeviceCode} entities in the database.
 * The main input is a {@link DeviceCodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeviceCodeDTO} or a {@link Page} of {@link DeviceCodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeviceCodeQueryService extends QueryService<DeviceCode> {

    private final Logger log = LoggerFactory.getLogger(DeviceCodeQueryService.class);

    private final DeviceCodeRepository deviceCodeRepository;

    private final DeviceCodeMapper deviceCodeMapper;

    public DeviceCodeQueryService(DeviceCodeRepository deviceCodeRepository, DeviceCodeMapper deviceCodeMapper) {
        this.deviceCodeRepository = deviceCodeRepository;
        this.deviceCodeMapper = deviceCodeMapper;
    }

    /**
     * Return a {@link List} of {@link DeviceCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeviceCodeDTO> findByCriteria(DeviceCodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeviceCode> specification = createSpecification(criteria);
        return deviceCodeMapper.toDto(deviceCodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeviceCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeviceCodeDTO> findByCriteria(DeviceCodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeviceCode> specification = createSpecification(criteria);
        return deviceCodeRepository.findAll(specification, page)
            .map(deviceCodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeviceCodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeviceCode> specification = createSpecification(criteria);
        return deviceCodeRepository.count(specification);
    }

    /**
     * Function to convert {@link DeviceCodeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DeviceCode> createSpecification(DeviceCodeCriteria criteria) {
        Specification<DeviceCode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DeviceCode_.id));
            }
            if (criteria.getDviCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDviCode(), DeviceCode_.dviCode));
            }
            if (criteria.getDviName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDviName(), DeviceCode_.dviName));
            }
            if (criteria.getDviCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDviCretime(), DeviceCode_.dviCretime));
            }
            if (criteria.getDviCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDviCreid(), DeviceCode_.dviCreid));
            }
            if (criteria.getDviModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDviModtime(), DeviceCode_.dviModtime));
            }
            if (criteria.getDviModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDviModid(), DeviceCode_.dviModid));
            }
        }
        return specification;
    }
}
