package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class DevicePatternIntroDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DevicePatternIntroDTO.class);
        DevicePatternIntroDTO devicePatternIntroDTO1 = new DevicePatternIntroDTO();
        devicePatternIntroDTO1.setId(1L);
        DevicePatternIntroDTO devicePatternIntroDTO2 = new DevicePatternIntroDTO();
        assertThat(devicePatternIntroDTO1).isNotEqualTo(devicePatternIntroDTO2);
        devicePatternIntroDTO2.setId(devicePatternIntroDTO1.getId());
        assertThat(devicePatternIntroDTO1).isEqualTo(devicePatternIntroDTO2);
        devicePatternIntroDTO2.setId(2L);
        assertThat(devicePatternIntroDTO1).isNotEqualTo(devicePatternIntroDTO2);
        devicePatternIntroDTO1.setId(null);
        assertThat(devicePatternIntroDTO1).isNotEqualTo(devicePatternIntroDTO2);
    }
}
