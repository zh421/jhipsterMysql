package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.EventStatcodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventStatcode} and its DTO {@link EventStatcodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventStatcodeMapper extends EntityMapper<EventStatcodeDTO, EventStatcode> {



    default EventStatcode fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventStatcode eventStatcode = new EventStatcode();
        eventStatcode.setId(id);
        return eventStatcode;
    }
}
