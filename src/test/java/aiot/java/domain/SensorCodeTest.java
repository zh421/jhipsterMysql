package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class SensorCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SensorCode.class);
        SensorCode sensorCode1 = new SensorCode();
        sensorCode1.setId(1L);
        SensorCode sensorCode2 = new SensorCode();
        sensorCode2.setId(sensorCode1.getId());
        assertThat(sensorCode1).isEqualTo(sensorCode2);
        sensorCode2.setId(2L);
        assertThat(sensorCode1).isNotEqualTo(sensorCode2);
        sensorCode1.setId(null);
        assertThat(sensorCode1).isNotEqualTo(sensorCode2);
    }
}
