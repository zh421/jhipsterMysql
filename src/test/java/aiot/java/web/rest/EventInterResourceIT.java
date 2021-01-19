package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.EventInter;
import aiot.java.repository.EventInterRepository;
import aiot.java.service.EventInterService;
import aiot.java.service.dto.EventInterDTO;
import aiot.java.service.mapper.EventInterMapper;
import aiot.java.service.dto.EventInterCriteria;
import aiot.java.service.EventInterQueryService;

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
 * Integration tests for the {@link EventInterResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EventInterResourceIT {

    private static final ZonedDateTime DEFAULT_EVNIN_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EVNIN_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_EVNIN_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_EVNIN_ES_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_ES_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_DEVICEID = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_DEVICEID = "BBBBBBBBBB";

    private static final Integer DEFAULT_EVNIN_DVI_MOD_NUM = 1;
    private static final Integer UPDATED_EVNIN_DVI_MOD_NUM = 2;
    private static final Integer SMALLER_EVNIN_DVI_MOD_NUM = 1 - 1;

    private static final String DEFAULT_EVNIN_DVI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_DVI_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_UNIT_UC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_UNIT_UC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_UNIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_UNIT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_UNIT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_UNIT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_UNIT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_UNIT_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_THEME = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_THEME = "BBBBBBBBBB";

    private static final String DEFAULT_EVNIN_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_MEMO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EVNIN_ISRES = false;
    private static final Boolean UPDATED_EVNIN_ISRES = true;

    private static final String DEFAULT_EVNIN_RES_MEMO = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_RES_MEMO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EVNIN_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EVNIN_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_EVNIN_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_EVNIN_CREID = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_EVNIN_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EVNIN_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_EVNIN_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_EVNIN_MODID = "AAAAAAAAAA";
    private static final String UPDATED_EVNIN_MODID = "BBBBBBBBBB";

    @Autowired
    private EventInterRepository eventInterRepository;

    @Autowired
    private EventInterMapper eventInterMapper;

    @Autowired
    private EventInterService eventInterService;

    @Autowired
    private EventInterQueryService eventInterQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventInterMockMvc;

    private EventInter eventInter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventInter createEntity(EntityManager em) {
        EventInter eventInter = new EventInter()
            .evninTime(DEFAULT_EVNIN_TIME)
            .evninEsCode(DEFAULT_EVNIN_ES_CODE)
            .evninDeviceid(DEFAULT_EVNIN_DEVICEID)
            .evninDviModNum(DEFAULT_EVNIN_DVI_MOD_NUM)
            .evninDviCode(DEFAULT_EVNIN_DVI_CODE)
            .evninUnitUcCode(DEFAULT_EVNIN_UNIT_UC_CODE)
            .evninUnitCode(DEFAULT_EVNIN_UNIT_CODE)
            .evninUnitName(DEFAULT_EVNIN_UNIT_NAME)
            .evninUnitAddr(DEFAULT_EVNIN_UNIT_ADDR)
            .evninTheme(DEFAULT_EVNIN_THEME)
            .evninMemo(DEFAULT_EVNIN_MEMO)
            .evninIsres(DEFAULT_EVNIN_ISRES)
            .evninResMemo(DEFAULT_EVNIN_RES_MEMO)
            .evninCretime(DEFAULT_EVNIN_CRETIME)
            .evninCreid(DEFAULT_EVNIN_CREID)
            .evninModtime(DEFAULT_EVNIN_MODTIME)
            .evninModid(DEFAULT_EVNIN_MODID);
        return eventInter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventInter createUpdatedEntity(EntityManager em) {
        EventInter eventInter = new EventInter()
            .evninTime(UPDATED_EVNIN_TIME)
            .evninEsCode(UPDATED_EVNIN_ES_CODE)
            .evninDeviceid(UPDATED_EVNIN_DEVICEID)
            .evninDviModNum(UPDATED_EVNIN_DVI_MOD_NUM)
            .evninDviCode(UPDATED_EVNIN_DVI_CODE)
            .evninUnitUcCode(UPDATED_EVNIN_UNIT_UC_CODE)
            .evninUnitCode(UPDATED_EVNIN_UNIT_CODE)
            .evninUnitName(UPDATED_EVNIN_UNIT_NAME)
            .evninUnitAddr(UPDATED_EVNIN_UNIT_ADDR)
            .evninTheme(UPDATED_EVNIN_THEME)
            .evninMemo(UPDATED_EVNIN_MEMO)
            .evninIsres(UPDATED_EVNIN_ISRES)
            .evninResMemo(UPDATED_EVNIN_RES_MEMO)
            .evninCretime(UPDATED_EVNIN_CRETIME)
            .evninCreid(UPDATED_EVNIN_CREID)
            .evninModtime(UPDATED_EVNIN_MODTIME)
            .evninModid(UPDATED_EVNIN_MODID);
        return eventInter;
    }

    @BeforeEach
    public void initTest() {
        eventInter = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventInter() throws Exception {
        int databaseSizeBeforeCreate = eventInterRepository.findAll().size();

        // Create the EventInter
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);
        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isCreated());

        // Validate the EventInter in the database
        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeCreate + 1);
        EventInter testEventInter = eventInterList.get(eventInterList.size() - 1);
        assertThat(testEventInter.getEvninTime()).isEqualTo(DEFAULT_EVNIN_TIME);
        assertThat(testEventInter.getEvninEsCode()).isEqualTo(DEFAULT_EVNIN_ES_CODE);
        assertThat(testEventInter.getEvninDeviceid()).isEqualTo(DEFAULT_EVNIN_DEVICEID);
        assertThat(testEventInter.getEvninDviModNum()).isEqualTo(DEFAULT_EVNIN_DVI_MOD_NUM);
        assertThat(testEventInter.getEvninDviCode()).isEqualTo(DEFAULT_EVNIN_DVI_CODE);
        assertThat(testEventInter.getEvninUnitUcCode()).isEqualTo(DEFAULT_EVNIN_UNIT_UC_CODE);
        assertThat(testEventInter.getEvninUnitCode()).isEqualTo(DEFAULT_EVNIN_UNIT_CODE);
        assertThat(testEventInter.getEvninUnitName()).isEqualTo(DEFAULT_EVNIN_UNIT_NAME);
        assertThat(testEventInter.getEvninUnitAddr()).isEqualTo(DEFAULT_EVNIN_UNIT_ADDR);
        assertThat(testEventInter.getEvninTheme()).isEqualTo(DEFAULT_EVNIN_THEME);
        assertThat(testEventInter.getEvninMemo()).isEqualTo(DEFAULT_EVNIN_MEMO);
        assertThat(testEventInter.isEvninIsres()).isEqualTo(DEFAULT_EVNIN_ISRES);
        assertThat(testEventInter.getEvninResMemo()).isEqualTo(DEFAULT_EVNIN_RES_MEMO);
        assertThat(testEventInter.getEvninCretime()).isEqualTo(DEFAULT_EVNIN_CRETIME);
        assertThat(testEventInter.getEvninCreid()).isEqualTo(DEFAULT_EVNIN_CREID);
        assertThat(testEventInter.getEvninModtime()).isEqualTo(DEFAULT_EVNIN_MODTIME);
        assertThat(testEventInter.getEvninModid()).isEqualTo(DEFAULT_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void createEventInterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventInterRepository.findAll().size();

        // Create the EventInter with an existing ID
        eventInter.setId(1L);
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventInter in the database
        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEvninTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninTime(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninEsCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninEsCode(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninDeviceidIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninDeviceid(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninDviModNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninDviModNum(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninDviCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninDviCode(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninUnitUcCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninUnitUcCode(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninUnitCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninUnitCode(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninUnitNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninUnitName(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninUnitAddrIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninUnitAddr(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninThemeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninTheme(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninCretime(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninCreid(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninModtime(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEvninModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventInterRepository.findAll().size();
        // set the field null
        eventInter.setEvninModid(null);

        // Create the EventInter, which fails.
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        restEventInterMockMvc.perform(post("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventInters() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList
        restEventInterMockMvc.perform(get("/api/event-inters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventInter.getId().intValue())))
            .andExpect(jsonPath("$.[*].evninTime").value(hasItem(sameInstant(DEFAULT_EVNIN_TIME))))
            .andExpect(jsonPath("$.[*].evninEsCode").value(hasItem(DEFAULT_EVNIN_ES_CODE)))
            .andExpect(jsonPath("$.[*].evninDeviceid").value(hasItem(DEFAULT_EVNIN_DEVICEID)))
            .andExpect(jsonPath("$.[*].evninDviModNum").value(hasItem(DEFAULT_EVNIN_DVI_MOD_NUM)))
            .andExpect(jsonPath("$.[*].evninDviCode").value(hasItem(DEFAULT_EVNIN_DVI_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitUcCode").value(hasItem(DEFAULT_EVNIN_UNIT_UC_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitCode").value(hasItem(DEFAULT_EVNIN_UNIT_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitName").value(hasItem(DEFAULT_EVNIN_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].evninUnitAddr").value(hasItem(DEFAULT_EVNIN_UNIT_ADDR)))
            .andExpect(jsonPath("$.[*].evninTheme").value(hasItem(DEFAULT_EVNIN_THEME)))
            .andExpect(jsonPath("$.[*].evninMemo").value(hasItem(DEFAULT_EVNIN_MEMO)))
            .andExpect(jsonPath("$.[*].evninIsres").value(hasItem(DEFAULT_EVNIN_ISRES.booleanValue())))
            .andExpect(jsonPath("$.[*].evninResMemo").value(hasItem(DEFAULT_EVNIN_RES_MEMO)))
            .andExpect(jsonPath("$.[*].evninCretime").value(hasItem(sameInstant(DEFAULT_EVNIN_CRETIME))))
            .andExpect(jsonPath("$.[*].evninCreid").value(hasItem(DEFAULT_EVNIN_CREID)))
            .andExpect(jsonPath("$.[*].evninModtime").value(hasItem(sameInstant(DEFAULT_EVNIN_MODTIME))))
            .andExpect(jsonPath("$.[*].evninModid").value(hasItem(DEFAULT_EVNIN_MODID)));
    }
    
    @Test
    @Transactional
    public void getEventInter() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get the eventInter
        restEventInterMockMvc.perform(get("/api/event-inters/{id}", eventInter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventInter.getId().intValue()))
            .andExpect(jsonPath("$.evninTime").value(sameInstant(DEFAULT_EVNIN_TIME)))
            .andExpect(jsonPath("$.evninEsCode").value(DEFAULT_EVNIN_ES_CODE))
            .andExpect(jsonPath("$.evninDeviceid").value(DEFAULT_EVNIN_DEVICEID))
            .andExpect(jsonPath("$.evninDviModNum").value(DEFAULT_EVNIN_DVI_MOD_NUM))
            .andExpect(jsonPath("$.evninDviCode").value(DEFAULT_EVNIN_DVI_CODE))
            .andExpect(jsonPath("$.evninUnitUcCode").value(DEFAULT_EVNIN_UNIT_UC_CODE))
            .andExpect(jsonPath("$.evninUnitCode").value(DEFAULT_EVNIN_UNIT_CODE))
            .andExpect(jsonPath("$.evninUnitName").value(DEFAULT_EVNIN_UNIT_NAME))
            .andExpect(jsonPath("$.evninUnitAddr").value(DEFAULT_EVNIN_UNIT_ADDR))
            .andExpect(jsonPath("$.evninTheme").value(DEFAULT_EVNIN_THEME))
            .andExpect(jsonPath("$.evninMemo").value(DEFAULT_EVNIN_MEMO))
            .andExpect(jsonPath("$.evninIsres").value(DEFAULT_EVNIN_ISRES.booleanValue()))
            .andExpect(jsonPath("$.evninResMemo").value(DEFAULT_EVNIN_RES_MEMO))
            .andExpect(jsonPath("$.evninCretime").value(sameInstant(DEFAULT_EVNIN_CRETIME)))
            .andExpect(jsonPath("$.evninCreid").value(DEFAULT_EVNIN_CREID))
            .andExpect(jsonPath("$.evninModtime").value(sameInstant(DEFAULT_EVNIN_MODTIME)))
            .andExpect(jsonPath("$.evninModid").value(DEFAULT_EVNIN_MODID));
    }


    @Test
    @Transactional
    public void getEventIntersByIdFiltering() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        Long id = eventInter.getId();

        defaultEventInterShouldBeFound("id.equals=" + id);
        defaultEventInterShouldNotBeFound("id.notEquals=" + id);

        defaultEventInterShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEventInterShouldNotBeFound("id.greaterThan=" + id);

        defaultEventInterShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEventInterShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime equals to DEFAULT_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.equals=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime equals to UPDATED_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.equals=" + UPDATED_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime not equals to DEFAULT_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.notEquals=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime not equals to UPDATED_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.notEquals=" + UPDATED_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime in DEFAULT_EVNIN_TIME or UPDATED_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.in=" + DEFAULT_EVNIN_TIME + "," + UPDATED_EVNIN_TIME);

        // Get all the eventInterList where evninTime equals to UPDATED_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.in=" + UPDATED_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime is not null
        defaultEventInterShouldBeFound("evninTime.specified=true");

        // Get all the eventInterList where evninTime is null
        defaultEventInterShouldNotBeFound("evninTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime is greater than or equal to DEFAULT_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.greaterThanOrEqual=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime is greater than or equal to UPDATED_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.greaterThanOrEqual=" + UPDATED_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime is less than or equal to DEFAULT_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.lessThanOrEqual=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime is less than or equal to SMALLER_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.lessThanOrEqual=" + SMALLER_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime is less than DEFAULT_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.lessThan=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime is less than UPDATED_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.lessThan=" + UPDATED_EVNIN_TIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninTimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTime is greater than DEFAULT_EVNIN_TIME
        defaultEventInterShouldNotBeFound("evninTime.greaterThan=" + DEFAULT_EVNIN_TIME);

        // Get all the eventInterList where evninTime is greater than SMALLER_EVNIN_TIME
        defaultEventInterShouldBeFound("evninTime.greaterThan=" + SMALLER_EVNIN_TIME);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode equals to DEFAULT_EVNIN_ES_CODE
        defaultEventInterShouldBeFound("evninEsCode.equals=" + DEFAULT_EVNIN_ES_CODE);

        // Get all the eventInterList where evninEsCode equals to UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldNotBeFound("evninEsCode.equals=" + UPDATED_EVNIN_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode not equals to DEFAULT_EVNIN_ES_CODE
        defaultEventInterShouldNotBeFound("evninEsCode.notEquals=" + DEFAULT_EVNIN_ES_CODE);

        // Get all the eventInterList where evninEsCode not equals to UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldBeFound("evninEsCode.notEquals=" + UPDATED_EVNIN_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode in DEFAULT_EVNIN_ES_CODE or UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldBeFound("evninEsCode.in=" + DEFAULT_EVNIN_ES_CODE + "," + UPDATED_EVNIN_ES_CODE);

        // Get all the eventInterList where evninEsCode equals to UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldNotBeFound("evninEsCode.in=" + UPDATED_EVNIN_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode is not null
        defaultEventInterShouldBeFound("evninEsCode.specified=true");

        // Get all the eventInterList where evninEsCode is null
        defaultEventInterShouldNotBeFound("evninEsCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode contains DEFAULT_EVNIN_ES_CODE
        defaultEventInterShouldBeFound("evninEsCode.contains=" + DEFAULT_EVNIN_ES_CODE);

        // Get all the eventInterList where evninEsCode contains UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldNotBeFound("evninEsCode.contains=" + UPDATED_EVNIN_ES_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninEsCodeNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninEsCode does not contain DEFAULT_EVNIN_ES_CODE
        defaultEventInterShouldNotBeFound("evninEsCode.doesNotContain=" + DEFAULT_EVNIN_ES_CODE);

        // Get all the eventInterList where evninEsCode does not contain UPDATED_EVNIN_ES_CODE
        defaultEventInterShouldBeFound("evninEsCode.doesNotContain=" + UPDATED_EVNIN_ES_CODE);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid equals to DEFAULT_EVNIN_DEVICEID
        defaultEventInterShouldBeFound("evninDeviceid.equals=" + DEFAULT_EVNIN_DEVICEID);

        // Get all the eventInterList where evninDeviceid equals to UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldNotBeFound("evninDeviceid.equals=" + UPDATED_EVNIN_DEVICEID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid not equals to DEFAULT_EVNIN_DEVICEID
        defaultEventInterShouldNotBeFound("evninDeviceid.notEquals=" + DEFAULT_EVNIN_DEVICEID);

        // Get all the eventInterList where evninDeviceid not equals to UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldBeFound("evninDeviceid.notEquals=" + UPDATED_EVNIN_DEVICEID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid in DEFAULT_EVNIN_DEVICEID or UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldBeFound("evninDeviceid.in=" + DEFAULT_EVNIN_DEVICEID + "," + UPDATED_EVNIN_DEVICEID);

        // Get all the eventInterList where evninDeviceid equals to UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldNotBeFound("evninDeviceid.in=" + UPDATED_EVNIN_DEVICEID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid is not null
        defaultEventInterShouldBeFound("evninDeviceid.specified=true");

        // Get all the eventInterList where evninDeviceid is null
        defaultEventInterShouldNotBeFound("evninDeviceid.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid contains DEFAULT_EVNIN_DEVICEID
        defaultEventInterShouldBeFound("evninDeviceid.contains=" + DEFAULT_EVNIN_DEVICEID);

        // Get all the eventInterList where evninDeviceid contains UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldNotBeFound("evninDeviceid.contains=" + UPDATED_EVNIN_DEVICEID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDeviceidNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDeviceid does not contain DEFAULT_EVNIN_DEVICEID
        defaultEventInterShouldNotBeFound("evninDeviceid.doesNotContain=" + DEFAULT_EVNIN_DEVICEID);

        // Get all the eventInterList where evninDeviceid does not contain UPDATED_EVNIN_DEVICEID
        defaultEventInterShouldBeFound("evninDeviceid.doesNotContain=" + UPDATED_EVNIN_DEVICEID);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum equals to DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.equals=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum equals to UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.equals=" + UPDATED_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum not equals to DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.notEquals=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum not equals to UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.notEquals=" + UPDATED_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum in DEFAULT_EVNIN_DVI_MOD_NUM or UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.in=" + DEFAULT_EVNIN_DVI_MOD_NUM + "," + UPDATED_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum equals to UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.in=" + UPDATED_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum is not null
        defaultEventInterShouldBeFound("evninDviModNum.specified=true");

        // Get all the eventInterList where evninDviModNum is null
        defaultEventInterShouldNotBeFound("evninDviModNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum is greater than or equal to DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.greaterThanOrEqual=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum is greater than or equal to UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.greaterThanOrEqual=" + UPDATED_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum is less than or equal to DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.lessThanOrEqual=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum is less than or equal to SMALLER_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.lessThanOrEqual=" + SMALLER_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsLessThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum is less than DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.lessThan=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum is less than UPDATED_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.lessThan=" + UPDATED_EVNIN_DVI_MOD_NUM);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviModNumIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviModNum is greater than DEFAULT_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldNotBeFound("evninDviModNum.greaterThan=" + DEFAULT_EVNIN_DVI_MOD_NUM);

        // Get all the eventInterList where evninDviModNum is greater than SMALLER_EVNIN_DVI_MOD_NUM
        defaultEventInterShouldBeFound("evninDviModNum.greaterThan=" + SMALLER_EVNIN_DVI_MOD_NUM);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode equals to DEFAULT_EVNIN_DVI_CODE
        defaultEventInterShouldBeFound("evninDviCode.equals=" + DEFAULT_EVNIN_DVI_CODE);

        // Get all the eventInterList where evninDviCode equals to UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldNotBeFound("evninDviCode.equals=" + UPDATED_EVNIN_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode not equals to DEFAULT_EVNIN_DVI_CODE
        defaultEventInterShouldNotBeFound("evninDviCode.notEquals=" + DEFAULT_EVNIN_DVI_CODE);

        // Get all the eventInterList where evninDviCode not equals to UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldBeFound("evninDviCode.notEquals=" + UPDATED_EVNIN_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode in DEFAULT_EVNIN_DVI_CODE or UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldBeFound("evninDviCode.in=" + DEFAULT_EVNIN_DVI_CODE + "," + UPDATED_EVNIN_DVI_CODE);

        // Get all the eventInterList where evninDviCode equals to UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldNotBeFound("evninDviCode.in=" + UPDATED_EVNIN_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode is not null
        defaultEventInterShouldBeFound("evninDviCode.specified=true");

        // Get all the eventInterList where evninDviCode is null
        defaultEventInterShouldNotBeFound("evninDviCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode contains DEFAULT_EVNIN_DVI_CODE
        defaultEventInterShouldBeFound("evninDviCode.contains=" + DEFAULT_EVNIN_DVI_CODE);

        // Get all the eventInterList where evninDviCode contains UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldNotBeFound("evninDviCode.contains=" + UPDATED_EVNIN_DVI_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninDviCodeNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninDviCode does not contain DEFAULT_EVNIN_DVI_CODE
        defaultEventInterShouldNotBeFound("evninDviCode.doesNotContain=" + DEFAULT_EVNIN_DVI_CODE);

        // Get all the eventInterList where evninDviCode does not contain UPDATED_EVNIN_DVI_CODE
        defaultEventInterShouldBeFound("evninDviCode.doesNotContain=" + UPDATED_EVNIN_DVI_CODE);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode equals to DEFAULT_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldBeFound("evninUnitUcCode.equals=" + DEFAULT_EVNIN_UNIT_UC_CODE);

        // Get all the eventInterList where evninUnitUcCode equals to UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldNotBeFound("evninUnitUcCode.equals=" + UPDATED_EVNIN_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode not equals to DEFAULT_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldNotBeFound("evninUnitUcCode.notEquals=" + DEFAULT_EVNIN_UNIT_UC_CODE);

        // Get all the eventInterList where evninUnitUcCode not equals to UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldBeFound("evninUnitUcCode.notEquals=" + UPDATED_EVNIN_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode in DEFAULT_EVNIN_UNIT_UC_CODE or UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldBeFound("evninUnitUcCode.in=" + DEFAULT_EVNIN_UNIT_UC_CODE + "," + UPDATED_EVNIN_UNIT_UC_CODE);

        // Get all the eventInterList where evninUnitUcCode equals to UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldNotBeFound("evninUnitUcCode.in=" + UPDATED_EVNIN_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode is not null
        defaultEventInterShouldBeFound("evninUnitUcCode.specified=true");

        // Get all the eventInterList where evninUnitUcCode is null
        defaultEventInterShouldNotBeFound("evninUnitUcCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode contains DEFAULT_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldBeFound("evninUnitUcCode.contains=" + DEFAULT_EVNIN_UNIT_UC_CODE);

        // Get all the eventInterList where evninUnitUcCode contains UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldNotBeFound("evninUnitUcCode.contains=" + UPDATED_EVNIN_UNIT_UC_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitUcCodeNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitUcCode does not contain DEFAULT_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldNotBeFound("evninUnitUcCode.doesNotContain=" + DEFAULT_EVNIN_UNIT_UC_CODE);

        // Get all the eventInterList where evninUnitUcCode does not contain UPDATED_EVNIN_UNIT_UC_CODE
        defaultEventInterShouldBeFound("evninUnitUcCode.doesNotContain=" + UPDATED_EVNIN_UNIT_UC_CODE);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode equals to DEFAULT_EVNIN_UNIT_CODE
        defaultEventInterShouldBeFound("evninUnitCode.equals=" + DEFAULT_EVNIN_UNIT_CODE);

        // Get all the eventInterList where evninUnitCode equals to UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldNotBeFound("evninUnitCode.equals=" + UPDATED_EVNIN_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode not equals to DEFAULT_EVNIN_UNIT_CODE
        defaultEventInterShouldNotBeFound("evninUnitCode.notEquals=" + DEFAULT_EVNIN_UNIT_CODE);

        // Get all the eventInterList where evninUnitCode not equals to UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldBeFound("evninUnitCode.notEquals=" + UPDATED_EVNIN_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode in DEFAULT_EVNIN_UNIT_CODE or UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldBeFound("evninUnitCode.in=" + DEFAULT_EVNIN_UNIT_CODE + "," + UPDATED_EVNIN_UNIT_CODE);

        // Get all the eventInterList where evninUnitCode equals to UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldNotBeFound("evninUnitCode.in=" + UPDATED_EVNIN_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode is not null
        defaultEventInterShouldBeFound("evninUnitCode.specified=true");

        // Get all the eventInterList where evninUnitCode is null
        defaultEventInterShouldNotBeFound("evninUnitCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode contains DEFAULT_EVNIN_UNIT_CODE
        defaultEventInterShouldBeFound("evninUnitCode.contains=" + DEFAULT_EVNIN_UNIT_CODE);

        // Get all the eventInterList where evninUnitCode contains UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldNotBeFound("evninUnitCode.contains=" + UPDATED_EVNIN_UNIT_CODE);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitCodeNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitCode does not contain DEFAULT_EVNIN_UNIT_CODE
        defaultEventInterShouldNotBeFound("evninUnitCode.doesNotContain=" + DEFAULT_EVNIN_UNIT_CODE);

        // Get all the eventInterList where evninUnitCode does not contain UPDATED_EVNIN_UNIT_CODE
        defaultEventInterShouldBeFound("evninUnitCode.doesNotContain=" + UPDATED_EVNIN_UNIT_CODE);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName equals to DEFAULT_EVNIN_UNIT_NAME
        defaultEventInterShouldBeFound("evninUnitName.equals=" + DEFAULT_EVNIN_UNIT_NAME);

        // Get all the eventInterList where evninUnitName equals to UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldNotBeFound("evninUnitName.equals=" + UPDATED_EVNIN_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName not equals to DEFAULT_EVNIN_UNIT_NAME
        defaultEventInterShouldNotBeFound("evninUnitName.notEquals=" + DEFAULT_EVNIN_UNIT_NAME);

        // Get all the eventInterList where evninUnitName not equals to UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldBeFound("evninUnitName.notEquals=" + UPDATED_EVNIN_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName in DEFAULT_EVNIN_UNIT_NAME or UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldBeFound("evninUnitName.in=" + DEFAULT_EVNIN_UNIT_NAME + "," + UPDATED_EVNIN_UNIT_NAME);

        // Get all the eventInterList where evninUnitName equals to UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldNotBeFound("evninUnitName.in=" + UPDATED_EVNIN_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName is not null
        defaultEventInterShouldBeFound("evninUnitName.specified=true");

        // Get all the eventInterList where evninUnitName is null
        defaultEventInterShouldNotBeFound("evninUnitName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName contains DEFAULT_EVNIN_UNIT_NAME
        defaultEventInterShouldBeFound("evninUnitName.contains=" + DEFAULT_EVNIN_UNIT_NAME);

        // Get all the eventInterList where evninUnitName contains UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldNotBeFound("evninUnitName.contains=" + UPDATED_EVNIN_UNIT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitNameNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitName does not contain DEFAULT_EVNIN_UNIT_NAME
        defaultEventInterShouldNotBeFound("evninUnitName.doesNotContain=" + DEFAULT_EVNIN_UNIT_NAME);

        // Get all the eventInterList where evninUnitName does not contain UPDATED_EVNIN_UNIT_NAME
        defaultEventInterShouldBeFound("evninUnitName.doesNotContain=" + UPDATED_EVNIN_UNIT_NAME);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr equals to DEFAULT_EVNIN_UNIT_ADDR
        defaultEventInterShouldBeFound("evninUnitAddr.equals=" + DEFAULT_EVNIN_UNIT_ADDR);

        // Get all the eventInterList where evninUnitAddr equals to UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldNotBeFound("evninUnitAddr.equals=" + UPDATED_EVNIN_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr not equals to DEFAULT_EVNIN_UNIT_ADDR
        defaultEventInterShouldNotBeFound("evninUnitAddr.notEquals=" + DEFAULT_EVNIN_UNIT_ADDR);

        // Get all the eventInterList where evninUnitAddr not equals to UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldBeFound("evninUnitAddr.notEquals=" + UPDATED_EVNIN_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr in DEFAULT_EVNIN_UNIT_ADDR or UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldBeFound("evninUnitAddr.in=" + DEFAULT_EVNIN_UNIT_ADDR + "," + UPDATED_EVNIN_UNIT_ADDR);

        // Get all the eventInterList where evninUnitAddr equals to UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldNotBeFound("evninUnitAddr.in=" + UPDATED_EVNIN_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr is not null
        defaultEventInterShouldBeFound("evninUnitAddr.specified=true");

        // Get all the eventInterList where evninUnitAddr is null
        defaultEventInterShouldNotBeFound("evninUnitAddr.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr contains DEFAULT_EVNIN_UNIT_ADDR
        defaultEventInterShouldBeFound("evninUnitAddr.contains=" + DEFAULT_EVNIN_UNIT_ADDR);

        // Get all the eventInterList where evninUnitAddr contains UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldNotBeFound("evninUnitAddr.contains=" + UPDATED_EVNIN_UNIT_ADDR);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninUnitAddrNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninUnitAddr does not contain DEFAULT_EVNIN_UNIT_ADDR
        defaultEventInterShouldNotBeFound("evninUnitAddr.doesNotContain=" + DEFAULT_EVNIN_UNIT_ADDR);

        // Get all the eventInterList where evninUnitAddr does not contain UPDATED_EVNIN_UNIT_ADDR
        defaultEventInterShouldBeFound("evninUnitAddr.doesNotContain=" + UPDATED_EVNIN_UNIT_ADDR);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninThemeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme equals to DEFAULT_EVNIN_THEME
        defaultEventInterShouldBeFound("evninTheme.equals=" + DEFAULT_EVNIN_THEME);

        // Get all the eventInterList where evninTheme equals to UPDATED_EVNIN_THEME
        defaultEventInterShouldNotBeFound("evninTheme.equals=" + UPDATED_EVNIN_THEME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninThemeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme not equals to DEFAULT_EVNIN_THEME
        defaultEventInterShouldNotBeFound("evninTheme.notEquals=" + DEFAULT_EVNIN_THEME);

        // Get all the eventInterList where evninTheme not equals to UPDATED_EVNIN_THEME
        defaultEventInterShouldBeFound("evninTheme.notEquals=" + UPDATED_EVNIN_THEME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninThemeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme in DEFAULT_EVNIN_THEME or UPDATED_EVNIN_THEME
        defaultEventInterShouldBeFound("evninTheme.in=" + DEFAULT_EVNIN_THEME + "," + UPDATED_EVNIN_THEME);

        // Get all the eventInterList where evninTheme equals to UPDATED_EVNIN_THEME
        defaultEventInterShouldNotBeFound("evninTheme.in=" + UPDATED_EVNIN_THEME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninThemeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme is not null
        defaultEventInterShouldBeFound("evninTheme.specified=true");

        // Get all the eventInterList where evninTheme is null
        defaultEventInterShouldNotBeFound("evninTheme.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninThemeContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme contains DEFAULT_EVNIN_THEME
        defaultEventInterShouldBeFound("evninTheme.contains=" + DEFAULT_EVNIN_THEME);

        // Get all the eventInterList where evninTheme contains UPDATED_EVNIN_THEME
        defaultEventInterShouldNotBeFound("evninTheme.contains=" + UPDATED_EVNIN_THEME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninThemeNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninTheme does not contain DEFAULT_EVNIN_THEME
        defaultEventInterShouldNotBeFound("evninTheme.doesNotContain=" + DEFAULT_EVNIN_THEME);

        // Get all the eventInterList where evninTheme does not contain UPDATED_EVNIN_THEME
        defaultEventInterShouldBeFound("evninTheme.doesNotContain=" + UPDATED_EVNIN_THEME);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninMemoIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo equals to DEFAULT_EVNIN_MEMO
        defaultEventInterShouldBeFound("evninMemo.equals=" + DEFAULT_EVNIN_MEMO);

        // Get all the eventInterList where evninMemo equals to UPDATED_EVNIN_MEMO
        defaultEventInterShouldNotBeFound("evninMemo.equals=" + UPDATED_EVNIN_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninMemoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo not equals to DEFAULT_EVNIN_MEMO
        defaultEventInterShouldNotBeFound("evninMemo.notEquals=" + DEFAULT_EVNIN_MEMO);

        // Get all the eventInterList where evninMemo not equals to UPDATED_EVNIN_MEMO
        defaultEventInterShouldBeFound("evninMemo.notEquals=" + UPDATED_EVNIN_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninMemoIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo in DEFAULT_EVNIN_MEMO or UPDATED_EVNIN_MEMO
        defaultEventInterShouldBeFound("evninMemo.in=" + DEFAULT_EVNIN_MEMO + "," + UPDATED_EVNIN_MEMO);

        // Get all the eventInterList where evninMemo equals to UPDATED_EVNIN_MEMO
        defaultEventInterShouldNotBeFound("evninMemo.in=" + UPDATED_EVNIN_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninMemoIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo is not null
        defaultEventInterShouldBeFound("evninMemo.specified=true");

        // Get all the eventInterList where evninMemo is null
        defaultEventInterShouldNotBeFound("evninMemo.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninMemoContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo contains DEFAULT_EVNIN_MEMO
        defaultEventInterShouldBeFound("evninMemo.contains=" + DEFAULT_EVNIN_MEMO);

        // Get all the eventInterList where evninMemo contains UPDATED_EVNIN_MEMO
        defaultEventInterShouldNotBeFound("evninMemo.contains=" + UPDATED_EVNIN_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninMemoNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninMemo does not contain DEFAULT_EVNIN_MEMO
        defaultEventInterShouldNotBeFound("evninMemo.doesNotContain=" + DEFAULT_EVNIN_MEMO);

        // Get all the eventInterList where evninMemo does not contain UPDATED_EVNIN_MEMO
        defaultEventInterShouldBeFound("evninMemo.doesNotContain=" + UPDATED_EVNIN_MEMO);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninIsresIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninIsres equals to DEFAULT_EVNIN_ISRES
        defaultEventInterShouldBeFound("evninIsres.equals=" + DEFAULT_EVNIN_ISRES);

        // Get all the eventInterList where evninIsres equals to UPDATED_EVNIN_ISRES
        defaultEventInterShouldNotBeFound("evninIsres.equals=" + UPDATED_EVNIN_ISRES);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninIsresIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninIsres not equals to DEFAULT_EVNIN_ISRES
        defaultEventInterShouldNotBeFound("evninIsres.notEquals=" + DEFAULT_EVNIN_ISRES);

        // Get all the eventInterList where evninIsres not equals to UPDATED_EVNIN_ISRES
        defaultEventInterShouldBeFound("evninIsres.notEquals=" + UPDATED_EVNIN_ISRES);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninIsresIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninIsres in DEFAULT_EVNIN_ISRES or UPDATED_EVNIN_ISRES
        defaultEventInterShouldBeFound("evninIsres.in=" + DEFAULT_EVNIN_ISRES + "," + UPDATED_EVNIN_ISRES);

        // Get all the eventInterList where evninIsres equals to UPDATED_EVNIN_ISRES
        defaultEventInterShouldNotBeFound("evninIsres.in=" + UPDATED_EVNIN_ISRES);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninIsresIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninIsres is not null
        defaultEventInterShouldBeFound("evninIsres.specified=true");

        // Get all the eventInterList where evninIsres is null
        defaultEventInterShouldNotBeFound("evninIsres.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo equals to DEFAULT_EVNIN_RES_MEMO
        defaultEventInterShouldBeFound("evninResMemo.equals=" + DEFAULT_EVNIN_RES_MEMO);

        // Get all the eventInterList where evninResMemo equals to UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldNotBeFound("evninResMemo.equals=" + UPDATED_EVNIN_RES_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo not equals to DEFAULT_EVNIN_RES_MEMO
        defaultEventInterShouldNotBeFound("evninResMemo.notEquals=" + DEFAULT_EVNIN_RES_MEMO);

        // Get all the eventInterList where evninResMemo not equals to UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldBeFound("evninResMemo.notEquals=" + UPDATED_EVNIN_RES_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo in DEFAULT_EVNIN_RES_MEMO or UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldBeFound("evninResMemo.in=" + DEFAULT_EVNIN_RES_MEMO + "," + UPDATED_EVNIN_RES_MEMO);

        // Get all the eventInterList where evninResMemo equals to UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldNotBeFound("evninResMemo.in=" + UPDATED_EVNIN_RES_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo is not null
        defaultEventInterShouldBeFound("evninResMemo.specified=true");

        // Get all the eventInterList where evninResMemo is null
        defaultEventInterShouldNotBeFound("evninResMemo.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo contains DEFAULT_EVNIN_RES_MEMO
        defaultEventInterShouldBeFound("evninResMemo.contains=" + DEFAULT_EVNIN_RES_MEMO);

        // Get all the eventInterList where evninResMemo contains UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldNotBeFound("evninResMemo.contains=" + UPDATED_EVNIN_RES_MEMO);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninResMemoNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninResMemo does not contain DEFAULT_EVNIN_RES_MEMO
        defaultEventInterShouldNotBeFound("evninResMemo.doesNotContain=" + DEFAULT_EVNIN_RES_MEMO);

        // Get all the eventInterList where evninResMemo does not contain UPDATED_EVNIN_RES_MEMO
        defaultEventInterShouldBeFound("evninResMemo.doesNotContain=" + UPDATED_EVNIN_RES_MEMO);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime equals to DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.equals=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime equals to UPDATED_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.equals=" + UPDATED_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime not equals to DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.notEquals=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime not equals to UPDATED_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.notEquals=" + UPDATED_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime in DEFAULT_EVNIN_CRETIME or UPDATED_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.in=" + DEFAULT_EVNIN_CRETIME + "," + UPDATED_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime equals to UPDATED_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.in=" + UPDATED_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime is not null
        defaultEventInterShouldBeFound("evninCretime.specified=true");

        // Get all the eventInterList where evninCretime is null
        defaultEventInterShouldNotBeFound("evninCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime is greater than or equal to DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.greaterThanOrEqual=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime is greater than or equal to UPDATED_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.greaterThanOrEqual=" + UPDATED_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime is less than or equal to DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.lessThanOrEqual=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime is less than or equal to SMALLER_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.lessThanOrEqual=" + SMALLER_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime is less than DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.lessThan=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime is less than UPDATED_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.lessThan=" + UPDATED_EVNIN_CRETIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCretime is greater than DEFAULT_EVNIN_CRETIME
        defaultEventInterShouldNotBeFound("evninCretime.greaterThan=" + DEFAULT_EVNIN_CRETIME);

        // Get all the eventInterList where evninCretime is greater than SMALLER_EVNIN_CRETIME
        defaultEventInterShouldBeFound("evninCretime.greaterThan=" + SMALLER_EVNIN_CRETIME);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid equals to DEFAULT_EVNIN_CREID
        defaultEventInterShouldBeFound("evninCreid.equals=" + DEFAULT_EVNIN_CREID);

        // Get all the eventInterList where evninCreid equals to UPDATED_EVNIN_CREID
        defaultEventInterShouldNotBeFound("evninCreid.equals=" + UPDATED_EVNIN_CREID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid not equals to DEFAULT_EVNIN_CREID
        defaultEventInterShouldNotBeFound("evninCreid.notEquals=" + DEFAULT_EVNIN_CREID);

        // Get all the eventInterList where evninCreid not equals to UPDATED_EVNIN_CREID
        defaultEventInterShouldBeFound("evninCreid.notEquals=" + UPDATED_EVNIN_CREID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCreidIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid in DEFAULT_EVNIN_CREID or UPDATED_EVNIN_CREID
        defaultEventInterShouldBeFound("evninCreid.in=" + DEFAULT_EVNIN_CREID + "," + UPDATED_EVNIN_CREID);

        // Get all the eventInterList where evninCreid equals to UPDATED_EVNIN_CREID
        defaultEventInterShouldNotBeFound("evninCreid.in=" + UPDATED_EVNIN_CREID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid is not null
        defaultEventInterShouldBeFound("evninCreid.specified=true");

        // Get all the eventInterList where evninCreid is null
        defaultEventInterShouldNotBeFound("evninCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninCreidContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid contains DEFAULT_EVNIN_CREID
        defaultEventInterShouldBeFound("evninCreid.contains=" + DEFAULT_EVNIN_CREID);

        // Get all the eventInterList where evninCreid contains UPDATED_EVNIN_CREID
        defaultEventInterShouldNotBeFound("evninCreid.contains=" + UPDATED_EVNIN_CREID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninCreidNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninCreid does not contain DEFAULT_EVNIN_CREID
        defaultEventInterShouldNotBeFound("evninCreid.doesNotContain=" + DEFAULT_EVNIN_CREID);

        // Get all the eventInterList where evninCreid does not contain UPDATED_EVNIN_CREID
        defaultEventInterShouldBeFound("evninCreid.doesNotContain=" + UPDATED_EVNIN_CREID);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime equals to DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.equals=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime equals to UPDATED_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.equals=" + UPDATED_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime not equals to DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.notEquals=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime not equals to UPDATED_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.notEquals=" + UPDATED_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime in DEFAULT_EVNIN_MODTIME or UPDATED_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.in=" + DEFAULT_EVNIN_MODTIME + "," + UPDATED_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime equals to UPDATED_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.in=" + UPDATED_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime is not null
        defaultEventInterShouldBeFound("evninModtime.specified=true");

        // Get all the eventInterList where evninModtime is null
        defaultEventInterShouldNotBeFound("evninModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime is greater than or equal to DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.greaterThanOrEqual=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime is greater than or equal to UPDATED_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.greaterThanOrEqual=" + UPDATED_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime is less than or equal to DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.lessThanOrEqual=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime is less than or equal to SMALLER_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.lessThanOrEqual=" + SMALLER_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime is less than DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.lessThan=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime is less than UPDATED_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.lessThan=" + UPDATED_EVNIN_MODTIME);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModtime is greater than DEFAULT_EVNIN_MODTIME
        defaultEventInterShouldNotBeFound("evninModtime.greaterThan=" + DEFAULT_EVNIN_MODTIME);

        // Get all the eventInterList where evninModtime is greater than SMALLER_EVNIN_MODTIME
        defaultEventInterShouldBeFound("evninModtime.greaterThan=" + SMALLER_EVNIN_MODTIME);
    }


    @Test
    @Transactional
    public void getAllEventIntersByEvninModidIsEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid equals to DEFAULT_EVNIN_MODID
        defaultEventInterShouldBeFound("evninModid.equals=" + DEFAULT_EVNIN_MODID);

        // Get all the eventInterList where evninModid equals to UPDATED_EVNIN_MODID
        defaultEventInterShouldNotBeFound("evninModid.equals=" + UPDATED_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid not equals to DEFAULT_EVNIN_MODID
        defaultEventInterShouldNotBeFound("evninModid.notEquals=" + DEFAULT_EVNIN_MODID);

        // Get all the eventInterList where evninModid not equals to UPDATED_EVNIN_MODID
        defaultEventInterShouldBeFound("evninModid.notEquals=" + UPDATED_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModidIsInShouldWork() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid in DEFAULT_EVNIN_MODID or UPDATED_EVNIN_MODID
        defaultEventInterShouldBeFound("evninModid.in=" + DEFAULT_EVNIN_MODID + "," + UPDATED_EVNIN_MODID);

        // Get all the eventInterList where evninModid equals to UPDATED_EVNIN_MODID
        defaultEventInterShouldNotBeFound("evninModid.in=" + UPDATED_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid is not null
        defaultEventInterShouldBeFound("evninModid.specified=true");

        // Get all the eventInterList where evninModid is null
        defaultEventInterShouldNotBeFound("evninModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventIntersByEvninModidContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid contains DEFAULT_EVNIN_MODID
        defaultEventInterShouldBeFound("evninModid.contains=" + DEFAULT_EVNIN_MODID);

        // Get all the eventInterList where evninModid contains UPDATED_EVNIN_MODID
        defaultEventInterShouldNotBeFound("evninModid.contains=" + UPDATED_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void getAllEventIntersByEvninModidNotContainsSomething() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        // Get all the eventInterList where evninModid does not contain DEFAULT_EVNIN_MODID
        defaultEventInterShouldNotBeFound("evninModid.doesNotContain=" + DEFAULT_EVNIN_MODID);

        // Get all the eventInterList where evninModid does not contain UPDATED_EVNIN_MODID
        defaultEventInterShouldBeFound("evninModid.doesNotContain=" + UPDATED_EVNIN_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEventInterShouldBeFound(String filter) throws Exception {
        restEventInterMockMvc.perform(get("/api/event-inters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventInter.getId().intValue())))
            .andExpect(jsonPath("$.[*].evninTime").value(hasItem(sameInstant(DEFAULT_EVNIN_TIME))))
            .andExpect(jsonPath("$.[*].evninEsCode").value(hasItem(DEFAULT_EVNIN_ES_CODE)))
            .andExpect(jsonPath("$.[*].evninDeviceid").value(hasItem(DEFAULT_EVNIN_DEVICEID)))
            .andExpect(jsonPath("$.[*].evninDviModNum").value(hasItem(DEFAULT_EVNIN_DVI_MOD_NUM)))
            .andExpect(jsonPath("$.[*].evninDviCode").value(hasItem(DEFAULT_EVNIN_DVI_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitUcCode").value(hasItem(DEFAULT_EVNIN_UNIT_UC_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitCode").value(hasItem(DEFAULT_EVNIN_UNIT_CODE)))
            .andExpect(jsonPath("$.[*].evninUnitName").value(hasItem(DEFAULT_EVNIN_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].evninUnitAddr").value(hasItem(DEFAULT_EVNIN_UNIT_ADDR)))
            .andExpect(jsonPath("$.[*].evninTheme").value(hasItem(DEFAULT_EVNIN_THEME)))
            .andExpect(jsonPath("$.[*].evninMemo").value(hasItem(DEFAULT_EVNIN_MEMO)))
            .andExpect(jsonPath("$.[*].evninIsres").value(hasItem(DEFAULT_EVNIN_ISRES.booleanValue())))
            .andExpect(jsonPath("$.[*].evninResMemo").value(hasItem(DEFAULT_EVNIN_RES_MEMO)))
            .andExpect(jsonPath("$.[*].evninCretime").value(hasItem(sameInstant(DEFAULT_EVNIN_CRETIME))))
            .andExpect(jsonPath("$.[*].evninCreid").value(hasItem(DEFAULT_EVNIN_CREID)))
            .andExpect(jsonPath("$.[*].evninModtime").value(hasItem(sameInstant(DEFAULT_EVNIN_MODTIME))))
            .andExpect(jsonPath("$.[*].evninModid").value(hasItem(DEFAULT_EVNIN_MODID)));

        // Check, that the count call also returns 1
        restEventInterMockMvc.perform(get("/api/event-inters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEventInterShouldNotBeFound(String filter) throws Exception {
        restEventInterMockMvc.perform(get("/api/event-inters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEventInterMockMvc.perform(get("/api/event-inters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEventInter() throws Exception {
        // Get the eventInter
        restEventInterMockMvc.perform(get("/api/event-inters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventInter() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        int databaseSizeBeforeUpdate = eventInterRepository.findAll().size();

        // Update the eventInter
        EventInter updatedEventInter = eventInterRepository.findById(eventInter.getId()).get();
        // Disconnect from session so that the updates on updatedEventInter are not directly saved in db
        em.detach(updatedEventInter);
        updatedEventInter
            .evninTime(UPDATED_EVNIN_TIME)
            .evninEsCode(UPDATED_EVNIN_ES_CODE)
            .evninDeviceid(UPDATED_EVNIN_DEVICEID)
            .evninDviModNum(UPDATED_EVNIN_DVI_MOD_NUM)
            .evninDviCode(UPDATED_EVNIN_DVI_CODE)
            .evninUnitUcCode(UPDATED_EVNIN_UNIT_UC_CODE)
            .evninUnitCode(UPDATED_EVNIN_UNIT_CODE)
            .evninUnitName(UPDATED_EVNIN_UNIT_NAME)
            .evninUnitAddr(UPDATED_EVNIN_UNIT_ADDR)
            .evninTheme(UPDATED_EVNIN_THEME)
            .evninMemo(UPDATED_EVNIN_MEMO)
            .evninIsres(UPDATED_EVNIN_ISRES)
            .evninResMemo(UPDATED_EVNIN_RES_MEMO)
            .evninCretime(UPDATED_EVNIN_CRETIME)
            .evninCreid(UPDATED_EVNIN_CREID)
            .evninModtime(UPDATED_EVNIN_MODTIME)
            .evninModid(UPDATED_EVNIN_MODID);
        EventInterDTO eventInterDTO = eventInterMapper.toDto(updatedEventInter);

        restEventInterMockMvc.perform(put("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isOk());

        // Validate the EventInter in the database
        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeUpdate);
        EventInter testEventInter = eventInterList.get(eventInterList.size() - 1);
        assertThat(testEventInter.getEvninTime()).isEqualTo(UPDATED_EVNIN_TIME);
        assertThat(testEventInter.getEvninEsCode()).isEqualTo(UPDATED_EVNIN_ES_CODE);
        assertThat(testEventInter.getEvninDeviceid()).isEqualTo(UPDATED_EVNIN_DEVICEID);
        assertThat(testEventInter.getEvninDviModNum()).isEqualTo(UPDATED_EVNIN_DVI_MOD_NUM);
        assertThat(testEventInter.getEvninDviCode()).isEqualTo(UPDATED_EVNIN_DVI_CODE);
        assertThat(testEventInter.getEvninUnitUcCode()).isEqualTo(UPDATED_EVNIN_UNIT_UC_CODE);
        assertThat(testEventInter.getEvninUnitCode()).isEqualTo(UPDATED_EVNIN_UNIT_CODE);
        assertThat(testEventInter.getEvninUnitName()).isEqualTo(UPDATED_EVNIN_UNIT_NAME);
        assertThat(testEventInter.getEvninUnitAddr()).isEqualTo(UPDATED_EVNIN_UNIT_ADDR);
        assertThat(testEventInter.getEvninTheme()).isEqualTo(UPDATED_EVNIN_THEME);
        assertThat(testEventInter.getEvninMemo()).isEqualTo(UPDATED_EVNIN_MEMO);
        assertThat(testEventInter.isEvninIsres()).isEqualTo(UPDATED_EVNIN_ISRES);
        assertThat(testEventInter.getEvninResMemo()).isEqualTo(UPDATED_EVNIN_RES_MEMO);
        assertThat(testEventInter.getEvninCretime()).isEqualTo(UPDATED_EVNIN_CRETIME);
        assertThat(testEventInter.getEvninCreid()).isEqualTo(UPDATED_EVNIN_CREID);
        assertThat(testEventInter.getEvninModtime()).isEqualTo(UPDATED_EVNIN_MODTIME);
        assertThat(testEventInter.getEvninModid()).isEqualTo(UPDATED_EVNIN_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingEventInter() throws Exception {
        int databaseSizeBeforeUpdate = eventInterRepository.findAll().size();

        // Create the EventInter
        EventInterDTO eventInterDTO = eventInterMapper.toDto(eventInter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventInterMockMvc.perform(put("/api/event-inters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventInterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventInter in the database
        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventInter() throws Exception {
        // Initialize the database
        eventInterRepository.saveAndFlush(eventInter);

        int databaseSizeBeforeDelete = eventInterRepository.findAll().size();

        // Delete the eventInter
        restEventInterMockMvc.perform(delete("/api/event-inters/{id}", eventInter.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventInter> eventInterList = eventInterRepository.findAll();
        assertThat(eventInterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
