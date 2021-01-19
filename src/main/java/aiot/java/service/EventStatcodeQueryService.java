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

import aiot.java.domain.EventStatcode;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.EventStatcodeRepository;
import aiot.java.service.dto.EventStatcodeCriteria;
import aiot.java.service.dto.EventStatcodeDTO;
import aiot.java.service.mapper.EventStatcodeMapper;

/**
 * Service for executing complex queries for {@link EventStatcode} entities in the database.
 * The main input is a {@link EventStatcodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventStatcodeDTO} or a {@link Page} of {@link EventStatcodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventStatcodeQueryService extends QueryService<EventStatcode> {

    private final Logger log = LoggerFactory.getLogger(EventStatcodeQueryService.class);

    private final EventStatcodeRepository eventStatcodeRepository;

    private final EventStatcodeMapper eventStatcodeMapper;

    public EventStatcodeQueryService(EventStatcodeRepository eventStatcodeRepository, EventStatcodeMapper eventStatcodeMapper) {
        this.eventStatcodeRepository = eventStatcodeRepository;
        this.eventStatcodeMapper = eventStatcodeMapper;
    }

    /**
     * Return a {@link List} of {@link EventStatcodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventStatcodeDTO> findByCriteria(EventStatcodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EventStatcode> specification = createSpecification(criteria);
        return eventStatcodeMapper.toDto(eventStatcodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventStatcodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventStatcodeDTO> findByCriteria(EventStatcodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EventStatcode> specification = createSpecification(criteria);
        return eventStatcodeRepository.findAll(specification, page)
            .map(eventStatcodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventStatcodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EventStatcode> specification = createSpecification(criteria);
        return eventStatcodeRepository.count(specification);
    }

    /**
     * Function to convert {@link EventStatcodeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EventStatcode> createSpecification(EventStatcodeCriteria criteria) {
        Specification<EventStatcode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EventStatcode_.id));
            }
            if (criteria.getEsCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsCode(), EventStatcode_.esCode));
            }
            if (criteria.getEsName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsName(), EventStatcode_.esName));
            }
            if (criteria.getEsCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEsCretime(), EventStatcode_.esCretime));
            }
            if (criteria.getEsCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsCreid(), EventStatcode_.esCreid));
            }
            if (criteria.getEsModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEsModtime(), EventStatcode_.esModtime));
            }
            if (criteria.getEsModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsModid(), EventStatcode_.esModid));
            }
        }
        return specification;
    }
}
