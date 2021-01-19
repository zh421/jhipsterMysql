package aiot.java.web.rest;

import aiot.java.service.EventStatcodeService;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.EventStatcodeDTO;
import aiot.java.service.dto.EventStatcodeCriteria;
import aiot.java.service.EventStatcodeQueryService;

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
 * REST controller for managing {@link aiot.java.domain.EventStatcode}.
 */
@RestController
@RequestMapping("/api")
public class EventStatcodeResource {

    private final Logger log = LoggerFactory.getLogger(EventStatcodeResource.class);

    private static final String ENTITY_NAME = "eventStatcode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventStatcodeService eventStatcodeService;

    private final EventStatcodeQueryService eventStatcodeQueryService;

    public EventStatcodeResource(EventStatcodeService eventStatcodeService, EventStatcodeQueryService eventStatcodeQueryService) {
        this.eventStatcodeService = eventStatcodeService;
        this.eventStatcodeQueryService = eventStatcodeQueryService;
    }

    /**
     * {@code POST  /event-statcodes} : Create a new eventStatcode.
     *
     * @param eventStatcodeDTO the eventStatcodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventStatcodeDTO, or with status {@code 400 (Bad Request)} if the eventStatcode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-statcodes")
    public ResponseEntity<EventStatcodeDTO> createEventStatcode(@Valid @RequestBody EventStatcodeDTO eventStatcodeDTO) throws URISyntaxException {
        log.debug("REST request to save EventStatcode : {}", eventStatcodeDTO);
        if (eventStatcodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventStatcode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventStatcodeDTO result = eventStatcodeService.save(eventStatcodeDTO);
        return ResponseEntity.created(new URI("/api/event-statcodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-statcodes} : Updates an existing eventStatcode.
     *
     * @param eventStatcodeDTO the eventStatcodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventStatcodeDTO,
     * or with status {@code 400 (Bad Request)} if the eventStatcodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventStatcodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-statcodes")
    public ResponseEntity<EventStatcodeDTO> updateEventStatcode(@Valid @RequestBody EventStatcodeDTO eventStatcodeDTO) throws URISyntaxException {
        log.debug("REST request to update EventStatcode : {}", eventStatcodeDTO);
        if (eventStatcodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventStatcodeDTO result = eventStatcodeService.save(eventStatcodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventStatcodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-statcodes} : get all the eventStatcodes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventStatcodes in body.
     */
    @GetMapping("/event-statcodes")
    public ResponseEntity<List<EventStatcodeDTO>> getAllEventStatcodes(EventStatcodeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EventStatcodes by criteria: {}", criteria);
        Page<EventStatcodeDTO> page = eventStatcodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /event-statcodes/count} : count all the eventStatcodes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/event-statcodes/count")
    public ResponseEntity<Long> countEventStatcodes(EventStatcodeCriteria criteria) {
        log.debug("REST request to count EventStatcodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventStatcodeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /event-statcodes/:id} : get the "id" eventStatcode.
     *
     * @param id the id of the eventStatcodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventStatcodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-statcodes/{id}")
    public ResponseEntity<EventStatcodeDTO> getEventStatcode(@PathVariable Long id) {
        log.debug("REST request to get EventStatcode : {}", id);
        Optional<EventStatcodeDTO> eventStatcodeDTO = eventStatcodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventStatcodeDTO);
    }

    /**
     * {@code DELETE  /event-statcodes/:id} : delete the "id" eventStatcode.
     *
     * @param id the id of the eventStatcodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-statcodes/{id}")
    public ResponseEntity<Void> deleteEventStatcode(@PathVariable Long id) {
        log.debug("REST request to delete EventStatcode : {}", id);
        eventStatcodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
