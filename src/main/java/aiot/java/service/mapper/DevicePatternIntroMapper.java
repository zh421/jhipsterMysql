package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.DevicePatternIntroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DevicePatternIntro} and its DTO {@link DevicePatternIntroDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DevicePatternIntroMapper extends EntityMapper<DevicePatternIntroDTO, DevicePatternIntro> {



    default DevicePatternIntro fromId(Long id) {
        if (id == null) {
            return null;
        }
        DevicePatternIntro devicePatternIntro = new DevicePatternIntro();
        devicePatternIntro.setId(id);
        return devicePatternIntro;
    }
}
