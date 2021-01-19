package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.EventStatcode;
import aiot.java.repository.EventStatcodeRepository;
import aiot.java.service.EventStatcodeService;
import aiot.java.service.dto.EventStatcodeDTO;
import aiot.java.service.mapper.EventStatcodeMapper;
import aiot.java.service.dto.EventStatcodeCriteria;
import aiot.java.service.EventStatcodeQueryService;

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
 * Integration tests for the {@link EventStatcodeResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EventStatcodeResourceIT {

    private static final String DEFAULT_ES_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ES_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ES_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ES_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ES_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ES_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_ES_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_ES_CREID = "AAAAAAAAAA";
    private static final String UPDATED_ES_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ES_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ES_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_ES_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_ES_MODID = "AAAAAAAAAA";
    private static final String UPDATED_ES_MODID = "BBBBBBBBBB";

    @Autowired
    private EventStatcodeRepository eventStatcodeRepository;

    @Autowired
    private EventStatcodeMapper eventStatcodeMapper;

    @Autowired
    private EventStatcodeService eventStatcodeService;

    @Autowired
    private EventStatcodeQueryService eventStatcodeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventStatcodeMockMvc;

    private EventStatcode eventStatcode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventStatcode createEntity(EntityManager em) {
        EventStatcode eventStatcode = new EventStatcode()
            .esCode(DEFAULT_ES_CODE)
            .esName(DEFAULT_ES_NAME)
            .esCretime(DEFAULT_ES_CRETIME)
            .esCreid(DEFAULT_ES_CREID)
            .esModtime(DEFAULT_ES_MODTIME)
            .esModid(DEFAULT_ES_MODID);
        return eventStatcode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventStatcode createUpdatedEntity(EntityManager em) {
        EventStatcode eventStatcode = new EventStatcode()
            .esCode(UPDATED_ES_CODE)
            .esName(UPDATED_ES_NAME)
            .esCretime(UPDATED_ES_CRETIME)
            .esCreid(UPDATED_ES_CREID)
            .esModtime(UPDATED_ES_MODTIME)
            .esModid(UPDATED_ES_MODID);
        return eventStatcode;
    }

    @BeforeEach
    public void initTest() {
        eventStatcode = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventStatcode() throws Exception {
        int databaseSizeBeforeCreate = eventStatcodeRepository.findAll().size();

        // Create the EventStatcode
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);
        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isCreated());

        // Validate the EventStatcode in the database
        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeCreate + 1);
        EventStatcode testEventStatcode = eventStatcodeList.get(eventStatcodeList.size() - 1);
        assertThat(testEventStatcode.getEsCode()).isEqualTo(DEFAULT_ES_CODE);
        assertThat(testEventStatcode.getEsName()).isEqualTo(DEFAULT_ES_NAME);
        assertThat(testEventStatcode.getEsCretime()).isEqualTo(DEFAULT_ES_CRETIME);
        assertThat(testEventStatcode.getEsCreid()).isEqualTo(DEFAULT_ES_CREID);
        assertThat(testEventStatcode.getEsModtime()).isEqualTo(DEFAULT_ES_MODTIME);
        assertThat(testEventStatcode.getEsModid()).isEqualTo(DEFAULT_ES_MODID);
    }

    @Test
    @Transactional
    public void createEventStatcodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventStatcodeRepository.findAll().size();

        // Create the EventStatcode with an existing ID
        eventStatcode.setId(1L);
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventStatcode in the database
        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEsCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsCode(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsName(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsCretime(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsCreid(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsModtime(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventStatcodeRepository.findAll().size();
        // set the field null
        eventStatcode.setEsModid(null);

        // Create the EventStatcode, which fails.
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        restEventStatcodeMockMvc.perform(post("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventStatcodes() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventStatcode.getId().intValue())))
            .andExpect(jsonPath("$.[*].esCode").value(hasItem(DEFAULT_ES_CODE)))
            .andExpect(jsonPath("$.[*].esName").value(hasItem(DEFAULT_ES_NAME)))
            .andExpect(jsonPath("$.[*].esCretime").value(hasItem(sameInstant(DEFAULT_ES_CRETIME))))
            .andExpect(jsonPath("$.[*].esCreid").value(hasItem(DEFAULT_ES_CREID)))
            .andExpect(jsonPath("$.[*].esModtime").value(hasItem(sameInstant(DEFAULT_ES_MODTIME))))
            .andExpect(jsonPath("$.[*].esModid").value(hasItem(DEFAULT_ES_MODID)));
    }
    
    @Test
    @Transactional
    public void getEventStatcode() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get the eventStatcode
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes/{id}", eventStatcode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventStatcode.getId().intValue()))
            .andExpect(jsonPath("$.esCode").value(DEFAULT_ES_CODE))
            .andExpect(jsonPath("$.esName").value(DEFAULT_ES_NAME))
            .andExpect(jsonPath("$.esCretime").value(sameInstant(DEFAULT_ES_CRETIME)))
            .andExpect(jsonPath("$.esCreid").value(DEFAULT_ES_CREID))
            .andExpect(jsonPath("$.esModtime").value(sameInstant(DEFAULT_ES_MODTIME)))
            .andExpect(jsonPath("$.esModid").value(DEFAULT_ES_MODID));
    }


    @Test
    @Transactional
    public void getEventStatcodesByIdFiltering() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        Long id = eventStatcode.getId();

        defaultEventStatcodeShouldBeFound("id.equals=" + id);
        defaultEventStatcodeShouldNotBeFound("id.notEquals=" + id);

        defaultEventStatcodeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEventStatcodeShouldNotBeFound("id.greaterThan=" + id);

        defaultEventStatcodeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEventStatcodeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode equals to DEFAULT_ES_CODE
        defaultEventStatcodeShouldBeFound("esCode.equals=" + DEFAULT_ES_CODE);

        // Get all the eventStatcodeList where esCode equals to UPDATED_ES_CODE
        defaultEventStatcodeShouldNotBeFound("esCode.equals=" + UPDATED_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode not equals to DEFAULT_ES_CODE
        defaultEventStatcodeShouldNotBeFound("esCode.notEquals=" + DEFAULT_ES_CODE);

        // Get all the eventStatcodeList where esCode not equals to UPDATED_ES_CODE
        defaultEventStatcodeShouldBeFound("esCode.notEquals=" + UPDATED_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode in DEFAULT_ES_CODE or UPDATED_ES_CODE
        defaultEventStatcodeShouldBeFound("esCode.in=" + DEFAULT_ES_CODE + "," + UPDATED_ES_CODE);

        // Get all the eventStatcodeList where esCode equals to UPDATED_ES_CODE
        defaultEventStatcodeShouldNotBeFound("esCode.in=" + UPDATED_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode is not null
        defaultEventStatcodeShouldBeFound("esCode.specified=true");

        // Get all the eventStatcodeList where esCode is null
        defaultEventStatcodeShouldNotBeFound("esCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode contains DEFAULT_ES_CODE
        defaultEventStatcodeShouldBeFound("esCode.contains=" + DEFAULT_ES_CODE);

        // Get all the eventStatcodeList where esCode contains UPDATED_ES_CODE
        defaultEventStatcodeShouldNotBeFound("esCode.contains=" + UPDATED_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCodeNotContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCode does not contain DEFAULT_ES_CODE
        defaultEventStatcodeShouldNotBeFound("esCode.doesNotContain=" + DEFAULT_ES_CODE);

        // Get all the eventStatcodeList where esCode does not contain UPDATED_ES_CODE
        defaultEventStatcodeShouldBeFound("esCode.doesNotContain=" + UPDATED_ES_CODE);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsNameIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName equals to DEFAULT_ES_NAME
        defaultEventStatcodeShouldBeFound("esName.equals=" + DEFAULT_ES_NAME);

        // Get all the eventStatcodeList where esName equals to UPDATED_ES_NAME
        defaultEventStatcodeShouldNotBeFound("esName.equals=" + UPDATED_ES_NAME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName not equals to DEFAULT_ES_NAME
        defaultEventStatcodeShouldNotBeFound("esName.notEquals=" + DEFAULT_ES_NAME);

        // Get all the eventStatcodeList where esName not equals to UPDATED_ES_NAME
        defaultEventStatcodeShouldBeFound("esName.notEquals=" + UPDATED_ES_NAME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsNameIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName in DEFAULT_ES_NAME or UPDATED_ES_NAME
        defaultEventStatcodeShouldBeFound("esName.in=" + DEFAULT_ES_NAME + "," + UPDATED_ES_NAME);

        // Get all the eventStatcodeList where esName equals to UPDATED_ES_NAME
        defaultEventStatcodeShouldNotBeFound("esName.in=" + UPDATED_ES_NAME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName is not null
        defaultEventStatcodeShouldBeFound("esName.specified=true");

        // Get all the eventStatcodeList where esName is null
        defaultEventStatcodeShouldNotBeFound("esName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventStatcodesByEsNameContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName contains DEFAULT_ES_NAME
        defaultEventStatcodeShouldBeFound("esName.contains=" + DEFAULT_ES_NAME);

        // Get all the eventStatcodeList where esName contains UPDATED_ES_NAME
        defaultEventStatcodeShouldNotBeFound("esName.contains=" + UPDATED_ES_NAME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsNameNotContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esName does not contain DEFAULT_ES_NAME
        defaultEventStatcodeShouldNotBeFound("esName.doesNotContain=" + DEFAULT_ES_NAME);

        // Get all the eventStatcodeList where esName does not contain UPDATED_ES_NAME
        defaultEventStatcodeShouldBeFound("esName.doesNotContain=" + UPDATED_ES_NAME);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime equals to DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.equals=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime equals to UPDATED_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.equals=" + UPDATED_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime not equals to DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.notEquals=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime not equals to UPDATED_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.notEquals=" + UPDATED_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime in DEFAULT_ES_CRETIME or UPDATED_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.in=" + DEFAULT_ES_CRETIME + "," + UPDATED_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime equals to UPDATED_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.in=" + UPDATED_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime is not null
        defaultEventStatcodeShouldBeFound("esCretime.specified=true");

        // Get all the eventStatcodeList where esCretime is null
        defaultEventStatcodeShouldNotBeFound("esCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime is greater than or equal to DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.greaterThanOrEqual=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime is greater than or equal to UPDATED_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.greaterThanOrEqual=" + UPDATED_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime is less than or equal to DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.lessThanOrEqual=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime is less than or equal to SMALLER_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.lessThanOrEqual=" + SMALLER_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime is less than DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.lessThan=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime is less than UPDATED_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.lessThan=" + UPDATED_ES_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCretime is greater than DEFAULT_ES_CRETIME
        defaultEventStatcodeShouldNotBeFound("esCretime.greaterThan=" + DEFAULT_ES_CRETIME);

        // Get all the eventStatcodeList where esCretime is greater than SMALLER_ES_CRETIME
        defaultEventStatcodeShouldBeFound("esCretime.greaterThan=" + SMALLER_ES_CRETIME);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid equals to DEFAULT_ES_CREID
        defaultEventStatcodeShouldBeFound("esCreid.equals=" + DEFAULT_ES_CREID);

        // Get all the eventStatcodeList where esCreid equals to UPDATED_ES_CREID
        defaultEventStatcodeShouldNotBeFound("esCreid.equals=" + UPDATED_ES_CREID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid not equals to DEFAULT_ES_CREID
        defaultEventStatcodeShouldNotBeFound("esCreid.notEquals=" + DEFAULT_ES_CREID);

        // Get all the eventStatcodeList where esCreid not equals to UPDATED_ES_CREID
        defaultEventStatcodeShouldBeFound("esCreid.notEquals=" + UPDATED_ES_CREID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid in DEFAULT_ES_CREID or UPDATED_ES_CREID
        defaultEventStatcodeShouldBeFound("esCreid.in=" + DEFAULT_ES_CREID + "," + UPDATED_ES_CREID);

        // Get all the eventStatcodeList where esCreid equals to UPDATED_ES_CREID
        defaultEventStatcodeShouldNotBeFound("esCreid.in=" + UPDATED_ES_CREID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid is not null
        defaultEventStatcodeShouldBeFound("esCreid.specified=true");

        // Get all the eventStatcodeList where esCreid is null
        defaultEventStatcodeShouldNotBeFound("esCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid contains DEFAULT_ES_CREID
        defaultEventStatcodeShouldBeFound("esCreid.contains=" + DEFAULT_ES_CREID);

        // Get all the eventStatcodeList where esCreid contains UPDATED_ES_CREID
        defaultEventStatcodeShouldNotBeFound("esCreid.contains=" + UPDATED_ES_CREID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsCreidNotContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esCreid does not contain DEFAULT_ES_CREID
        defaultEventStatcodeShouldNotBeFound("esCreid.doesNotContain=" + DEFAULT_ES_CREID);

        // Get all the eventStatcodeList where esCreid does not contain UPDATED_ES_CREID
        defaultEventStatcodeShouldBeFound("esCreid.doesNotContain=" + UPDATED_ES_CREID);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime equals to DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.equals=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime equals to UPDATED_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.equals=" + UPDATED_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime not equals to DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.notEquals=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime not equals to UPDATED_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.notEquals=" + UPDATED_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime in DEFAULT_ES_MODTIME or UPDATED_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.in=" + DEFAULT_ES_MODTIME + "," + UPDATED_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime equals to UPDATED_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.in=" + UPDATED_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime is not null
        defaultEventStatcodeShouldBeFound("esModtime.specified=true");

        // Get all the eventStatcodeList where esModtime is null
        defaultEventStatcodeShouldNotBeFound("esModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime is greater than or equal to DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.greaterThanOrEqual=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime is greater than or equal to UPDATED_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.greaterThanOrEqual=" + UPDATED_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime is less than or equal to DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.lessThanOrEqual=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime is less than or equal to SMALLER_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.lessThanOrEqual=" + SMALLER_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime is less than DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.lessThan=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime is less than UPDATED_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.lessThan=" + UPDATED_ES_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModtime is greater than DEFAULT_ES_MODTIME
        defaultEventStatcodeShouldNotBeFound("esModtime.greaterThan=" + DEFAULT_ES_MODTIME);

        // Get all the eventStatcodeList where esModtime is greater than SMALLER_ES_MODTIME
        defaultEventStatcodeShouldBeFound("esModtime.greaterThan=" + SMALLER_ES_MODTIME);
    }


    @Test
    @Transactional
    public void getAllEventStatcodesByEsModidIsEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid equals to DEFAULT_ES_MODID
        defaultEventStatcodeShouldBeFound("esModid.equals=" + DEFAULT_ES_MODID);

        // Get all the eventStatcodeList where esModid equals to UPDATED_ES_MODID
        defaultEventStatcodeShouldNotBeFound("esModid.equals=" + UPDATED_ES_MODID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid not equals to DEFAULT_ES_MODID
        defaultEventStatcodeShouldNotBeFound("esModid.notEquals=" + DEFAULT_ES_MODID);

        // Get all the eventStatcodeList where esModid not equals to UPDATED_ES_MODID
        defaultEventStatcodeShouldBeFound("esModid.notEquals=" + UPDATED_ES_MODID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModidIsInShouldWork() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid in DEFAULT_ES_MODID or UPDATED_ES_MODID
        defaultEventStatcodeShouldBeFound("esModid.in=" + DEFAULT_ES_MODID + "," + UPDATED_ES_MODID);

        // Get all the eventStatcodeList where esModid equals to UPDATED_ES_MODID
        defaultEventStatcodeShouldNotBeFound("esModid.in=" + UPDATED_ES_MODID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid is not null
        defaultEventStatcodeShouldBeFound("esModid.specified=true");

        // Get all the eventStatcodeList where esModid is null
        defaultEventStatcodeShouldNotBeFound("esModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventStatcodesByEsModidContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid contains DEFAULT_ES_MODID
        defaultEventStatcodeShouldBeFound("esModid.contains=" + DEFAULT_ES_MODID);

        // Get all the eventStatcodeList where esModid contains UPDATED_ES_MODID
        defaultEventStatcodeShouldNotBeFound("esModid.contains=" + UPDATED_ES_MODID);
    }

    @Test
    @Transactional
    public void getAllEventStatcodesByEsModidNotContainsSomething() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        // Get all the eventStatcodeList where esModid does not contain DEFAULT_ES_MODID
        defaultEventStatcodeShouldNotBeFound("esModid.doesNotContain=" + DEFAULT_ES_MODID);

        // Get all the eventStatcodeList where esModid does not contain UPDATED_ES_MODID
        defaultEventStatcodeShouldBeFound("esModid.doesNotContain=" + UPDATED_ES_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEventStatcodeShouldBeFound(String filter) throws Exception {
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventStatcode.getId().intValue())))
            .andExpect(jsonPath("$.[*].esCode").value(hasItem(DEFAULT_ES_CODE)))
            .andExpect(jsonPath("$.[*].esName").value(hasItem(DEFAULT_ES_NAME)))
            .andExpect(jsonPath("$.[*].esCretime").value(hasItem(sameInstant(DEFAULT_ES_CRETIME))))
            .andExpect(jsonPath("$.[*].esCreid").value(hasItem(DEFAULT_ES_CREID)))
            .andExpect(jsonPath("$.[*].esModtime").value(hasItem(sameInstant(DEFAULT_ES_MODTIME))))
            .andExpect(jsonPath("$.[*].esModid").value(hasItem(DEFAULT_ES_MODID)));

        // Check, that the count call also returns 1
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEventStatcodeShouldNotBeFound(String filter) throws Exception {
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEventStatcode() throws Exception {
        // Get the eventStatcode
        restEventStatcodeMockMvc.perform(get("/api/event-statcodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventStatcode() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        int databaseSizeBeforeUpdate = eventStatcodeRepository.findAll().size();

        // Update the eventStatcode
        EventStatcode updatedEventStatcode = eventStatcodeRepository.findById(eventStatcode.getId()).get();
        // Disconnect from session so that the updates on updatedEventStatcode are not directly saved in db
        em.detach(updatedEventStatcode);
        updatedEventStatcode
            .esCode(UPDATED_ES_CODE)
            .esName(UPDATED_ES_NAME)
            .esCretime(UPDATED_ES_CRETIME)
            .esCreid(UPDATED_ES_CREID)
            .esModtime(UPDATED_ES_MODTIME)
            .esModid(UPDATED_ES_MODID);
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(updatedEventStatcode);

        restEventStatcodeMockMvc.perform(put("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isOk());

        // Validate the EventStatcode in the database
        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeUpdate);
        EventStatcode testEventStatcode = eventStatcodeList.get(eventStatcodeList.size() - 1);
        assertThat(testEventStatcode.getEsCode()).isEqualTo(UPDATED_ES_CODE);
        assertThat(testEventStatcode.getEsName()).isEqualTo(UPDATED_ES_NAME);
        assertThat(testEventStatcode.getEsCretime()).isEqualTo(UPDATED_ES_CRETIME);
        assertThat(testEventStatcode.getEsCreid()).isEqualTo(UPDATED_ES_CREID);
        assertThat(testEventStatcode.getEsModtime()).isEqualTo(UPDATED_ES_MODTIME);
        assertThat(testEventStatcode.getEsModid()).isEqualTo(UPDATED_ES_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingEventStatcode() throws Exception {
        int databaseSizeBeforeUpdate = eventStatcodeRepository.findAll().size();

        // Create the EventStatcode
        EventStatcodeDTO eventStatcodeDTO = eventStatcodeMapper.toDto(eventStatcode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventStatcodeMockMvc.perform(put("/api/event-statcodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventStatcodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventStatcode in the database
        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventStatcode() throws Exception {
        // Initialize the database
        eventStatcodeRepository.saveAndFlush(eventStatcode);

        int databaseSizeBeforeDelete = eventStatcodeRepository.findAll().size();

        // Delete the eventStatcode
        restEventStatcodeMockMvc.perform(delete("/api/event-statcodes/{id}", eventStatcode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventStatcode> eventStatcodeList = eventStatcodeRepository.findAll();
        assertThat(eventStatcodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
