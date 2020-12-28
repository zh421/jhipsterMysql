package aiot.java.web.rest;

import aiot.java.service.DevicePatternIntroService;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.DevicePatternIntroDTO;
import aiot.java.service.dto.DevicePatternIntroCriteria;
import aiot.java.service.DevicePatternIntroQueryService;

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
 * REST controller for managing {@link aiot.java.domain.DevicePatternIntro}.
 */
@RestController
@RequestMapping("/api")
public class DevicePatternIntroResource {

    private final Logger log = LoggerFactory.getLogger(DevicePatternIntroResource.class);

    private static final String ENTITY_NAME = "devicePatternIntro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DevicePatternIntroService devicePatternIntroService;

    private final DevicePatternIntroQueryService devicePatternIntroQueryService;

    public DevicePatternIntroResource(DevicePatternIntroService devicePatternIntroService, DevicePatternIntroQueryService devicePatternIntroQueryService) {
        this.devicePatternIntroService = devicePatternIntroService;
        this.devicePatternIntroQueryService = devicePatternIntroQueryService;
    }

    /**
     * {@code POST  /device-pattern-intros} : Create a new devicePatternIntro.
     *
     * @param devicePatternIntroDTO the devicePatternIntroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new devicePatternIntroDTO, or with status {@code 400 (Bad Request)} if the devicePatternIntro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/device-pattern-intros")
    public ResponseEntity<DevicePatternIntroDTO> createDevicePatternIntro(@Valid @RequestBody DevicePatternIntroDTO devicePatternIntroDTO) throws URISyntaxException {
        log.debug("REST request to save DevicePatternIntro : {}", devicePatternIntroDTO);
        if (devicePatternIntroDTO.getId() != null) {
            throw new BadRequestAlertException("A new devicePatternIntro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DevicePatternIntroDTO result = devicePatternIntroService.save(devicePatternIntroDTO);
        return ResponseEntity.created(new URI("/api/device-pattern-intros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /device-pattern-intros} : Updates an existing devicePatternIntro.
     *
     * @param devicePatternIntroDTO the devicePatternIntroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated devicePatternIntroDTO,
     * or with status {@code 400 (Bad Request)} if the devicePatternIntroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the devicePatternIntroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/device-pattern-intros")
    public ResponseEntity<DevicePatternIntroDTO> updateDevicePatternIntro(@Valid @RequestBody DevicePatternIntroDTO devicePatternIntroDTO) throws URISyntaxException {
        log.debug("REST request to update DevicePatternIntro : {}", devicePatternIntroDTO);
        if (devicePatternIntroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DevicePatternIntroDTO result = devicePatternIntroService.save(devicePatternIntroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, devicePatternIntroDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /device-pattern-intros} : get all the devicePatternIntros.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of devicePatternIntros in body.
     */
    @GetMapping("/device-pattern-intros")
    public ResponseEntity<List<DevicePatternIntroDTO>> getAllDevicePatternIntros(DevicePatternIntroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DevicePatternIntros by criteria: {}", criteria);
        Page<DevicePatternIntroDTO> page = devicePatternIntroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /device-pattern-intros/count} : count all the devicePatternIntros.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/device-pattern-intros/count")
    public ResponseEntity<Long> countDevicePatternIntros(DevicePatternIntroCriteria criteria) {
        log.debug("REST request to count DevicePatternIntros by criteria: {}", criteria);
        return ResponseEntity.ok().body(devicePatternIntroQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /device-pattern-intros/:id} : get the "id" devicePatternIntro.
     *
     * @param id the id of the devicePatternIntroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the devicePatternIntroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/device-pattern-intros/{id}")
    public ResponseEntity<DevicePatternIntroDTO> getDevicePatternIntro(@PathVariable Long id) {
        log.debug("REST request to get DevicePatternIntro : {}", id);
        Optional<DevicePatternIntroDTO> devicePatternIntroDTO = devicePatternIntroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(devicePatternIntroDTO);
    }

    /**
     * {@code DELETE  /device-pattern-intros/:id} : delete the "id" devicePatternIntro.
     *
     * @param id the id of the devicePatternIntroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/device-pattern-intros/{id}")
    public ResponseEntity<Void> deleteDevicePatternIntro(@PathVariable Long id) {
        log.debug("REST request to delete DevicePatternIntro : {}", id);
        devicePatternIntroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
