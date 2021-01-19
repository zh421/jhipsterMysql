package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NoticeInfoMapperTest {

    private NoticeInfoMapper noticeInfoMapper;

    @BeforeEach
    public void setUp() {
        noticeInfoMapper = new NoticeInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(noticeInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(noticeInfoMapper.fromId(null)).isNull();
    }
}
