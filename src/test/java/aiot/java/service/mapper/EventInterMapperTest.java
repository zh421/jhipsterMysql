package aiot.java.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventInterMapperTest {

    private EventInterMapper eventInterMapper;

    @BeforeEach
    public void setUp() {
        eventInterMapper = new EventInterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventInterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventInterMapper.fromId(null)).isNull();
    }
}
