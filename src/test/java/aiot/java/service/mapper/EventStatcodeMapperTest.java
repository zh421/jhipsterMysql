package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventStatcodeMapperTest {

    private EventStatcodeMapper eventStatcodeMapper;

    @BeforeEach
    public void setUp() {
        eventStatcodeMapper = new EventStatcodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventStatcodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventStatcodeMapper.fromId(null)).isNull();
    }
}
