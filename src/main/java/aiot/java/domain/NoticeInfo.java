package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A NoticeInfo.
 */
@Entity
@Table(name = "notice_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoticeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_caseid", length = 255, nullable = false)
    private String notiCaseid;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_title", length = 255, nullable = false)
    private String notiTitle;

    @NotNull
    @Size(max = 2048)
    @Column(name = "noti_content", length = 2048, nullable = false)
    private String notiContent;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_infotype", length = 255, nullable = false)
    private String notiInfotype;

    @NotNull
    @Column(name = "noti_starttime", nullable = false)
    private ZonedDateTime notiStarttime;

    @NotNull
    @Column(name = "noti_endtime", nullable = false)
    private ZonedDateTime notiEndtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_statcode", length = 255, nullable = false)
    private String notiStatcode;

    @NotNull
    @Column(name = "noti_cretime", nullable = false)
    private ZonedDateTime notiCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_creid", length = 255, nullable = false)
    private String notiCreid;

    @NotNull
    @Column(name = "noti_modtime", nullable = false)
    private ZonedDateTime notiModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "noti_modid", length = 255, nullable = false)
    private String notiModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotiCaseid() {
        return notiCaseid;
    }

    public NoticeInfo notiCaseid(String notiCaseid) {
        this.notiCaseid = notiCaseid;
        return this;
    }

    public void setNotiCaseid(String notiCaseid) {
        this.notiCaseid = notiCaseid;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public NoticeInfo notiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
        return this;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public String getNotiContent() {
        return notiContent;
    }

    public NoticeInfo notiContent(String notiContent) {
        this.notiContent = notiContent;
        return this;
    }

    public void setNotiContent(String notiContent) {
        this.notiContent = notiContent;
    }

    public String getNotiInfotype() {
        return notiInfotype;
    }

    public NoticeInfo notiInfotype(String notiInfotype) {
        this.notiInfotype = notiInfotype;
        return this;
    }

    public void setNotiInfotype(String notiInfotype) {
        this.notiInfotype = notiInfotype;
    }

    public ZonedDateTime getNotiStarttime() {
        return notiStarttime;
    }

    public NoticeInfo notiStarttime(ZonedDateTime notiStarttime) {
        this.notiStarttime = notiStarttime;
        return this;
    }

    public void setNotiStarttime(ZonedDateTime notiStarttime) {
        this.notiStarttime = notiStarttime;
    }

    public ZonedDateTime getNotiEndtime() {
        return notiEndtime;
    }

    public NoticeInfo notiEndtime(ZonedDateTime notiEndtime) {
        this.notiEndtime = notiEndtime;
        return this;
    }

    public void setNotiEndtime(ZonedDateTime notiEndtime) {
        this.notiEndtime = notiEndtime;
    }

    public String getNotiStatcode() {
        return notiStatcode;
    }

    public NoticeInfo notiStatcode(String notiStatcode) {
        this.notiStatcode = notiStatcode;
        return this;
    }

    public void setNotiStatcode(String notiStatcode) {
        this.notiStatcode = notiStatcode;
    }

    public ZonedDateTime getNotiCretime() {
        return notiCretime;
    }

    public NoticeInfo notiCretime(ZonedDateTime notiCretime) {
        this.notiCretime = notiCretime;
        return this;
    }

    public void setNotiCretime(ZonedDateTime notiCretime) {
        this.notiCretime = notiCretime;
    }

    public String getNotiCreid() {
        return notiCreid;
    }

    public NoticeInfo notiCreid(String notiCreid) {
        this.notiCreid = notiCreid;
        return this;
    }

    public void setNotiCreid(String notiCreid) {
        this.notiCreid = notiCreid;
    }

    public ZonedDateTime getNotiModtime() {
        return notiModtime;
    }

    public NoticeInfo notiModtime(ZonedDateTime notiModtime) {
        this.notiModtime = notiModtime;
        return this;
    }

    public void setNotiModtime(ZonedDateTime notiModtime) {
        this.notiModtime = notiModtime;
    }

    public String getNotiModid() {
        return notiModid;
    }

    public NoticeInfo notiModid(String notiModid) {
        this.notiModid = notiModid;
        return this;
    }

    public void setNotiModid(String notiModid) {
        this.notiModid = notiModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoticeInfo)) {
            return false;
        }
        return id != null && id.equals(((NoticeInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
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
