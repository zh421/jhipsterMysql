package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.UnitClass;
import aiot.java.repository.UnitClassRepository;
import aiot.java.service.UnitClassService;
import aiot.java.service.dto.UnitClassDTO;
import aiot.java.service.mapper.UnitClassMapper;
import aiot.java.service.dto.UnitClassCriteria;
import aiot.java.service.UnitClassQueryService;

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
 * Integration tests for the {@link UnitClassResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UnitClassResourceIT {

    private static final String DEFAULT_UC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_UC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_UC_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UC_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UC_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_UC_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_UC_CREID = "AAAAAAAAAA";
    private static final String UPDATED_UC_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UC_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UC_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_UC_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_UC_MODID = "AAAAAAAAAA";
    private static final String UPDATED_UC_MODID = "BBBBBBBBBB";

    @Autowired
    private UnitClassRepository unitClassRepository;

    @Autowired
    private UnitClassMapper unitClassMapper;

    @Autowired
    private UnitClassService unitClassService;

    @Autowired
    private UnitClassQueryService unitClassQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitClassMockMvc;

    private UnitClass unitClass;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitClass createEntity(EntityManager em) {
        UnitClass unitClass = new UnitClass()
            .ucCode(DEFAULT_UC_CODE)
            .ucName(DEFAULT_UC_NAME)
            .ucCretime(DEFAULT_UC_CRETIME)
            .ucCreid(DEFAULT_UC_CREID)
            .ucModtime(DEFAULT_UC_MODTIME)
            .ucModid(DEFAULT_UC_MODID);
        return unitClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitClass createUpdatedEntity(EntityManager em) {
        UnitClass unitClass = new UnitClass()
            .ucCode(UPDATED_UC_CODE)
            .ucName(UPDATED_UC_NAME)
            .ucCretime(UPDATED_UC_CRETIME)
            .ucCreid(UPDATED_UC_CREID)
            .ucModtime(UPDATED_UC_MODTIME)
            .ucModid(UPDATED_UC_MODID);
        return unitClass;
    }

    @BeforeEach
    public void initTest() {
        unitClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitClass() throws Exception {
        int databaseSizeBeforeCreate = unitClassRepository.findAll().size();

        // Create the UnitClass
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);
        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isCreated());

        // Validate the UnitClass in the database
        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeCreate + 1);
        UnitClass testUnitClass = unitClassList.get(unitClassList.size() - 1);
        assertThat(testUnitClass.getUcCode()).isEqualTo(DEFAULT_UC_CODE);
        assertThat(testUnitClass.getUcName()).isEqualTo(DEFAULT_UC_NAME);
        assertThat(testUnitClass.getUcCretime()).isEqualTo(DEFAULT_UC_CRETIME);
        assertThat(testUnitClass.getUcCreid()).isEqualTo(DEFAULT_UC_CREID);
        assertThat(testUnitClass.getUcModtime()).isEqualTo(DEFAULT_UC_MODTIME);
        assertThat(testUnitClass.getUcModid()).isEqualTo(DEFAULT_UC_MODID);
    }

    @Test
    @Transactional
    public void createUnitClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitClassRepository.findAll().size();

        // Create the UnitClass with an existing ID
        unitClass.setId(1L);
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnitClass in the database
        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcCode(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcName(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcCretime(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcCreid(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcModtime(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUcModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitClassRepository.findAll().size();
        // set the field null
        unitClass.setUcModid(null);

        // Create the UnitClass, which fails.
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        restUnitClassMockMvc.perform(post("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUnitClasses() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList
        restUnitClassMockMvc.perform(get("/api/unit-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].ucCode").value(hasItem(DEFAULT_UC_CODE)))
            .andExpect(jsonPath("$.[*].ucName").value(hasItem(DEFAULT_UC_NAME)))
            .andExpect(jsonPath("$.[*].ucCretime").value(hasItem(sameInstant(DEFAULT_UC_CRETIME))))
            .andExpect(jsonPath("$.[*].ucCreid").value(hasItem(DEFAULT_UC_CREID)))
            .andExpect(jsonPath("$.[*].ucModtime").value(hasItem(sameInstant(DEFAULT_UC_MODTIME))))
            .andExpect(jsonPath("$.[*].ucModid").value(hasItem(DEFAULT_UC_MODID)));
    }
    
    @Test
    @Transactional
    public void getUnitClass() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get the unitClass
        restUnitClassMockMvc.perform(get("/api/unit-classes/{id}", unitClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unitClass.getId().intValue()))
            .andExpect(jsonPath("$.ucCode").value(DEFAULT_UC_CODE))
            .andExpect(jsonPath("$.ucName").value(DEFAULT_UC_NAME))
            .andExpect(jsonPath("$.ucCretime").value(sameInstant(DEFAULT_UC_CRETIME)))
            .andExpect(jsonPath("$.ucCreid").value(DEFAULT_UC_CREID))
            .andExpect(jsonPath("$.ucModtime").value(sameInstant(DEFAULT_UC_MODTIME)))
            .andExpect(jsonPath("$.ucModid").value(DEFAULT_UC_MODID));
    }


    @Test
    @Transactional
    public void getUnitClassesByIdFiltering() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        Long id = unitClass.getId();

        defaultUnitClassShouldBeFound("id.equals=" + id);
        defaultUnitClassShouldNotBeFound("id.notEquals=" + id);

        defaultUnitClassShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUnitClassShouldNotBeFound("id.greaterThan=" + id);

        defaultUnitClassShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUnitClassShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode equals to DEFAULT_UC_CODE
        defaultUnitClassShouldBeFound("ucCode.equals=" + DEFAULT_UC_CODE);

        // Get all the unitClassList where ucCode equals to UPDATED_UC_CODE
        defaultUnitClassShouldNotBeFound("ucCode.equals=" + UPDATED_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode not equals to DEFAULT_UC_CODE
        defaultUnitClassShouldNotBeFound("ucCode.notEquals=" + DEFAULT_UC_CODE);

        // Get all the unitClassList where ucCode not equals to UPDATED_UC_CODE
        defaultUnitClassShouldBeFound("ucCode.notEquals=" + UPDATED_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCodeIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode in DEFAULT_UC_CODE or UPDATED_UC_CODE
        defaultUnitClassShouldBeFound("ucCode.in=" + DEFAULT_UC_CODE + "," + UPDATED_UC_CODE);

        // Get all the unitClassList where ucCode equals to UPDATED_UC_CODE
        defaultUnitClassShouldNotBeFound("ucCode.in=" + UPDATED_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode is not null
        defaultUnitClassShouldBeFound("ucCode.specified=true");

        // Get all the unitClassList where ucCode is null
        defaultUnitClassShouldNotBeFound("ucCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitClassesByUcCodeContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode contains DEFAULT_UC_CODE
        defaultUnitClassShouldBeFound("ucCode.contains=" + DEFAULT_UC_CODE);

        // Get all the unitClassList where ucCode contains UPDATED_UC_CODE
        defaultUnitClassShouldNotBeFound("ucCode.contains=" + UPDATED_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCodeNotContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCode does not contain DEFAULT_UC_CODE
        defaultUnitClassShouldNotBeFound("ucCode.doesNotContain=" + DEFAULT_UC_CODE);

        // Get all the unitClassList where ucCode does not contain UPDATED_UC_CODE
        defaultUnitClassShouldBeFound("ucCode.doesNotContain=" + UPDATED_UC_CODE);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcNameIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName equals to DEFAULT_UC_NAME
        defaultUnitClassShouldBeFound("ucName.equals=" + DEFAULT_UC_NAME);

        // Get all the unitClassList where ucName equals to UPDATED_UC_NAME
        defaultUnitClassShouldNotBeFound("ucName.equals=" + UPDATED_UC_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName not equals to DEFAULT_UC_NAME
        defaultUnitClassShouldNotBeFound("ucName.notEquals=" + DEFAULT_UC_NAME);

        // Get all the unitClassList where ucName not equals to UPDATED_UC_NAME
        defaultUnitClassShouldBeFound("ucName.notEquals=" + UPDATED_UC_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcNameIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName in DEFAULT_UC_NAME or UPDATED_UC_NAME
        defaultUnitClassShouldBeFound("ucName.in=" + DEFAULT_UC_NAME + "," + UPDATED_UC_NAME);

        // Get all the unitClassList where ucName equals to UPDATED_UC_NAME
        defaultUnitClassShouldNotBeFound("ucName.in=" + UPDATED_UC_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName is not null
        defaultUnitClassShouldBeFound("ucName.specified=true");

        // Get all the unitClassList where ucName is null
        defaultUnitClassShouldNotBeFound("ucName.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitClassesByUcNameContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName contains DEFAULT_UC_NAME
        defaultUnitClassShouldBeFound("ucName.contains=" + DEFAULT_UC_NAME);

        // Get all the unitClassList where ucName contains UPDATED_UC_NAME
        defaultUnitClassShouldNotBeFound("ucName.contains=" + UPDATED_UC_NAME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcNameNotContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucName does not contain DEFAULT_UC_NAME
        defaultUnitClassShouldNotBeFound("ucName.doesNotContain=" + DEFAULT_UC_NAME);

        // Get all the unitClassList where ucName does not contain UPDATED_UC_NAME
        defaultUnitClassShouldBeFound("ucName.doesNotContain=" + UPDATED_UC_NAME);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime equals to DEFAULT_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.equals=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime equals to UPDATED_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.equals=" + UPDATED_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime not equals to DEFAULT_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.notEquals=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime not equals to UPDATED_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.notEquals=" + UPDATED_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime in DEFAULT_UC_CRETIME or UPDATED_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.in=" + DEFAULT_UC_CRETIME + "," + UPDATED_UC_CRETIME);

        // Get all the unitClassList where ucCretime equals to UPDATED_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.in=" + UPDATED_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime is not null
        defaultUnitClassShouldBeFound("ucCretime.specified=true");

        // Get all the unitClassList where ucCretime is null
        defaultUnitClassShouldNotBeFound("ucCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime is greater than or equal to DEFAULT_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.greaterThanOrEqual=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime is greater than or equal to UPDATED_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.greaterThanOrEqual=" + UPDATED_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime is less than or equal to DEFAULT_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.lessThanOrEqual=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime is less than or equal to SMALLER_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.lessThanOrEqual=" + SMALLER_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime is less than DEFAULT_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.lessThan=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime is less than UPDATED_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.lessThan=" + UPDATED_UC_CRETIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCretime is greater than DEFAULT_UC_CRETIME
        defaultUnitClassShouldNotBeFound("ucCretime.greaterThan=" + DEFAULT_UC_CRETIME);

        // Get all the unitClassList where ucCretime is greater than SMALLER_UC_CRETIME
        defaultUnitClassShouldBeFound("ucCretime.greaterThan=" + SMALLER_UC_CRETIME);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid equals to DEFAULT_UC_CREID
        defaultUnitClassShouldBeFound("ucCreid.equals=" + DEFAULT_UC_CREID);

        // Get all the unitClassList where ucCreid equals to UPDATED_UC_CREID
        defaultUnitClassShouldNotBeFound("ucCreid.equals=" + UPDATED_UC_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid not equals to DEFAULT_UC_CREID
        defaultUnitClassShouldNotBeFound("ucCreid.notEquals=" + DEFAULT_UC_CREID);

        // Get all the unitClassList where ucCreid not equals to UPDATED_UC_CREID
        defaultUnitClassShouldBeFound("ucCreid.notEquals=" + UPDATED_UC_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCreidIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid in DEFAULT_UC_CREID or UPDATED_UC_CREID
        defaultUnitClassShouldBeFound("ucCreid.in=" + DEFAULT_UC_CREID + "," + UPDATED_UC_CREID);

        // Get all the unitClassList where ucCreid equals to UPDATED_UC_CREID
        defaultUnitClassShouldNotBeFound("ucCreid.in=" + UPDATED_UC_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid is not null
        defaultUnitClassShouldBeFound("ucCreid.specified=true");

        // Get all the unitClassList where ucCreid is null
        defaultUnitClassShouldNotBeFound("ucCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitClassesByUcCreidContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid contains DEFAULT_UC_CREID
        defaultUnitClassShouldBeFound("ucCreid.contains=" + DEFAULT_UC_CREID);

        // Get all the unitClassList where ucCreid contains UPDATED_UC_CREID
        defaultUnitClassShouldNotBeFound("ucCreid.contains=" + UPDATED_UC_CREID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcCreidNotContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucCreid does not contain DEFAULT_UC_CREID
        defaultUnitClassShouldNotBeFound("ucCreid.doesNotContain=" + DEFAULT_UC_CREID);

        // Get all the unitClassList where ucCreid does not contain UPDATED_UC_CREID
        defaultUnitClassShouldBeFound("ucCreid.doesNotContain=" + UPDATED_UC_CREID);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime equals to DEFAULT_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.equals=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime equals to UPDATED_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.equals=" + UPDATED_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime not equals to DEFAULT_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.notEquals=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime not equals to UPDATED_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.notEquals=" + UPDATED_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime in DEFAULT_UC_MODTIME or UPDATED_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.in=" + DEFAULT_UC_MODTIME + "," + UPDATED_UC_MODTIME);

        // Get all the unitClassList where ucModtime equals to UPDATED_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.in=" + UPDATED_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime is not null
        defaultUnitClassShouldBeFound("ucModtime.specified=true");

        // Get all the unitClassList where ucModtime is null
        defaultUnitClassShouldNotBeFound("ucModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime is greater than or equal to DEFAULT_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.greaterThanOrEqual=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime is greater than or equal to UPDATED_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.greaterThanOrEqual=" + UPDATED_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime is less than or equal to DEFAULT_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.lessThanOrEqual=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime is less than or equal to SMALLER_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.lessThanOrEqual=" + SMALLER_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime is less than DEFAULT_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.lessThan=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime is less than UPDATED_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.lessThan=" + UPDATED_UC_MODTIME);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModtime is greater than DEFAULT_UC_MODTIME
        defaultUnitClassShouldNotBeFound("ucModtime.greaterThan=" + DEFAULT_UC_MODTIME);

        // Get all the unitClassList where ucModtime is greater than SMALLER_UC_MODTIME
        defaultUnitClassShouldBeFound("ucModtime.greaterThan=" + SMALLER_UC_MODTIME);
    }


    @Test
    @Transactional
    public void getAllUnitClassesByUcModidIsEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid equals to DEFAULT_UC_MODID
        defaultUnitClassShouldBeFound("ucModid.equals=" + DEFAULT_UC_MODID);

        // Get all the unitClassList where ucModid equals to UPDATED_UC_MODID
        defaultUnitClassShouldNotBeFound("ucModid.equals=" + UPDATED_UC_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid not equals to DEFAULT_UC_MODID
        defaultUnitClassShouldNotBeFound("ucModid.notEquals=" + DEFAULT_UC_MODID);

        // Get all the unitClassList where ucModid not equals to UPDATED_UC_MODID
        defaultUnitClassShouldBeFound("ucModid.notEquals=" + UPDATED_UC_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModidIsInShouldWork() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid in DEFAULT_UC_MODID or UPDATED_UC_MODID
        defaultUnitClassShouldBeFound("ucModid.in=" + DEFAULT_UC_MODID + "," + UPDATED_UC_MODID);

        // Get all the unitClassList where ucModid equals to UPDATED_UC_MODID
        defaultUnitClassShouldNotBeFound("ucModid.in=" + UPDATED_UC_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid is not null
        defaultUnitClassShouldBeFound("ucModid.specified=true");

        // Get all the unitClassList where ucModid is null
        defaultUnitClassShouldNotBeFound("ucModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnitClassesByUcModidContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid contains DEFAULT_UC_MODID
        defaultUnitClassShouldBeFound("ucModid.contains=" + DEFAULT_UC_MODID);

        // Get all the unitClassList where ucModid contains UPDATED_UC_MODID
        defaultUnitClassShouldNotBeFound("ucModid.contains=" + UPDATED_UC_MODID);
    }

    @Test
    @Transactional
    public void getAllUnitClassesByUcModidNotContainsSomething() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        // Get all the unitClassList where ucModid does not contain DEFAULT_UC_MODID
        defaultUnitClassShouldNotBeFound("ucModid.doesNotContain=" + DEFAULT_UC_MODID);

        // Get all the unitClassList where ucModid does not contain UPDATED_UC_MODID
        defaultUnitClassShouldBeFound("ucModid.doesNotContain=" + UPDATED_UC_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUnitClassShouldBeFound(String filter) throws Exception {
        restUnitClassMockMvc.perform(get("/api/unit-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].ucCode").value(hasItem(DEFAULT_UC_CODE)))
            .andExpect(jsonPath("$.[*].ucName").value(hasItem(DEFAULT_UC_NAME)))
            .andExpect(jsonPath("$.[*].ucCretime").value(hasItem(sameInstant(DEFAULT_UC_CRETIME))))
            .andExpect(jsonPath("$.[*].ucCreid").value(hasItem(DEFAULT_UC_CREID)))
            .andExpect(jsonPath("$.[*].ucModtime").value(hasItem(sameInstant(DEFAULT_UC_MODTIME))))
            .andExpect(jsonPath("$.[*].ucModid").value(hasItem(DEFAULT_UC_MODID)));

        // Check, that the count call also returns 1
        restUnitClassMockMvc.perform(get("/api/unit-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUnitClassShouldNotBeFound(String filter) throws Exception {
        restUnitClassMockMvc.perform(get("/api/unit-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUnitClassMockMvc.perform(get("/api/unit-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingUnitClass() throws Exception {
        // Get the unitClass
        restUnitClassMockMvc.perform(get("/api/unit-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitClass() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        int databaseSizeBeforeUpdate = unitClassRepository.findAll().size();

        // Update the unitClass
        UnitClass updatedUnitClass = unitClassRepository.findById(unitClass.getId()).get();
        // Disconnect from session so that the updates on updatedUnitClass are not directly saved in db
        em.detach(updatedUnitClass);
        updatedUnitClass
            .ucCode(UPDATED_UC_CODE)
            .ucName(UPDATED_UC_NAME)
            .ucCretime(UPDATED_UC_CRETIME)
            .ucCreid(UPDATED_UC_CREID)
            .ucModtime(UPDATED_UC_MODTIME)
            .ucModid(UPDATED_UC_MODID);
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(updatedUnitClass);

        restUnitClassMockMvc.perform(put("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isOk());

        // Validate the UnitClass in the database
        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeUpdate);
        UnitClass testUnitClass = unitClassList.get(unitClassList.size() - 1);
        assertThat(testUnitClass.getUcCode()).isEqualTo(UPDATED_UC_CODE);
        assertThat(testUnitClass.getUcName()).isEqualTo(UPDATED_UC_NAME);
        assertThat(testUnitClass.getUcCretime()).isEqualTo(UPDATED_UC_CRETIME);
        assertThat(testUnitClass.getUcCreid()).isEqualTo(UPDATED_UC_CREID);
        assertThat(testUnitClass.getUcModtime()).isEqualTo(UPDATED_UC_MODTIME);
        assertThat(testUnitClass.getUcModid()).isEqualTo(UPDATED_UC_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitClass() throws Exception {
        int databaseSizeBeforeUpdate = unitClassRepository.findAll().size();

        // Create the UnitClass
        UnitClassDTO unitClassDTO = unitClassMapper.toDto(unitClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitClassMockMvc.perform(put("/api/unit-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnitClass in the database
        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnitClass() throws Exception {
        // Initialize the database
        unitClassRepository.saveAndFlush(unitClass);

        int databaseSizeBeforeDelete = unitClassRepository.findAll().size();

        // Delete the unitClass
        restUnitClassMockMvc.perform(delete("/api/unit-classes/{id}", unitClass.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnitClass> unitClassList = unitClassRepository.findAll();
        assertThat(unitClassList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
