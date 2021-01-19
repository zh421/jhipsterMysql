package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.UnitClassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnitClass} and its DTO {@link UnitClassDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UnitClassMapper extends EntityMapper<UnitClassDTO, UnitClass> {



    default UnitClass fromId(Long id) {
        if (id == null) {
            return null;
        }
        UnitClass unitClass = new UnitClass();
        unitClass.setId(id);
        return unitClass;
    }
}
