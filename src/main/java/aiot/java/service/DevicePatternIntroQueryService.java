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

import aiot.java.domain.DevicePatternIntro;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.DevicePatternIntroRepository;
import aiot.java.service.dto.DevicePatternIntroCriteria;
import aiot.java.service.dto.DevicePatternIntroDTO;
import aiot.java.service.mapper.DevicePatternIntroMapper;

/**
 * Service for executing complex queries for {@link DevicePatternIntro} entities in the database.
 * The main input is a {@link DevicePatternIntroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DevicePatternIntroDTO} or a {@link Page} of {@link DevicePatternIntroDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DevicePatternIntroQueryService extends QueryService<DevicePatternIntro> {

    private final Logger log = LoggerFactory.getLogger(DevicePatternIntroQueryService.class);

    private final DevicePatternIntroRepository devicePatternIntroRepository;

    private final DevicePatternIntroMapper devicePatternIntroMapper;

    public DevicePatternIntroQueryService(DevicePatternIntroRepository devicePatternIntroRepository, DevicePatternIntroMapper devicePatternIntroMapper) {
        this.devicePatternIntroRepository = devicePatternIntroRepository;
        this.devicePatternIntroMapper = devicePatternIntroMapper;
    }

    /**
     * Return a {@link List} of {@link DevicePatternIntroDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DevicePatternIntroDTO> findByCriteria(DevicePatternIntroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DevicePatternIntro> specification = createSpecification(criteria);
        return devicePatternIntroMapper.toDto(devicePatternIntroRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DevicePatternIntroDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DevicePatternIntroDTO> findByCriteria(DevicePatternIntroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DevicePatternIntro> specification = createSpecification(criteria);
        return devicePatternIntroRepository.findAll(specification, page)
            .map(devicePatternIntroMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DevicePatternIntroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DevicePatternIntro> specification = createSpecification(criteria);
        return devicePatternIntroRepository.count(specification);
    }

    /**
     * Function to convert {@link DevicePatternIntroCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DevicePatternIntro> createSpecification(DevicePatternIntroCriteria criteria) {
        Specification<DevicePatternIntro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DevicePatternIntro_.id));
            }
            if (criteria.getDevicepatternId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDevicepatternId(), DevicePatternIntro_.devicepatternId));
            }
            if (criteria.getDevicepatternCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDevicepatternCode(), DevicePatternIntro_.devicepatternCode));
            }
            if (criteria.getMemo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMemo(), DevicePatternIntro_.memo));
            }
            if (criteria.getCreuser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreuser(), DevicePatternIntro_.creuser));
            }
            if (criteria.getCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCretime(), DevicePatternIntro_.cretime));
            }
            if (criteria.getModuser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModuser(), DevicePatternIntro_.moduser));
            }
            if (criteria.getModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModtime(), DevicePatternIntro_.modtime));
            }
        }
        return specification;
    }
}
