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

import aiot.java.domain.EventInter;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.EventInterRepository;
import aiot.java.service.dto.EventInterCriteria;
import aiot.java.service.dto.EventInterDTO;
import aiot.java.service.mapper.EventInterMapper;

/**
 * Service for executing complex queries for {@link EventInter} entities in the database.
 * The main input is a {@link EventInterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventInterDTO} or a {@link Page} of {@link EventInterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventInterQueryService extends QueryService<EventInter> {

    private final Logger log = LoggerFactory.getLogger(EventInterQueryService.class);

    private final EventInterRepository eventInterRepository;

    private final EventInterMapper eventInterMapper;

    public EventInterQueryService(EventInterRepository eventInterRepository, EventInterMapper eventInterMapper) {
        this.eventInterRepository = eventInterRepository;
        this.eventInterMapper = eventInterMapper;
    }

    /**
     * Return a {@link List} of {@link EventInterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventInterDTO> findByCriteria(EventInterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EventInter> specification = createSpecification(criteria);
        return eventInterMapper.toDto(eventInterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventInterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventInterDTO> findByCriteria(EventInterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EventInter> specification = createSpecification(criteria);
        return eventInterRepository.findAll(specification, page)
            .map(eventInterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventInterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EventInter> specification = createSpecification(criteria);
        return eventInterRepository.count(specification);
    }

    /**
     * Function to convert {@link EventInterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EventInter> createSpecification(EventInterCriteria criteria) {
        Specification<EventInter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EventInter_.id));
            }
            if (criteria.getEvninTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEvninTime(), EventInter_.evninTime));
            }
            if (criteria.getEvninEsCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninEsCode(), EventInter_.evninEsCode));
            }
            if (criteria.getEvninDeviceid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninDeviceid(), EventInter_.evninDeviceid));
            }
            if (criteria.getEvninDviModNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEvninDviModNum(), EventInter_.evninDviModNum));
            }
            if (criteria.getEvninDviCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninDviCode(), EventInter_.evninDviCode));
            }
            if (criteria.getEvninUnitUcCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninUnitUcCode(), EventInter_.evninUnitUcCode));
            }
            if (criteria.getEvninUnitCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninUnitCode(), EventInter_.evninUnitCode));
            }
            if (criteria.getEvninUnitName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninUnitName(), EventInter_.evninUnitName));
            }
            if (criteria.getEvninUnitAddr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninUnitAddr(), EventInter_.evninUnitAddr));
            }
            if (criteria.getEvninTheme() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninTheme(), EventInter_.evninTheme));
            }
            if (criteria.getEvninMemo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninMemo(), EventInter_.evninMemo));
            }
            if (criteria.getEvninIsres() != null) {
                specification = specification.and(buildSpecification(criteria.getEvninIsres(), EventInter_.evninIsres));
            }
            if (criteria.getEvninResMemo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninResMemo(), EventInter_.evninResMemo));
            }
            if (criteria.getEvninCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEvninCretime(), EventInter_.evninCretime));
            }
            if (criteria.getEvninCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninCreid(), EventInter_.evninCreid));
            }
            if (criteria.getEvninModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEvninModtime(), EventInter_.evninModtime));
            }
            if (criteria.getEvninModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvninModid(), EventInter_.evninModid));
            }
        }
        return specification;
    }
}
