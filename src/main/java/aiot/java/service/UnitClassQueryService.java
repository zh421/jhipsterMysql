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

import aiot.java.domain.UnitClass;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.UnitClassRepository;
import aiot.java.service.dto.UnitClassCriteria;
import aiot.java.service.dto.UnitClassDTO;
import aiot.java.service.mapper.UnitClassMapper;

/**
 * Service for executing complex queries for {@link UnitClass} entities in the database.
 * The main input is a {@link UnitClassCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UnitClassDTO} or a {@link Page} of {@link UnitClassDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UnitClassQueryService extends QueryService<UnitClass> {

    private final Logger log = LoggerFactory.getLogger(UnitClassQueryService.class);

    private final UnitClassRepository unitClassRepository;

    private final UnitClassMapper unitClassMapper;

    public UnitClassQueryService(UnitClassRepository unitClassRepository, UnitClassMapper unitClassMapper) {
        this.unitClassRepository = unitClassRepository;
        this.unitClassMapper = unitClassMapper;
    }

    /**
     * Return a {@link List} of {@link UnitClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UnitClassDTO> findByCriteria(UnitClassCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UnitClass> specification = createSpecification(criteria);
        return unitClassMapper.toDto(unitClassRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UnitClassDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UnitClassDTO> findByCriteria(UnitClassCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UnitClass> specification = createSpecification(criteria);
        return unitClassRepository.findAll(specification, page)
            .map(unitClassMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UnitClassCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UnitClass> specification = createSpecification(criteria);
        return unitClassRepository.count(specification);
    }

    /**
     * Function to convert {@link UnitClassCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UnitClass> createSpecification(UnitClassCriteria criteria) {
        Specification<UnitClass> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UnitClass_.id));
            }
            if (criteria.getUcCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUcCode(), UnitClass_.ucCode));
            }
            if (criteria.getUcName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUcName(), UnitClass_.ucName));
            }
            if (criteria.getUcCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUcCretime(), UnitClass_.ucCretime));
            }
            if (criteria.getUcCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUcCreid(), UnitClass_.ucCreid));
            }
            if (criteria.getUcModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUcModtime(), UnitClass_.ucModtime));
            }
            if (criteria.getUcModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUcModid(), UnitClass_.ucModid));
            }
        }
        return specification;
    }
}
