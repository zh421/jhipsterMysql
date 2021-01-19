package aiot.java.web.rest;

import aiot.java.service.UnitClassService;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.UnitClassDTO;
import aiot.java.service.dto.UnitClassCriteria;
import aiot.java.service.UnitClassQueryService;

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
 * REST controller for managing {@link aiot.java.domain.UnitClass}.
 */
@RestController
@RequestMapping("/api")
public class UnitClassResource {

    private final Logger log = LoggerFactory.getLogger(UnitClassResource.class);

    private static final String ENTITY_NAME = "unitClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnitClassService unitClassService;

    private final UnitClassQueryService unitClassQueryService;

    public UnitClassResource(UnitClassService unitClassService, UnitClassQueryService unitClassQueryService) {
        this.unitClassService = unitClassService;
        this.unitClassQueryService = unitClassQueryService;
    }

    /**
     * {@code POST  /unit-classes} : Create a new unitClass.
     *
     * @param unitClassDTO the unitClassDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unitClassDTO, or with status {@code 400 (Bad Request)} if the unitClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unit-classes")
    public ResponseEntity<UnitClassDTO> createUnitClass(@Valid @RequestBody UnitClassDTO unitClassDTO) throws URISyntaxException {
        log.debug("REST request to save UnitClass : {}", unitClassDTO);
        if (unitClassDTO.getId() != null) {
            throw new BadRequestAlertException("A new unitClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitClassDTO result = unitClassService.save(unitClassDTO);
        return ResponseEntity.created(new URI("/api/unit-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unit-classes} : Updates an existing unitClass.
     *
     * @param unitClassDTO the unitClassDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitClassDTO,
     * or with status {@code 400 (Bad Request)} if the unitClassDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unitClassDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unit-classes")
    public ResponseEntity<UnitClassDTO> updateUnitClass(@Valid @RequestBody UnitClassDTO unitClassDTO) throws URISyntaxException {
        log.debug("REST request to update UnitClass : {}", unitClassDTO);
        if (unitClassDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnitClassDTO result = unitClassService.save(unitClassDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitClassDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unit-classes} : get all the unitClasses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unitClasses in body.
     */
    @GetMapping("/unit-classes")
    public ResponseEntity<List<UnitClassDTO>> getAllUnitClasses(UnitClassCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UnitClasses by criteria: {}", criteria);
        Page<UnitClassDTO> page = unitClassQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unit-classes/count} : count all the unitClasses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unit-classes/count")
    public ResponseEntity<Long> countUnitClasses(UnitClassCriteria criteria) {
        log.debug("REST request to count UnitClasses by criteria: {}", criteria);
        return ResponseEntity.ok().body(unitClassQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unit-classes/:id} : get the "id" unitClass.
     *
     * @param id the id of the unitClassDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unitClassDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unit-classes/{id}")
    public ResponseEntity<UnitClassDTO> getUnitClass(@PathVariable Long id) {
        log.debug("REST request to get UnitClass : {}", id);
        Optional<UnitClassDTO> unitClassDTO = unitClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unitClassDTO);
    }

    /**
     * {@code DELETE  /unit-classes/:id} : delete the "id" unitClass.
     *
     * @param id the id of the unitClassDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unit-classes/{id}")
    public ResponseEntity<Void> deleteUnitClass(@PathVariable Long id) {
        log.debug("REST request to delete UnitClass : {}", id);
        unitClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
