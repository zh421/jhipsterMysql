package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceCodeMapperTest {

    private DeviceCodeMapper deviceCodeMapper;

    @BeforeEach
    public void setUp() {
        deviceCodeMapper = new DeviceCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deviceCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deviceCodeMapper.fromId(null)).isNull();
    }
}
