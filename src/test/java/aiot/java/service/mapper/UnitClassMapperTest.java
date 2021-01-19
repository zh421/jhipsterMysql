package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitClassMapperTest {

    private UnitClassMapper unitClassMapper;

    @BeforeEach
    public void setUp() {
        unitClassMapper = new UnitClassMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(unitClassMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(unitClassMapper.fromId(null)).isNull();
    }
}
