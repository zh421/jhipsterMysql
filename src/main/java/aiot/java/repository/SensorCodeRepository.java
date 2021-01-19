package aiot.java.repository;

import aiot.java.domain.SensorCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SensorCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SensorCodeRepository extends JpaRepository<SensorCode, Long>, JpaSpecificationExecutor<SensorCode> {
}
