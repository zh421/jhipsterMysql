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
 * Criteria class for the {@link aiot.java.domain.DevicePatternIntro} entity. This class is used
 * in {@link aiot.java.web.rest.DevicePatternIntroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /device-pattern-intros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DevicePatternIntroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter devicepatternId;

    private StringFilter devicepatternCode;

    private StringFilter memo;

    private StringFilter creuser;

    private ZonedDateTimeFilter cretime;

    private StringFilter moduser;

    private ZonedDateTimeFilter modtime;

    public DevicePatternIntroCriteria() {
    }

    public DevicePatternIntroCriteria(DevicePatternIntroCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.devicepatternId = other.devicepatternId == null ? null : other.devicepatternId.copy();
        this.devicepatternCode = other.devicepatternCode == null ? null : other.devicepatternCode.copy();
        this.memo = other.memo == null ? null : other.memo.copy();
        this.creuser = other.creuser == null ? null : other.creuser.copy();
        this.cretime = other.cretime == null ? null : other.cretime.copy();
        this.moduser = other.moduser == null ? null : other.moduser.copy();
        this.modtime = other.modtime == null ? null : other.modtime.copy();
    }

    @Override
    public DevicePatternIntroCriteria copy() {
        return new DevicePatternIntroCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDevicepatternId() {
        return devicepatternId;
    }

    public void setDevicepatternId(StringFilter devicepatternId) {
        this.devicepatternId = devicepatternId;
    }

    public StringFilter getDevicepatternCode() {
        return devicepatternCode;
    }

    public void setDevicepatternCode(StringFilter devicepatternCode) {
        this.devicepatternCode = devicepatternCode;
    }

    public StringFilter getMemo() {
        return memo;
    }

    public void setMemo(StringFilter memo) {
        this.memo = memo;
    }

    public StringFilter getCreuser() {
        return creuser;
    }

    public void setCreuser(StringFilter creuser) {
        this.creuser = creuser;
    }

    public ZonedDateTimeFilter getCretime() {
        return cretime;
    }

    public void setCretime(ZonedDateTimeFilter cretime) {
        this.cretime = cretime;
    }

    public StringFilter getModuser() {
        return moduser;
    }

    public void setModuser(StringFilter moduser) {
        this.moduser = moduser;
    }

    public ZonedDateTimeFilter getModtime() {
        return modtime;
    }

    public void setModtime(ZonedDateTimeFilter modtime) {
        this.modtime = modtime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DevicePatternIntroCriteria that = (DevicePatternIntroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(devicepatternId, that.devicepatternId) &&
            Objects.equals(devicepatternCode, that.devicepatternCode) &&
            Objects.equals(memo, that.memo) &&
            Objects.equals(creuser, that.creuser) &&
            Objects.equals(cretime, that.cretime) &&
            Objects.equals(moduser, that.moduser) &&
            Objects.equals(modtime, that.modtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        devicepatternId,
        devicepatternCode,
        memo,
        creuser,
        cretime,
        moduser,
        modtime
        );
    }

    @Override
    public String toString() {
        return "DevicePatternIntroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (devicepatternId != null ? "devicepatternId=" + devicepatternId + ", " : "") +
                (devicepatternCode != null ? "devicepatternCode=" + devicepatternCode + ", " : "") +
                (memo != null ? "memo=" + memo + ", " : "") +
                (creuser != null ? "creuser=" + creuser + ", " : "") +
                (cretime != null ? "cretime=" + cretime + ", " : "") +
                (moduser != null ? "moduser=" + moduser + ", " : "") +
                (modtime != null ? "modtime=" + modtime + ", " : "") +
            "}";
    }

}
