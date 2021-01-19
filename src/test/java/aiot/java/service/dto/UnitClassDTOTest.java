package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class UnitClassDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitClassDTO.class);
        UnitClassDTO unitClassDTO1 = new UnitClassDTO();
        unitClassDTO1.setId(1L);
        UnitClassDTO unitClassDTO2 = new UnitClassDTO();
        assertThat(unitClassDTO1).isNotEqualTo(unitClassDTO2);
        unitClassDTO2.setId(unitClassDTO1.getId());
        assertThat(unitClassDTO1).isEqualTo(unitClassDTO2);
        unitClassDTO2.setId(2L);
        assertThat(unitClassDTO1).isNotEqualTo(unitClassDTO2);
        unitClassDTO1.setId(null);
        assertThat(unitClassDTO1).isNotEqualTo(unitClassDTO2);
    }
}
