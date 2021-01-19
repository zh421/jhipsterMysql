package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.EventInterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventInter} and its DTO {@link EventInterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventInterMapper extends EntityMapper<EventInterDTO, EventInter> {



    default EventInter fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventInter eventInter = new EventInter();
        eventInter.setId(id);
        return eventInter;
    }
}
