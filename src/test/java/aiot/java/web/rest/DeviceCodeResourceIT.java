package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.DeviceCode;
import aiot.java.repository.DeviceCodeRepository;
import aiot.java.service.DeviceCodeService;
import aiot.java.service.dto.DeviceCodeDTO;
import aiot.java.service.mapper.DeviceCodeMapper;
import aiot.java.service.dto.DeviceCodeCriteria;
import aiot.java.service.DeviceCodeQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static aiot.java.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DeviceCodeResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DeviceCodeResourceIT {

    private static final String DEFAULT_DVI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DVI_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DVI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DVI_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DVI_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DVI_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_DVI_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_DVI_CREID = "AAAAAAAAAA";
    private static final String UPDATED_DVI_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DVI_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DVI_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_DVI_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_DVI_MODID = "AAAAAAAAAA";
    private static final String UPDATED_DVI_MODID = "BBBBBBBBBB";

    @Autowired
    private DeviceCodeRepository deviceCodeRepository;

    @Autowired
    private DeviceCodeMapper deviceCodeMapper;

    @Autowired
    private DeviceCodeService deviceCodeService;

    @Autowired
    private DeviceCodeQueryService deviceCodeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeviceCodeMockMvc;

    private DeviceCode deviceCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceCode createEntity(EntityManager em) {
        DeviceCode deviceCode = new DeviceCode()
            .dviCode(DEFAULT_DVI_CODE)
            .dviName(DEFAULT_DVI_NAME)
            .dviCretime(DEFAULT_DVI_CRETIME)
            .dviCreid(DEFAULT_DVI_CREID)
            .dviModtime(DEFAULT_DVI_MODTIME)
            .dviModid(DEFAULT_DVI_MODID);
        return deviceCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceCode createUpdatedEntity(EntityManager em) {
        DeviceCode deviceCode = new DeviceCode()
            .dviCode(UPDATED_DVI_CODE)
            .dviName(UPDATED_DVI_NAME)
            .dviCretime(UPDATED_DVI_CRETIME)
            .dviCreid(UPDATED_DVI_CREID)
            .dviModtime(UPDATED_DVI_MODTIME)
            .dviModid(UPDATED_DVI_MODID);
        return deviceCode;
    }

    @BeforeEach
    public void initTest() {
        deviceCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceCode() throws Exception {
        int databaseSizeBeforeCreate = deviceCodeRepository.findAll().size();

        // Create the DeviceCode
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);
        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isCreated());

        // Validate the DeviceCode in the database
        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceCode testDeviceCode = deviceCodeList.get(deviceCodeList.size() - 1);
        assertThat(testDeviceCode.getDviCode()).isEqualTo(DEFAULT_DVI_CODE);
        assertThat(testDeviceCode.getDviName()).isEqualTo(DEFAULT_DVI_NAME);
        assertThat(testDeviceCode.getDviCretime()).isEqualTo(DEFAULT_DVI_CRETIME);
        assertThat(testDeviceCode.getDviCreid()).isEqualTo(DEFAULT_DVI_CREID);
        assertThat(testDeviceCode.getDviModtime()).isEqualTo(DEFAULT_DVI_MODTIME);
        assertThat(testDeviceCode.getDviModid()).isEqualTo(DEFAULT_DVI_MODID);
    }

    @Test
    @Transactional
    public void createDeviceCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceCodeRepository.findAll().size();

        // Create the DeviceCode with an existing ID
        deviceCode.setId(1L);
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceCode in the database
        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDviCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviCode(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDviNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviName(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDviCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviCretime(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDviCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviCreid(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDviModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviModtime(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDviModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceCodeRepository.findAll().size();
        // set the field null
        deviceCode.setDviModid(null);

        // Create the DeviceCode, which fails.
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        restDeviceCodeMockMvc.perform(post("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeviceCodes() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList
        restDeviceCodeMockMvc.perform(get("/api/device-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].dviCode").value(hasItem(DEFAULT_DVI_CODE)))
            .andExpect(jsonPath("$.[*].dviName").value(hasItem(DEFAULT_DVI_NAME)))
            .andExpect(jsonPath("$.[*].dviCretime").value(hasItem(sameInstant(DEFAULT_DVI_CRETIME))))
            .andExpect(jsonPath("$.[*].dviCreid").value(hasItem(DEFAULT_DVI_CREID)))
            .andExpect(jsonPath("$.[*].dviModtime").value(hasItem(sameInstant(DEFAULT_DVI_MODTIME))))
            .andExpect(jsonPath("$.[*].dviModid").value(hasItem(DEFAULT_DVI_MODID)));
    }
    
    @Test
    @Transactional
    public void getDeviceCode() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get the deviceCode
        restDeviceCodeMockMvc.perform(get("/api/device-codes/{id}", deviceCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deviceCode.getId().intValue()))
            .andExpect(jsonPath("$.dviCode").value(DEFAULT_DVI_CODE))
            .andExpect(jsonPath("$.dviName").value(DEFAULT_DVI_NAME))
            .andExpect(jsonPath("$.dviCretime").value(sameInstant(DEFAULT_DVI_CRETIME)))
            .andExpect(jsonPath("$.dviCreid").value(DEFAULT_DVI_CREID))
            .andExpect(jsonPath("$.dviModtime").value(sameInstant(DEFAULT_DVI_MODTIME)))
            .andExpect(jsonPath("$.dviModid").value(DEFAULT_DVI_MODID));
    }


    @Test
    @Transactional
    public void getDeviceCodesByIdFiltering() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        Long id = deviceCode.getId();

        defaultDeviceCodeShouldBeFound("id.equals=" + id);
        defaultDeviceCodeShouldNotBeFound("id.notEquals=" + id);

        defaultDeviceCodeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDeviceCodeShouldNotBeFound("id.greaterThan=" + id);

        defaultDeviceCodeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDeviceCodeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode equals to DEFAULT_DVI_CODE
        defaultDeviceCodeShouldBeFound("dviCode.equals=" + DEFAULT_DVI_CODE);

        // Get all the deviceCodeList where dviCode equals to UPDATED_DVI_CODE
        defaultDeviceCodeShouldNotBeFound("dviCode.equals=" + UPDATED_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode not equals to DEFAULT_DVI_CODE
        defaultDeviceCodeShouldNotBeFound("dviCode.notEquals=" + DEFAULT_DVI_CODE);

        // Get all the deviceCodeList where dviCode not equals to UPDATED_DVI_CODE
        defaultDeviceCodeShouldBeFound("dviCode.notEquals=" + UPDATED_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode in DEFAULT_DVI_CODE or UPDATED_DVI_CODE
        defaultDeviceCodeShouldBeFound("dviCode.in=" + DEFAULT_DVI_CODE + "," + UPDATED_DVI_CODE);

        // Get all the deviceCodeList where dviCode equals to UPDATED_DVI_CODE
        defaultDeviceCodeShouldNotBeFound("dviCode.in=" + UPDATED_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode is not null
        defaultDeviceCodeShouldBeFound("dviCode.specified=true");

        // Get all the deviceCodeList where dviCode is null
        defaultDeviceCodeShouldNotBeFound("dviCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode contains DEFAULT_DVI_CODE
        defaultDeviceCodeShouldBeFound("dviCode.contains=" + DEFAULT_DVI_CODE);

        // Get all the deviceCodeList where dviCode contains UPDATED_DVI_CODE
        defaultDeviceCodeShouldNotBeFound("dviCode.contains=" + UPDATED_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCodeNotContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCode does not contain DEFAULT_DVI_CODE
        defaultDeviceCodeShouldNotBeFound("dviCode.doesNotContain=" + DEFAULT_DVI_CODE);

        // Get all the deviceCodeList where dviCode does not contain UPDATED_DVI_CODE
        defaultDeviceCodeShouldBeFound("dviCode.doesNotContain=" + UPDATED_DVI_CODE);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviNameIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName equals to DEFAULT_DVI_NAME
        defaultDeviceCodeShouldBeFound("dviName.equals=" + DEFAULT_DVI_NAME);

        // Get all the deviceCodeList where dviName equals to UPDATED_DVI_NAME
        defaultDeviceCodeShouldNotBeFound("dviName.equals=" + UPDATED_DVI_NAME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName not equals to DEFAULT_DVI_NAME
        defaultDeviceCodeShouldNotBeFound("dviName.notEquals=" + DEFAULT_DVI_NAME);

        // Get all the deviceCodeList where dviName not equals to UPDATED_DVI_NAME
        defaultDeviceCodeShouldBeFound("dviName.notEquals=" + UPDATED_DVI_NAME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviNameIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName in DEFAULT_DVI_NAME or UPDATED_DVI_NAME
        defaultDeviceCodeShouldBeFound("dviName.in=" + DEFAULT_DVI_NAME + "," + UPDATED_DVI_NAME);

        // Get all the deviceCodeList where dviName equals to UPDATED_DVI_NAME
        defaultDeviceCodeShouldNotBeFound("dviName.in=" + UPDATED_DVI_NAME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName is not null
        defaultDeviceCodeShouldBeFound("dviName.specified=true");

        // Get all the deviceCodeList where dviName is null
        defaultDeviceCodeShouldNotBeFound("dviName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeviceCodesByDviNameContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName contains DEFAULT_DVI_NAME
        defaultDeviceCodeShouldBeFound("dviName.contains=" + DEFAULT_DVI_NAME);

        // Get all the deviceCodeList where dviName contains UPDATED_DVI_NAME
        defaultDeviceCodeShouldNotBeFound("dviName.contains=" + UPDATED_DVI_NAME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviNameNotContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviName does not contain DEFAULT_DVI_NAME
        defaultDeviceCodeShouldNotBeFound("dviName.doesNotContain=" + DEFAULT_DVI_NAME);

        // Get all the deviceCodeList where dviName does not contain UPDATED_DVI_NAME
        defaultDeviceCodeShouldBeFound("dviName.doesNotContain=" + UPDATED_DVI_NAME);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime equals to DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.equals=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime equals to UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.equals=" + UPDATED_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime not equals to DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.notEquals=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime not equals to UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.notEquals=" + UPDATED_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime in DEFAULT_DVI_CRETIME or UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.in=" + DEFAULT_DVI_CRETIME + "," + UPDATED_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime equals to UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.in=" + UPDATED_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime is not null
        defaultDeviceCodeShouldBeFound("dviCretime.specified=true");

        // Get all the deviceCodeList where dviCretime is null
        defaultDeviceCodeShouldNotBeFound("dviCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime is greater than or equal to DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.greaterThanOrEqual=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime is greater than or equal to UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.greaterThanOrEqual=" + UPDATED_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime is less than or equal to DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.lessThanOrEqual=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime is less than or equal to SMALLER_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.lessThanOrEqual=" + SMALLER_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime is less than DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.lessThan=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime is less than UPDATED_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.lessThan=" + UPDATED_DVI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCretime is greater than DEFAULT_DVI_CRETIME
        defaultDeviceCodeShouldNotBeFound("dviCretime.greaterThan=" + DEFAULT_DVI_CRETIME);

        // Get all the deviceCodeList where dviCretime is greater than SMALLER_DVI_CRETIME
        defaultDeviceCodeShouldBeFound("dviCretime.greaterThan=" + SMALLER_DVI_CRETIME);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid equals to DEFAULT_DVI_CREID
        defaultDeviceCodeShouldBeFound("dviCreid.equals=" + DEFAULT_DVI_CREID);

        // Get all the deviceCodeList where dviCreid equals to UPDATED_DVI_CREID
        defaultDeviceCodeShouldNotBeFound("dviCreid.equals=" + UPDATED_DVI_CREID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid not equals to DEFAULT_DVI_CREID
        defaultDeviceCodeShouldNotBeFound("dviCreid.notEquals=" + DEFAULT_DVI_CREID);

        // Get all the deviceCodeList where dviCreid not equals to UPDATED_DVI_CREID
        defaultDeviceCodeShouldBeFound("dviCreid.notEquals=" + UPDATED_DVI_CREID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid in DEFAULT_DVI_CREID or UPDATED_DVI_CREID
        defaultDeviceCodeShouldBeFound("dviCreid.in=" + DEFAULT_DVI_CREID + "," + UPDATED_DVI_CREID);

        // Get all the deviceCodeList where dviCreid equals to UPDATED_DVI_CREID
        defaultDeviceCodeShouldNotBeFound("dviCreid.in=" + UPDATED_DVI_CREID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid is not null
        defaultDeviceCodeShouldBeFound("dviCreid.specified=true");

        // Get all the deviceCodeList where dviCreid is null
        defaultDeviceCodeShouldNotBeFound("dviCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid contains DEFAULT_DVI_CREID
        defaultDeviceCodeShouldBeFound("dviCreid.contains=" + DEFAULT_DVI_CREID);

        // Get all the deviceCodeList where dviCreid contains UPDATED_DVI_CREID
        defaultDeviceCodeShouldNotBeFound("dviCreid.contains=" + UPDATED_DVI_CREID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviCreidNotContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviCreid does not contain DEFAULT_DVI_CREID
        defaultDeviceCodeShouldNotBeFound("dviCreid.doesNotContain=" + DEFAULT_DVI_CREID);

        // Get all the deviceCodeList where dviCreid does not contain UPDATED_DVI_CREID
        defaultDeviceCodeShouldBeFound("dviCreid.doesNotContain=" + UPDATED_DVI_CREID);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime equals to DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.equals=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime equals to UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.equals=" + UPDATED_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime not equals to DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.notEquals=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime not equals to UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.notEquals=" + UPDATED_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime in DEFAULT_DVI_MODTIME or UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.in=" + DEFAULT_DVI_MODTIME + "," + UPDATED_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime equals to UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.in=" + UPDATED_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime is not null
        defaultDeviceCodeShouldBeFound("dviModtime.specified=true");

        // Get all the deviceCodeList where dviModtime is null
        defaultDeviceCodeShouldNotBeFound("dviModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime is greater than or equal to DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.greaterThanOrEqual=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime is greater than or equal to UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.greaterThanOrEqual=" + UPDATED_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime is less than or equal to DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.lessThanOrEqual=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime is less than or equal to SMALLER_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.lessThanOrEqual=" + SMALLER_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime is less than DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.lessThan=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime is less than UPDATED_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.lessThan=" + UPDATED_DVI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModtime is greater than DEFAULT_DVI_MODTIME
        defaultDeviceCodeShouldNotBeFound("dviModtime.greaterThan=" + DEFAULT_DVI_MODTIME);

        // Get all the deviceCodeList where dviModtime is greater than SMALLER_DVI_MODTIME
        defaultDeviceCodeShouldBeFound("dviModtime.greaterThan=" + SMALLER_DVI_MODTIME);
    }


    @Test
    @Transactional
    public void getAllDeviceCodesByDviModidIsEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid equals to DEFAULT_DVI_MODID
        defaultDeviceCodeShouldBeFound("dviModid.equals=" + DEFAULT_DVI_MODID);

        // Get all the deviceCodeList where dviModid equals to UPDATED_DVI_MODID
        defaultDeviceCodeShouldNotBeFound("dviModid.equals=" + UPDATED_DVI_MODID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid not equals to DEFAULT_DVI_MODID
        defaultDeviceCodeShouldNotBeFound("dviModid.notEquals=" + DEFAULT_DVI_MODID);

        // Get all the deviceCodeList where dviModid not equals to UPDATED_DVI_MODID
        defaultDeviceCodeShouldBeFound("dviModid.notEquals=" + UPDATED_DVI_MODID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModidIsInShouldWork() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid in DEFAULT_DVI_MODID or UPDATED_DVI_MODID
        defaultDeviceCodeShouldBeFound("dviModid.in=" + DEFAULT_DVI_MODID + "," + UPDATED_DVI_MODID);

        // Get all the deviceCodeList where dviModid equals to UPDATED_DVI_MODID
        defaultDeviceCodeShouldNotBeFound("dviModid.in=" + UPDATED_DVI_MODID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid is not null
        defaultDeviceCodeShouldBeFound("dviModid.specified=true");

        // Get all the deviceCodeList where dviModid is null
        defaultDeviceCodeShouldNotBeFound("dviModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllDeviceCodesByDviModidContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid contains DEFAULT_DVI_MODID
        defaultDeviceCodeShouldBeFound("dviModid.contains=" + DEFAULT_DVI_MODID);

        // Get all the deviceCodeList where dviModid contains UPDATED_DVI_MODID
        defaultDeviceCodeShouldNotBeFound("dviModid.contains=" + UPDATED_DVI_MODID);
    }

    @Test
    @Transactional
    public void getAllDeviceCodesByDviModidNotContainsSomething() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        // Get all the deviceCodeList where dviModid does not contain DEFAULT_DVI_MODID
        defaultDeviceCodeShouldNotBeFound("dviModid.doesNotContain=" + DEFAULT_DVI_MODID);

        // Get all the deviceCodeList where dviModid does not contain UPDATED_DVI_MODID
        defaultDeviceCodeShouldBeFound("dviModid.doesNotContain=" + UPDATED_DVI_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDeviceCodeShouldBeFound(String filter) throws Exception {
        restDeviceCodeMockMvc.perform(get("/api/device-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].dviCode").value(hasItem(DEFAULT_DVI_CODE)))
            .andExpect(jsonPath("$.[*].dviName").value(hasItem(DEFAULT_DVI_NAME)))
            .andExpect(jsonPath("$.[*].dviCretime").value(hasItem(sameInstant(DEFAULT_DVI_CRETIME))))
            .andExpect(jsonPath("$.[*].dviCreid").value(hasItem(DEFAULT_DVI_CREID)))
            .andExpect(jsonPath("$.[*].dviModtime").value(hasItem(sameInstant(DEFAULT_DVI_MODTIME))))
            .andExpect(jsonPath("$.[*].dviModid").value(hasItem(DEFAULT_DVI_MODID)));

        // Check, that the count call also returns 1
        restDeviceCodeMockMvc.perform(get("/api/device-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDeviceCodeShouldNotBeFound(String filter) throws Exception {
        restDeviceCodeMockMvc.perform(get("/api/device-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDeviceCodeMockMvc.perform(get("/api/device-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDeviceCode() throws Exception {
        // Get the deviceCode
        restDeviceCodeMockMvc.perform(get("/api/device-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceCode() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        int databaseSizeBeforeUpdate = deviceCodeRepository.findAll().size();

        // Update the deviceCode
        DeviceCode updatedDeviceCode = deviceCodeRepository.findById(deviceCode.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceCode are not directly saved in db
        em.detach(updatedDeviceCode);
        updatedDeviceCode
            .dviCode(UPDATED_DVI_CODE)
            .dviName(UPDATED_DVI_NAME)
            .dviCretime(UPDATED_DVI_CRETIME)
            .dviCreid(UPDATED_DVI_CREID)
            .dviModtime(UPDATED_DVI_MODTIME)
            .dviModid(UPDATED_DVI_MODID);
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(updatedDeviceCode);

        restDeviceCodeMockMvc.perform(put("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isOk());

        // Validate the DeviceCode in the database
        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeUpdate);
        DeviceCode testDeviceCode = deviceCodeList.get(deviceCodeList.size() - 1);
        assertThat(testDeviceCode.getDviCode()).isEqualTo(UPDATED_DVI_CODE);
        assertThat(testDeviceCode.getDviName()).isEqualTo(UPDATED_DVI_NAME);
        assertThat(testDeviceCode.getDviCretime()).isEqualTo(UPDATED_DVI_CRETIME);
        assertThat(testDeviceCode.getDviCreid()).isEqualTo(UPDATED_DVI_CREID);
        assertThat(testDeviceCode.getDviModtime()).isEqualTo(UPDATED_DVI_MODTIME);
        assertThat(testDeviceCode.getDviModid()).isEqualTo(UPDATED_DVI_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceCode() throws Exception {
        int databaseSizeBeforeUpdate = deviceCodeRepository.findAll().size();

        // Create the DeviceCode
        DeviceCodeDTO deviceCodeDTO = deviceCodeMapper.toDto(deviceCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceCodeMockMvc.perform(put("/api/device-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceCode in the database
        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceCode() throws Exception {
        // Initialize the database
        deviceCodeRepository.saveAndFlush(deviceCode);

        int databaseSizeBeforeDelete = deviceCodeRepository.findAll().size();

        // Delete the deviceCode
        restDeviceCodeMockMvc.perform(delete("/api/device-codes/{id}", deviceCode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeviceCode> deviceCodeList = deviceCodeRepository.findAll();
        assertThat(deviceCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
