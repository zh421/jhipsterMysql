package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.EventInter} entity.
 */
public class EventInterDTO implements Serializable {
    
    private Long id;

    @NotNull
    private ZonedDateTime evninTime;

    @NotNull
    @Size(max = 60)
    private String evninEsCode;

    @NotNull
    @Size(max = 30)
    private String evninDeviceid;

    @NotNull
    private Integer evninDviModNum;

    @NotNull
    @Size(max = 50)
    private String evninDviCode;

    @NotNull
    @Size(max = 60)
    private String evninUnitUcCode;

    @NotNull
    @Size(max = 50)
    private String evninUnitCode;

    @NotNull
    @Size(max = 60)
    private String evninUnitName;

    @NotNull
    @Size(max = 255)
    private String evninUnitAddr;

    @NotNull
    @Size(max = 20)
    private String evninTheme;

    @Size(max = 1023)
    private String evninMemo;

    private Boolean evninIsres;

    @Size(max = 1023)
    private String evninResMemo;

    @NotNull
    private ZonedDateTime evninCretime;

    @NotNull
    @Size(max = 255)
    private String evninCreid;

    @NotNull
    private ZonedDateTime evninModtime;

    @NotNull
    @Size(max = 255)
    private String evninModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getEvninTime() {
        return evninTime;
    }

    public void setEvninTime(ZonedDateTime evninTime) {
        this.evninTime = evninTime;
    }

    public String getEvninEsCode() {
        return evninEsCode;
    }

    public void setEvninEsCode(String evninEsCode) {
        this.evninEsCode = evninEsCode;
    }

    public String getEvninDeviceid() {
        return evninDeviceid;
    }

    public void setEvninDeviceid(String evninDeviceid) {
        this.evninDeviceid = evninDeviceid;
    }

    public Integer getEvninDviModNum() {
        return evninDviModNum;
    }

    public void setEvninDviModNum(Integer evninDviModNum) {
        this.evninDviModNum = evninDviModNum;
    }

    public String getEvninDviCode() {
        return evninDviCode;
    }

    public void setEvninDviCode(String evninDviCode) {
        this.evninDviCode = evninDviCode;
    }

    public String getEvninUnitUcCode() {
        return evninUnitUcCode;
    }

    public void setEvninUnitUcCode(String evninUnitUcCode) {
        this.evninUnitUcCode = evninUnitUcCode;
    }

    public String getEvninUnitCode() {
        return evninUnitCode;
    }

    public void setEvninUnitCode(String evninUnitCode) {
        this.evninUnitCode = evninUnitCode;
    }

    public String getEvninUnitName() {
        return evninUnitName;
    }

    public void setEvninUnitName(String evninUnitName) {
        this.evninUnitName = evninUnitName;
    }

    public String getEvninUnitAddr() {
        return evninUnitAddr;
    }

    public void setEvninUnitAddr(String evninUnitAddr) {
        this.evninUnitAddr = evninUnitAddr;
    }

    public String getEvninTheme() {
        return evninTheme;
    }

    public void setEvninTheme(String evninTheme) {
        this.evninTheme = evninTheme;
    }

    public String getEvninMemo() {
        return evninMemo;
    }

    public void setEvninMemo(String evninMemo) {
        this.evninMemo = evninMemo;
    }

    public Boolean isEvninIsres() {
        return evninIsres;
    }

    public void setEvninIsres(Boolean evninIsres) {
        this.evninIsres = evninIsres;
    }

    public String getEvninResMemo() {
        return evninResMemo;
    }

    public void setEvninResMemo(String evninResMemo) {
        this.evninResMemo = evninResMemo;
    }

    public ZonedDateTime getEvninCretime() {
        return evninCretime;
    }

    public void setEvninCretime(ZonedDateTime evninCretime) {
        this.evninCretime = evninCretime;
    }

    public String getEvninCreid() {
        return evninCreid;
    }

    public void setEvninCreid(String evninCreid) {
        this.evninCreid = evninCreid;
    }

    public ZonedDateTime getEvninModtime() {
        return evninModtime;
    }

    public void setEvninModtime(ZonedDateTime evninModtime) {
        this.evninModtime = evninModtime;
    }

    public String getEvninModid() {
        return evninModid;
    }

    public void setEvninModid(String evninModid) {
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

        EventInterDTO eventInterDTO = (EventInterDTO) o;
        if (eventInterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventInterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventInterDTO{" +
            "id=" + getId() +
            ", evninTime='" + getEvninTime() + "'" +
            ", evninEsCode='" + getEvninEsCode() + "'" +
            ", evninDeviceid='" + getEvninDeviceid() + "'" +
            ", evninDviModNum=" + getEvninDviModNum() +
            ", evninDviCode='" + getEvninDviCode() + "'" +
            ", evninUnitUcCode='" + getEvninUnitUcCode() + "'" +
            ", evninUnitCode='" + getEvninUnitCode() + "'" +
            ", evninUnitName='" + getEvninUnitName() + "'" +
            ", evninUnitAddr='" + getEvninUnitAddr() + "'" +
            ", evninTheme='" + getEvninTheme() + "'" +
            ", evninMemo='" + getEvninMemo() + "'" +
            ", evninIsres='" + isEvninIsres() + "'" +
            ", evninResMemo='" + getEvninResMemo() + "'" +
            ", evninCretime='" + getEvninCretime() + "'" +
            ", evninCreid='" + getEvninCreid() + "'" +
            ", evninModtime='" + getEvninModtime() + "'" +
            ", evninModid='" + getEvninModid() + "'" +
            "}";
    }
}
