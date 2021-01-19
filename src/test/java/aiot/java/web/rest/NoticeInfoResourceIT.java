package aiot.java.web.rest;

import aiot.java.AIoTapplicationApp;
import aiot.java.domain.NoticeInfo;
import aiot.java.repository.NoticeInfoRepository;
import aiot.java.service.NoticeInfoService;
import aiot.java.service.dto.NoticeInfoDTO;
import aiot.java.service.mapper.NoticeInfoMapper;
import aiot.java.service.dto.NoticeInfoCriteria;
import aiot.java.service.NoticeInfoQueryService;

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
 * Integration tests for the {@link NoticeInfoResource} REST controller.
 */
@SpringBootTest(classes = AIoTapplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class NoticeInfoResourceIT {

    private static final String DEFAULT_NOTI_CASEID = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_CASEID = "BBBBBBBBBB";

    private static final String DEFAULT_NOTI_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTI_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_NOTI_INFOTYPE = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_INFOTYPE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NOTI_STARTTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOTI_STARTTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NOTI_STARTTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final ZonedDateTime DEFAULT_NOTI_ENDTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOTI_ENDTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NOTI_ENDTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_NOTI_STATCODE = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_STATCODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NOTI_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOTI_CRETIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NOTI_CRETIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_NOTI_CREID = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_CREID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NOTI_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOTI_MODTIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_NOTI_MODTIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final String DEFAULT_NOTI_MODID = "AAAAAAAAAA";
    private static final String UPDATED_NOTI_MODID = "BBBBBBBBBB";

    @Autowired
    private NoticeInfoRepository noticeInfoRepository;

    @Autowired
    private NoticeInfoMapper noticeInfoMapper;

    @Autowired
    private NoticeInfoService noticeInfoService;

    @Autowired
    private NoticeInfoQueryService noticeInfoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoticeInfoMockMvc;

    private NoticeInfo noticeInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoticeInfo createEntity(EntityManager em) {
        NoticeInfo noticeInfo = new NoticeInfo()
            .notiCaseid(DEFAULT_NOTI_CASEID)
            .notiTitle(DEFAULT_NOTI_TITLE)
            .notiContent(DEFAULT_NOTI_CONTENT)
            .notiInfotype(DEFAULT_NOTI_INFOTYPE)
            .notiStarttime(DEFAULT_NOTI_STARTTIME)
            .notiEndtime(DEFAULT_NOTI_ENDTIME)
            .notiStatcode(DEFAULT_NOTI_STATCODE)
            .notiCretime(DEFAULT_NOTI_CRETIME)
            .notiCreid(DEFAULT_NOTI_CREID)
            .notiModtime(DEFAULT_NOTI_MODTIME)
            .notiModid(DEFAULT_NOTI_MODID);
        return noticeInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoticeInfo createUpdatedEntity(EntityManager em) {
        NoticeInfo noticeInfo = new NoticeInfo()
            .notiCaseid(UPDATED_NOTI_CASEID)
            .notiTitle(UPDATED_NOTI_TITLE)
            .notiContent(UPDATED_NOTI_CONTENT)
            .notiInfotype(UPDATED_NOTI_INFOTYPE)
            .notiStarttime(UPDATED_NOTI_STARTTIME)
            .notiEndtime(UPDATED_NOTI_ENDTIME)
            .notiStatcode(UPDATED_NOTI_STATCODE)
            .notiCretime(UPDATED_NOTI_CRETIME)
            .notiCreid(UPDATED_NOTI_CREID)
            .notiModtime(UPDATED_NOTI_MODTIME)
            .notiModid(UPDATED_NOTI_MODID);
        return noticeInfo;
    }

    @BeforeEach
    public void initTest() {
        noticeInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoticeInfo() throws Exception {
        int databaseSizeBeforeCreate = noticeInfoRepository.findAll().size();

        // Create the NoticeInfo
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);
        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the NoticeInfo in the database
        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeCreate + 1);
        NoticeInfo testNoticeInfo = noticeInfoList.get(noticeInfoList.size() - 1);
        assertThat(testNoticeInfo.getNotiCaseid()).isEqualTo(DEFAULT_NOTI_CASEID);
        assertThat(testNoticeInfo.getNotiTitle()).isEqualTo(DEFAULT_NOTI_TITLE);
        assertThat(testNoticeInfo.getNotiContent()).isEqualTo(DEFAULT_NOTI_CONTENT);
        assertThat(testNoticeInfo.getNotiInfotype()).isEqualTo(DEFAULT_NOTI_INFOTYPE);
        assertThat(testNoticeInfo.getNotiStarttime()).isEqualTo(DEFAULT_NOTI_STARTTIME);
        assertThat(testNoticeInfo.getNotiEndtime()).isEqualTo(DEFAULT_NOTI_ENDTIME);
        assertThat(testNoticeInfo.getNotiStatcode()).isEqualTo(DEFAULT_NOTI_STATCODE);
        assertThat(testNoticeInfo.getNotiCretime()).isEqualTo(DEFAULT_NOTI_CRETIME);
        assertThat(testNoticeInfo.getNotiCreid()).isEqualTo(DEFAULT_NOTI_CREID);
        assertThat(testNoticeInfo.getNotiModtime()).isEqualTo(DEFAULT_NOTI_MODTIME);
        assertThat(testNoticeInfo.getNotiModid()).isEqualTo(DEFAULT_NOTI_MODID);
    }

    @Test
    @Transactional
    public void createNoticeInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noticeInfoRepository.findAll().size();

        // Create the NoticeInfo with an existing ID
        noticeInfo.setId(1L);
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoticeInfo in the database
        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNotiCaseidIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiCaseid(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiTitle(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiContentIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiContent(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiInfotypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiInfotype(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiStarttimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiStarttime(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiEndtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiEndtime(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiStatcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiStatcode(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiCretimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiCretime(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiCreidIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiCreid(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiModtimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiModtime(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNotiModidIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticeInfoRepository.findAll().size();
        // set the field null
        noticeInfo.setNotiModid(null);

        // Create the NoticeInfo, which fails.
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        restNoticeInfoMockMvc.perform(post("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoticeInfos() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList
        restNoticeInfoMockMvc.perform(get("/api/notice-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticeInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].notiCaseid").value(hasItem(DEFAULT_NOTI_CASEID)))
            .andExpect(jsonPath("$.[*].notiTitle").value(hasItem(DEFAULT_NOTI_TITLE)))
            .andExpect(jsonPath("$.[*].notiContent").value(hasItem(DEFAULT_NOTI_CONTENT)))
            .andExpect(jsonPath("$.[*].notiInfotype").value(hasItem(DEFAULT_NOTI_INFOTYPE)))
            .andExpect(jsonPath("$.[*].notiStarttime").value(hasItem(sameInstant(DEFAULT_NOTI_STARTTIME))))
            .andExpect(jsonPath("$.[*].notiEndtime").value(hasItem(sameInstant(DEFAULT_NOTI_ENDTIME))))
            .andExpect(jsonPath("$.[*].notiStatcode").value(hasItem(DEFAULT_NOTI_STATCODE)))
            .andExpect(jsonPath("$.[*].notiCretime").value(hasItem(sameInstant(DEFAULT_NOTI_CRETIME))))
            .andExpect(jsonPath("$.[*].notiCreid").value(hasItem(DEFAULT_NOTI_CREID)))
            .andExpect(jsonPath("$.[*].notiModtime").value(hasItem(sameInstant(DEFAULT_NOTI_MODTIME))))
            .andExpect(jsonPath("$.[*].notiModid").value(hasItem(DEFAULT_NOTI_MODID)));
    }
    
    @Test
    @Transactional
    public void getNoticeInfo() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get the noticeInfo
        restNoticeInfoMockMvc.perform(get("/api/notice-infos/{id}", noticeInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(noticeInfo.getId().intValue()))
            .andExpect(jsonPath("$.notiCaseid").value(DEFAULT_NOTI_CASEID))
            .andExpect(jsonPath("$.notiTitle").value(DEFAULT_NOTI_TITLE))
            .andExpect(jsonPath("$.notiContent").value(DEFAULT_NOTI_CONTENT))
            .andExpect(jsonPath("$.notiInfotype").value(DEFAULT_NOTI_INFOTYPE))
            .andExpect(jsonPath("$.notiStarttime").value(sameInstant(DEFAULT_NOTI_STARTTIME)))
            .andExpect(jsonPath("$.notiEndtime").value(sameInstant(DEFAULT_NOTI_ENDTIME)))
            .andExpect(jsonPath("$.notiStatcode").value(DEFAULT_NOTI_STATCODE))
            .andExpect(jsonPath("$.notiCretime").value(sameInstant(DEFAULT_NOTI_CRETIME)))
            .andExpect(jsonPath("$.notiCreid").value(DEFAULT_NOTI_CREID))
            .andExpect(jsonPath("$.notiModtime").value(sameInstant(DEFAULT_NOTI_MODTIME)))
            .andExpect(jsonPath("$.notiModid").value(DEFAULT_NOTI_MODID));
    }


    @Test
    @Transactional
    public void getNoticeInfosByIdFiltering() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        Long id = noticeInfo.getId();

        defaultNoticeInfoShouldBeFound("id.equals=" + id);
        defaultNoticeInfoShouldNotBeFound("id.notEquals=" + id);

        defaultNoticeInfoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultNoticeInfoShouldNotBeFound("id.greaterThan=" + id);

        defaultNoticeInfoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultNoticeInfoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid equals to DEFAULT_NOTI_CASEID
        defaultNoticeInfoShouldBeFound("notiCaseid.equals=" + DEFAULT_NOTI_CASEID);

        // Get all the noticeInfoList where notiCaseid equals to UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldNotBeFound("notiCaseid.equals=" + UPDATED_NOTI_CASEID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid not equals to DEFAULT_NOTI_CASEID
        defaultNoticeInfoShouldNotBeFound("notiCaseid.notEquals=" + DEFAULT_NOTI_CASEID);

        // Get all the noticeInfoList where notiCaseid not equals to UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldBeFound("notiCaseid.notEquals=" + UPDATED_NOTI_CASEID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid in DEFAULT_NOTI_CASEID or UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldBeFound("notiCaseid.in=" + DEFAULT_NOTI_CASEID + "," + UPDATED_NOTI_CASEID);

        // Get all the noticeInfoList where notiCaseid equals to UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldNotBeFound("notiCaseid.in=" + UPDATED_NOTI_CASEID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid is not null
        defaultNoticeInfoShouldBeFound("notiCaseid.specified=true");

        // Get all the noticeInfoList where notiCaseid is null
        defaultNoticeInfoShouldNotBeFound("notiCaseid.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid contains DEFAULT_NOTI_CASEID
        defaultNoticeInfoShouldBeFound("notiCaseid.contains=" + DEFAULT_NOTI_CASEID);

        // Get all the noticeInfoList where notiCaseid contains UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldNotBeFound("notiCaseid.contains=" + UPDATED_NOTI_CASEID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCaseidNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCaseid does not contain DEFAULT_NOTI_CASEID
        defaultNoticeInfoShouldNotBeFound("notiCaseid.doesNotContain=" + DEFAULT_NOTI_CASEID);

        // Get all the noticeInfoList where notiCaseid does not contain UPDATED_NOTI_CASEID
        defaultNoticeInfoShouldBeFound("notiCaseid.doesNotContain=" + UPDATED_NOTI_CASEID);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle equals to DEFAULT_NOTI_TITLE
        defaultNoticeInfoShouldBeFound("notiTitle.equals=" + DEFAULT_NOTI_TITLE);

        // Get all the noticeInfoList where notiTitle equals to UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldNotBeFound("notiTitle.equals=" + UPDATED_NOTI_TITLE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle not equals to DEFAULT_NOTI_TITLE
        defaultNoticeInfoShouldNotBeFound("notiTitle.notEquals=" + DEFAULT_NOTI_TITLE);

        // Get all the noticeInfoList where notiTitle not equals to UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldBeFound("notiTitle.notEquals=" + UPDATED_NOTI_TITLE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle in DEFAULT_NOTI_TITLE or UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldBeFound("notiTitle.in=" + DEFAULT_NOTI_TITLE + "," + UPDATED_NOTI_TITLE);

        // Get all the noticeInfoList where notiTitle equals to UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldNotBeFound("notiTitle.in=" + UPDATED_NOTI_TITLE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle is not null
        defaultNoticeInfoShouldBeFound("notiTitle.specified=true");

        // Get all the noticeInfoList where notiTitle is null
        defaultNoticeInfoShouldNotBeFound("notiTitle.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle contains DEFAULT_NOTI_TITLE
        defaultNoticeInfoShouldBeFound("notiTitle.contains=" + DEFAULT_NOTI_TITLE);

        // Get all the noticeInfoList where notiTitle contains UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldNotBeFound("notiTitle.contains=" + UPDATED_NOTI_TITLE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiTitleNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiTitle does not contain DEFAULT_NOTI_TITLE
        defaultNoticeInfoShouldNotBeFound("notiTitle.doesNotContain=" + DEFAULT_NOTI_TITLE);

        // Get all the noticeInfoList where notiTitle does not contain UPDATED_NOTI_TITLE
        defaultNoticeInfoShouldBeFound("notiTitle.doesNotContain=" + UPDATED_NOTI_TITLE);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent equals to DEFAULT_NOTI_CONTENT
        defaultNoticeInfoShouldBeFound("notiContent.equals=" + DEFAULT_NOTI_CONTENT);

        // Get all the noticeInfoList where notiContent equals to UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldNotBeFound("notiContent.equals=" + UPDATED_NOTI_CONTENT);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent not equals to DEFAULT_NOTI_CONTENT
        defaultNoticeInfoShouldNotBeFound("notiContent.notEquals=" + DEFAULT_NOTI_CONTENT);

        // Get all the noticeInfoList where notiContent not equals to UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldBeFound("notiContent.notEquals=" + UPDATED_NOTI_CONTENT);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent in DEFAULT_NOTI_CONTENT or UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldBeFound("notiContent.in=" + DEFAULT_NOTI_CONTENT + "," + UPDATED_NOTI_CONTENT);

        // Get all the noticeInfoList where notiContent equals to UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldNotBeFound("notiContent.in=" + UPDATED_NOTI_CONTENT);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent is not null
        defaultNoticeInfoShouldBeFound("notiContent.specified=true");

        // Get all the noticeInfoList where notiContent is null
        defaultNoticeInfoShouldNotBeFound("notiContent.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent contains DEFAULT_NOTI_CONTENT
        defaultNoticeInfoShouldBeFound("notiContent.contains=" + DEFAULT_NOTI_CONTENT);

        // Get all the noticeInfoList where notiContent contains UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldNotBeFound("notiContent.contains=" + UPDATED_NOTI_CONTENT);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiContentNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiContent does not contain DEFAULT_NOTI_CONTENT
        defaultNoticeInfoShouldNotBeFound("notiContent.doesNotContain=" + DEFAULT_NOTI_CONTENT);

        // Get all the noticeInfoList where notiContent does not contain UPDATED_NOTI_CONTENT
        defaultNoticeInfoShouldBeFound("notiContent.doesNotContain=" + UPDATED_NOTI_CONTENT);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype equals to DEFAULT_NOTI_INFOTYPE
        defaultNoticeInfoShouldBeFound("notiInfotype.equals=" + DEFAULT_NOTI_INFOTYPE);

        // Get all the noticeInfoList where notiInfotype equals to UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldNotBeFound("notiInfotype.equals=" + UPDATED_NOTI_INFOTYPE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype not equals to DEFAULT_NOTI_INFOTYPE
        defaultNoticeInfoShouldNotBeFound("notiInfotype.notEquals=" + DEFAULT_NOTI_INFOTYPE);

        // Get all the noticeInfoList where notiInfotype not equals to UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldBeFound("notiInfotype.notEquals=" + UPDATED_NOTI_INFOTYPE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype in DEFAULT_NOTI_INFOTYPE or UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldBeFound("notiInfotype.in=" + DEFAULT_NOTI_INFOTYPE + "," + UPDATED_NOTI_INFOTYPE);

        // Get all the noticeInfoList where notiInfotype equals to UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldNotBeFound("notiInfotype.in=" + UPDATED_NOTI_INFOTYPE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype is not null
        defaultNoticeInfoShouldBeFound("notiInfotype.specified=true");

        // Get all the noticeInfoList where notiInfotype is null
        defaultNoticeInfoShouldNotBeFound("notiInfotype.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype contains DEFAULT_NOTI_INFOTYPE
        defaultNoticeInfoShouldBeFound("notiInfotype.contains=" + DEFAULT_NOTI_INFOTYPE);

        // Get all the noticeInfoList where notiInfotype contains UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldNotBeFound("notiInfotype.contains=" + UPDATED_NOTI_INFOTYPE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiInfotypeNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiInfotype does not contain DEFAULT_NOTI_INFOTYPE
        defaultNoticeInfoShouldNotBeFound("notiInfotype.doesNotContain=" + DEFAULT_NOTI_INFOTYPE);

        // Get all the noticeInfoList where notiInfotype does not contain UPDATED_NOTI_INFOTYPE
        defaultNoticeInfoShouldBeFound("notiInfotype.doesNotContain=" + UPDATED_NOTI_INFOTYPE);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime equals to DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.equals=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime equals to UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.equals=" + UPDATED_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime not equals to DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.notEquals=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime not equals to UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.notEquals=" + UPDATED_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime in DEFAULT_NOTI_STARTTIME or UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.in=" + DEFAULT_NOTI_STARTTIME + "," + UPDATED_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime equals to UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.in=" + UPDATED_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime is not null
        defaultNoticeInfoShouldBeFound("notiStarttime.specified=true");

        // Get all the noticeInfoList where notiStarttime is null
        defaultNoticeInfoShouldNotBeFound("notiStarttime.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime is greater than or equal to DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.greaterThanOrEqual=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime is greater than or equal to UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.greaterThanOrEqual=" + UPDATED_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime is less than or equal to DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.lessThanOrEqual=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime is less than or equal to SMALLER_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.lessThanOrEqual=" + SMALLER_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsLessThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime is less than DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.lessThan=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime is less than UPDATED_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.lessThan=" + UPDATED_NOTI_STARTTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStarttimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStarttime is greater than DEFAULT_NOTI_STARTTIME
        defaultNoticeInfoShouldNotBeFound("notiStarttime.greaterThan=" + DEFAULT_NOTI_STARTTIME);

        // Get all the noticeInfoList where notiStarttime is greater than SMALLER_NOTI_STARTTIME
        defaultNoticeInfoShouldBeFound("notiStarttime.greaterThan=" + SMALLER_NOTI_STARTTIME);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime equals to DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.equals=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime equals to UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.equals=" + UPDATED_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime not equals to DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.notEquals=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime not equals to UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.notEquals=" + UPDATED_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime in DEFAULT_NOTI_ENDTIME or UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.in=" + DEFAULT_NOTI_ENDTIME + "," + UPDATED_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime equals to UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.in=" + UPDATED_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime is not null
        defaultNoticeInfoShouldBeFound("notiEndtime.specified=true");

        // Get all the noticeInfoList where notiEndtime is null
        defaultNoticeInfoShouldNotBeFound("notiEndtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime is greater than or equal to DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.greaterThanOrEqual=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime is greater than or equal to UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.greaterThanOrEqual=" + UPDATED_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime is less than or equal to DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.lessThanOrEqual=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime is less than or equal to SMALLER_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.lessThanOrEqual=" + SMALLER_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime is less than DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.lessThan=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime is less than UPDATED_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.lessThan=" + UPDATED_NOTI_ENDTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiEndtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiEndtime is greater than DEFAULT_NOTI_ENDTIME
        defaultNoticeInfoShouldNotBeFound("notiEndtime.greaterThan=" + DEFAULT_NOTI_ENDTIME);

        // Get all the noticeInfoList where notiEndtime is greater than SMALLER_NOTI_ENDTIME
        defaultNoticeInfoShouldBeFound("notiEndtime.greaterThan=" + SMALLER_NOTI_ENDTIME);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode equals to DEFAULT_NOTI_STATCODE
        defaultNoticeInfoShouldBeFound("notiStatcode.equals=" + DEFAULT_NOTI_STATCODE);

        // Get all the noticeInfoList where notiStatcode equals to UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldNotBeFound("notiStatcode.equals=" + UPDATED_NOTI_STATCODE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode not equals to DEFAULT_NOTI_STATCODE
        defaultNoticeInfoShouldNotBeFound("notiStatcode.notEquals=" + DEFAULT_NOTI_STATCODE);

        // Get all the noticeInfoList where notiStatcode not equals to UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldBeFound("notiStatcode.notEquals=" + UPDATED_NOTI_STATCODE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode in DEFAULT_NOTI_STATCODE or UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldBeFound("notiStatcode.in=" + DEFAULT_NOTI_STATCODE + "," + UPDATED_NOTI_STATCODE);

        // Get all the noticeInfoList where notiStatcode equals to UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldNotBeFound("notiStatcode.in=" + UPDATED_NOTI_STATCODE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode is not null
        defaultNoticeInfoShouldBeFound("notiStatcode.specified=true");

        // Get all the noticeInfoList where notiStatcode is null
        defaultNoticeInfoShouldNotBeFound("notiStatcode.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode contains DEFAULT_NOTI_STATCODE
        defaultNoticeInfoShouldBeFound("notiStatcode.contains=" + DEFAULT_NOTI_STATCODE);

        // Get all the noticeInfoList where notiStatcode contains UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldNotBeFound("notiStatcode.contains=" + UPDATED_NOTI_STATCODE);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiStatcodeNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiStatcode does not contain DEFAULT_NOTI_STATCODE
        defaultNoticeInfoShouldNotBeFound("notiStatcode.doesNotContain=" + DEFAULT_NOTI_STATCODE);

        // Get all the noticeInfoList where notiStatcode does not contain UPDATED_NOTI_STATCODE
        defaultNoticeInfoShouldBeFound("notiStatcode.doesNotContain=" + UPDATED_NOTI_STATCODE);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime equals to DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.equals=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime equals to UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.equals=" + UPDATED_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime not equals to DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.notEquals=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime not equals to UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.notEquals=" + UPDATED_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime in DEFAULT_NOTI_CRETIME or UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.in=" + DEFAULT_NOTI_CRETIME + "," + UPDATED_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime equals to UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.in=" + UPDATED_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime is not null
        defaultNoticeInfoShouldBeFound("notiCretime.specified=true");

        // Get all the noticeInfoList where notiCretime is null
        defaultNoticeInfoShouldNotBeFound("notiCretime.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime is greater than or equal to DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.greaterThanOrEqual=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime is greater than or equal to UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.greaterThanOrEqual=" + UPDATED_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime is less than or equal to DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.lessThanOrEqual=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime is less than or equal to SMALLER_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.lessThanOrEqual=" + SMALLER_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsLessThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime is less than DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.lessThan=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime is less than UPDATED_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.lessThan=" + UPDATED_NOTI_CRETIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCretimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCretime is greater than DEFAULT_NOTI_CRETIME
        defaultNoticeInfoShouldNotBeFound("notiCretime.greaterThan=" + DEFAULT_NOTI_CRETIME);

        // Get all the noticeInfoList where notiCretime is greater than SMALLER_NOTI_CRETIME
        defaultNoticeInfoShouldBeFound("notiCretime.greaterThan=" + SMALLER_NOTI_CRETIME);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid equals to DEFAULT_NOTI_CREID
        defaultNoticeInfoShouldBeFound("notiCreid.equals=" + DEFAULT_NOTI_CREID);

        // Get all the noticeInfoList where notiCreid equals to UPDATED_NOTI_CREID
        defaultNoticeInfoShouldNotBeFound("notiCreid.equals=" + UPDATED_NOTI_CREID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid not equals to DEFAULT_NOTI_CREID
        defaultNoticeInfoShouldNotBeFound("notiCreid.notEquals=" + DEFAULT_NOTI_CREID);

        // Get all the noticeInfoList where notiCreid not equals to UPDATED_NOTI_CREID
        defaultNoticeInfoShouldBeFound("notiCreid.notEquals=" + UPDATED_NOTI_CREID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid in DEFAULT_NOTI_CREID or UPDATED_NOTI_CREID
        defaultNoticeInfoShouldBeFound("notiCreid.in=" + DEFAULT_NOTI_CREID + "," + UPDATED_NOTI_CREID);

        // Get all the noticeInfoList where notiCreid equals to UPDATED_NOTI_CREID
        defaultNoticeInfoShouldNotBeFound("notiCreid.in=" + UPDATED_NOTI_CREID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid is not null
        defaultNoticeInfoShouldBeFound("notiCreid.specified=true");

        // Get all the noticeInfoList where notiCreid is null
        defaultNoticeInfoShouldNotBeFound("notiCreid.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid contains DEFAULT_NOTI_CREID
        defaultNoticeInfoShouldBeFound("notiCreid.contains=" + DEFAULT_NOTI_CREID);

        // Get all the noticeInfoList where notiCreid contains UPDATED_NOTI_CREID
        defaultNoticeInfoShouldNotBeFound("notiCreid.contains=" + UPDATED_NOTI_CREID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiCreidNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiCreid does not contain DEFAULT_NOTI_CREID
        defaultNoticeInfoShouldNotBeFound("notiCreid.doesNotContain=" + DEFAULT_NOTI_CREID);

        // Get all the noticeInfoList where notiCreid does not contain UPDATED_NOTI_CREID
        defaultNoticeInfoShouldBeFound("notiCreid.doesNotContain=" + UPDATED_NOTI_CREID);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime equals to DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.equals=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime equals to UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.equals=" + UPDATED_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime not equals to DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.notEquals=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime not equals to UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.notEquals=" + UPDATED_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime in DEFAULT_NOTI_MODTIME or UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.in=" + DEFAULT_NOTI_MODTIME + "," + UPDATED_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime equals to UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.in=" + UPDATED_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime is not null
        defaultNoticeInfoShouldBeFound("notiModtime.specified=true");

        // Get all the noticeInfoList where notiModtime is null
        defaultNoticeInfoShouldNotBeFound("notiModtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime is greater than or equal to DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.greaterThanOrEqual=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime is greater than or equal to UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.greaterThanOrEqual=" + UPDATED_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime is less than or equal to DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.lessThanOrEqual=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime is less than or equal to SMALLER_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.lessThanOrEqual=" + SMALLER_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsLessThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime is less than DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.lessThan=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime is less than UPDATED_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.lessThan=" + UPDATED_NOTI_MODTIME);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModtimeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModtime is greater than DEFAULT_NOTI_MODTIME
        defaultNoticeInfoShouldNotBeFound("notiModtime.greaterThan=" + DEFAULT_NOTI_MODTIME);

        // Get all the noticeInfoList where notiModtime is greater than SMALLER_NOTI_MODTIME
        defaultNoticeInfoShouldBeFound("notiModtime.greaterThan=" + SMALLER_NOTI_MODTIME);
    }


    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidIsEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid equals to DEFAULT_NOTI_MODID
        defaultNoticeInfoShouldBeFound("notiModid.equals=" + DEFAULT_NOTI_MODID);

        // Get all the noticeInfoList where notiModid equals to UPDATED_NOTI_MODID
        defaultNoticeInfoShouldNotBeFound("notiModid.equals=" + UPDATED_NOTI_MODID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid not equals to DEFAULT_NOTI_MODID
        defaultNoticeInfoShouldNotBeFound("notiModid.notEquals=" + DEFAULT_NOTI_MODID);

        // Get all the noticeInfoList where notiModid not equals to UPDATED_NOTI_MODID
        defaultNoticeInfoShouldBeFound("notiModid.notEquals=" + UPDATED_NOTI_MODID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidIsInShouldWork() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid in DEFAULT_NOTI_MODID or UPDATED_NOTI_MODID
        defaultNoticeInfoShouldBeFound("notiModid.in=" + DEFAULT_NOTI_MODID + "," + UPDATED_NOTI_MODID);

        // Get all the noticeInfoList where notiModid equals to UPDATED_NOTI_MODID
        defaultNoticeInfoShouldNotBeFound("notiModid.in=" + UPDATED_NOTI_MODID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidIsNullOrNotNull() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid is not null
        defaultNoticeInfoShouldBeFound("notiModid.specified=true");

        // Get all the noticeInfoList where notiModid is null
        defaultNoticeInfoShouldNotBeFound("notiModid.specified=false");
    }
                @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid contains DEFAULT_NOTI_MODID
        defaultNoticeInfoShouldBeFound("notiModid.contains=" + DEFAULT_NOTI_MODID);

        // Get all the noticeInfoList where notiModid contains UPDATED_NOTI_MODID
        defaultNoticeInfoShouldNotBeFound("notiModid.contains=" + UPDATED_NOTI_MODID);
    }

    @Test
    @Transactional
    public void getAllNoticeInfosByNotiModidNotContainsSomething() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        // Get all the noticeInfoList where notiModid does not contain DEFAULT_NOTI_MODID
        defaultNoticeInfoShouldNotBeFound("notiModid.doesNotContain=" + DEFAULT_NOTI_MODID);

        // Get all the noticeInfoList where notiModid does not contain UPDATED_NOTI_MODID
        defaultNoticeInfoShouldBeFound("notiModid.doesNotContain=" + UPDATED_NOTI_MODID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNoticeInfoShouldBeFound(String filter) throws Exception {
        restNoticeInfoMockMvc.perform(get("/api/notice-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticeInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].notiCaseid").value(hasItem(DEFAULT_NOTI_CASEID)))
            .andExpect(jsonPath("$.[*].notiTitle").value(hasItem(DEFAULT_NOTI_TITLE)))
            .andExpect(jsonPath("$.[*].notiContent").value(hasItem(DEFAULT_NOTI_CONTENT)))
            .andExpect(jsonPath("$.[*].notiInfotype").value(hasItem(DEFAULT_NOTI_INFOTYPE)))
            .andExpect(jsonPath("$.[*].notiStarttime").value(hasItem(sameInstant(DEFAULT_NOTI_STARTTIME))))
            .andExpect(jsonPath("$.[*].notiEndtime").value(hasItem(sameInstant(DEFAULT_NOTI_ENDTIME))))
            .andExpect(jsonPath("$.[*].notiStatcode").value(hasItem(DEFAULT_NOTI_STATCODE)))
            .andExpect(jsonPath("$.[*].notiCretime").value(hasItem(sameInstant(DEFAULT_NOTI_CRETIME))))
            .andExpect(jsonPath("$.[*].notiCreid").value(hasItem(DEFAULT_NOTI_CREID)))
            .andExpect(jsonPath("$.[*].notiModtime").value(hasItem(sameInstant(DEFAULT_NOTI_MODTIME))))
            .andExpect(jsonPath("$.[*].notiModid").value(hasItem(DEFAULT_NOTI_MODID)));

        // Check, that the count call also returns 1
        restNoticeInfoMockMvc.perform(get("/api/notice-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNoticeInfoShouldNotBeFound(String filter) throws Exception {
        restNoticeInfoMockMvc.perform(get("/api/notice-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNoticeInfoMockMvc.perform(get("/api/notice-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNoticeInfo() throws Exception {
        // Get the noticeInfo
        restNoticeInfoMockMvc.perform(get("/api/notice-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoticeInfo() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        int databaseSizeBeforeUpdate = noticeInfoRepository.findAll().size();

        // Update the noticeInfo
        NoticeInfo updatedNoticeInfo = noticeInfoRepository.findById(noticeInfo.getId()).get();
        // Disconnect from session so that the updates on updatedNoticeInfo are not directly saved in db
        em.detach(updatedNoticeInfo);
        updatedNoticeInfo
            .notiCaseid(UPDATED_NOTI_CASEID)
            .notiTitle(UPDATED_NOTI_TITLE)
            .notiContent(UPDATED_NOTI_CONTENT)
            .notiInfotype(UPDATED_NOTI_INFOTYPE)
            .notiStarttime(UPDATED_NOTI_STARTTIME)
            .notiEndtime(UPDATED_NOTI_ENDTIME)
            .notiStatcode(UPDATED_NOTI_STATCODE)
            .notiCretime(UPDATED_NOTI_CRETIME)
            .notiCreid(UPDATED_NOTI_CREID)
            .notiModtime(UPDATED_NOTI_MODTIME)
            .notiModid(UPDATED_NOTI_MODID);
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(updatedNoticeInfo);

        restNoticeInfoMockMvc.perform(put("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isOk());

        // Validate the NoticeInfo in the database
        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeUpdate);
        NoticeInfo testNoticeInfo = noticeInfoList.get(noticeInfoList.size() - 1);
        assertThat(testNoticeInfo.getNotiCaseid()).isEqualTo(UPDATED_NOTI_CASEID);
        assertThat(testNoticeInfo.getNotiTitle()).isEqualTo(UPDATED_NOTI_TITLE);
        assertThat(testNoticeInfo.getNotiContent()).isEqualTo(UPDATED_NOTI_CONTENT);
        assertThat(testNoticeInfo.getNotiInfotype()).isEqualTo(UPDATED_NOTI_INFOTYPE);
        assertThat(testNoticeInfo.getNotiStarttime()).isEqualTo(UPDATED_NOTI_STARTTIME);
        assertThat(testNoticeInfo.getNotiEndtime()).isEqualTo(UPDATED_NOTI_ENDTIME);
        assertThat(testNoticeInfo.getNotiStatcode()).isEqualTo(UPDATED_NOTI_STATCODE);
        assertThat(testNoticeInfo.getNotiCretime()).isEqualTo(UPDATED_NOTI_CRETIME);
        assertThat(testNoticeInfo.getNotiCreid()).isEqualTo(UPDATED_NOTI_CREID);
        assertThat(testNoticeInfo.getNotiModtime()).isEqualTo(UPDATED_NOTI_MODTIME);
        assertThat(testNoticeInfo.getNotiModid()).isEqualTo(UPDATED_NOTI_MODID);
    }

    @Test
    @Transactional
    public void updateNonExistingNoticeInfo() throws Exception {
        int databaseSizeBeforeUpdate = noticeInfoRepository.findAll().size();

        // Create the NoticeInfo
        NoticeInfoDTO noticeInfoDTO = noticeInfoMapper.toDto(noticeInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoticeInfoMockMvc.perform(put("/api/notice-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(noticeInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoticeInfo in the database
        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoticeInfo() throws Exception {
        // Initialize the database
        noticeInfoRepository.saveAndFlush(noticeInfo);

        int databaseSizeBeforeDelete = noticeInfoRepository.findAll().size();

        // Delete the noticeInfo
        restNoticeInfoMockMvc.perform(delete("/api/notice-infos/{id}", noticeInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NoticeInfo> noticeInfoList = noticeInfoRepository.findAll();
        assertThat(noticeInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
