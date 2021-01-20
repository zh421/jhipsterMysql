package aiot.java.web.rest;

import aiot.java.service.EventInterService;
import aiot.java.util.StringFilterUtils;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.EventInterDTO;
import aiot.java.service.dto.EventInterCriteria;
import aiot.java.service.EventInterQueryService;

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
 * REST controller for managing {@link aiot.java.domain.EventInter}.
 */
@RestController
@RequestMapping("/api")
public class EventInterResource {

    private final Logger log = LoggerFactory.getLogger(EventInterResource.class);

    private static final String ENTITY_NAME = "eventInter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventInterService eventInterService;

    private final EventInterQueryService eventInterQueryService;

    public EventInterResource(EventInterService eventInterService, EventInterQueryService eventInterQueryService) {
        this.eventInterService = eventInterService;
        this.eventInterQueryService = eventInterQueryService;
    }

    /**
     * {@code POST  /event-inters} : Create a new eventInter.
     *
     * @param eventInterDTO the eventInterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventInterDTO, or with status {@code 400 (Bad Request)} if the eventInter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-inters")
    public ResponseEntity<EventInterDTO> createEventInter(@Valid @RequestBody EventInterDTO eventInterDTO) throws URISyntaxException {
        log.debug("REST request to save EventInter : {}", eventInterDTO);
        if (eventInterDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventInter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventInterDTO result = eventInterService.save(eventInterDTO);
        return ResponseEntity.created(new URI("/api/event-inters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-inters} : Updates an existing eventInter.
     *
     * @param eventInterDTO the eventInterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventInterDTO,
     * or with status {@code 400 (Bad Request)} if the eventInterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventInterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-inters")
    public ResponseEntity<EventInterDTO> updateEventInter(@Valid @RequestBody EventInterDTO eventInterDTO) throws URISyntaxException {
        log.debug("REST request to update EventInter : {}", eventInterDTO);
        if (eventInterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventInterDTO result = eventInterService.save(eventInterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventInterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-inters} : get all the eventInters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventInters in body.
     */
    @GetMapping("/event-inters")
    public ResponseEntity<List<EventInterDTO>> getAllEventInters(EventInterCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EventInters by criteria: {}", criteria);
        Page<EventInterDTO> page = eventInterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code POST  /event-inters} : Search a new eventInter.
     *
     * @param eventInterDTO the eventInterDTO to search datas.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventInterDTO, or with status {@code 400 (Bad Request)} if the eventInter has already an ID.
     *
     */
    @PostMapping("/event-inters/search")
    public ResponseEntity<List<EventInterDTO>> searchEventInter( @RequestBody EventInterDTO eventInterDTO, EventInterCriteria criteria, Pageable pageable){
        log.debug("REST request to save EventInter : {}", eventInterDTO);
        criteria.setEvninUnitName(StringFilterUtils.toContainStringFilter(eventInterDTO.getEvninUnitName()));
        criteria.setEvninTheme(StringFilterUtils.toContainStringFilter(eventInterDTO.getEvninTheme()));
        criteria.setEvninEsCode(StringFilterUtils.toContainStringFilter(eventInterDTO.getEvninEsCode()));
        Page<EventInterDTO> page = eventInterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /event-inters/count} : count all the eventInters.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/event-inters/count")
    public ResponseEntity<Long> countEventInters(EventInterCriteria criteria) {
        log.debug("REST request to count EventInters by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventInterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /event-inters/:id} : get the "id" eventInter.
     *
     * @param id the id of the eventInterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventInterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-inters/{id}")
    public ResponseEntity<EventInterDTO> getEventInter(@PathVariable Long id) {
        log.debug("REST request to get EventInter : {}", id);
        Optional<EventInterDTO> eventInterDTO = eventInterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventInterDTO);
    }

    /**
     * {@code DELETE  /event-inters/:id} : delete the "id" eventInter.
     *
     * @param id the id of the eventInterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-inters/{id}")
    public ResponseEntity<Void> deleteEventInter(@PathVariable Long id) {
        log.debug("REST request to delete EventInter : {}", id);
        eventInterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
