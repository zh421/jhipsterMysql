package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.SensorCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SensorCode} and its DTO {@link SensorCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SensorCodeMapper extends EntityMapper<SensorCodeDTO, SensorCode> {



    default SensorCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        SensorCode sensorCode = new SensorCode();
        sensorCode.setId(id);
        return sensorCode;
    }
}
