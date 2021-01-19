package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SensorCodeMapperTest {

    private SensorCodeMapper sensorCodeMapper;

    @BeforeEach
    public void setUp() {
        sensorCodeMapper = new SensorCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sensorCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sensorCodeMapper.fromId(null)).isNull();
    }
}
