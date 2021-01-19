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
 * Criteria class for the {@link aiot.java.domain.UnitClass} entity. This class is used
 * in {@link aiot.java.web.rest.UnitClassResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /unit-classes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UnitClassCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ucCode;

    private StringFilter ucName;

    private ZonedDateTimeFilter ucCretime;

    private StringFilter ucCreid;

    private ZonedDateTimeFilter ucModtime;

    private StringFilter ucModid;

    public UnitClassCriteria() {
    }

    public UnitClassCriteria(UnitClassCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ucCode = other.ucCode == null ? null : other.ucCode.copy();
        this.ucName = other.ucName == null ? null : other.ucName.copy();
        this.ucCretime = other.ucCretime == null ? null : other.ucCretime.copy();
        this.ucCreid = other.ucCreid == null ? null : other.ucCreid.copy();
        this.ucModtime = other.ucModtime == null ? null : other.ucModtime.copy();
        this.ucModid = other.ucModid == null ? null : other.ucModid.copy();
    }

    @Override
    public UnitClassCriteria copy() {
        return new UnitClassCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUcCode() {
        return ucCode;
    }

    public void setUcCode(StringFilter ucCode) {
        this.ucCode = ucCode;
    }

    public StringFilter getUcName() {
        return ucName;
    }

    public void setUcName(StringFilter ucName) {
        this.ucName = ucName;
    }

    public ZonedDateTimeFilter getUcCretime() {
        return ucCretime;
    }

    public void setUcCretime(ZonedDateTimeFilter ucCretime) {
        this.ucCretime = ucCretime;
    }

    public StringFilter getUcCreid() {
        return ucCreid;
    }

    public void setUcCreid(StringFilter ucCreid) {
        this.ucCreid = ucCreid;
    }

    public ZonedDateTimeFilter getUcModtime() {
        return ucModtime;
    }

    public void setUcModtime(ZonedDateTimeFilter ucModtime) {
        this.ucModtime = ucModtime;
    }

    public StringFilter getUcModid() {
        return ucModid;
    }

    public void setUcModid(StringFilter ucModid) {
        this.ucModid = ucModid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UnitClassCriteria that = (UnitClassCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ucCode, that.ucCode) &&
            Objects.equals(ucName, that.ucName) &&
            Objects.equals(ucCretime, that.ucCretime) &&
            Objects.equals(ucCreid, that.ucCreid) &&
            Objects.equals(ucModtime, that.ucModtime) &&
            Objects.equals(ucModid, that.ucModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ucCode,
        ucName,
        ucCretime,
        ucCreid,
        ucModtime,
        ucModid
        );
    }

    @Override
    public String toString() {
        return "UnitClassCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ucCode != null ? "ucCode=" + ucCode + ", " : "") +
                (ucName != null ? "ucName=" + ucName + ", " : "") +
                (ucCretime != null ? "ucCretime=" + ucCretime + ", " : "") +
                (ucCreid != null ? "ucCreid=" + ucCreid + ", " : "") +
                (ucModtime != null ? "ucModtime=" + ucModtime + ", " : "") +
                (ucModid != null ? "ucModid=" + ucModid + ", " : "") +
            "}";
    }

}
