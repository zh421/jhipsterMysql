package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A EventInter.
 */
@Entity
@Table(name = "event_inter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EventInter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "evnin_time", nullable = false)
    private ZonedDateTime evninTime;

    @NotNull
    @Size(max = 60)
    @Column(name = "evnin_es_code", length = 60, nullable = false)
    private String evninEsCode;

    @NotNull
    @Size(max = 30)
    @Column(name = "evnin_deviceid", length = 30, nullable = false)
    private String evninDeviceid;

    @NotNull
    @Column(name = "evnin_dvi_mod_num", nullable = false)
    private Integer evninDviModNum;

    @NotNull
    @Size(max = 50)
    @Column(name = "evnin_dvi_code", length = 50, nullable = false)
    private String evninDviCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "evnin_unit_uc_code", length = 60, nullable = false)
    private String evninUnitUcCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "evnin_unit_code", length = 50, nullable = false)
    private String evninUnitCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "evnin_unit_name", length = 60, nullable = false)
    private String evninUnitName;

    @NotNull
    @Size(max = 255)
    @Column(name = "evnin_unit_addr", length = 255, nullable = false)
    private String evninUnitAddr;

    @NotNull
    @Size(max = 20)
    @Column(name = "evnin_theme", length = 20, nullable = false)
    private String evninTheme;

    @Size(max = 1023)
    @Column(name = "evnin_memo", length = 1023)
    private String evninMemo;

    @Column(name = "evnin_isres")
    private Boolean evninIsres;

    @Size(max = 1023)
    @Column(name = "evnin_res_memo", length = 1023)
    private String evninResMemo;

    @NotNull
    @Column(name = "evnin_cretime", nullable = false)
    private ZonedDateTime evninCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "evnin_creid", length = 255, nullable = false)
    private String evninCreid;

    @NotNull
    @Column(name = "evnin_modtime", nullable = false)
    private ZonedDateTime evninModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "evnin_modid", length = 255, nullable = false)
    private String evninModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getEvninTime() {
        return evninTime;
    }

    public EventInter evninTime(ZonedDateTime evninTime) {
        this.evninTime = evninTime;
        return this;
    }

    public void setEvninTime(ZonedDateTime evninTime) {
        this.evninTime = evninTime;
    }

    public String getEvninEsCode() {
        return evninEsCode;
    }

    public EventInter evninEsCode(String evninEsCode) {
        this.evninEsCode = evninEsCode;
        return this;
    }

    public void setEvninEsCode(String evninEsCode) {
        this.evninEsCode = evninEsCode;
    }

    public String getEvninDeviceid() {
        return evninDeviceid;
    }

    public EventInter evninDeviceid(String evninDeviceid) {
        this.evninDeviceid = evninDeviceid;
        return this;
    }

    public void setEvninDeviceid(String evninDeviceid) {
        this.evninDeviceid = evninDeviceid;
    }

    public Integer getEvninDviModNum() {
        return evninDviModNum;
    }

    public EventInter evninDviModNum(Integer evninDviModNum) {
        this.evninDviModNum = evninDviModNum;
        return this;
    }

    public void setEvninDviModNum(Integer evninDviModNum) {
        this.evninDviModNum = evninDviModNum;
    }

    public String getEvninDviCode() {
        return evninDviCode;
    }

    public EventInter evninDviCode(String evninDviCode) {
        this.evninDviCode = evninDviCode;
        return this;
    }

    public void setEvninDviCode(String evninDviCode) {
        this.evninDviCode = evninDviCode;
    }

    public String getEvninUnitUcCode() {
        return evninUnitUcCode;
    }

    public EventInter evninUnitUcCode(String evninUnitUcCode) {
        this.evninUnitUcCode = evninUnitUcCode;
        return this;
    }

    public void setEvninUnitUcCode(String evninUnitUcCode) {
        this.evninUnitUcCode = evninUnitUcCode;
    }

    public String getEvninUnitCode() {
        return evninUnitCode;
    }

    public EventInter evninUnitCode(String evninUnitCode) {
        this.evninUnitCode = evninUnitCode;
        return this;
    }

    public void setEvninUnitCode(String evninUnitCode) {
        this.evninUnitCode = evninUnitCode;
    }

    public String getEvninUnitName() {
        return evninUnitName;
    }

    public EventInter evninUnitName(String evninUnitName) {
        this.evninUnitName = evninUnitName;
        return this;
    }

    public void setEvninUnitName(String evninUnitName) {
        this.evninUnitName = evninUnitName;
    }

    public String getEvninUnitAddr() {
        return evninUnitAddr;
    }

    public EventInter evninUnitAddr(String evninUnitAddr) {
        this.evninUnitAddr = evninUnitAddr;
        return this;
    }

    public void setEvninUnitAddr(String evninUnitAddr) {
        this.evninUnitAddr = evninUnitAddr;
    }

    public String getEvninTheme() {
        return evninTheme;
    }

    public EventInter evninTheme(String evninTheme) {
        this.evninTheme = evninTheme;
        return this;
    }

    public void setEvninTheme(String evninTheme) {
        this.evninTheme = evninTheme;
    }

    public String getEvninMemo() {
        return evninMemo;
    }

    public EventInter evninMemo(String evninMemo) {
        this.evninMemo = evninMemo;
        return this;
    }

    public void setEvninMemo(String evninMemo) {
        this.evninMemo = evninMemo;
    }

    public Boolean isEvninIsres() {
        return evninIsres;
    }

    public EventInter evninIsres(Boolean evninIsres) {
        this.evninIsres = evninIsres;
        return this;
    }

    public void setEvninIsres(Boolean evninIsres) {
        this.evninIsres = evninIsres;
    }

    public String getEvninResMemo() {
        return evninResMemo;
    }

    public EventInter evninResMemo(String evninResMemo) {
        this.evninResMemo = evninResMemo;
        return this;
    }

    public void setEvninResMemo(String evninResMemo) {
        this.evninResMemo = evninResMemo;
    }

    public ZonedDateTime getEvninCretime() {
        return evninCretime;
    }

    public EventInter evninCretime(ZonedDateTime evninCretime) {
        this.evninCretime = evninCretime;
        return this;
    }

    public void setEvninCretime(ZonedDateTime evninCretime) {
        this.evninCretime = evninCretime;
    }

    public String getEvninCreid() {
        return evninCreid;
    }

    public EventInter evninCreid(String evninCreid) {
        this.evninCreid = evninCreid;
        return this;
    }

    public void setEvninCreid(String evninCreid) {
        this.evninCreid = evninCreid;
    }

    public ZonedDateTime getEvninModtime() {
        return evninModtime;
    }

    public EventInter evninModtime(ZonedDateTime evninModtime) {
        this.evninModtime = evninModtime;
        return this;
    }

    public void setEvninModtime(ZonedDateTime evninModtime) {
        this.evninModtime = evninModtime;
    }

    public String getEvninModid() {
        return evninModid;
    }

    public EventInter evninModid(String evninModid) {
        this.evninModid = evninModid;
        return this;
    }

    public void setEvninModid(String evninModid) {
        this.evninModid = evninModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventInter)) {
            return false;
        }
        return id != null && id.equals(((EventInter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EventInter{" +
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
