package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class EventStatcodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventStatcode.class);
        EventStatcode eventStatcode1 = new EventStatcode();
        eventStatcode1.setId(1L);
        EventStatcode eventStatcode2 = new EventStatcode();
        eventStatcode2.setId(eventStatcode1.getId());
        assertThat(eventStatcode1).isEqualTo(eventStatcode2);
        eventStatcode2.setId(2L);
        assertThat(eventStatcode1).isNotEqualTo(eventStatcode2);
        eventStatcode1.setId(null);
        assertThat(eventStatcode1).isNotEqualTo(eventStatcode2);
    }
}
