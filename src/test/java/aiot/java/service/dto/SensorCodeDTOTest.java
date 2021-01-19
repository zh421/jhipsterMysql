package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class SensorCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SensorCodeDTO.class);
        SensorCodeDTO sensorCodeDTO1 = new SensorCodeDTO();
        sensorCodeDTO1.setId(1L);
        SensorCodeDTO sensorCodeDTO2 = new SensorCodeDTO();
        assertThat(sensorCodeDTO1).isNotEqualTo(sensorCodeDTO2);
        sensorCodeDTO2.setId(sensorCodeDTO1.getId());
        assertThat(sensorCodeDTO1).isEqualTo(sensorCodeDTO2);
        sensorCodeDTO2.setId(2L);
        assertThat(sensorCodeDTO1).isNotEqualTo(sensorCodeDTO2);
        sensorCodeDTO1.setId(null);
        assertThat(sensorCodeDTO1).isNotEqualTo(sensorCodeDTO2);
    }
}
