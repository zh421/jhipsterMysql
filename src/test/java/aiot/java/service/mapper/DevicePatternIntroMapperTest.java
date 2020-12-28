package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DevicePatternIntroMapperTest {

    private DevicePatternIntroMapper devicePatternIntroMapper;

    @BeforeEach
    public void setUp() {
        devicePatternIntroMapper = new DevicePatternIntroMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(devicePatternIntroMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(devicePatternIntroMapper.fromId(null)).isNull();
    }
}
