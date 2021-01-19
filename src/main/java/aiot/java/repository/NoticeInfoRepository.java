package aiot.java.repository;

import aiot.java.domain.NoticeInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NoticeInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoticeInfoRepository extends JpaRepository<NoticeInfo, Long>, JpaSpecificationExecutor<NoticeInfo> {
}
