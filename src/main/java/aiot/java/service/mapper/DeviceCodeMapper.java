package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.DeviceCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeviceCode} and its DTO {@link DeviceCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeviceCodeMapper extends EntityMapper<DeviceCodeDTO, DeviceCode> {



    default DeviceCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeviceCode deviceCode = new DeviceCode();
        deviceCode.setId(id);
        return deviceCode;
    }
}
