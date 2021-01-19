package aiot.java.service.mapper;


import aiot.java.domain.*;
import aiot.java.service.dto.NoticeInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoticeInfo} and its DTO {@link NoticeInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeInfoMapper extends EntityMapper<NoticeInfoDTO, NoticeInfo> {



    default NoticeInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoticeInfo noticeInfo = new NoticeInfo();
        noticeInfo.setId(id);
        return noticeInfo;
    }
}
