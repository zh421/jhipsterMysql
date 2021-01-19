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

import aiot.java.domain.NoticeInfo;
import aiot.java.domain.*; // for static metamodels
import aiot.java.repository.NoticeInfoRepository;
import aiot.java.service.dto.NoticeInfoCriteria;
import aiot.java.service.dto.NoticeInfoDTO;
import aiot.java.service.mapper.NoticeInfoMapper;

/**
 * Service for executing complex queries for {@link NoticeInfo} entities in the database.
 * The main input is a {@link NoticeInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NoticeInfoDTO} or a {@link Page} of {@link NoticeInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NoticeInfoQueryService extends QueryService<NoticeInfo> {

    private final Logger log = LoggerFactory.getLogger(NoticeInfoQueryService.class);

    private final NoticeInfoRepository noticeInfoRepository;

    private final NoticeInfoMapper noticeInfoMapper;

    public NoticeInfoQueryService(NoticeInfoRepository noticeInfoRepository, NoticeInfoMapper noticeInfoMapper) {
        this.noticeInfoRepository = noticeInfoRepository;
        this.noticeInfoMapper = noticeInfoMapper;
    }

    /**
     * Return a {@link List} of {@link NoticeInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NoticeInfoDTO> findByCriteria(NoticeInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NoticeInfo> specification = createSpecification(criteria);
        return noticeInfoMapper.toDto(noticeInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NoticeInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NoticeInfoDTO> findByCriteria(NoticeInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NoticeInfo> specification = createSpecification(criteria);
        return noticeInfoRepository.findAll(specification, page)
            .map(noticeInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NoticeInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NoticeInfo> specification = createSpecification(criteria);
        return noticeInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link NoticeInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<NoticeInfo> createSpecification(NoticeInfoCriteria criteria) {
        Specification<NoticeInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), NoticeInfo_.id));
            }
            if (criteria.getNotiCaseid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiCaseid(), NoticeInfo_.notiCaseid));
            }
            if (criteria.getNotiTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiTitle(), NoticeInfo_.notiTitle));
            }
            if (criteria.getNotiContent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiContent(), NoticeInfo_.notiContent));
            }
            if (criteria.getNotiInfotype() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiInfotype(), NoticeInfo_.notiInfotype));
            }
            if (criteria.getNotiStarttime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNotiStarttime(), NoticeInfo_.notiStarttime));
            }
            if (criteria.getNotiEndtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNotiEndtime(), NoticeInfo_.notiEndtime));
            }
            if (criteria.getNotiStatcode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiStatcode(), NoticeInfo_.notiStatcode));
            }
            if (criteria.getNotiCretime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNotiCretime(), NoticeInfo_.notiCretime));
            }
            if (criteria.getNotiCreid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiCreid(), NoticeInfo_.notiCreid));
            }
            if (criteria.getNotiModtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNotiModtime(), NoticeInfo_.notiModtime));
            }
            if (criteria.getNotiModid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotiModid(), NoticeInfo_.notiModid));
            }
        }
        return specification;
    }
}
