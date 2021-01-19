package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class EventInterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventInterDTO.class);
        EventInterDTO eventInterDTO1 = new EventInterDTO();
        eventInterDTO1.setId(1L);
        EventInterDTO eventInterDTO2 = new EventInterDTO();
        assertThat(eventInterDTO1).isNotEqualTo(eventInterDTO2);
        eventInterDTO2.setId(eventInterDTO1.getId());
        assertThat(eventInterDTO1).isEqualTo(eventInterDTO2);
        eventInterDTO2.setId(2L);
        assertThat(eventInterDTO1).isNotEqualTo(eventInterDTO2);
        eventInterDTO1.setId(null);
        assertThat(eventInterDTO1).isNotEqualTo(eventInterDTO2);
    }
}
