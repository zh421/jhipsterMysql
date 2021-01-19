package aiot.java.repository;

import aiot.java.domain.EventStatcode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventStatcode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventStatcodeRepository extends JpaRepository<EventStatcode, Long>, JpaSpecificationExecutor<EventStatcode> {
}
