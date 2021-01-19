package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class DeviceCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceCode.class);
        DeviceCode deviceCode1 = new DeviceCode();
        deviceCode1.setId(1L);
        DeviceCode deviceCode2 = new DeviceCode();
        deviceCode2.setId(deviceCode1.getId());
        assertThat(deviceCode1).isEqualTo(deviceCode2);
        deviceCode2.setId(2L);
        assertThat(deviceCode1).isNotEqualTo(deviceCode2);
        deviceCode1.setId(null);
        assertThat(deviceCode1).isNotEqualTo(deviceCode2);
    }
}
