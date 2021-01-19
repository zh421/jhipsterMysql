package aiot.java.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class NoticeInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticeInfoDTO.class);
        NoticeInfoDTO noticeInfoDTO1 = new NoticeInfoDTO();
        noticeInfoDTO1.setId(1L);
        NoticeInfoDTO noticeInfoDTO2 = new NoticeInfoDTO();
        assertThat(noticeInfoDTO1).isNotEqualTo(noticeInfoDTO2);
        noticeInfoDTO2.setId(noticeInfoDTO1.getId());
        assertThat(noticeInfoDTO1).isEqualTo(noticeInfoDTO2);
        noticeInfoDTO2.setId(2L);
        assertThat(noticeInfoDTO1).isNotEqualTo(noticeInfoDTO2);
        noticeInfoDTO1.setId(null);
        assertThat(noticeInfoDTO1).isNotEqualTo(noticeInfoDTO2);
    }
}
