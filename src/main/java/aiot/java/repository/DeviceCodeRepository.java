package aiot.java.repository;

import aiot.java.domain.DeviceCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DeviceCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceCodeRepository extends JpaRepository<DeviceCode, Long>, JpaSpecificationExecutor<DeviceCode> {
}
