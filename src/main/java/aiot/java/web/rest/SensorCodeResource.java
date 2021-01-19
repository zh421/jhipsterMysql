package aiot.java.web.rest;

import aiot.java.service.SensorCodeService;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.SensorCodeDTO;
import aiot.java.service.dto.SensorCodeCriteria;
import aiot.java.service.SensorCodeQueryService;

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
 * REST controller for managing {@link aiot.java.domain.SensorCode}.
 */
@RestController
@RequestMapping("/api")
public class SensorCodeResource {

    private final Logger log = LoggerFactory.getLogger(SensorCodeResource.class);

    private static final String ENTITY_NAME = "sensorCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SensorCodeService sensorCodeService;

    private final SensorCodeQueryService sensorCodeQueryService;

    public SensorCodeResource(SensorCodeService sensorCodeService, SensorCodeQueryService sensorCodeQueryService) {
        this.sensorCodeService = sensorCodeService;
        this.sensorCodeQueryService = sensorCodeQueryService;
    }

    /**
     * {@code POST  /sensor-codes} : Create a new sensorCode.
     *
     * @param sensorCodeDTO the sensorCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sensorCodeDTO, or with status {@code 400 (Bad Request)} if the sensorCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sensor-codes")
    public ResponseEntity<SensorCodeDTO> createSensorCode(@Valid @RequestBody SensorCodeDTO sensorCodeDTO) throws URISyntaxException {
        log.debug("REST request to save SensorCode : {}", sensorCodeDTO);
        if (sensorCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sensorCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SensorCodeDTO result = sensorCodeService.save(sensorCodeDTO);
        return ResponseEntity.created(new URI("/api/sensor-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sensor-codes} : Updates an existing sensorCode.
     *
     * @param sensorCodeDTO the sensorCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sensorCodeDTO,
     * or with status {@code 400 (Bad Request)} if the sensorCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sensorCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sensor-codes")
    public ResponseEntity<SensorCodeDTO> updateSensorCode(@Valid @RequestBody SensorCodeDTO sensorCodeDTO) throws URISyntaxException {
        log.debug("REST request to update SensorCode : {}", sensorCodeDTO);
        if (sensorCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SensorCodeDTO result = sensorCodeService.save(sensorCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sensorCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sensor-codes} : get all the sensorCodes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sensorCodes in body.
     */
    @GetMapping("/sensor-codes")
    public ResponseEntity<List<SensorCodeDTO>> getAllSensorCodes(SensorCodeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SensorCodes by criteria: {}", criteria);
        Page<SensorCodeDTO> page = sensorCodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sensor-codes/count} : count all the sensorCodes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/sensor-codes/count")
    public ResponseEntity<Long> countSensorCodes(SensorCodeCriteria criteria) {
        log.debug("REST request to count SensorCodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(sensorCodeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /sensor-codes/:id} : get the "id" sensorCode.
     *
     * @param id the id of the sensorCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sensorCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sensor-codes/{id}")
    public ResponseEntity<SensorCodeDTO> getSensorCode(@PathVariable Long id) {
        log.debug("REST request to get SensorCode : {}", id);
        Optional<SensorCodeDTO> sensorCodeDTO = sensorCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sensorCodeDTO);
    }

    /**
     * {@code DELETE  /sensor-codes/:id} : delete the "id" sensorCode.
     *
     * @param id the id of the sensorCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sensor-codes/{id}")
    public ResponseEntity<Void> deleteSensorCode(@PathVariable Long id) {
        log.debug("REST request to delete SensorCode : {}", id);
        sensorCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
