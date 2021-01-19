package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class EventInterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventInter.class);
        EventInter eventInter1 = new EventInter();
        eventInter1.setId(1L);
        EventInter eventInter2 = new EventInter();
        eventInter2.setId(eventInter1.getId());
        assertThat(eventInter1).isEqualTo(eventInter2);
        eventInter2.setId(2L);
        assertThat(eventInter1).isNotEqualTo(eventInter2);
        eventInter1.setId(null);
        assertThat(eventInter1).isNotEqualTo(eventInter2);
    }
}
