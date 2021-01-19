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
 * Criteria class for the {@link aiot.java.domain.SensorCode} entity. This class is used
 * in {@link aiot.java.web.rest.SensorCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sensor-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SensorCodeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter scCode;

    private StringFilter scName;

    private ZonedDateTimeFilter scCretime;

    private StringFilter scCreid;

    private ZonedDateTimeFilter scModtime;

    private StringFilter scModid;

    public SensorCodeCriteria() {
    }

    public SensorCodeCriteria(SensorCodeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.scCode = other.scCode == null ? null : other.scCode.copy();
        this.scName = other.scName == null ? null : other.scName.copy();
        this.scCretime = other.scCretime == null ? null : other.scCretime.copy();
        this.scCreid = other.scCreid == null ? null : other.scCreid.copy();
        this.scModtime = other.scModtime == null ? null : other.scModtime.copy();
        this.scModid = other.scModid == null ? null : other.scModid.copy();
    }

    @Override
    public SensorCodeCriteria copy() {
        return new SensorCodeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getScCode() {
        return scCode;
    }

    public void setScCode(StringFilter scCode) {
        this.scCode = scCode;
    }

    public StringFilter getScName() {
        return scName;
    }

    public void setScName(StringFilter scName) {
        this.scName = scName;
    }

    public ZonedDateTimeFilter getScCretime() {
        return scCretime;
    }

    public void setScCretime(ZonedDateTimeFilter scCretime) {
        this.scCretime = scCretime;
    }

    public StringFilter getScCreid() {
        return scCreid;
    }

    public void setScCreid(StringFilter scCreid) {
        this.scCreid = scCreid;
    }

    public ZonedDateTimeFilter getScModtime() {
        return scModtime;
    }

    public void setScModtime(ZonedDateTimeFilter scModtime) {
        this.scModtime = scModtime;
    }

    public StringFilter getScModid() {
        return scModid;
    }

    public void setScModid(StringFilter scModid) {
        this.scModid = scModid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SensorCodeCriteria that = (SensorCodeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(scCode, that.scCode) &&
            Objects.equals(scName, that.scName) &&
            Objects.equals(scCretime, that.scCretime) &&
            Objects.equals(scCreid, that.scCreid) &&
            Objects.equals(scModtime, that.scModtime) &&
            Objects.equals(scModid, that.scModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        scCode,
        scName,
        scCretime,
        scCreid,
        scModtime,
        scModid
        );
    }

    @Override
    public String toString() {
        return "SensorCodeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (scCode != null ? "scCode=" + scCode + ", " : "") +
                (scName != null ? "scName=" + scName + ", " : "") +
                (scCretime != null ? "scCretime=" + scCretime + ", " : "") +
                (scCreid != null ? "scCreid=" + scCreid + ", " : "") +
                (scModtime != null ? "scModtime=" + scModtime + ", " : "") +
                (scModid != null ? "scModid=" + scModid + ", " : "") +
            "}";
    }

}
