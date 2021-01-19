package aiot.java.service;

import aiot.java.domain.NoticeInfo;
import aiot.java.repository.NoticeInfoRepository;
import aiot.java.service.dto.NoticeInfoDTO;
import aiot.java.service.mapper.NoticeInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NoticeInfo}.
 */
@Service
@Transactional
public class NoticeInfoService {

    private final Logger log = LoggerFactory.getLogger(NoticeInfoService.class);

    private final NoticeInfoRepository noticeInfoRepository;

    private final NoticeInfoMapper noticeInfoMapper;

    public NoticeInfoService(NoticeInfoRepository noticeInfoRepository, NoticeInfoMapper noticeInfoMapper) {
        this.noticeInfoRepository = noticeInfoRepository;
        this.noticeInfoMapper = noticeInfoMapper;
    }

    /**
     * Save a noticeInfo.
     *
     * @param noticeInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public NoticeInfoDTO save(NoticeInfoDTO noticeInfoDTO) {
        log.debug("Request to save NoticeInfo : {}", noticeInfoDTO);
        NoticeInfo noticeInfo = noticeInfoMapper.toEntity(noticeInfoDTO);
        noticeInfo = noticeInfoRepository.save(noticeInfo);
        return noticeInfoMapper.toDto(noticeInfo);
    }

    /**
     * Get all the noticeInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NoticeInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoticeInfos");
        return noticeInfoRepository.findAll(pageable)
            .map(noticeInfoMapper::toDto);
    }

    /**
     * Get one noticeInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NoticeInfoDTO> findOne(Long id) {
        log.debug("Request to get NoticeInfo : {}", id);
        return noticeInfoRepository.findById(id)
            .map(noticeInfoMapper::toDto);
    }

    /**
     * Delete the noticeInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NoticeInfo : {}", id);
        noticeInfoRepository.deleteById(id);
    }
}
