package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class UnitClassTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitClass.class);
        UnitClass unitClass1 = new UnitClass();
        unitClass1.setId(1L);
        UnitClass unitClass2 = new UnitClass();
        unitClass2.setId(unitClass1.getId());
        assertThat(unitClass1).isEqualTo(unitClass2);
        unitClass2.setId(2L);
        assertThat(unitClass1).isNotEqualTo(unitClass2);
        unitClass1.setId(null);
        assertThat(unitClass1).isNotEqualTo(unitClass2);
    }
}
