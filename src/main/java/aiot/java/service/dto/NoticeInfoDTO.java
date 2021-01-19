package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.NoticeInfo} entity.
 */
public class NoticeInfoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 255)
    private String notiCaseid;

    @NotNull
    @Size(max = 255)
    private String notiTitle;

    @NotNull
    @Size(max = 2048)
    private String notiContent;

    @NotNull
    @Size(max = 255)
    private String notiInfotype;

    @NotNull
    private ZonedDateTime notiStarttime;

    @NotNull
    private ZonedDateTime notiEndtime;

    @NotNull
    @Size(max = 255)
    private String notiStatcode;

    @NotNull
    private ZonedDateTime notiCretime;

    @NotNull
    @Size(max = 255)
    private String notiCreid;

    @NotNull
    private ZonedDateTime notiModtime;

    @NotNull
    @Size(max = 255)
    private String notiModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotiCaseid() {
        return notiCaseid;
    }

    public void setNotiCaseid(String notiCaseid) {
        this.notiCaseid = notiCaseid;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }

    public String getNotiInfotype() {
        return notiInfotype;
    }

    public void setNotiInfotype(String notiInfotype) {
        this.notiInfotype = notiInfotype;
    }

    public ZonedDateTime getNotiStarttime() {
        return notiStarttime;
    }

    public void setNotiStarttime(ZonedDateTime notiStarttime) {
        this.notiStarttime = notiStarttime;
    }

    public ZonedDateTime getNotiEndtime() {
        return notiEndtime;
    }

    public void setNotiEndtime(ZonedDateTime notiEndtime) {
        this.notiEndtime = notiEndtime;
    }

    public String getNotiStatcode() {
        return notiStatcode;
    }

    public void setNotiStatcode(String notiStatcode) {
        this.notiStatcode = notiStatcode;
    }

    public ZonedDateTime getNotiCretime() {
        return notiCretime;
    }

    public void setNotiCretime(ZonedDateTime notiCretime) {
        this.notiCretime = notiCretime;
    }

    public String getNotiCreid() {
        return notiCreid;
    }

    public void setNotiCreid(String notiCreid) {
        this.notiCreid = notiCreid;
    }

    public ZonedDateTime getNotiModtime() {
        return notiModtime;
    }

    public void setNotiModtime(ZonedDateTime notiModtime) {
        this.notiModtime = notiModtime;
    }

    public String getNotiModid() {
        return notiModid;
    }

    public void setNotiModid(String notiModid) {
        this.notiModid = notiModid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoticeInfoDTO noticeInfoDTO = (NoticeInfoDTO) o;
        if (noticeInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticeInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoticeInfoDTO{" +
            "id=" + getId() +
            ", notiCaseid='" + getNotiCaseid() + "'" +
            ", notiTitle='" + getNotiTitle() + "'" +
            ", notiContent='" + getNotiContent() + "'" +
            ", notiInfotype='" + getNotiInfotype() + "'" +
            ", notiStarttime='" + getNotiStarttime() + "'" +
            ", notiEndtime='" + getNotiEndtime() + "'" +
            ", notiStatcode='" + getNotiStatcode() + "'" +
            ", notiCretime='" + getNotiCretime() + "'" +
            ", notiCreid='" + getNotiCreid() + "'" +
            ", notiModtime='" + getNotiModtime() + "'" +
            ", notiModid='" + getNotiModid() + "'" +
            "}";
    }
}
