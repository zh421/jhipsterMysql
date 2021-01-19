package aiot.java.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link aiot.java.domain.NoticeInfo} entity. This class is used
 * in {@link aiot.java.web.rest.NoticeInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /notice-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NoticeInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter notiCaseid;

    private StringFilter notiTitle;

    private StringFilter notiContent;

    private StringFilter notiInfotype;

    private ZonedDateTimeFilter notiStarttime;

    private ZonedDateTimeFilter notiEndtime;

    private StringFilter notiStatcode;

    private ZonedDateTimeFilter notiCretime;

    private StringFilter notiCreid;

    private ZonedDateTimeFilter notiModtime;

    private StringFilter notiModid;

    public NoticeInfoCriteria() {
    }

    public NoticeInfoCriteria(NoticeInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.notiCaseid = other.notiCaseid == null ? null : other.notiCaseid.copy();
        this.notiTitle = other.notiTitle == null ? null : other.notiTitle.copy();
        this.notiContent = other.notiContent == null ? null : other.notiContent.copy();
        this.notiInfotype = other.notiInfotype == null ? null : other.notiInfotype.copy();
        this.notiStarttime = other.notiStarttime == null ? null : other.notiStarttime.copy();
        this.notiEndtime = other.notiEndtime == null ? null : other.notiEndtime.copy();
        this.notiStatcode = other.notiStatcode == null ? null : other.notiStatcode.copy();
        this.notiCretime = other.notiCretime == null ? null : other.notiCretime.copy();
        this.notiCreid = other.notiCreid == null ? null : other.notiCreid.copy();
        this.notiModtime = other.notiModtime == null ? null : other.notiModtime.copy();
        this.notiModid = other.notiModid == null ? null : other.notiModid.copy();
    }

    @Override
    public NoticeInfoCriteria copy() {
        return new NoticeInfoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNotiCaseid() {
        return notiCaseid;
    }

    public void setNotiCaseid(StringFilter notiCaseid) {
        this.notiCaseid = notiCaseid;
    }

    public StringFilter getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(StringFilter notiTitle) {
        this.notiTitle = notiTitle;
    }

    public StringFilter getNotiContent() {
        return notiContent;
    }

    public void setNotiContent(StringFilter notiContent) {
        this.notiContent = notiContent;
    }

    public StringFilter getNotiInfotype() {
        return notiInfotype;
    }

    public void setNotiInfotype(StringFilter notiInfotype) {
        this.notiInfotype = notiInfotype;
    }

    public ZonedDateTimeFilter getNotiStarttime() {
        return notiStarttime;
    }

    public void setNotiStarttime(ZonedDateTimeFilter notiStarttime) {
        this.notiStarttime = notiStarttime;
    }

    public ZonedDateTimeFilter getNotiEndtime() {
        return notiEndtime;
    }

    public void setNotiEndtime(ZonedDateTimeFilter notiEndtime) {
        this.notiEndtime = notiEndtime;
    }

    public StringFilter getNotiStatcode() {
        return notiStatcode;
    }

    public void setNotiStatcode(StringFilter notiStatcode) {
        this.notiStatcode = notiStatcode;
    }

    public ZonedDateTimeFilter getNotiCretime() {
        return notiCretime;
    }

    public void setNotiCretime(ZonedDateTimeFilter notiCretime) {
        this.notiCretime = notiCretime;
    }

    public StringFilter getNotiCreid() {
        return notiCreid;
    }

    public void setNotiCreid(StringFilter notiCreid) {
        this.notiCreid = notiCreid;
    }

    public ZonedDateTimeFilter getNotiModtime() {
        return notiModtime;
    }

    public void setNotiModtime(ZonedDateTimeFilter notiModtime) {
        this.notiModtime = notiModtime;
    }

    public StringFilter getNotiModid() {
        return notiModid;
    }

    public void setNotiModid(StringFilter notiModid) {
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
        final NoticeInfoCriteria that = (NoticeInfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(notiCaseid, that.notiCaseid) &&
            Objects.equals(notiTitle, that.notiTitle) &&
            Objects.equals(notiContent, that.notiContent) &&
            Objects.equals(notiInfotype, that.notiInfotype) &&
            Objects.equals(notiStarttime, that.notiStarttime) &&
            Objects.equals(notiEndtime, that.notiEndtime) &&
            Objects.equals(notiStatcode, that.notiStatcode) &&
            Objects.equals(notiCretime, that.notiCretime) &&
            Objects.equals(notiCreid, that.notiCreid) &&
            Objects.equals(notiModtime, that.notiModtime) &&
            Objects.equals(notiModid, that.notiModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        notiCaseid,
        notiTitle,
        notiContent,
        notiInfotype,
        notiStarttime,
        notiEndtime,
        notiStatcode,
        notiCretime,
        notiCreid,
        notiModtime,
        notiModid
        );
    }

    @Override
    public String toString() {
        return "NoticeInfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (notiCaseid != null ? "notiCaseid=" + notiCaseid + ", " : "") +
                (notiTitle != null ? "notiTitle=" + notiTitle + ", " : "") +
                (notiContent != null ? "notiContent=" + notiContent + ", " : "") +
                (notiInfotype != null ? "notiInfotype=" + notiInfotype + ", " : "") +
                (notiStarttime != null ? "notiStarttime=" + notiStarttime + ", " : "") +
                (notiEndtime != null ? "notiEndtime=" + notiEndtime + ", " : "") +
                (notiStatcode != null ? "notiStatcode=" + notiStatcode + ", " : "") +
                (notiCretime != null ? "notiCretime=" + notiCretime + ", " : "") +
                (notiCreid != null ? "notiCreid=" + notiCreid + ", " : "") +
                (notiModtime != null ? "notiModtime=" + notiModtime + ", " : "") +
                (notiModid != null ? "notiModid=" + notiModid + ", " : "") +
            "}";
    }

}
