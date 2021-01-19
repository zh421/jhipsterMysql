package aiot.java.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import aiot.java.web.rest.TestUtil;

public class NoticeInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticeInfo.class);
        NoticeInfo noticeInfo1 = new NoticeInfo();
        noticeInfo1.setId(1L);
        NoticeInfo noticeInfo2 = new NoticeInfo();
        noticeInfo2.setId(noticeInfo1.getId());
        assertThat(noticeInfo1).isEqualTo(noticeInfo2);
        noticeInfo2.setId(2L);
        assertThat(noticeInfo1).isNotEqualTo(noticeInfo2);
        noticeInfo1.setId(null);
        assertThat(noticeInfo1).isNotEqualTo(noticeInfo2);
    }
}
