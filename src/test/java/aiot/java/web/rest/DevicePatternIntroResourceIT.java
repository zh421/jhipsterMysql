package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.DevicePatternIntro;
import aiot.java.repository.DevicePatternIntroRepository;
import aiot.java.service.DevicePatternIntroService;
import aiot.java.service.dto.DevicePatternIntroDTO;
import aiot.java.service.mapper.DevicePatternIntroMapper;
import aiot.java.service.dto.DevicePatternIntroCriteria;
import aiot.java.service.DevicePatternIntroQueryService;

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
 * Integration tests for the {@link DevicePatternIntroResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DevicePatternIntroResourceIT {

    private static final String DEFAULT_DEVICEPATTERN_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICEPATTERN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICEPATTERN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICEPATTERN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_MEMO = "BBBBBBBBBB";

    private static final String DEFAULT_CREUSER = "AAAAAAAAAA";
    private static final String UPDATED_CREUSER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_MODUSER = "AAAAAAAAAA";
    private static final String UPDATED_MODUSER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    @Autowired
    private DevicePatternIntroRepository devicePatternIntroRepository;

    @Autowired
    private DevicePatternIntroMapper devicePatternIntroMapper;

    @Autowired
    private DevicePatternIntroService devicePatternIntroService;

    @Autowired
    private DevicePatternIntroQueryService devicePatternIntroQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDevicePatternIntroMockMvc;

    private DevicePatternIntro devicePatternIntro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DevicePatternIntro createEntity(EntityManager em) {
        DevicePatternIntro devicePatternIntro = new DevicePatternIntro()
            .devicepatternId(DEFAULT_DEVICEPATTERN_ID)
            .devicepatternCode(DEFAULT_DEVICEPATTERN_CODE)
            .memo(DEFAULT_MEMO)
            .creuser(DEFAULT_CREUSER)
            .cretime(DEFAULT_CRETIME)
            .moduser(DEFAULT_MODUSER)
            .modtime(DEFAULT_MODTIME);
        return devicePatternIntro;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DevicePatternIntro createUpdatedEntity(EntityManager em) {
        DevicePatternIntro devicePatternIntro = new DevicePatternIntro()
            .devicepatternId(UPDATED_DEVICEPATTERN_ID)
            .devicepatternCode(UPDATED_DEVICEPATTERN_CODE)
            .memo(UPDATED_MEMO)
            .creuser(UPDATED_CREUSER)
            .cretime(UPDATED_CRETIME)
            .moduser(UPDATED_MODUSER)
            .modtime(UPDATED_MODTIME);
        return devicePatternIntro;
    }

    @BeforeEach
    public void initTest() {
        devicePatternIntro = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevicePatternIntro() throws Exception {
        int databaseSizeBeforeCreate = devicePatternIntroRepository.findAll().size();

        // Create the DevicePatternIntro
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);
        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isCreated());

        // Validate the DevicePatternIntro in the database
        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeCreate + 1);
        DevicePatternIntro testDevicePatternIntro = devicePatternIntroList.get(devicePatternIntroList.size() - 1);
        assertThat(testDevicePatternIntro.getDevicepatternId()).isEqualTo(DEFAULT_DEVICEPATTERN_ID);
        assertThat(testDevicePatternIntro.getDevicepatternCode()).isEqualTo(DEFAULT_DEVICEPATTERN_CODE);
        assertThat(testDevicePatternIntro.getMemo()).isEqualTo(DEFAULT_MEMO);
        assertThat(testDevicePatternIntro.getCreuser()).isEqualTo(DEFAULT_CREUSER);
        assertThat(testDevicePatternIntro.getCretime()).isEqualTo(DEFAULT_CRETIME);
        assertThat(testDevicePatternIntro.getModuser()).isEqualTo(DEFAULT_MODUSER);
        assertThat(testDevicePatternIntro.getModtime()).isEqualTo(DEFAULT_MODTIME);
    }

    @Test
    @Transactional
    public void createDevicePatternIntroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = devicePatternIntroRepository.findAll().size();

        // Create the DevicePatternIntro with an existing ID
        devicePatternIntro.setId(1L);
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DevicePatternIntro in the database
        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDevicepatternIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setDevicepatternId(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDevicepatternCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setDevicepatternCode(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreuserIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setCreuser(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setCretime(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModuserIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setModuser(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicePatternIntroRepository.findAll().size();
        // set the field null
        devicePatternIntro.setModtime(null);

        // Create the DevicePatternIntro, which fails.
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        restDevicePatternIntroMockMvc.perform(post("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntros() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devicePatternIntro.getId().intValue())))
            .andExpect(jsonPath("$.[*].devicepatternId").value(hasItem(DEFAULT_DEVICEPATTERN_ID)))
            .andExpect(jsonPath("$.[*].devicepatternCode").value(hasItem(DEFAULT_DEVICEPATTERN_CODE)))
            .andExpect(jsonPath("$.[*].memo").value(hasItem(DEFAULT_MEMO)))
            .andExpect(jsonPath("$.[*].creuser").value(hasItem(DEFAULT_CREUSER)))
            .andExpect(jsonPath("$.[*].cretime").value(hasItem(sameInstant(DEFAULT_CRETIME))))
            .andExpect(jsonPath("$.[*].moduser").value(hasItem(DEFAULT_MODUSER)))
            .andExpect(jsonPath("$.[*].modtime").value(hasItem(sameInstant(DEFAULT_MODTIME))));
    }
    
    @Test
    @Transactional
    public void getDevicePatternIntro() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get the devicePatternIntro
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros/{id}", devicePatternIntro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(devicePatternIntro.getId().intValue()))
            .andExpect(jsonPath("$.devicepatternId").value(DEFAULT_DEVICEPATTERN_ID))
            .andExpect(jsonPath("$.devicepatternCode").value(DEFAULT_DEVICEPATTERN_CODE))
            .andExpect(jsonPath("$.memo").value(DEFAULT_MEMO))
            .andExpect(jsonPath("$.creuser").value(DEFAULT_CREUSER))
            .andExpect(jsonPath("$.cretime").value(sameInstant(DEFAULT_CRETIME)))
            .andExpect(jsonPath("$.moduser").value(DEFAULT_MODUSER))
            .andExpect(jsonPath("$.modtime").value(sameInstant(DEFAULT_MODTIME)));
    }


    @Test
    @Transactional
    public void getDevicePatternIntrosByIdFiltering() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        Long id = devicePatternIntro.getId();

        defaultDevicePatternIntroShouldBeFound("id.equals=" + id);
        defaultDevicePatternIntroShouldNotBeFound("id.notEquals=" + id);

        defaultDevicePatternIntroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDevicePatternIntroShouldNotBeFound("id.greaterThan=" + id);

        defaultDevicePatternIntroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDevicePatternIntroShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId equals to DEFAULT_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldBeFound("devicepatternId.equals=" + DEFAULT_DEVICEPATTERN_ID);

        // Get all the devicePatternIntroList where devicepatternId equals to UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.equals=" + UPDATED_DEVICEPATTERN_ID);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId not equals to DEFAULT_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.notEquals=" + DEFAULT_DEVICEPATTERN_ID);

        // Get all the devicePatternIntroList where devicepatternId not equals to UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldBeFound("devicepatternId.notEquals=" + UPDATED_DEVICEPATTERN_ID);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId in DEFAULT_DEVICEPATTERN_ID or UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldBeFound("devicepatternId.in=" + DEFAULT_DEVICEPATTERN_ID + "," + UPDATED_DEVICEPATTERN_ID);

        // Get all the devicePatternIntroList where devicepatternId equals to UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.in=" + UPDATED_DEVICEPATTERN_ID);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId is not null
        defaultDevicePatternIntroShouldBeFound("devicepatternId.specified=true");

        // Get all the devicePatternIntroList where devicepatternId is null
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.specified=false");
    }
                @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId contains DEFAULT_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldBeFound("devicepatternId.contains=" + DEFAULT_DEVICEPATTERN_ID);

        // Get all the devicePatternIntroList where devicepatternId contains UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.contains=" + UPDATED_DEVICEPATTERN_ID);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternIdNotContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternId does not contain DEFAULT_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldNotBeFound("devicepatternId.doesNotContain=" + DEFAULT_DEVICEPATTERN_ID);

        // Get all the devicePatternIntroList where devicepatternId does not contain UPDATED_DEVICEPATTERN_ID
        defaultDevicePatternIntroShouldBeFound("devicepatternId.doesNotContain=" + UPDATED_DEVICEPATTERN_ID);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode equals to DEFAULT_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.equals=" + DEFAULT_DEVICEPATTERN_CODE);

        // Get all the devicePatternIntroList where devicepatternCode equals to UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.equals=" + UPDATED_DEVICEPATTERN_CODE);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode not equals to DEFAULT_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.notEquals=" + DEFAULT_DEVICEPATTERN_CODE);

        // Get all the devicePatternIntroList where devicepatternCode not equals to UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.notEquals=" + UPDATED_DEVICEPATTERN_CODE);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode in DEFAULT_DEVICEPATTERN_CODE or UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.in=" + DEFAULT_DEVICEPATTERN_CODE + "," + UPDATED_DEVICEPATTERN_CODE);

        // Get all the devicePatternIntroList where devicepatternCode equals to UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.in=" + UPDATED_DEVICEPATTERN_CODE);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode is not null
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.specified=true");

        // Get all the devicePatternIntroList where devicepatternCode is null
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode contains DEFAULT_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.contains=" + DEFAULT_DEVICEPATTERN_CODE);

        // Get all the devicePatternIntroList where devicepatternCode contains UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.contains=" + UPDATED_DEVICEPATTERN_CODE);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByDevicepatternCodeNotContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where devicepatternCode does not contain DEFAULT_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldNotBeFound("devicepatternCode.doesNotContain=" + DEFAULT_DEVICEPATTERN_CODE);

        // Get all the devicePatternIntroList where devicepatternCode does not contain UPDATED_DEVICEPATTERN_CODE
        defaultDevicePatternIntroShouldBeFound("devicepatternCode.doesNotContain=" + UPDATED_DEVICEPATTERN_CODE);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo equals to DEFAULT_MEMO
        defaultDevicePatternIntroShouldBeFound("memo.equals=" + DEFAULT_MEMO);

        // Get all the devicePatternIntroList where memo equals to UPDATED_MEMO
        defaultDevicePatternIntroShouldNotBeFound("memo.equals=" + UPDATED_MEMO);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo not equals to DEFAULT_MEMO
        defaultDevicePatternIntroShouldNotBeFound("memo.notEquals=" + DEFAULT_MEMO);

        // Get all the devicePatternIntroList where memo not equals to UPDATED_MEMO
        defaultDevicePatternIntroShouldBeFound("memo.notEquals=" + UPDATED_MEMO);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo in DEFAULT_MEMO or UPDATED_MEMO
        defaultDevicePatternIntroShouldBeFound("memo.in=" + DEFAULT_MEMO + "," + UPDATED_MEMO);

        // Get all the devicePatternIntroList where memo equals to UPDATED_MEMO
        defaultDevicePatternIntroShouldNotBeFound("memo.in=" + UPDATED_MEMO);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo is not null
        defaultDevicePatternIntroShouldBeFound("memo.specified=true");

        // Get all the devicePatternIntroList where memo is null
        defaultDevicePatternIntroShouldNotBeFound("memo.specified=false");
    }
                @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo contains DEFAULT_MEMO
        defaultDevicePatternIntroShouldBeFound("memo.contains=" + DEFAULT_MEMO);

        // Get all the devicePatternIntroList where memo contains UPDATED_MEMO
        defaultDevicePatternIntroShouldNotBeFound("memo.contains=" + UPDATED_MEMO);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByMemoNotContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where memo does not contain DEFAULT_MEMO
        defaultDevicePatternIntroShouldNotBeFound("memo.doesNotContain=" + DEFAULT_MEMO);

        // Get all the devicePatternIntroList where memo does not contain UPDATED_MEMO
        defaultDevicePatternIntroShouldBeFound("memo.doesNotContain=" + UPDATED_MEMO);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser equals to DEFAULT_CREUSER
        defaultDevicePatternIntroShouldBeFound("creuser.equals=" + DEFAULT_CREUSER);

        // Get all the devicePatternIntroList where creuser equals to UPDATED_CREUSER
        defaultDevicePatternIntroShouldNotBeFound("creuser.equals=" + UPDATED_CREUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser not equals to DEFAULT_CREUSER
        defaultDevicePatternIntroShouldNotBeFound("creuser.notEquals=" + DEFAULT_CREUSER);

        // Get all the devicePatternIntroList where creuser not equals to UPDATED_CREUSER
        defaultDevicePatternIntroShouldBeFound("creuser.notEquals=" + UPDATED_CREUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser in DEFAULT_CREUSER or UPDATED_CREUSER
        defaultDevicePatternIntroShouldBeFound("creuser.in=" + DEFAULT_CREUSER + "," + UPDATED_CREUSER);

        // Get all the devicePatternIntroList where creuser equals to UPDATED_CREUSER
        defaultDevicePatternIntroShouldNotBeFound("creuser.in=" + UPDATED_CREUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser is not null
        defaultDevicePatternIntroShouldBeFound("creuser.specified=true");

        // Get all the devicePatternIntroList where creuser is null
        defaultDevicePatternIntroShouldNotBeFound("creuser.specified=false");
    }
                @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser contains DEFAULT_CREUSER
        defaultDevicePatternIntroShouldBeFound("creuser.contains=" + DEFAULT_CREUSER);

        // Get all the devicePatternIntroList where creuser contains UPDATED_CREUSER
        defaultDevicePatternIntroShouldNotBeFound("creuser.contains=" + UPDATED_CREUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCreuserNotContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where creuser does not contain DEFAULT_CREUSER
        defaultDevicePatternIntroShouldNotBeFound("creuser.doesNotContain=" + DEFAULT_CREUSER);

        // Get all the devicePatternIntroList where creuser does not contain UPDATED_CREUSER
        defaultDevicePatternIntroShouldBeFound("creuser.doesNotContain=" + UPDATED_CREUSER);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime equals to DEFAULT_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.equals=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime equals to UPDATED_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.equals=" + UPDATED_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime not equals to DEFAULT_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.notEquals=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime not equals to UPDATED_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.notEquals=" + UPDATED_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime in DEFAULT_CRETIME or UPDATED_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.in=" + DEFAULT_CRETIME + "," + UPDATED_CRETIME);

        // Get all the devicePatternIntroList where cretime equals to UPDATED_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.in=" + UPDATED_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime is not null
        defaultDevicePatternIntroShouldBeFound("cretime.specified=true");

        // Get all the devicePatternIntroList where cretime is null
        defaultDevicePatternIntroShouldNotBeFound("cretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime is greater than or equal to DEFAULT_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.greaterThanOrEqual=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime is greater than or equal to UPDATED_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.greaterThanOrEqual=" + UPDATED_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime is less than or equal to DEFAULT_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.lessThanOrEqual=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime is less than or equal to SMALLER_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.lessThanOrEqual=" + SMALLER_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime is less than DEFAULT_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.lessThan=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime is less than UPDATED_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.lessThan=" + UPDATED_CRETIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where cretime is greater than DEFAULT_CRETIME
        defaultDevicePatternIntroShouldNotBeFound("cretime.greaterThan=" + DEFAULT_CRETIME);

        // Get all the devicePatternIntroList where cretime is greater than SMALLER_CRETIME
        defaultDevicePatternIntroShouldBeFound("cretime.greaterThan=" + SMALLER_CRETIME);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser equals to DEFAULT_MODUSER
        defaultDevicePatternIntroShouldBeFound("moduser.equals=" + DEFAULT_MODUSER);

        // Get all the devicePatternIntroList where moduser equals to UPDATED_MODUSER
        defaultDevicePatternIntroShouldNotBeFound("moduser.equals=" + UPDATED_MODUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser not equals to DEFAULT_MODUSER
        defaultDevicePatternIntroShouldNotBeFound("moduser.notEquals=" + DEFAULT_MODUSER);

        // Get all the devicePatternIntroList where moduser not equals to UPDATED_MODUSER
        defaultDevicePatternIntroShouldBeFound("moduser.notEquals=" + UPDATED_MODUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser in DEFAULT_MODUSER or UPDATED_MODUSER
        defaultDevicePatternIntroShouldBeFound("moduser.in=" + DEFAULT_MODUSER + "," + UPDATED_MODUSER);

        // Get all the devicePatternIntroList where moduser equals to UPDATED_MODUSER
        defaultDevicePatternIntroShouldNotBeFound("moduser.in=" + UPDATED_MODUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser is not null
        defaultDevicePatternIntroShouldBeFound("moduser.specified=true");

        // Get all the devicePatternIntroList where moduser is null
        defaultDevicePatternIntroShouldNotBeFound("moduser.specified=false");
    }
                @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser contains DEFAULT_MODUSER
        defaultDevicePatternIntroShouldBeFound("moduser.contains=" + DEFAULT_MODUSER);

        // Get all the devicePatternIntroList where moduser contains UPDATED_MODUSER
        defaultDevicePatternIntroShouldNotBeFound("moduser.contains=" + UPDATED_MODUSER);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModuserNotContainsSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where moduser does not contain DEFAULT_MODUSER
        defaultDevicePatternIntroShouldNotBeFound("moduser.doesNotContain=" + DEFAULT_MODUSER);

        // Get all the devicePatternIntroList where moduser does not contain UPDATED_MODUSER
        defaultDevicePatternIntroShouldBeFound("moduser.doesNotContain=" + UPDATED_MODUSER);
    }


    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime equals to DEFAULT_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.equals=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime equals to UPDATED_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.equals=" + UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime not equals to DEFAULT_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.notEquals=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime not equals to UPDATED_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.notEquals=" + UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime in DEFAULT_MODTIME or UPDATED_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.in=" + DEFAULT_MODTIME + "," + UPDATED_MODTIME);

        // Get all the devicePatternIntroList where modtime equals to UPDATED_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.in=" + UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime is not null
        defaultDevicePatternIntroShouldBeFound("modtime.specified=true");

        // Get all the devicePatternIntroList where modtime is null
        defaultDevicePatternIntroShouldNotBeFound("modtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime is greater than or equal to DEFAULT_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.greaterThanOrEqual=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime is greater than or equal to UPDATED_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.greaterThanOrEqual=" + UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime is less than or equal to DEFAULT_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.lessThanOrEqual=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime is less than or equal to SMALLER_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.lessThanOrEqual=" + SMALLER_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime is less than DEFAULT_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.lessThan=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime is less than UPDATED_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.lessThan=" + UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void getAllDevicePatternIntrosByModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        // Get all the devicePatternIntroList where modtime is greater than DEFAULT_MODTIME
        defaultDevicePatternIntroShouldNotBeFound("modtime.greaterThan=" + DEFAULT_MODTIME);

        // Get all the devicePatternIntroList where modtime is greater than SMALLER_MODTIME
        defaultDevicePatternIntroShouldBeFound("modtime.greaterThan=" + SMALLER_MODTIME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDevicePatternIntroShouldBeFound(String filter) throws Exception {
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devicePatternIntro.getId().intValue())))
            .andExpect(jsonPath("$.[*].devicepatternId").value(hasItem(DEFAULT_DEVICEPATTERN_ID)))
            .andExpect(jsonPath("$.[*].devicepatternCode").value(hasItem(DEFAULT_DEVICEPATTERN_CODE)))
            .andExpect(jsonPath("$.[*].memo").value(hasItem(DEFAULT_MEMO)))
            .andExpect(jsonPath("$.[*].creuser").value(hasItem(DEFAULT_CREUSER)))
            .andExpect(jsonPath("$.[*].cretime").value(hasItem(sameInstant(DEFAULT_CRETIME))))
            .andExpect(jsonPath("$.[*].moduser").value(hasItem(DEFAULT_MODUSER)))
            .andExpect(jsonPath("$.[*].modtime").value(hasItem(sameInstant(DEFAULT_MODTIME))));

        // Check, that the count call also returns 1
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDevicePatternIntroShouldNotBeFound(String filter) throws Exception {
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDevicePatternIntro() throws Exception {
        // Get the devicePatternIntro
        restDevicePatternIntroMockMvc.perform(get("/api/device-pattern-intros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevicePatternIntro() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        int databaseSizeBeforeUpdate = devicePatternIntroRepository.findAll().size();

        // Update the devicePatternIntro
        DevicePatternIntro updatedDevicePatternIntro = devicePatternIntroRepository.findById(devicePatternIntro.getId()).get();
        // Disconnect from session so that the updates on updatedDevicePatternIntro are not directly saved in db
        em.detach(updatedDevicePatternIntro);
        updatedDevicePatternIntro
            .devicepatternId(UPDATED_DEVICEPATTERN_ID)
            .devicepatternCode(UPDATED_DEVICEPATTERN_CODE)
            .memo(UPDATED_MEMO)
            .creuser(UPDATED_CREUSER)
            .cretime(UPDATED_CRETIME)
            .moduser(UPDATED_MODUSER)
            .modtime(UPDATED_MODTIME);
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(updatedDevicePatternIntro);

        restDevicePatternIntroMockMvc.perform(put("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isOk());

        // Validate the DevicePatternIntro in the database
        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeUpdate);
        DevicePatternIntro testDevicePatternIntro = devicePatternIntroList.get(devicePatternIntroList.size() - 1);
        assertThat(testDevicePatternIntro.getDevicepatternId()).isEqualTo(UPDATED_DEVICEPATTERN_ID);
        assertThat(testDevicePatternIntro.getDevicepatternCode()).isEqualTo(UPDATED_DEVICEPATTERN_CODE);
        assertThat(testDevicePatternIntro.getMemo()).isEqualTo(UPDATED_MEMO);
        assertThat(testDevicePatternIntro.getCreuser()).isEqualTo(UPDATED_CREUSER);
        assertThat(testDevicePatternIntro.getCretime()).isEqualTo(UPDATED_CRETIME);
        assertThat(testDevicePatternIntro.getModuser()).isEqualTo(UPDATED_MODUSER);
        assertThat(testDevicePatternIntro.getModtime()).isEqualTo(UPDATED_MODTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingDevicePatternIntro() throws Exception {
        int databaseSizeBeforeUpdate = devicePatternIntroRepository.findAll().size();

        // Create the DevicePatternIntro
        DevicePatternIntroDTO devicePatternIntroDTO = devicePatternIntroMapper.toDto(devicePatternIntro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDevicePatternIntroMockMvc.perform(put("/api/device-pattern-intros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(devicePatternIntroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DevicePatternIntro in the database
        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDevicePatternIntro() throws Exception {
        // Initialize the database
        devicePatternIntroRepository.saveAndFlush(devicePatternIntro);

        int databaseSizeBeforeDelete = devicePatternIntroRepository.findAll().size();

        // Delete the devicePatternIntro
        restDevicePatternIntroMockMvc.perform(delete("/api/device-pattern-intros/{id}", devicePatternIntro.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DevicePatternIntro> devicePatternIntroList = devicePatternIntroRepository.findAll();
        assertThat(devicePatternIntroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
