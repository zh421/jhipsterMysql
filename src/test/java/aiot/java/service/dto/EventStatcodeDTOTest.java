package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class EventStatcodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventStatcodeDTO.class);
        EventStatcodeDTO eventStatcodeDTO1 = new EventStatcodeDTO();
        eventStatcodeDTO1.setId(1L);
        EventStatcodeDTO eventStatcodeDTO2 = new EventStatcodeDTO();
        assertThat(eventStatcodeDTO1).isNotEqualTo(eventStatcodeDTO2);
        eventStatcodeDTO2.setId(eventStatcodeDTO1.getId());
        assertThat(eventStatcodeDTO1).isEqualTo(eventStatcodeDTO2);
        eventStatcodeDTO2.setId(2L);
        assertThat(eventStatcodeDTO1).isNotEqualTo(eventStatcodeDTO2);
        eventStatcodeDTO1.setId(null);
        assertThat(eventStatcodeDTO1).isNotEqualTo(eventStatcodeDTO2);
    }
}
