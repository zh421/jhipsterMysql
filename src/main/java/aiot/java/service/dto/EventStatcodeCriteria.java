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
 * Criteria class for the {@link aiot.java.domain.EventStatcode} entity. This class is used
 * in {@link aiot.java.web.rest.EventStatcodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /event-statcodes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EventStatcodeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter esCode;

    private StringFilter esName;

    private ZonedDateTimeFilter esCretime;

    private StringFilter esCreid;

    private ZonedDateTimeFilter esModtime;

    private StringFilter esModid;

    public EventStatcodeCriteria() {
    }

    public EventStatcodeCriteria(EventStatcodeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.esCode = other.esCode == null ? null : other.esCode.copy();
        this.esName = other.esName == null ? null : other.esName.copy();
        this.esCretime = other.esCretime == null ? null : other.esCretime.copy();
        this.esCreid = other.esCreid == null ? null : other.esCreid.copy();
        this.esModtime = other.esModtime == null ? null : other.esModtime.copy();
        this.esModid = other.esModid == null ? null : other.esModid.copy();
    }

    @Override
    public EventStatcodeCriteria copy() {
        return new EventStatcodeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEsCode() {
        return esCode;
    }

    public void setEsCode(StringFilter esCode) {
        this.esCode = esCode;
    }

    public StringFilter getEsName() {
        return esName;
    }

    public void setEsName(StringFilter esName) {
        this.esName = esName;
    }

    public ZonedDateTimeFilter getEsCretime() {
        return esCretime;
    }

    public void setEsCretime(ZonedDateTimeFilter esCretime) {
        this.esCretime = esCretime;
    }

    public StringFilter getEsCreid() {
        return esCreid;
    }

    public void setEsCreid(StringFilter esCreid) {
        this.esCreid = esCreid;
    }

    public ZonedDateTimeFilter getEsModtime() {
        return esModtime;
    }

    public void setEsModtime(ZonedDateTimeFilter esModtime) {
        this.esModtime = esModtime;
    }

    public StringFilter getEsModid() {
        return esModid;
    }

    public void setEsModid(StringFilter esModid) {
        this.esModid = esModid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EventStatcodeCriteria that = (EventStatcodeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(esCode, that.esCode) &&
            Objects.equals(esName, that.esName) &&
            Objects.equals(esCretime, that.esCretime) &&
            Objects.equals(esCreid, that.esCreid) &&
            Objects.equals(esModtime, that.esModtime) &&
            Objects.equals(esModid, that.esModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        esCode,
        esName,
        esCretime,
        esCreid,
        esModtime,
        esModid
        );
    }

    @Override
    public String toString() {
        return "EventStatcodeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (esCode != null ? "esCode=" + esCode + ", " : "") +
                (esName != null ? "esName=" + esName + ", " : "") +
                (esCretime != null ? "esCretime=" + esCretime + ", " : "") +
                (esCreid != null ? "esCreid=" + esCreid + ", " : "") +
                (esModtime != null ? "esModtime=" + esModtime + ", " : "") +
                (esModid != null ? "esModid=" + esModid + ", " : "") +
            "}";
    }

}
