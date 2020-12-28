package aiot.java.repository;

import aiot.java.domain.DevicePatternIntro;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DevicePatternIntro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevicePatternIntroRepository extends JpaRepository<DevicePatternIntro, Long>, JpaSpecificationExecutor<DevicePatternIntro> {
}
