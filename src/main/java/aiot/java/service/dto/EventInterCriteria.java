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
 * Criteria class for the {@link aiot.java.domain.EventInter} entity. This class is used
 * in {@link aiot.java.web.rest.EventInterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /event-inters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EventInterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter evninTime;

    private StringFilter evninEsCode;

    private StringFilter evninDeviceid;

    private IntegerFilter evninDviModNum;

    private StringFilter evninDviCode;

    private StringFilter evninUnitUcCode;

    private StringFilter evninUnitCode;

    private StringFilter evninUnitName;

    private StringFilter evninUnitAddr;

    private StringFilter evninTheme;

    private StringFilter evninMemo;

    private BooleanFilter evninIsres;

    private StringFilter evninResMemo;

    private ZonedDateTimeFilter evninCretime;

    private StringFilter evninCreid;

    private ZonedDateTimeFilter evninModtime;

    private StringFilter evninModid;

    public EventInterCriteria() {
    }

    public EventInterCriteria(EventInterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.evninTime = other.evninTime == null ? null : other.evninTime.copy();
        this.evninEsCode = other.evninEsCode == null ? null : other.evninEsCode.copy();
        this.evninDeviceid = other.evninDeviceid == null ? null : other.evninDeviceid.copy();
        this.evninDviModNum = other.evninDviModNum == null ? null : other.evninDviModNum.copy();
        this.evninDviCode = other.evninDviCode == null ? null : other.evninDviCode.copy();
        this.evninUnitUcCode = other.evninUnitUcCode == null ? null : other.evninUnitUcCode.copy();
        this.evninUnitCode = other.evninUnitCode == null ? null : other.evninUnitCode.copy();
        this.evninUnitName = other.evninUnitName == null ? null : other.evninUnitName.copy();
        this.evninUnitAddr = other.evninUnitAddr == null ? null : other.evninUnitAddr.copy();
        this.evninTheme = other.evninTheme == null ? null : other.evninTheme.copy();
        this.evninMemo = other.evninMemo == null ? null : other.evninMemo.copy();
        this.evninIsres = other.evninIsres == null ? null : other.evninIsres.copy();
        this.evninResMemo = other.evninResMemo == null ? null : other.evninResMemo.copy();
        this.evninCretime = other.evninCretime == null ? null : other.evninCretime.copy();
        this.evninCreid = other.evninCreid == null ? null : other.evninCreid.copy();
        this.evninModtime = other.evninModtime == null ? null : other.evninModtime.copy();
        this.evninModid = other.evninModid == null ? null : other.evninModid.copy();
    }

    @Override
    public EventInterCriteria copy() {
        return new EventInterCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getEvninTime() {
        return evninTime;
    }

    public void setEvninTime(ZonedDateTimeFilter evninTime) {
        this.evninTime = evninTime;
    }

    public StringFilter getEvninEsCode() {
        return evninEsCode;
    }

    public void setEvninEsCode(StringFilter evninEsCode) {
        this.evninEsCode = evninEsCode;
    }

    public StringFilter getEvninDeviceid() {
        return evninDeviceid;
    }

    public void setEvninDeviceid(StringFilter evninDeviceid) {
        this.evninDeviceid = evninDeviceid;
    }

    public IntegerFilter getEvninDviModNum() {
        return evninDviModNum;
    }

    public void setEvninDviModNum(IntegerFilter evninDviModNum) {
        this.evninDviModNum = evninDviModNum;
    }

    public StringFilter getEvninDviCode() {
        return evninDviCode;
    }

    public void setEvninDviCode(StringFilter evninDviCode) {
        this.evninDviCode = evninDviCode;
    }

    public StringFilter getEvninUnitUcCode() {
        return evninUnitUcCode;
    }

    public void setEvninUnitUcCode(StringFilter evninUnitUcCode) {
        this.evninUnitUcCode = evninUnitUcCode;
    }

    public StringFilter getEvninUnitCode() {
        return evninUnitCode;
    }

    public void setEvninUnitCode(StringFilter evninUnitCode) {
        this.evninUnitCode = evninUnitCode;
    }

    public StringFilter getEvninUnitName() {
        return evninUnitName;
    }

    public void setEvninUnitName(StringFilter evninUnitName) {
        this.evninUnitName = evninUnitName;
    }

    public StringFilter getEvninUnitAddr() {
        return evninUnitAddr;
    }

    public void setEvninUnitAddr(StringFilter evninUnitAddr) {
        this.evninUnitAddr = evninUnitAddr;
    }

    public StringFilter getEvninTheme() {
        return evninTheme;
    }

    public void setEvninTheme(StringFilter evninTheme) {
        this.evninTheme = evninTheme;
    }

    public StringFilter getEvninMemo() {
        return evninMemo;
    }

    public void setEvninMemo(StringFilter evninMemo) {
        this.evninMemo = evninMemo;
    }

    public BooleanFilter getEvninIsres() {
        return evninIsres;
    }

    public void setEvninIsres(BooleanFilter evninIsres) {
        this.evninIsres = evninIsres;
    }

    public StringFilter getEvninResMemo() {
        return evninResMemo;
    }

    public void setEvninResMemo(StringFilter evninResMemo) {
        this.evninResMemo = evninResMemo;
    }

    public ZonedDateTimeFilter getEvninCretime() {
        return evninCretime;
    }

    public void setEvninCretime(ZonedDateTimeFilter evninCretime) {
        this.evninCretime = evninCretime;
    }

    public StringFilter getEvninCreid() {
        return evninCreid;
    }

    public void setEvninCreid(StringFilter evninCreid) {
        this.evninCreid = evninCreid;
    }

    public ZonedDateTimeFilter getEvninModtime() {
        return evninModtime;
    }

    public void setEvninModtime(ZonedDateTimeFilter evninModtime) {
        this.evninModtime = evninModtime;
    }

    public StringFilter getEvninModid() {
        return evninModid;
    }

    public void setEvninModid(StringFilter evninModid) {
        this.evninModid = evninModid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EventInterCriteria that = (EventInterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(evninTime, that.evninTime) &&
            Objects.equals(evninEsCode, that.evninEsCode) &&
            Objects.equals(evninDeviceid, that.evninDeviceid) &&
            Objects.equals(evninDviModNum, that.evninDviModNum) &&
            Objects.equals(evninDviCode, that.evninDviCode) &&
            Objects.equals(evninUnitUcCode, that.evninUnitUcCode) &&
            Objects.equals(evninUnitCode, that.evninUnitCode) &&
            Objects.equals(evninUnitName, that.evninUnitName) &&
            Objects.equals(evninUnitAddr, that.evninUnitAddr) &&
            Objects.equals(evninTheme, that.evninTheme) &&
            Objects.equals(evninMemo, that.evninMemo) &&
            Objects.equals(evninIsres, that.evninIsres) &&
            Objects.equals(evninResMemo, that.evninResMemo) &&
            Objects.equals(evninCretime, that.evninCretime) &&
            Objects.equals(evninCreid, that.evninCreid) &&
            Objects.equals(evninModtime, that.evninModtime) &&
            Objects.equals(evninModid, that.evninModid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        evninTime,
        evninEsCode,
        evninDeviceid,
        evninDviModNum,
        evninDviCode,
        evninUnitUcCode,
        evninUnitCode,
        evninUnitName,
        evninUnitAddr,
        evninTheme,
        evninMemo,
        evninIsres,
        evninResMemo,
        evninCretime,
        evninCreid,
        evninModtime,
        evninModid
        );
    }

    @Override
    public String toString() {
        return "EventInterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (evninTime != null ? "evninTime=" + evninTime + ", " : "") +
                (evninEsCode != null ? "evninEsCode=" + evninEsCode + ", " : "") +
                (evninDeviceid != null ? "evninDeviceid=" + evninDeviceid + ", " : "") +
                (evninDviModNum != null ? "evninDviModNum=" + evninDviModNum + ", " : "") +
                (evninDviCode != null ? "evninDviCode=" + evninDviCode + ", " : "") +
                (evninUnitUcCode != null ? "evninUnitUcCode=" + evninUnitUcCode + ", " : "") +
                (evninUnitCode != null ? "evninUnitCode=" + evninUnitCode + ", " : "") +
                (evninUnitName != null ? "evninUnitName=" + evninUnitName + ", " : "") +
                (evninUnitAddr != null ? "evninUnitAddr=" + evninUnitAddr + ", " : "") +
                (evninTheme != null ? "evninTheme=" + evninTheme + ", " : "") +
                (evninMemo != null ? "evninMemo=" + evninMemo + ", " : "") +
                (evninIsres != null ? "evninIsres=" + evninIsres + ", " : "") +
                (evninResMemo != null ? "evninResMemo=" + evninResMemo + ", " : "") +
                (evninCretime != null ? "evninCretime=" + evninCretime + ", " : "") +
                (evninCreid != null ? "evninCreid=" + evninCreid + ", " : "") +
                (evninModtime != null ? "evninModtime=" + evninModtime + ", " : "") +
                (evninModid != null ? "evninModid=" + evninModid + ", " : "") +
            "}";
    }

}
