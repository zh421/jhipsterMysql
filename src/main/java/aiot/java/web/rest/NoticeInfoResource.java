package aiot.java.web.rest;

import aiot.java.service.NoticeInfoService;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.NoticeInfoDTO;
import aiot.java.service.dto.NoticeInfoCriteria;
import aiot.java.service.NoticeInfoQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link aiot.java.domain.NoticeInfo}.
 */
@RestController
@RequestMapping("/api")
public class NoticeInfoResource {

    private final Logger log = LoggerFactory.getLogger(NoticeInfoResource.class);

    private static final String ENTITY_NAME = "noticeInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoticeInfoService noticeInfoService;

    private final NoticeInfoQueryService noticeInfoQueryService;

    public NoticeInfoResource(NoticeInfoService noticeInfoService, NoticeInfoQueryService noticeInfoQueryService) {
        this.noticeInfoService = noticeInfoService;
        this.noticeInfoQueryService = noticeInfoQueryService;
    }

    /**
     * {@code POST  /notice-infos} : Create a new noticeInfo.
     *
     * @param noticeInfoDTO the noticeInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noticeInfoDTO, or with status {@code 400 (Bad Request)} if the noticeInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notice-infos")
    public ResponseEntity<NoticeInfoDTO> createNoticeInfo(@Valid @RequestBody NoticeInfoDTO noticeInfoDTO) throws URISyntaxException {
        log.debug("REST request to save NoticeInfo : {}", noticeInfoDTO);
        if (noticeInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new noticeInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoticeInfoDTO result = noticeInfoService.save(noticeInfoDTO);
        return ResponseEntity.created(new URI("/api/notice-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notice-infos} : Updates an existing noticeInfo.
     *
     * @param noticeInfoDTO the noticeInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noticeInfoDTO,
     * or with status {@code 400 (Bad Request)} if the noticeInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noticeInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notice-infos")
    public ResponseEntity<NoticeInfoDTO> updateNoticeInfo(@Valid @RequestBody NoticeInfoDTO noticeInfoDTO) throws URISyntaxException {
        log.debug("REST request to update NoticeInfo : {}", noticeInfoDTO);
        if (noticeInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoticeInfoDTO result = noticeInfoService.save(noticeInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noticeInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notice-infos} : get all the noticeInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noticeInfos in body.
     */
    @GetMapping("/notice-infos")
    public ResponseEntity<List<NoticeInfoDTO>> getAllNoticeInfos(NoticeInfoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NoticeInfos by criteria: {}", criteria);
        Page<NoticeInfoDTO> page = noticeInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notice-infos/count} : count all the noticeInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/notice-infos/count")
    public ResponseEntity<Long> countNoticeInfos(NoticeInfoCriteria criteria) {
        log.debug("REST request to count NoticeInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(noticeInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /notice-infos/:id} : get the "id" noticeInfo.
     *
     * @param id the id of the noticeInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noticeInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notice-infos/{id}")
    public ResponseEntity<NoticeInfoDTO> getNoticeInfo(@PathVariable Long id) {
        log.debug("REST request to get NoticeInfo : {}", id);
        Optional<NoticeInfoDTO> noticeInfoDTO = noticeInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noticeInfoDTO);
    }

    /**
     * {@code DELETE  /notice-infos/:id} : delete the "id" noticeInfo.
     *
     * @param id the id of the noticeInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notice-infos/{id}")
    public ResponseEntity<Void> deleteNoticeInfo(@PathVariable Long id) {
        log.debug("REST request to delete NoticeInfo : {}", id);
        noticeInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
