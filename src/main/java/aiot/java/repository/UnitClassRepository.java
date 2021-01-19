package aiot.java.repository;

import aiot.java.domain.UnitClass;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UnitClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitClassRepository extends JpaRepository<UnitClass, Long>, JpaSpecificationExecutor<UnitClass> {
}
