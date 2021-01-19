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
 * Criteria class for the {@link aiot.java.domain.DeviceCode} entity. This class is used
 * in {@link aiot.java.web.rest.DeviceCodeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /device-codes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DeviceCodeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter dviCode;

    private StringFilter dviName;

    private ZonedDateTimeFilter dviCretime;

    private StringFilter dviCreid;

    private ZonedDateTimeFilter dviModtime;

    private StringFilter dviModid;

    public DeviceCodeCriteria() {
    }

    public DeviceCodeCriteria(DeviceCodeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dviCode = other.dviCode == null ? null : other.dviCode.copy();
        this.dviName = other.dviName == null ? null : other.dviName.copy();
        this.dviCretime = other.dviCretime == null ? null : other.dviCretime.copy();
        this.dviCreid = other.dviCreid == null ? null : other.dviCreid.copy();
        this.dviModtime = other.dviModtime == null ? null : other.dviModtime.copy();
        this.dviModid = other.dviModid == null ? null : other.dviModid.copy();
    }

    @Override
    public DeviceCodeCriteria copy() {
        return new DeviceCodeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDviCode() {
        return dviCode;
    }

    public void setDviCode(StringFilter dviCode) {
        this.dviCode = dviCode;
    }

    public StringFilter getDviName() {
        return dviName;
    }

    public void setDviName(StringFilter dviName) {
        this.dviName = dviName;
    }

    public ZonedDateTimeFilter getDviCretime() {
        return dviCretime;
    }

    public void setDviCretime(ZonedDateTimeFilter dviCretime) {
        this.dviCretime = dviCretime;
    }

    public StringFilter getDviCreid() {
        return dviCreid;
    }

    public void setDviCreid(StringFilter dviCreid) {
        this.dviCreid = dviCreid;
    }

    public ZonedDateTimeFilter getDviModtime() {
        return dviModtime;
    }

    public void setDviModtime(ZonedDateTimeFilter dviModtime) {
        this.dviModtime = dviModtime;
    }

    public StringFilter getDviModid() {
        return dviModid;
    }

    public void setDviModid(StringFilter dviModid) {
        this.dviModid = dviModid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DeviceCodeCriteria that = (DeviceCodeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dviCode, that.dviCode) &&
            Objects.equals(dviName, that.dviName) &&
            Objects.equals(dviCretime, that.dviCretime) &&
            Objects.equals(dviCreid, that.dviCreid) &&
            Objects.equals(dviModtime, that.dviModtime) &&
            Objects.equals(dviModid, that.dviModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dviCode,
        dviName,
        dviCretime,
        dviCreid,
        dviModtime,
        dviModid
        );
    }

    @Override
    public String toString() {
        return "DeviceCodeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dviCode != null ? "dviCode=" + dviCode + ", " : "") +
                (dviName != null ? "dviName=" + dviName + ", " : "") +
                (dviCretime != null ? "dviCretime=" + dviCretime + ", " : "") +
                (dviCreid != null ? "dviCreid=" + dviCreid + ", " : "") +
                (dviModtime != null ? "dviModtime=" + dviModtime + ", " : "") +
                (dviModid != null ? "dviModid=" + dviModid + ", " : "") +
            "}";
    }

}
