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

import aiot.java.domain.SensorCode;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.SensorCodeRepository;
import aiot.java.service.dto.SensorCodeCriteria;
import aiot.java.service.dto.SensorCodeDTO;
import aiot.java.service.mapper.SensorCodeMapper;

/**
 * Service for executing complex queries for {@link SensorCode} entities in the database.
 * The main input is a {@link SensorCodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SensorCodeDTO} or a {@link Page} of {@link SensorCodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SensorCodeQueryService extends QueryService<SensorCode> {

    private final Logger log = LoggerFactory.getLogger(SensorCodeQueryService.class);

    private final SensorCodeRepository sensorCodeRepository;

    private final SensorCodeMapper sensorCodeMapper;

    public SensorCodeQueryService(SensorCodeRepository sensorCodeRepository, SensorCodeMapper sensorCodeMapper) {
        this.sensorCodeRepository = sensorCodeRepository;
        this.sensorCodeMapper = sensorCodeMapper;
    }

    /**
     * Return a {@link List} of {@link SensorCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SensorCodeDTO> findByCriteria(SensorCodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SensorCode> specification = createSpecification(criteria);
        return sensorCodeMapper.toDto(sensorCodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SensorCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SensorCodeDTO> findByCriteria(SensorCodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SensorCode> specification = createSpecification(criteria);
        return sensorCodeRepository.findAll(specification, page)
            .map(sensorCodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SensorCodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SensorCode> specification = createSpecification(criteria);
        return sensorCodeRepository.count(specification);
    }

    /**
     * Function to convert {@link SensorCodeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SensorCode> createSpecification(SensorCodeCriteria criteria) {
        Specification<SensorCode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SensorCode_.id));
            }
            if (criteria.getScCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getScCode(), SensorCode_.scCode));
            }
            if (criteria.getScName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getScName(), SensorCode_.scName));
            }
            if (criteria.getScCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScCretime(), SensorCode_.scCretime));
            }
            if (criteria.getScCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getScCreid(), SensorCode_.scCreid));
            }
            if (criteria.getScModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScModtime(), SensorCode_.scModtime));
            }
            if (criteria.getScModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getScModid(), SensorCode_.scModid));
            }
        }
        return specification;
    }
}
