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
 * Criteria class for the {@link aiot.java.domain.Unit} entity. This class is used
 * in {@link aiot.java.web.rest.UnitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /units?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UnitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter unitUcCode;

    private StringFilter unitCode;

    private StringFilter unitName;

    private StringFilter unitAddr;

    private StringFilter unitLongitude;

    private StringFilter unitLatitude;

    private StringFilter unitPic;

    private StringFilter unitPhone;

    private StringFilter unitEmail;

    private ZonedDateTimeFilter unitCretime;

    private StringFilter unitCreid;

    private ZonedDateTimeFilter unitModtime;

    private StringFilter unitModid;

    private StringFilter unitLogip;

    private ZonedDateTimeFilter unitSignDate;

    public UnitCriteria() {
    }

    public UnitCriteria(UnitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.unitUcCode = other.unitUcCode == null ? null : other.unitUcCode.copy();
        this.unitCode = other.unitCode == null ? null : other.unitCode.copy();
        this.unitName = other.unitName == null ? null : other.unitName.copy();
        this.unitAddr = other.unitAddr == null ? null : other.unitAddr.copy();
        this.unitLongitude = other.unitLongitude == null ? null : other.unitLongitude.copy();
        this.unitLatitude = other.unitLatitude == null ? null : other.unitLatitude.copy();
        this.unitPic = other.unitPic == null ? null : other.unitPic.copy();
        this.unitPhone = other.unitPhone == null ? null : other.unitPhone.copy();
        this.unitEmail = other.unitEmail == null ? null : other.unitEmail.copy();
        this.unitCretime = other.unitCretime == null ? null : other.unitCretime.copy();
        this.unitCreid = other.unitCreid == null ? null : other.unitCreid.copy();
        this.unitModtime = other.unitModtime == null ? null : other.unitModtime.copy();
        this.unitModid = other.unitModid == null ? null : other.unitModid.copy();
        this.unitLogip = other.unitLogip == null ? null : other.unitLogip.copy();
        this.unitSignDate = other.unitSignDate == null ? null : other.unitSignDate.copy();
    }

    @Override
    public UnitCriteria copy() {
        return new UnitCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUnitUcCode() {
        return unitUcCode;
    }

    public void setUnitUcCode(StringFilter unitUcCode) {
        this.unitUcCode = unitUcCode;
    }

    public StringFilter getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(StringFilter unitCode) {
        this.unitCode = unitCode;
    }

    public StringFilter getUnitName() {
        return unitName;
    }

    public void setUnitName(StringFilter unitName) {
        this.unitName = unitName;
    }

    public StringFilter getUnitAddr() {
        return unitAddr;
    }

    public void setUnitAddr(StringFilter unitAddr) {
        this.unitAddr = unitAddr;
    }

    public StringFilter getUnitLongitude() {
        return unitLongitude;
    }

    public void setUnitLongitude(StringFilter unitLongitude) {
        this.unitLongitude = unitLongitude;
    }

    public StringFilter getUnitLatitude() {
        return unitLatitude;
    }

    public void setUnitLatitude(StringFilter unitLatitude) {
        this.unitLatitude = unitLatitude;
    }

    public StringFilter getUnitPic() {
        return unitPic;
    }

    public void setUnitPic(StringFilter unitPic) {
        this.unitPic = unitPic;
    }

    public StringFilter getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(StringFilter unitPhone) {
        this.unitPhone = unitPhone;
    }

    public StringFilter getUnitEmail() {
        return unitEmail;
    }

    public void setUnitEmail(StringFilter unitEmail) {
        this.unitEmail = unitEmail;
    }

    public ZonedDateTimeFilter getUnitCretime() {
        return unitCretime;
    }

    public void setUnitCretime(ZonedDateTimeFilter unitCretime) {
        this.unitCretime = unitCretime;
    }

    public StringFilter getUnitCreid() {
        return unitCreid;
    }

    public void setUnitCreid(StringFilter unitCreid) {
        this.unitCreid = unitCreid;
    }

    public ZonedDateTimeFilter getUnitModtime() {
        return unitModtime;
    }

    public void setUnitModtime(ZonedDateTimeFilter unitModtime) {
        this.unitModtime = unitModtime;
    }

    public StringFilter getUnitModid() {
        return unitModid;
    }

    public void setUnitModid(StringFilter unitModid) {
        this.unitModid = unitModid;
    }

    public StringFilter getUnitLogip() {
        return unitLogip;
    }

    public void setUnitLogip(StringFilter unitLogip) {
        this.unitLogip = unitLogip;
    }

    public ZonedDateTimeFilter getUnitSignDate() {
        return unitSignDate;
    }

    public void setUnitSignDate(ZonedDateTimeFilter unitSignDate) {
        this.unitSignDate = unitSignDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UnitCriteria that = (UnitCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(unitUcCode, that.unitUcCode) &&
            Objects.equals(unitCode, that.unitCode) &&
            Objects.equals(unitName, that.unitName) &&
            Objects.equals(unitAddr, that.unitAddr) &&
            Objects.equals(unitLongitude, that.unitLongitude) &&
            Objects.equals(unitLatitude, that.unitLatitude) &&
            Objects.equals(unitPic, that.unitPic) &&
            Objects.equals(unitPhone, that.unitPhone) &&
            Objects.equals(unitEmail, that.unitEmail) &&
            Objects.equals(unitCretime, that.unitCretime) &&
            Objects.equals(unitCreid, that.unitCreid) &&
            Objects.equals(unitModtime, that.unitModtime) &&
            Objects.equals(unitModid, that.unitModid) &&
            Objects.equals(unitLogip, that.unitLogip) &&
            Objects.equals(unitSignDate, that.unitSignDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        unitUcCode,
        unitCode,
        unitName,
        unitAddr,
        unitLongitude,
        unitLatitude,
        unitPic,
        unitPhone,
        unitEmail,
        unitCretime,
        unitCreid,
        unitModtime,
        unitModid,
        unitLogip,
        unitSignDate
        );
    }

    @Override
    public String toString() {
        return "UnitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (unitUcCode != null ? "unitUcCode=" + unitUcCode + ", " : "") +
                (unitCode != null ? "unitCode=" + unitCode + ", " : "") +
                (unitName != null ? "unitName=" + unitName + ", " : "") +
                (unitAddr != null ? "unitAddr=" + unitAddr + ", " : "") +
                (unitLongitude != null ? "unitLongitude=" + unitLongitude + ", " : "") +
                (unitLatitude != null ? "unitLatitude=" + unitLatitude + ", " : "") +
                (unitPic != null ? "unitPic=" + unitPic + ", " : "") +
                (unitPhone != null ? "unitPhone=" + unitPhone + ", " : "") +
                (unitEmail != null ? "unitEmail=" + unitEmail + ", " : "") +
                (unitCretime != null ? "unitCretime=" + unitCretime + ", " : "") +
                (unitCreid != null ? "unitCreid=" + unitCreid + ", " : "") +
                (unitModtime != null ? "unitModtime=" + unitModtime + ", " : "") +
                (unitModid != null ? "unitModid=" + unitModid + ", " : "") +
                (unitLogip != null ? "unitLogip=" + unitLogip + ", " : "") +
                (unitSignDate != null ? "unitSignDate=" + unitSignDate + ", " : "") +
            "}";
    }

}
