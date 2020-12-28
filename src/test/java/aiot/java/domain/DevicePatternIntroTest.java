package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class DevicePatternIntroTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DevicePatternIntro.class);
        DevicePatternIntro devicePatternIntro1 = new DevicePatternIntro();
        devicePatternIntro1.setId(1L);
        DevicePatternIntro devicePatternIntro2 = new DevicePatternIntro();
        devicePatternIntro2.setId(devicePatternIntro1.getId());
        assertThat(devicePatternIntro1).isEqualTo(devicePatternIntro2);
        devicePatternIntro2.setId(2L);
        assertThat(devicePatternIntro1).isNotEqualTo(devicePatternIntro2);
        devicePatternIntro1.setId(null);
        assertThat(devicePatternIntro1).isNotEqualTo(devicePatternIntro2);
    }
}
