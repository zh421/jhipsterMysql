package aiot.java.web.rest;

import aiot.java.service.DeviceCodeService;
import aiot.java.util.StringFilterUtils;
import aiot.java.web.rest.errors.BadRequestAlertException;
import aiot.java.service.dto.DeviceCodeDTO;
import aiot.java.service.dto.DeviceCodeCriteria;
import aiot.java.service.DeviceCodeQueryService;

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
 * REST controller for managing {@link aiot.java.domain.DeviceCode}.
 */
@RestController
@RequestMapping("/api")
public class DeviceCodeResource {

    private final Logger log = LoggerFactory.getLogger(DeviceCodeResource.class);

    private static final String ENTITY_NAME = "deviceCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeviceCodeService deviceCodeService;

    private final DeviceCodeQueryService deviceCodeQueryService;

    public DeviceCodeResource(DeviceCodeService deviceCodeService, DeviceCodeQueryService deviceCodeQueryService) {
        this.deviceCodeService = deviceCodeService;
        this.deviceCodeQueryService = deviceCodeQueryService;
    }

    /**
     * {@code POST  /device-codes} : Create a new deviceCode.
     *
     * @param deviceCodeDTO the deviceCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceCodeDTO, or with status {@code 400 (Bad Request)} if the deviceCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/device-codes")
    public ResponseEntity<DeviceCodeDTO> createDeviceCode(@Valid @RequestBody DeviceCodeDTO deviceCodeDTO) throws URISyntaxException {
        log.debug("REST request to save DeviceCode : {}", deviceCodeDTO);
        if (deviceCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new deviceCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceCodeDTO result = deviceCodeService.save(deviceCodeDTO);
        return ResponseEntity.created(new URI("/api/device-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /device-codes} : Updates an existing deviceCode.
     *
     * @param deviceCodeDTO the deviceCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceCodeDTO,
     * or with status {@code 400 (Bad Request)} if the deviceCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviceCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/device-codes")
    public ResponseEntity<DeviceCodeDTO> updateDeviceCode(@Valid @RequestBody DeviceCodeDTO deviceCodeDTO) throws URISyntaxException {
        log.debug("REST request to update DeviceCode : {}", deviceCodeDTO);
        if (deviceCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceCodeDTO result = deviceCodeService.save(deviceCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /device-codes} : get all the deviceCodes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviceCodes in body.
     */
    @GetMapping("/device-codes")
    public ResponseEntity<List<DeviceCodeDTO>> getAllDeviceCodes(DeviceCodeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DeviceCodes by criteria: {}", criteria);
        Page<DeviceCodeDTO> page = deviceCodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code POST  /device-codes} : Search deviceCode.
     *
     * @param deviceCodeDTO the deviceCodeDTO to search.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceCodeDTO, or with status {@code 400 (Bad Request)} if the deviceCode has already an ID.
     *
     */
    @PostMapping("/device-codes/search")
    public ResponseEntity<List<DeviceCodeDTO>> searchDeviceCode(@RequestBody DeviceCodeDTO deviceCodeDTO, DeviceCodeCriteria criteria, Pageable pageable) {
        log.debug("REST request to save DeviceCode : {}", deviceCodeDTO);
        criteria.setDviCode(StringFilterUtils.toContainStringFilter(deviceCodeDTO.getDviCode()));
        criteria.setDviName(StringFilterUtils.toContainStringFilter(deviceCodeDTO.getDviName()));
        Page<DeviceCodeDTO> page = deviceCodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /device-codes/count} : count all the deviceCodes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/device-codes/count")
    public ResponseEntity<Long> countDeviceCodes(DeviceCodeCriteria criteria) {
        log.debug("REST request to count DeviceCodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(deviceCodeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /device-codes/:id} : get the "id" deviceCode.
     *
     * @param id the id of the deviceCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviceCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/device-codes/{id}")
    public ResponseEntity<DeviceCodeDTO> getDeviceCode(@PathVariable Long id) {
        log.debug("REST request to get DeviceCode : {}", id);
        Optional<DeviceCodeDTO> deviceCodeDTO = deviceCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceCodeDTO);
    }

    /**
     * {@code DELETE  /device-codes/:id} : delete the "id" deviceCode.
     *
     * @param id the id of the deviceCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/device-codes/{id}")
    public ResponseEntity<Void> deleteDeviceCode(@PathVariable Long id) {
        log.debug("REST request to delete DeviceCode : {}", id);
        deviceCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
