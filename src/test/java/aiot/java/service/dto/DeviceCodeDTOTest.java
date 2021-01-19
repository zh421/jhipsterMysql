package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class DeviceCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceCodeDTO.class);
        DeviceCodeDTO deviceCodeDTO1 = new DeviceCodeDTO();
        deviceCodeDTO1.setId(1L);
        DeviceCodeDTO deviceCodeDTO2 = new DeviceCodeDTO();
        assertThat(deviceCodeDTO1).isNotEqualTo(deviceCodeDTO2);
        deviceCodeDTO2.setId(deviceCodeDTO1.getId());
        assertThat(deviceCodeDTO1).isEqualTo(deviceCodeDTO2);
        deviceCodeDTO2.setId(2L);
        assertThat(deviceCodeDTO1).isNotEqualTo(deviceCodeDTO2);
        deviceCodeDTO1.setId(null);
        assertThat(deviceCodeDTO1).isNotEqualTo(deviceCodeDTO2);
    }
}
