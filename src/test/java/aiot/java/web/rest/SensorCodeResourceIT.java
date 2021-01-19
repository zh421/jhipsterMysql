package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.SensorCode;
import aiot.java.repository.SensorCodeRepository;
import aiot.java.service.SensorCodeService;
import aiot.java.service.dto.SensorCodeDTO;
import aiot.java.service.mapper.SensorCodeMapper;
import aiot.java.service.dto.SensorCodeCriteria;
import aiot.java.service.SensorCodeQueryService;

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
 * Integration tests for the {@link SensorCodeResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SensorCodeResourceIT {

    private static final String DEFAULT_SC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SC_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_SC_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SC_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_SC_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_SC_CREID = "AAAAAAAAAA";
    private static final String UPDATED_SC_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_SC_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SC_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_SC_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_SC_MODID = "AAAAAAAAAA";
    private static final String UPDATED_SC_MODID = "BBBBBBBBBB";

    @Autowired
    private SensorCodeRepository sensorCodeRepository;

    @Autowired
    private SensorCodeMapper sensorCodeMapper;

    @Autowired
    private SensorCodeService sensorCodeService;

    @Autowired
    private SensorCodeQueryService sensorCodeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSensorCodeMockMvc;

    private SensorCode sensorCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SensorCode createEntity(EntityManager em) {
        SensorCode sensorCode = new SensorCode()
            .scCode(DEFAULT_SC_CODE)
            .scName(DEFAULT_SC_NAME)
            .scCretime(DEFAULT_SC_CRETIME)
            .scCreid(DEFAULT_SC_CREID)
            .scModtime(DEFAULT_SC_MODTIME)
            .scModid(DEFAULT_SC_MODID);
        return sensorCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SensorCode createUpdatedEntity(EntityManager em) {
        SensorCode sensorCode = new SensorCode()
            .scCode(UPDATED_SC_CODE)
            .scName(UPDATED_SC_NAME)
            .scCretime(UPDATED_SC_CRETIME)
            .scCreid(UPDATED_SC_CREID)
            .scModtime(UPDATED_SC_MODTIME)
            .scModid(UPDATED_SC_MODID);
        return sensorCode;
    }

    @BeforeEach
    public void initTest() {
        sensorCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createSensorCode() throws Exception {
        int databaseSizeBeforeCreate = sensorCodeRepository.findAll().size();

        // Create the SensorCode
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);
        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isCreated());

        // Validate the SensorCode in the database
        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeCreate + 1);
        SensorCode testSensorCode = sensorCodeList.get(sensorCodeList.size() - 1);
        assertThat(testSensorCode.getScCode()).isEqualTo(DEFAULT_SC_CODE);
        assertThat(testSensorCode.getScName()).isEqualTo(DEFAULT_SC_NAME);
        assertThat(testSensorCode.getScCretime()).isEqualTo(DEFAULT_SC_CRETIME);
        assertThat(testSensorCode.getScCreid()).isEqualTo(DEFAULT_SC_CREID);
        assertThat(testSensorCode.getScModtime()).isEqualTo(DEFAULT_SC_MODTIME);
        assertThat(testSensorCode.getScModid()).isEqualTo(DEFAULT_SC_MODID);
    }

    @Test
    @Transactional
    public void createSensorCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sensorCodeRepository.findAll().size();

        // Create the SensorCode with an existing ID
        sensorCode.setId(1L);
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SensorCode in the database
        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkScCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScCode(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScName(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScCretime(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScCreid(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScModtime(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorCodeRepository.findAll().size();
        // set the field null
        sensorCode.setScModid(null);

        // Create the SensorCode, which fails.
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        restSensorCodeMockMvc.perform(post("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSensorCodes() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList
        restSensorCodeMockMvc.perform(get("/api/sensor-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensorCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].scCode").value(hasItem(DEFAULT_SC_CODE)))
            .andExpect(jsonPath("$.[*].scName").value(hasItem(DEFAULT_SC_NAME)))
            .andExpect(jsonPath("$.[*].scCretime").value(hasItem(sameInstant(DEFAULT_SC_CRETIME))))
            .andExpect(jsonPath("$.[*].scCreid").value(hasItem(DEFAULT_SC_CREID)))
            .andExpect(jsonPath("$.[*].scModtime").value(hasItem(sameInstant(DEFAULT_SC_MODTIME))))
            .andExpect(jsonPath("$.[*].scModid").value(hasItem(DEFAULT_SC_MODID)));
    }
    
    @Test
    @Transactional
    public void getSensorCode() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get the sensorCode
        restSensorCodeMockMvc.perform(get("/api/sensor-codes/{id}", sensorCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sensorCode.getId().intValue()))
            .andExpect(jsonPath("$.scCode").value(DEFAULT_SC_CODE))
            .andExpect(jsonPath("$.scName").value(DEFAULT_SC_NAME))
            .andExpect(jsonPath("$.scCretime").value(sameInstant(DEFAULT_SC_CRETIME)))
            .andExpect(jsonPath("$.scCreid").value(DEFAULT_SC_CREID))
            .andExpect(jsonPath("$.scModtime").value(sameInstant(DEFAULT_SC_MODTIME)))
            .andExpect(jsonPath("$.scModid").value(DEFAULT_SC_MODID));
    }


    @Test
    @Transactional
    public void getSensorCodesByIdFiltering() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        Long id = sensorCode.getId();

        defaultSensorCodeShouldBeFound("id.equals=" + id);
        defaultSensorCodeShouldNotBeFound("id.notEquals=" + id);

        defaultSensorCodeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSensorCodeShouldNotBeFound("id.greaterThan=" + id);

        defaultSensorCodeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSensorCodeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode equals to DEFAULT_SC_CODE
        defaultSensorCodeShouldBeFound("scCode.equals=" + DEFAULT_SC_CODE);

        // Get all the sensorCodeList where scCode equals to UPDATED_SC_CODE
        defaultSensorCodeShouldNotBeFound("scCode.equals=" + UPDATED_SC_CODE);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode not equals to DEFAULT_SC_CODE
        defaultSensorCodeShouldNotBeFound("scCode.notEquals=" + DEFAULT_SC_CODE);

        // Get all the sensorCodeList where scCode not equals to UPDATED_SC_CODE
        defaultSensorCodeShouldBeFound("scCode.notEquals=" + UPDATED_SC_CODE);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode in DEFAULT_SC_CODE or UPDATED_SC_CODE
        defaultSensorCodeShouldBeFound("scCode.in=" + DEFAULT_SC_CODE + "," + UPDATED_SC_CODE);

        // Get all the sensorCodeList where scCode equals to UPDATED_SC_CODE
        defaultSensorCodeShouldNotBeFound("scCode.in=" + UPDATED_SC_CODE);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode is not null
        defaultSensorCodeShouldBeFound("scCode.specified=true");

        // Get all the sensorCodeList where scCode is null
        defaultSensorCodeShouldNotBeFound("scCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllSensorCodesByScCodeContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode contains DEFAULT_SC_CODE
        defaultSensorCodeShouldBeFound("scCode.contains=" + DEFAULT_SC_CODE);

        // Get all the sensorCodeList where scCode contains UPDATED_SC_CODE
        defaultSensorCodeShouldNotBeFound("scCode.contains=" + UPDATED_SC_CODE);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCodeNotContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCode does not contain DEFAULT_SC_CODE
        defaultSensorCodeShouldNotBeFound("scCode.doesNotContain=" + DEFAULT_SC_CODE);

        // Get all the sensorCodeList where scCode does not contain UPDATED_SC_CODE
        defaultSensorCodeShouldBeFound("scCode.doesNotContain=" + UPDATED_SC_CODE);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName equals to DEFAULT_SC_NAME
        defaultSensorCodeShouldBeFound("scName.equals=" + DEFAULT_SC_NAME);

        // Get all the sensorCodeList where scName equals to UPDATED_SC_NAME
        defaultSensorCodeShouldNotBeFound("scName.equals=" + UPDATED_SC_NAME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName not equals to DEFAULT_SC_NAME
        defaultSensorCodeShouldNotBeFound("scName.notEquals=" + DEFAULT_SC_NAME);

        // Get all the sensorCodeList where scName not equals to UPDATED_SC_NAME
        defaultSensorCodeShouldBeFound("scName.notEquals=" + UPDATED_SC_NAME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScNameIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName in DEFAULT_SC_NAME or UPDATED_SC_NAME
        defaultSensorCodeShouldBeFound("scName.in=" + DEFAULT_SC_NAME + "," + UPDATED_SC_NAME);

        // Get all the sensorCodeList where scName equals to UPDATED_SC_NAME
        defaultSensorCodeShouldNotBeFound("scName.in=" + UPDATED_SC_NAME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName is not null
        defaultSensorCodeShouldBeFound("scName.specified=true");

        // Get all the sensorCodeList where scName is null
        defaultSensorCodeShouldNotBeFound("scName.specified=false");
    }
                @Test
    @Transactional
    public void getAllSensorCodesByScNameContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName contains DEFAULT_SC_NAME
        defaultSensorCodeShouldBeFound("scName.contains=" + DEFAULT_SC_NAME);

        // Get all the sensorCodeList where scName contains UPDATED_SC_NAME
        defaultSensorCodeShouldNotBeFound("scName.contains=" + UPDATED_SC_NAME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScNameNotContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scName does not contain DEFAULT_SC_NAME
        defaultSensorCodeShouldNotBeFound("scName.doesNotContain=" + DEFAULT_SC_NAME);

        // Get all the sensorCodeList where scName does not contain UPDATED_SC_NAME
        defaultSensorCodeShouldBeFound("scName.doesNotContain=" + UPDATED_SC_NAME);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime equals to DEFAULT_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.equals=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime equals to UPDATED_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.equals=" + UPDATED_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime not equals to DEFAULT_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.notEquals=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime not equals to UPDATED_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.notEquals=" + UPDATED_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime in DEFAULT_SC_CRETIME or UPDATED_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.in=" + DEFAULT_SC_CRETIME + "," + UPDATED_SC_CRETIME);

        // Get all the sensorCodeList where scCretime equals to UPDATED_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.in=" + UPDATED_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime is not null
        defaultSensorCodeShouldBeFound("scCretime.specified=true");

        // Get all the sensorCodeList where scCretime is null
        defaultSensorCodeShouldNotBeFound("scCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime is greater than or equal to DEFAULT_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.greaterThanOrEqual=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime is greater than or equal to UPDATED_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.greaterThanOrEqual=" + UPDATED_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime is less than or equal to DEFAULT_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.lessThanOrEqual=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime is less than or equal to SMALLER_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.lessThanOrEqual=" + SMALLER_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime is less than DEFAULT_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.lessThan=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime is less than UPDATED_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.lessThan=" + UPDATED_SC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCretime is greater than DEFAULT_SC_CRETIME
        defaultSensorCodeShouldNotBeFound("scCretime.greaterThan=" + DEFAULT_SC_CRETIME);

        // Get all the sensorCodeList where scCretime is greater than SMALLER_SC_CRETIME
        defaultSensorCodeShouldBeFound("scCretime.greaterThan=" + SMALLER_SC_CRETIME);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid equals to DEFAULT_SC_CREID
        defaultSensorCodeShouldBeFound("scCreid.equals=" + DEFAULT_SC_CREID);

        // Get all the sensorCodeList where scCreid equals to UPDATED_SC_CREID
        defaultSensorCodeShouldNotBeFound("scCreid.equals=" + UPDATED_SC_CREID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid not equals to DEFAULT_SC_CREID
        defaultSensorCodeShouldNotBeFound("scCreid.notEquals=" + DEFAULT_SC_CREID);

        // Get all the sensorCodeList where scCreid not equals to UPDATED_SC_CREID
        defaultSensorCodeShouldBeFound("scCreid.notEquals=" + UPDATED_SC_CREID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCreidIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid in DEFAULT_SC_CREID or UPDATED_SC_CREID
        defaultSensorCodeShouldBeFound("scCreid.in=" + DEFAULT_SC_CREID + "," + UPDATED_SC_CREID);

        // Get all the sensorCodeList where scCreid equals to UPDATED_SC_CREID
        defaultSensorCodeShouldNotBeFound("scCreid.in=" + UPDATED_SC_CREID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid is not null
        defaultSensorCodeShouldBeFound("scCreid.specified=true");

        // Get all the sensorCodeList where scCreid is null
        defaultSensorCodeShouldNotBeFound("scCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllSensorCodesByScCreidContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid contains DEFAULT_SC_CREID
        defaultSensorCodeShouldBeFound("scCreid.contains=" + DEFAULT_SC_CREID);

        // Get all the sensorCodeList where scCreid contains UPDATED_SC_CREID
        defaultSensorCodeShouldNotBeFound("scCreid.contains=" + UPDATED_SC_CREID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScCreidNotContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scCreid does not contain DEFAULT_SC_CREID
        defaultSensorCodeShouldNotBeFound("scCreid.doesNotContain=" + DEFAULT_SC_CREID);

        // Get all the sensorCodeList where scCreid does not contain UPDATED_SC_CREID
        defaultSensorCodeShouldBeFound("scCreid.doesNotContain=" + UPDATED_SC_CREID);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime equals to DEFAULT_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.equals=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime equals to UPDATED_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.equals=" + UPDATED_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime not equals to DEFAULT_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.notEquals=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime not equals to UPDATED_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.notEquals=" + UPDATED_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime in DEFAULT_SC_MODTIME or UPDATED_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.in=" + DEFAULT_SC_MODTIME + "," + UPDATED_SC_MODTIME);

        // Get all the sensorCodeList where scModtime equals to UPDATED_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.in=" + UPDATED_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime is not null
        defaultSensorCodeShouldBeFound("scModtime.specified=true");

        // Get all the sensorCodeList where scModtime is null
        defaultSensorCodeShouldNotBeFound("scModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime is greater than or equal to DEFAULT_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.greaterThanOrEqual=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime is greater than or equal to UPDATED_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.greaterThanOrEqual=" + UPDATED_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime is less than or equal to DEFAULT_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.lessThanOrEqual=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime is less than or equal to SMALLER_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.lessThanOrEqual=" + SMALLER_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime is less than DEFAULT_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.lessThan=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime is less than UPDATED_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.lessThan=" + UPDATED_SC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModtime is greater than DEFAULT_SC_MODTIME
        defaultSensorCodeShouldNotBeFound("scModtime.greaterThan=" + DEFAULT_SC_MODTIME);

        // Get all the sensorCodeList where scModtime is greater than SMALLER_SC_MODTIME
        defaultSensorCodeShouldBeFound("scModtime.greaterThan=" + SMALLER_SC_MODTIME);
    }


    @Test
    @Transactional
    public void getAllSensorCodesByScModidIsEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid equals to DEFAULT_SC_MODID
        defaultSensorCodeShouldBeFound("scModid.equals=" + DEFAULT_SC_MODID);

        // Get all the sensorCodeList where scModid equals to UPDATED_SC_MODID
        defaultSensorCodeShouldNotBeFound("scModid.equals=" + UPDATED_SC_MODID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid not equals to DEFAULT_SC_MODID
        defaultSensorCodeShouldNotBeFound("scModid.notEquals=" + DEFAULT_SC_MODID);

        // Get all the sensorCodeList where scModid not equals to UPDATED_SC_MODID
        defaultSensorCodeShouldBeFound("scModid.notEquals=" + UPDATED_SC_MODID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModidIsInShouldWork() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid in DEFAULT_SC_MODID or UPDATED_SC_MODID
        defaultSensorCodeShouldBeFound("scModid.in=" + DEFAULT_SC_MODID + "," + UPDATED_SC_MODID);

        // Get all the sensorCodeList where scModid equals to UPDATED_SC_MODID
        defaultSensorCodeShouldNotBeFound("scModid.in=" + UPDATED_SC_MODID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid is not null
        defaultSensorCodeShouldBeFound("scModid.specified=true");

        // Get all the sensorCodeList where scModid is null
        defaultSensorCodeShouldNotBeFound("scModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllSensorCodesByScModidContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid contains DEFAULT_SC_MODID
        defaultSensorCodeShouldBeFound("scModid.contains=" + DEFAULT_SC_MODID);

        // Get all the sensorCodeList where scModid contains UPDATED_SC_MODID
        defaultSensorCodeShouldNotBeFound("scModid.contains=" + UPDATED_SC_MODID);
    }

    @Test
    @Transactional
    public void getAllSensorCodesByScModidNotContainsSomething() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        // Get all the sensorCodeList where scModid does not contain DEFAULT_SC_MODID
        defaultSensorCodeShouldNotBeFound("scModid.doesNotContain=" + DEFAULT_SC_MODID);

        // Get all the sensorCodeList where scModid does not contain UPDATED_SC_MODID
        defaultSensorCodeShouldBeFound("scModid.doesNotContain=" + UPDATED_SC_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSensorCodeShouldBeFound(String filter) throws Exception {
        restSensorCodeMockMvc.perform(get("/api/sensor-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensorCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].scCode").value(hasItem(DEFAULT_SC_CODE)))
            .andExpect(jsonPath("$.[*].scName").value(hasItem(DEFAULT_SC_NAME)))
            .andExpect(jsonPath("$.[*].scCretime").value(hasItem(sameInstant(DEFAULT_SC_CRETIME))))
            .andExpect(jsonPath("$.[*].scCreid").value(hasItem(DEFAULT_SC_CREID)))
            .andExpect(jsonPath("$.[*].scModtime").value(hasItem(sameInstant(DEFAULT_SC_MODTIME))))
            .andExpect(jsonPath("$.[*].scModid").value(hasItem(DEFAULT_SC_MODID)));

        // Check, that the count call also returns 1
        restSensorCodeMockMvc.perform(get("/api/sensor-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSensorCodeShouldNotBeFound(String filter) throws Exception {
        restSensorCodeMockMvc.perform(get("/api/sensor-codes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSensorCodeMockMvc.perform(get("/api/sensor-codes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSensorCode() throws Exception {
        // Get the sensorCode
        restSensorCodeMockMvc.perform(get("/api/sensor-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSensorCode() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        int databaseSizeBeforeUpdate = sensorCodeRepository.findAll().size();

        // Update the sensorCode
        SensorCode updatedSensorCode = sensorCodeRepository.findById(sensorCode.getId()).get();
        // Disconnect from session so that the updates on updatedSensorCode are not directly saved in db
        em.detach(updatedSensorCode);
        updatedSensorCode
            .scCode(UPDATED_SC_CODE)
            .scName(UPDATED_SC_NAME)
            .scCretime(UPDATED_SC_CRETIME)
            .scCreid(UPDATED_SC_CREID)
            .scModtime(UPDATED_SC_MODTIME)
            .scModid(UPDATED_SC_MODID);
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(updatedSensorCode);

        restSensorCodeMockMvc.perform(put("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isOk());

        // Validate the SensorCode in the database
        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeUpdate);
        SensorCode testSensorCode = sensorCodeList.get(sensorCodeList.size() - 1);
        assertThat(testSensorCode.getScCode()).isEqualTo(UPDATED_SC_CODE);
        assertThat(testSensorCode.getScName()).isEqualTo(UPDATED_SC_NAME);
        assertThat(testSensorCode.getScCretime()).isEqualTo(UPDATED_SC_CRETIME);
        assertThat(testSensorCode.getScCreid()).isEqualTo(UPDATED_SC_CREID);
        assertThat(testSensorCode.getScModtime()).isEqualTo(UPDATED_SC_MODTIME);
        assertThat(testSensorCode.getScModid()).isEqualTo(UPDATED_SC_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingSensorCode() throws Exception {
        int databaseSizeBeforeUpdate = sensorCodeRepository.findAll().size();

        // Create the SensorCode
        SensorCodeDTO sensorCodeDTO = sensorCodeMapper.toDto(sensorCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSensorCodeMockMvc.perform(put("/api/sensor-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensorCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SensorCode in the database
        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSensorCode() throws Exception {
        // Initialize the database
        sensorCodeRepository.saveAndFlush(sensorCode);

        int databaseSizeBeforeDelete = sensorCodeRepository.findAll().size();

        // Delete the sensorCode
        restSensorCodeMockMvc.perform(delete("/api/sensor-codes/{id}", sensorCode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SensorCode> sensorCodeList = sensorCodeRepository.findAll();
        assertThat(sensorCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
