package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.Unit;
import aiot.java.repository.UnitRepository;
import aiot.java.service.UnitService;
import aiot.java.service.dto.UnitDTO;
import aiot.java.service.mapper.UnitMapper;
import aiot.java.service.dto.UnitCriteria;
import aiot.java.service.UnitQueryService;

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
 * Integration tests for the {@link UnitResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UnitResourceIT {

    private static final String DEFAULT_UNIT_UC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_UC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_LONGITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_PIC = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_PIC = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_EMAIL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UNIT_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UNIT_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_UNIT_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_UNIT_CREID = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UNIT_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UNIT_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_UNIT_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_UNIT_MODID = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_MODID = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_LOGIP = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_LOGIP = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UNIT_SIGN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UNIT_SIGN_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_UNIT_SIGN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private UnitService unitService;

    @Autowired
    private UnitQueryService unitQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitMockMvc;

    private Unit unit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unit createEntity(EntityManager em) {
        Unit unit = new Unit()
            .unitUcCode(DEFAULT_UNIT_UC_CODE)
            .unitCode(DEFAULT_UNIT_CODE)
            .unitName(DEFAULT_UNIT_NAME)
            .unitAddr(DEFAULT_UNIT_ADDR)
            .unitLongitude(DEFAULT_UNIT_LONGITUDE)
            .unitLatitude(DEFAULT_UNIT_LATITUDE)
            .unitPic(DEFAULT_UNIT_PIC)
            .unitPhone(DEFAULT_UNIT_PHONE)
            .unitEmail(DEFAULT_UNIT_EMAIL)
            .unitCretime(DEFAULT_UNIT_CRETIME)
            .unitCreid(DEFAULT_UNIT_CREID)
            .unitModtime(DEFAULT_UNIT_MODTIME)
            .unitModid(DEFAULT_UNIT_MODID)
            .unitLogip(DEFAULT_UNIT_LOGIP)
            .unitSignDate(DEFAULT_UNIT_SIGN_DATE);
        return unit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unit createUpdatedEntity(EntityManager em) {
        Unit unit = new Unit()
            .unitUcCode(UPDATED_UNIT_UC_CODE)
            .unitCode(UPDATED_UNIT_CODE)
            .unitName(UPDATED_UNIT_NAME)
            .unitAddr(UPDATED_UNIT_ADDR)
            .unitLongitude(UPDATED_UNIT_LONGITUDE)
            .unitLatitude(UPDATED_UNIT_LATITUDE)
            .unitPic(UPDATED_UNIT_PIC)
            .unitPhone(UPDATED_UNIT_PHONE)
            .unitEmail(UPDATED_UNIT_EMAIL)
            .unitCretime(UPDATED_UNIT_CRETIME)
            .unitCreid(UPDATED_UNIT_CREID)
            .unitModtime(UPDATED_UNIT_MODTIME)
            .unitModid(UPDATED_UNIT_MODID)
            .unitLogip(UPDATED_UNIT_LOGIP)
            .unitSignDate(UPDATED_UNIT_SIGN_DATE);
        return unit;
    }

    @BeforeEach
    public void initTest() {
        unit = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnit() throws Exception {
        int databaseSizeBeforeCreate = unitRepository.findAll().size();

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);
        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isCreated());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeCreate + 1);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getUnitUcCode()).isEqualTo(DEFAULT_UNIT_UC_CODE);
        assertThat(testUnit.getUnitCode()).isEqualTo(DEFAULT_UNIT_CODE);
        assertThat(testUnit.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testUnit.getUnitAddr()).isEqualTo(DEFAULT_UNIT_ADDR);
        assertThat(testUnit.getUnitLongitude()).isEqualTo(DEFAULT_UNIT_LONGITUDE);
        assertThat(testUnit.getUnitLatitude()).isEqualTo(DEFAULT_UNIT_LATITUDE);
        assertThat(testUnit.getUnitPic()).isEqualTo(DEFAULT_UNIT_PIC);
        assertThat(testUnit.getUnitPhone()).isEqualTo(DEFAULT_UNIT_PHONE);
        assertThat(testUnit.getUnitEmail()).isEqualTo(DEFAULT_UNIT_EMAIL);
        assertThat(testUnit.getUnitCretime()).isEqualTo(DEFAULT_UNIT_CRETIME);
        assertThat(testUnit.getUnitCreid()).isEqualTo(DEFAULT_UNIT_CREID);
        assertThat(testUnit.getUnitModtime()).isEqualTo(DEFAULT_UNIT_MODTIME);
        assertThat(testUnit.getUnitModid()).isEqualTo(DEFAULT_UNIT_MODID);
        assertThat(testUnit.getUnitLogip()).isEqualTo(DEFAULT_UNIT_LOGIP);
        assertThat(testUnit.getUnitSignDate()).isEqualTo(DEFAULT_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void createUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitRepository.findAll().size();

        // Create the Unit with an existing ID
        unit.setId(1L);
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUnitUcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitUcCode(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitCode(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitName(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitAddrIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitAddr(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitLongitude(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitLatitude(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitPicIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitPic(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitPhone(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitEmail(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitCretime(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitCreid(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitModtime(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitModid(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitLogipIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitLogip(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitSignDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitRepository.findAll().size();
        // set the field null
        unit.setUnitSignDate(null);

        // Create the Unit, which fails.
        UnitDTO unitDTO = unitMapper.toDto(unit);

        restUnitMockMvc.perform(post("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUnits() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList
        restUnitMockMvc.perform(get("/api/units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unit.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitUcCode").value(hasItem(DEFAULT_UNIT_UC_CODE)))
            .andExpect(jsonPath("$.[*].unitCode").value(hasItem(DEFAULT_UNIT_CODE)))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].unitAddr").value(hasItem(DEFAULT_UNIT_ADDR)))
            .andExpect(jsonPath("$.[*].unitLongitude").value(hasItem(DEFAULT_UNIT_LONGITUDE)))
            .andExpect(jsonPath("$.[*].unitLatitude").value(hasItem(DEFAULT_UNIT_LATITUDE)))
            .andExpect(jsonPath("$.[*].unitPic").value(hasItem(DEFAULT_UNIT_PIC)))
            .andExpect(jsonPath("$.[*].unitPhone").value(hasItem(DEFAULT_UNIT_PHONE)))
            .andExpect(jsonPath("$.[*].unitEmail").value(hasItem(DEFAULT_UNIT_EMAIL)))
            .andExpect(jsonPath("$.[*].unitCretime").value(hasItem(sameInstant(DEFAULT_UNIT_CRETIME))))
            .andExpect(jsonPath("$.[*].unitCreid").value(hasItem(DEFAULT_UNIT_CREID)))
            .andExpect(jsonPath("$.[*].unitModtime").value(hasItem(sameInstant(DEFAULT_UNIT_MODTIME))))
            .andExpect(jsonPath("$.[*].unitModid").value(hasItem(DEFAULT_UNIT_MODID)))
            .andExpect(jsonPath("$.[*].unitLogip").value(hasItem(DEFAULT_UNIT_LOGIP)))
            .andExpect(jsonPath("$.[*].unitSignDate").value(hasItem(sameInstant(DEFAULT_UNIT_SIGN_DATE))));
    }
    
    @Test
    @Transactional
    public void getUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get the unit
        restUnitMockMvc.perform(get("/api/units/{id}", unit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unit.getId().intValue()))
            .andExpect(jsonPath("$.unitUcCode").value(DEFAULT_UNIT_UC_CODE))
            .andExpect(jsonPath("$.unitCode").value(DEFAULT_UNIT_CODE))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME))
            .andExpect(jsonPath("$.unitAddr").value(DEFAULT_UNIT_ADDR))
            .andExpect(jsonPath("$.unitLongitude").value(DEFAULT_UNIT_LONGITUDE))
            .andExpect(jsonPath("$.unitLatitude").value(DEFAULT_UNIT_LATITUDE))
            .andExpect(jsonPath("$.unitPic").value(DEFAULT_UNIT_PIC))
            .andExpect(jsonPath("$.unitPhone").value(DEFAULT_UNIT_PHONE))
            .andExpect(jsonPath("$.unitEmail").value(DEFAULT_UNIT_EMAIL))
            .andExpect(jsonPath("$.unitCretime").value(sameInstant(DEFAULT_UNIT_CRETIME)))
            .andExpect(jsonPath("$.unitCreid").value(DEFAULT_UNIT_CREID))
            .andExpect(jsonPath("$.unitModtime").value(sameInstant(DEFAULT_UNIT_MODTIME)))
            .andExpect(jsonPath("$.unitModid").value(DEFAULT_UNIT_MODID))
            .andExpect(jsonPath("$.unitLogip").value(DEFAULT_UNIT_LOGIP))
            .andExpect(jsonPath("$.unitSignDate").value(sameInstant(DEFAULT_UNIT_SIGN_DATE)));
    }


    @Test
    @Transactional
    public void getUnitsByIdFiltering() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        Long id = unit.getId();

        defaultUnitShouldBeFound("id.equals=" + id);
        defaultUnitShouldNotBeFound("id.notEquals=" + id);

        defaultUnitShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUnitShouldNotBeFound("id.greaterThan=" + id);

        defaultUnitShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUnitShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode equals to DEFAULT_UNIT_UC_CODE
        defaultUnitShouldBeFound("unitUcCode.equals=" + DEFAULT_UNIT_UC_CODE);

        // Get all the unitList where unitUcCode equals to UPDATED_UNIT_UC_CODE
        defaultUnitShouldNotBeFound("unitUcCode.equals=" + UPDATED_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode not equals to DEFAULT_UNIT_UC_CODE
        defaultUnitShouldNotBeFound("unitUcCode.notEquals=" + DEFAULT_UNIT_UC_CODE);

        // Get all the unitList where unitUcCode not equals to UPDATED_UNIT_UC_CODE
        defaultUnitShouldBeFound("unitUcCode.notEquals=" + UPDATED_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode in DEFAULT_UNIT_UC_CODE or UPDATED_UNIT_UC_CODE
        defaultUnitShouldBeFound("unitUcCode.in=" + DEFAULT_UNIT_UC_CODE + "," + UPDATED_UNIT_UC_CODE);

        // Get all the unitList where unitUcCode equals to UPDATED_UNIT_UC_CODE
        defaultUnitShouldNotBeFound("unitUcCode.in=" + UPDATED_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode is not null
        defaultUnitShouldBeFound("unitUcCode.specified=true");

        // Get all the unitList where unitUcCode is null
        defaultUnitShouldNotBeFound("unitUcCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode contains DEFAULT_UNIT_UC_CODE
        defaultUnitShouldBeFound("unitUcCode.contains=" + DEFAULT_UNIT_UC_CODE);

        // Get all the unitList where unitUcCode contains UPDATED_UNIT_UC_CODE
        defaultUnitShouldNotBeFound("unitUcCode.contains=" + UPDATED_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitUcCodeNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitUcCode does not contain DEFAULT_UNIT_UC_CODE
        defaultUnitShouldNotBeFound("unitUcCode.doesNotContain=" + DEFAULT_UNIT_UC_CODE);

        // Get all the unitList where unitUcCode does not contain UPDATED_UNIT_UC_CODE
        defaultUnitShouldBeFound("unitUcCode.doesNotContain=" + UPDATED_UNIT_UC_CODE);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode equals to DEFAULT_UNIT_CODE
        defaultUnitShouldBeFound("unitCode.equals=" + DEFAULT_UNIT_CODE);

        // Get all the unitList where unitCode equals to UPDATED_UNIT_CODE
        defaultUnitShouldNotBeFound("unitCode.equals=" + UPDATED_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode not equals to DEFAULT_UNIT_CODE
        defaultUnitShouldNotBeFound("unitCode.notEquals=" + DEFAULT_UNIT_CODE);

        // Get all the unitList where unitCode not equals to UPDATED_UNIT_CODE
        defaultUnitShouldBeFound("unitCode.notEquals=" + UPDATED_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCodeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode in DEFAULT_UNIT_CODE or UPDATED_UNIT_CODE
        defaultUnitShouldBeFound("unitCode.in=" + DEFAULT_UNIT_CODE + "," + UPDATED_UNIT_CODE);

        // Get all the unitList where unitCode equals to UPDATED_UNIT_CODE
        defaultUnitShouldNotBeFound("unitCode.in=" + UPDATED_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode is not null
        defaultUnitShouldBeFound("unitCode.specified=true");

        // Get all the unitList where unitCode is null
        defaultUnitShouldNotBeFound("unitCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitCodeContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode contains DEFAULT_UNIT_CODE
        defaultUnitShouldBeFound("unitCode.contains=" + DEFAULT_UNIT_CODE);

        // Get all the unitList where unitCode contains UPDATED_UNIT_CODE
        defaultUnitShouldNotBeFound("unitCode.contains=" + UPDATED_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCodeNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCode does not contain DEFAULT_UNIT_CODE
        defaultUnitShouldNotBeFound("unitCode.doesNotContain=" + DEFAULT_UNIT_CODE);

        // Get all the unitList where unitCode does not contain UPDATED_UNIT_CODE
        defaultUnitShouldBeFound("unitCode.doesNotContain=" + UPDATED_UNIT_CODE);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitNameIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName equals to DEFAULT_UNIT_NAME
        defaultUnitShouldBeFound("unitName.equals=" + DEFAULT_UNIT_NAME);

        // Get all the unitList where unitName equals to UPDATED_UNIT_NAME
        defaultUnitShouldNotBeFound("unitName.equals=" + UPDATED_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName not equals to DEFAULT_UNIT_NAME
        defaultUnitShouldNotBeFound("unitName.notEquals=" + DEFAULT_UNIT_NAME);

        // Get all the unitList where unitName not equals to UPDATED_UNIT_NAME
        defaultUnitShouldBeFound("unitName.notEquals=" + UPDATED_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitNameIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName in DEFAULT_UNIT_NAME or UPDATED_UNIT_NAME
        defaultUnitShouldBeFound("unitName.in=" + DEFAULT_UNIT_NAME + "," + UPDATED_UNIT_NAME);

        // Get all the unitList where unitName equals to UPDATED_UNIT_NAME
        defaultUnitShouldNotBeFound("unitName.in=" + UPDATED_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName is not null
        defaultUnitShouldBeFound("unitName.specified=true");

        // Get all the unitList where unitName is null
        defaultUnitShouldNotBeFound("unitName.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitNameContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName contains DEFAULT_UNIT_NAME
        defaultUnitShouldBeFound("unitName.contains=" + DEFAULT_UNIT_NAME);

        // Get all the unitList where unitName contains UPDATED_UNIT_NAME
        defaultUnitShouldNotBeFound("unitName.contains=" + UPDATED_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitNameNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitName does not contain DEFAULT_UNIT_NAME
        defaultUnitShouldNotBeFound("unitName.doesNotContain=" + DEFAULT_UNIT_NAME);

        // Get all the unitList where unitName does not contain UPDATED_UNIT_NAME
        defaultUnitShouldBeFound("unitName.doesNotContain=" + UPDATED_UNIT_NAME);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitAddrIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr equals to DEFAULT_UNIT_ADDR
        defaultUnitShouldBeFound("unitAddr.equals=" + DEFAULT_UNIT_ADDR);

        // Get all the unitList where unitAddr equals to UPDATED_UNIT_ADDR
        defaultUnitShouldNotBeFound("unitAddr.equals=" + UPDATED_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitAddrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr not equals to DEFAULT_UNIT_ADDR
        defaultUnitShouldNotBeFound("unitAddr.notEquals=" + DEFAULT_UNIT_ADDR);

        // Get all the unitList where unitAddr not equals to UPDATED_UNIT_ADDR
        defaultUnitShouldBeFound("unitAddr.notEquals=" + UPDATED_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitAddrIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr in DEFAULT_UNIT_ADDR or UPDATED_UNIT_ADDR
        defaultUnitShouldBeFound("unitAddr.in=" + DEFAULT_UNIT_ADDR + "," + UPDATED_UNIT_ADDR);

        // Get all the unitList where unitAddr equals to UPDATED_UNIT_ADDR
        defaultUnitShouldNotBeFound("unitAddr.in=" + UPDATED_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitAddrIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr is not null
        defaultUnitShouldBeFound("unitAddr.specified=true");

        // Get all the unitList where unitAddr is null
        defaultUnitShouldNotBeFound("unitAddr.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitAddrContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr contains DEFAULT_UNIT_ADDR
        defaultUnitShouldBeFound("unitAddr.contains=" + DEFAULT_UNIT_ADDR);

        // Get all the unitList where unitAddr contains UPDATED_UNIT_ADDR
        defaultUnitShouldNotBeFound("unitAddr.contains=" + UPDATED_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitAddrNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitAddr does not contain DEFAULT_UNIT_ADDR
        defaultUnitShouldNotBeFound("unitAddr.doesNotContain=" + DEFAULT_UNIT_ADDR);

        // Get all the unitList where unitAddr does not contain UPDATED_UNIT_ADDR
        defaultUnitShouldBeFound("unitAddr.doesNotContain=" + UPDATED_UNIT_ADDR);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude equals to DEFAULT_UNIT_LONGITUDE
        defaultUnitShouldBeFound("unitLongitude.equals=" + DEFAULT_UNIT_LONGITUDE);

        // Get all the unitList where unitLongitude equals to UPDATED_UNIT_LONGITUDE
        defaultUnitShouldNotBeFound("unitLongitude.equals=" + UPDATED_UNIT_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude not equals to DEFAULT_UNIT_LONGITUDE
        defaultUnitShouldNotBeFound("unitLongitude.notEquals=" + DEFAULT_UNIT_LONGITUDE);

        // Get all the unitList where unitLongitude not equals to UPDATED_UNIT_LONGITUDE
        defaultUnitShouldBeFound("unitLongitude.notEquals=" + UPDATED_UNIT_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude in DEFAULT_UNIT_LONGITUDE or UPDATED_UNIT_LONGITUDE
        defaultUnitShouldBeFound("unitLongitude.in=" + DEFAULT_UNIT_LONGITUDE + "," + UPDATED_UNIT_LONGITUDE);

        // Get all the unitList where unitLongitude equals to UPDATED_UNIT_LONGITUDE
        defaultUnitShouldNotBeFound("unitLongitude.in=" + UPDATED_UNIT_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude is not null
        defaultUnitShouldBeFound("unitLongitude.specified=true");

        // Get all the unitList where unitLongitude is null
        defaultUnitShouldNotBeFound("unitLongitude.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude contains DEFAULT_UNIT_LONGITUDE
        defaultUnitShouldBeFound("unitLongitude.contains=" + DEFAULT_UNIT_LONGITUDE);

        // Get all the unitList where unitLongitude contains UPDATED_UNIT_LONGITUDE
        defaultUnitShouldNotBeFound("unitLongitude.contains=" + UPDATED_UNIT_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLongitudeNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLongitude does not contain DEFAULT_UNIT_LONGITUDE
        defaultUnitShouldNotBeFound("unitLongitude.doesNotContain=" + DEFAULT_UNIT_LONGITUDE);

        // Get all the unitList where unitLongitude does not contain UPDATED_UNIT_LONGITUDE
        defaultUnitShouldBeFound("unitLongitude.doesNotContain=" + UPDATED_UNIT_LONGITUDE);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude equals to DEFAULT_UNIT_LATITUDE
        defaultUnitShouldBeFound("unitLatitude.equals=" + DEFAULT_UNIT_LATITUDE);

        // Get all the unitList where unitLatitude equals to UPDATED_UNIT_LATITUDE
        defaultUnitShouldNotBeFound("unitLatitude.equals=" + UPDATED_UNIT_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude not equals to DEFAULT_UNIT_LATITUDE
        defaultUnitShouldNotBeFound("unitLatitude.notEquals=" + DEFAULT_UNIT_LATITUDE);

        // Get all the unitList where unitLatitude not equals to UPDATED_UNIT_LATITUDE
        defaultUnitShouldBeFound("unitLatitude.notEquals=" + UPDATED_UNIT_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude in DEFAULT_UNIT_LATITUDE or UPDATED_UNIT_LATITUDE
        defaultUnitShouldBeFound("unitLatitude.in=" + DEFAULT_UNIT_LATITUDE + "," + UPDATED_UNIT_LATITUDE);

        // Get all the unitList where unitLatitude equals to UPDATED_UNIT_LATITUDE
        defaultUnitShouldNotBeFound("unitLatitude.in=" + UPDATED_UNIT_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude is not null
        defaultUnitShouldBeFound("unitLatitude.specified=true");

        // Get all the unitList where unitLatitude is null
        defaultUnitShouldNotBeFound("unitLatitude.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude contains DEFAULT_UNIT_LATITUDE
        defaultUnitShouldBeFound("unitLatitude.contains=" + DEFAULT_UNIT_LATITUDE);

        // Get all the unitList where unitLatitude contains UPDATED_UNIT_LATITUDE
        defaultUnitShouldNotBeFound("unitLatitude.contains=" + UPDATED_UNIT_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLatitudeNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLatitude does not contain DEFAULT_UNIT_LATITUDE
        defaultUnitShouldNotBeFound("unitLatitude.doesNotContain=" + DEFAULT_UNIT_LATITUDE);

        // Get all the unitList where unitLatitude does not contain UPDATED_UNIT_LATITUDE
        defaultUnitShouldBeFound("unitLatitude.doesNotContain=" + UPDATED_UNIT_LATITUDE);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitPicIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic equals to DEFAULT_UNIT_PIC
        defaultUnitShouldBeFound("unitPic.equals=" + DEFAULT_UNIT_PIC);

        // Get all the unitList where unitPic equals to UPDATED_UNIT_PIC
        defaultUnitShouldNotBeFound("unitPic.equals=" + UPDATED_UNIT_PIC);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPicIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic not equals to DEFAULT_UNIT_PIC
        defaultUnitShouldNotBeFound("unitPic.notEquals=" + DEFAULT_UNIT_PIC);

        // Get all the unitList where unitPic not equals to UPDATED_UNIT_PIC
        defaultUnitShouldBeFound("unitPic.notEquals=" + UPDATED_UNIT_PIC);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPicIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic in DEFAULT_UNIT_PIC or UPDATED_UNIT_PIC
        defaultUnitShouldBeFound("unitPic.in=" + DEFAULT_UNIT_PIC + "," + UPDATED_UNIT_PIC);

        // Get all the unitList where unitPic equals to UPDATED_UNIT_PIC
        defaultUnitShouldNotBeFound("unitPic.in=" + UPDATED_UNIT_PIC);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPicIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic is not null
        defaultUnitShouldBeFound("unitPic.specified=true");

        // Get all the unitList where unitPic is null
        defaultUnitShouldNotBeFound("unitPic.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitPicContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic contains DEFAULT_UNIT_PIC
        defaultUnitShouldBeFound("unitPic.contains=" + DEFAULT_UNIT_PIC);

        // Get all the unitList where unitPic contains UPDATED_UNIT_PIC
        defaultUnitShouldNotBeFound("unitPic.contains=" + UPDATED_UNIT_PIC);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPicNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPic does not contain DEFAULT_UNIT_PIC
        defaultUnitShouldNotBeFound("unitPic.doesNotContain=" + DEFAULT_UNIT_PIC);

        // Get all the unitList where unitPic does not contain UPDATED_UNIT_PIC
        defaultUnitShouldBeFound("unitPic.doesNotContain=" + UPDATED_UNIT_PIC);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone equals to DEFAULT_UNIT_PHONE
        defaultUnitShouldBeFound("unitPhone.equals=" + DEFAULT_UNIT_PHONE);

        // Get all the unitList where unitPhone equals to UPDATED_UNIT_PHONE
        defaultUnitShouldNotBeFound("unitPhone.equals=" + UPDATED_UNIT_PHONE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPhoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone not equals to DEFAULT_UNIT_PHONE
        defaultUnitShouldNotBeFound("unitPhone.notEquals=" + DEFAULT_UNIT_PHONE);

        // Get all the unitList where unitPhone not equals to UPDATED_UNIT_PHONE
        defaultUnitShouldBeFound("unitPhone.notEquals=" + UPDATED_UNIT_PHONE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone in DEFAULT_UNIT_PHONE or UPDATED_UNIT_PHONE
        defaultUnitShouldBeFound("unitPhone.in=" + DEFAULT_UNIT_PHONE + "," + UPDATED_UNIT_PHONE);

        // Get all the unitList where unitPhone equals to UPDATED_UNIT_PHONE
        defaultUnitShouldNotBeFound("unitPhone.in=" + UPDATED_UNIT_PHONE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone is not null
        defaultUnitShouldBeFound("unitPhone.specified=true");

        // Get all the unitList where unitPhone is null
        defaultUnitShouldNotBeFound("unitPhone.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitPhoneContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone contains DEFAULT_UNIT_PHONE
        defaultUnitShouldBeFound("unitPhone.contains=" + DEFAULT_UNIT_PHONE);

        // Get all the unitList where unitPhone contains UPDATED_UNIT_PHONE
        defaultUnitShouldNotBeFound("unitPhone.contains=" + UPDATED_UNIT_PHONE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitPhoneNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitPhone does not contain DEFAULT_UNIT_PHONE
        defaultUnitShouldNotBeFound("unitPhone.doesNotContain=" + DEFAULT_UNIT_PHONE);

        // Get all the unitList where unitPhone does not contain UPDATED_UNIT_PHONE
        defaultUnitShouldBeFound("unitPhone.doesNotContain=" + UPDATED_UNIT_PHONE);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail equals to DEFAULT_UNIT_EMAIL
        defaultUnitShouldBeFound("unitEmail.equals=" + DEFAULT_UNIT_EMAIL);

        // Get all the unitList where unitEmail equals to UPDATED_UNIT_EMAIL
        defaultUnitShouldNotBeFound("unitEmail.equals=" + UPDATED_UNIT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail not equals to DEFAULT_UNIT_EMAIL
        defaultUnitShouldNotBeFound("unitEmail.notEquals=" + DEFAULT_UNIT_EMAIL);

        // Get all the unitList where unitEmail not equals to UPDATED_UNIT_EMAIL
        defaultUnitShouldBeFound("unitEmail.notEquals=" + UPDATED_UNIT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitEmailIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail in DEFAULT_UNIT_EMAIL or UPDATED_UNIT_EMAIL
        defaultUnitShouldBeFound("unitEmail.in=" + DEFAULT_UNIT_EMAIL + "," + UPDATED_UNIT_EMAIL);

        // Get all the unitList where unitEmail equals to UPDATED_UNIT_EMAIL
        defaultUnitShouldNotBeFound("unitEmail.in=" + UPDATED_UNIT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail is not null
        defaultUnitShouldBeFound("unitEmail.specified=true");

        // Get all the unitList where unitEmail is null
        defaultUnitShouldNotBeFound("unitEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitEmailContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail contains DEFAULT_UNIT_EMAIL
        defaultUnitShouldBeFound("unitEmail.contains=" + DEFAULT_UNIT_EMAIL);

        // Get all the unitList where unitEmail contains UPDATED_UNIT_EMAIL
        defaultUnitShouldNotBeFound("unitEmail.contains=" + UPDATED_UNIT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitEmailNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitEmail does not contain DEFAULT_UNIT_EMAIL
        defaultUnitShouldNotBeFound("unitEmail.doesNotContain=" + DEFAULT_UNIT_EMAIL);

        // Get all the unitList where unitEmail does not contain UPDATED_UNIT_EMAIL
        defaultUnitShouldBeFound("unitEmail.doesNotContain=" + UPDATED_UNIT_EMAIL);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime equals to DEFAULT_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.equals=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime equals to UPDATED_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.equals=" + UPDATED_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime not equals to DEFAULT_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.notEquals=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime not equals to UPDATED_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.notEquals=" + UPDATED_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime in DEFAULT_UNIT_CRETIME or UPDATED_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.in=" + DEFAULT_UNIT_CRETIME + "," + UPDATED_UNIT_CRETIME);

        // Get all the unitList where unitCretime equals to UPDATED_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.in=" + UPDATED_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime is not null
        defaultUnitShouldBeFound("unitCretime.specified=true");

        // Get all the unitList where unitCretime is null
        defaultUnitShouldNotBeFound("unitCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime is greater than or equal to DEFAULT_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.greaterThanOrEqual=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime is greater than or equal to UPDATED_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.greaterThanOrEqual=" + UPDATED_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime is less than or equal to DEFAULT_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.lessThanOrEqual=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime is less than or equal to SMALLER_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.lessThanOrEqual=" + SMALLER_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime is less than DEFAULT_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.lessThan=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime is less than UPDATED_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.lessThan=" + UPDATED_UNIT_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCretime is greater than DEFAULT_UNIT_CRETIME
        defaultUnitShouldNotBeFound("unitCretime.greaterThan=" + DEFAULT_UNIT_CRETIME);

        // Get all the unitList where unitCretime is greater than SMALLER_UNIT_CRETIME
        defaultUnitShouldBeFound("unitCretime.greaterThan=" + SMALLER_UNIT_CRETIME);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid equals to DEFAULT_UNIT_CREID
        defaultUnitShouldBeFound("unitCreid.equals=" + DEFAULT_UNIT_CREID);

        // Get all the unitList where unitCreid equals to UPDATED_UNIT_CREID
        defaultUnitShouldNotBeFound("unitCreid.equals=" + UPDATED_UNIT_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid not equals to DEFAULT_UNIT_CREID
        defaultUnitShouldNotBeFound("unitCreid.notEquals=" + DEFAULT_UNIT_CREID);

        // Get all the unitList where unitCreid not equals to UPDATED_UNIT_CREID
        defaultUnitShouldBeFound("unitCreid.notEquals=" + UPDATED_UNIT_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCreidIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid in DEFAULT_UNIT_CREID or UPDATED_UNIT_CREID
        defaultUnitShouldBeFound("unitCreid.in=" + DEFAULT_UNIT_CREID + "," + UPDATED_UNIT_CREID);

        // Get all the unitList where unitCreid equals to UPDATED_UNIT_CREID
        defaultUnitShouldNotBeFound("unitCreid.in=" + UPDATED_UNIT_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid is not null
        defaultUnitShouldBeFound("unitCreid.specified=true");

        // Get all the unitList where unitCreid is null
        defaultUnitShouldNotBeFound("unitCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitCreidContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid contains DEFAULT_UNIT_CREID
        defaultUnitShouldBeFound("unitCreid.contains=" + DEFAULT_UNIT_CREID);

        // Get all the unitList where unitCreid contains UPDATED_UNIT_CREID
        defaultUnitShouldNotBeFound("unitCreid.contains=" + UPDATED_UNIT_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitCreidNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitCreid does not contain DEFAULT_UNIT_CREID
        defaultUnitShouldNotBeFound("unitCreid.doesNotContain=" + DEFAULT_UNIT_CREID);

        // Get all the unitList where unitCreid does not contain UPDATED_UNIT_CREID
        defaultUnitShouldBeFound("unitCreid.doesNotContain=" + UPDATED_UNIT_CREID);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime equals to DEFAULT_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.equals=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime equals to UPDATED_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.equals=" + UPDATED_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime not equals to DEFAULT_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.notEquals=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime not equals to UPDATED_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.notEquals=" + UPDATED_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime in DEFAULT_UNIT_MODTIME or UPDATED_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.in=" + DEFAULT_UNIT_MODTIME + "," + UPDATED_UNIT_MODTIME);

        // Get all the unitList where unitModtime equals to UPDATED_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.in=" + UPDATED_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime is not null
        defaultUnitShouldBeFound("unitModtime.specified=true");

        // Get all the unitList where unitModtime is null
        defaultUnitShouldNotBeFound("unitModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime is greater than or equal to DEFAULT_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.greaterThanOrEqual=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime is greater than or equal to UPDATED_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.greaterThanOrEqual=" + UPDATED_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime is less than or equal to DEFAULT_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.lessThanOrEqual=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime is less than or equal to SMALLER_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.lessThanOrEqual=" + SMALLER_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime is less than DEFAULT_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.lessThan=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime is less than UPDATED_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.lessThan=" + UPDATED_UNIT_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModtime is greater than DEFAULT_UNIT_MODTIME
        defaultUnitShouldNotBeFound("unitModtime.greaterThan=" + DEFAULT_UNIT_MODTIME);

        // Get all the unitList where unitModtime is greater than SMALLER_UNIT_MODTIME
        defaultUnitShouldBeFound("unitModtime.greaterThan=" + SMALLER_UNIT_MODTIME);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitModidIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid equals to DEFAULT_UNIT_MODID
        defaultUnitShouldBeFound("unitModid.equals=" + DEFAULT_UNIT_MODID);

        // Get all the unitList where unitModid equals to UPDATED_UNIT_MODID
        defaultUnitShouldNotBeFound("unitModid.equals=" + UPDATED_UNIT_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid not equals to DEFAULT_UNIT_MODID
        defaultUnitShouldNotBeFound("unitModid.notEquals=" + DEFAULT_UNIT_MODID);

        // Get all the unitList where unitModid not equals to UPDATED_UNIT_MODID
        defaultUnitShouldBeFound("unitModid.notEquals=" + UPDATED_UNIT_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModidIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid in DEFAULT_UNIT_MODID or UPDATED_UNIT_MODID
        defaultUnitShouldBeFound("unitModid.in=" + DEFAULT_UNIT_MODID + "," + UPDATED_UNIT_MODID);

        // Get all the unitList where unitModid equals to UPDATED_UNIT_MODID
        defaultUnitShouldNotBeFound("unitModid.in=" + UPDATED_UNIT_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid is not null
        defaultUnitShouldBeFound("unitModid.specified=true");

        // Get all the unitList where unitModid is null
        defaultUnitShouldNotBeFound("unitModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitModidContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid contains DEFAULT_UNIT_MODID
        defaultUnitShouldBeFound("unitModid.contains=" + DEFAULT_UNIT_MODID);

        // Get all the unitList where unitModid contains UPDATED_UNIT_MODID
        defaultUnitShouldNotBeFound("unitModid.contains=" + UPDATED_UNIT_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitModidNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitModid does not contain DEFAULT_UNIT_MODID
        defaultUnitShouldNotBeFound("unitModid.doesNotContain=" + DEFAULT_UNIT_MODID);

        // Get all the unitList where unitModid does not contain UPDATED_UNIT_MODID
        defaultUnitShouldBeFound("unitModid.doesNotContain=" + UPDATED_UNIT_MODID);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitLogipIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip equals to DEFAULT_UNIT_LOGIP
        defaultUnitShouldBeFound("unitLogip.equals=" + DEFAULT_UNIT_LOGIP);

        // Get all the unitList where unitLogip equals to UPDATED_UNIT_LOGIP
        defaultUnitShouldNotBeFound("unitLogip.equals=" + UPDATED_UNIT_LOGIP);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLogipIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip not equals to DEFAULT_UNIT_LOGIP
        defaultUnitShouldNotBeFound("unitLogip.notEquals=" + DEFAULT_UNIT_LOGIP);

        // Get all the unitList where unitLogip not equals to UPDATED_UNIT_LOGIP
        defaultUnitShouldBeFound("unitLogip.notEquals=" + UPDATED_UNIT_LOGIP);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLogipIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip in DEFAULT_UNIT_LOGIP or UPDATED_UNIT_LOGIP
        defaultUnitShouldBeFound("unitLogip.in=" + DEFAULT_UNIT_LOGIP + "," + UPDATED_UNIT_LOGIP);

        // Get all the unitList where unitLogip equals to UPDATED_UNIT_LOGIP
        defaultUnitShouldNotBeFound("unitLogip.in=" + UPDATED_UNIT_LOGIP);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLogipIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip is not null
        defaultUnitShouldBeFound("unitLogip.specified=true");

        // Get all the unitList where unitLogip is null
        defaultUnitShouldNotBeFound("unitLogip.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitsByUnitLogipContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip contains DEFAULT_UNIT_LOGIP
        defaultUnitShouldBeFound("unitLogip.contains=" + DEFAULT_UNIT_LOGIP);

        // Get all the unitList where unitLogip contains UPDATED_UNIT_LOGIP
        defaultUnitShouldNotBeFound("unitLogip.contains=" + UPDATED_UNIT_LOGIP);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitLogipNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitLogip does not contain DEFAULT_UNIT_LOGIP
        defaultUnitShouldNotBeFound("unitLogip.doesNotContain=" + DEFAULT_UNIT_LOGIP);

        // Get all the unitList where unitLogip does not contain UPDATED_UNIT_LOGIP
        defaultUnitShouldBeFound("unitLogip.doesNotContain=" + UPDATED_UNIT_LOGIP);
    }


    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate equals to DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.equals=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate equals to UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.equals=" + UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate not equals to DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.notEquals=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate not equals to UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.notEquals=" + UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate in DEFAULT_UNIT_SIGN_DATE or UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.in=" + DEFAULT_UNIT_SIGN_DATE + "," + UPDATED_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate equals to UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.in=" + UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate is not null
        defaultUnitShouldBeFound("unitSignDate.specified=true");

        // Get all the unitList where unitSignDate is null
        defaultUnitShouldNotBeFound("unitSignDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate is greater than or equal to DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.greaterThanOrEqual=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate is greater than or equal to UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.greaterThanOrEqual=" + UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate is less than or equal to DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.lessThanOrEqual=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate is less than or equal to SMALLER_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.lessThanOrEqual=" + SMALLER_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate is less than DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.lessThan=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate is less than UPDATED_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.lessThan=" + UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void getAllUnitsByUnitSignDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where unitSignDate is greater than DEFAULT_UNIT_SIGN_DATE
        defaultUnitShouldNotBeFound("unitSignDate.greaterThan=" + DEFAULT_UNIT_SIGN_DATE);

        // Get all the unitList where unitSignDate is greater than SMALLER_UNIT_SIGN_DATE
        defaultUnitShouldBeFound("unitSignDate.greaterThan=" + SMALLER_UNIT_SIGN_DATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUnitShouldBeFound(String filter) throws Exception {
        restUnitMockMvc.perform(get("/api/units?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unit.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitUcCode").value(hasItem(DEFAULT_UNIT_UC_CODE)))
            .andExpect(jsonPath("$.[*].unitCode").value(hasItem(DEFAULT_UNIT_CODE)))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].unitAddr").value(hasItem(DEFAULT_UNIT_ADDR)))
            .andExpect(jsonPath("$.[*].unitLongitude").value(hasItem(DEFAULT_UNIT_LONGITUDE)))
            .andExpect(jsonPath("$.[*].unitLatitude").value(hasItem(DEFAULT_UNIT_LATITUDE)))
            .andExpect(jsonPath("$.[*].unitPic").value(hasItem(DEFAULT_UNIT_PIC)))
            .andExpect(jsonPath("$.[*].unitPhone").value(hasItem(DEFAULT_UNIT_PHONE)))
            .andExpect(jsonPath("$.[*].unitEmail").value(hasItem(DEFAULT_UNIT_EMAIL)))
            .andExpect(jsonPath("$.[*].unitCretime").value(hasItem(sameInstant(DEFAULT_UNIT_CRETIME))))
            .andExpect(jsonPath("$.[*].unitCreid").value(hasItem(DEFAULT_UNIT_CREID)))
            .andExpect(jsonPath("$.[*].unitModtime").value(hasItem(sameInstant(DEFAULT_UNIT_MODTIME))))
            .andExpect(jsonPath("$.[*].unitModid").value(hasItem(DEFAULT_UNIT_MODID)))
            .andExpect(jsonPath("$.[*].unitLogip").value(hasItem(DEFAULT_UNIT_LOGIP)))
            .andExpect(jsonPath("$.[*].unitSignDate").value(hasItem(sameInstant(DEFAULT_UNIT_SIGN_DATE))));

        // Check, that the count call also returns 1
        restUnitMockMvc.perform(get("/api/units/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUnitShouldNotBeFound(String filter) throws Exception {
        restUnitMockMvc.perform(get("/api/units?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUnitMockMvc.perform(get("/api/units/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingUnit() throws Exception {
        // Get the unit
        restUnitMockMvc.perform(get("/api/units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeUpdate = unitRepository.findAll().size();

        // Update the unit
        Unit updatedUnit = unitRepository.findById(unit.getId()).get();
        // Disconnect from session so that the updates on updatedUnit are not directly saved in db
        em.detach(updatedUnit);
        updatedUnit
            .unitUcCode(UPDATED_UNIT_UC_CODE)
            .unitCode(UPDATED_UNIT_CODE)
            .unitName(UPDATED_UNIT_NAME)
            .unitAddr(UPDATED_UNIT_ADDR)
            .unitLongitude(UPDATED_UNIT_LONGITUDE)
            .unitLatitude(UPDATED_UNIT_LATITUDE)
            .unitPic(UPDATED_UNIT_PIC)
            .unitPhone(UPDATED_UNIT_PHONE)
            .unitEmail(UPDATED_UNIT_EMAIL)
            .unitCretime(UPDATED_UNIT_CRETIME)
            .unitCreid(UPDATED_UNIT_CREID)
            .unitModtime(UPDATED_UNIT_MODTIME)
            .unitModid(UPDATED_UNIT_MODID)
            .unitLogip(UPDATED_UNIT_LOGIP)
            .unitSignDate(UPDATED_UNIT_SIGN_DATE);
        UnitDTO unitDTO = unitMapper.toDto(updatedUnit);

        restUnitMockMvc.perform(put("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isOk());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getUnitUcCode()).isEqualTo(UPDATED_UNIT_UC_CODE);
        assertThat(testUnit.getUnitCode()).isEqualTo(UPDATED_UNIT_CODE);
        assertThat(testUnit.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testUnit.getUnitAddr()).isEqualTo(UPDATED_UNIT_ADDR);
        assertThat(testUnit.getUnitLongitude()).isEqualTo(UPDATED_UNIT_LONGITUDE);
        assertThat(testUnit.getUnitLatitude()).isEqualTo(UPDATED_UNIT_LATITUDE);
        assertThat(testUnit.getUnitPic()).isEqualTo(UPDATED_UNIT_PIC);
        assertThat(testUnit.getUnitPhone()).isEqualTo(UPDATED_UNIT_PHONE);
        assertThat(testUnit.getUnitEmail()).isEqualTo(UPDATED_UNIT_EMAIL);
        assertThat(testUnit.getUnitCretime()).isEqualTo(UPDATED_UNIT_CRETIME);
        assertThat(testUnit.getUnitCreid()).isEqualTo(UPDATED_UNIT_CREID);
        assertThat(testUnit.getUnitModtime()).isEqualTo(UPDATED_UNIT_MODTIME);
        assertThat(testUnit.getUnitModid()).isEqualTo(UPDATED_UNIT_MODID);
        assertThat(testUnit.getUnitLogip()).isEqualTo(UPDATED_UNIT_LOGIP);
        assertThat(testUnit.getUnitSignDate()).isEqualTo(UPDATED_UNIT_SIGN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitMockMvc.perform(put("/api/units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeDelete = unitRepository.findAll().size();

        // Delete the unit
        restUnitMockMvc.perform(delete("/api/units/{id}", unit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
