package aiot.java.repository;

import aiot.java.domain.EventInter;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventInter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventInterRepository extends JpaRepository<EventInter, Long>, JpaSpecificationExecutor<EventInter> {
}
