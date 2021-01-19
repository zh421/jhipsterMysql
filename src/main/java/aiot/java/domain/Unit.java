package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A Unit.
 */
@Entity
@Table(name = "unit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "unit_uc_code", length = 60, nullable = false)
    private String unitUcCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "unit_code", length = 50, nullable = false)
    private String unitCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "unit_name", length = 60, nullable = false)
    private String unitName;

    @NotNull
    @Size(max = 255)
    @Column(name = "unit_addr", length = 255, nullable = false)
    private String unitAddr;

    @NotNull
    @Size(max = 10)
    @Column(name = "unit_longitude", length = 10, nullable = false)
    private String unitLongitude;

    @NotNull
    @Size(max = 10)
    @Column(name = "unit_latitude", length = 10, nullable = false)
    private String unitLatitude;

    @NotNull
    @Size(max = 50)
    @Column(name = "unit_pic", length = 50, nullable = false)
    private String unitPic;

    @NotNull
    @Size(max = 20)
    @Column(name = "unit_phone", length = 20, nullable = false)
    private String unitPhone;

    @NotNull
    @Size(max = 255)
    @Column(name = "unit_email", length = 255, nullable = false)
    private String unitEmail;

    @NotNull
    @Column(name = "unit_cretime", nullable = false)
    private ZonedDateTime unitCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "unit_creid", length = 255, nullable = false)
    private String unitCreid;

    @NotNull
    @Column(name = "unit_modtime", nullable = false)
    private ZonedDateTime unitModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "unit_modid", length = 255, nullable = false)
    private String unitModid;

    @NotNull
    @Size(max = 30)
    @Column(name = "unit_logip", length = 30, nullable = false)
    private String unitLogip;

    @NotNull
    @Column(name = "unit_sign_date", nullable = false)
    private ZonedDateTime unitSignDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitUcCode() {
        return unitUcCode;
    }

    public Unit unitUcCode(String unitUcCode) {
        this.unitUcCode = unitUcCode;
        return this;
    }

    public void setUnitUcCode(String unitUcCode) {
        this.unitUcCode = unitUcCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public Unit unitCode(String unitCode) {
        this.unitCode = unitCode;
        return this;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public Unit unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddr() {
        return unitAddr;
    }

    public Unit unitAddr(String unitAddr) {
        this.unitAddr = unitAddr;
        return this;
    }

    public void setUnitAddr(String unitAddr) {
        this.unitAddr = unitAddr;
    }

    public String getUnitLongitude() {
        return unitLongitude;
    }

    public Unit unitLongitude(String unitLongitude) {
        this.unitLongitude = unitLongitude;
        return this;
    }

    public void setUnitLongitude(String unitLongitude) {
        this.unitLongitude = unitLongitude;
    }

    public String getUnitLatitude() {
        return unitLatitude;
    }

    public Unit unitLatitude(String unitLatitude) {
        this.unitLatitude = unitLatitude;
        return this;
    }

    public void setUnitLatitude(String unitLatitude) {
        this.unitLatitude = unitLatitude;
    }

    public String getUnitPic() {
        return unitPic;
    }

    public Unit unitPic(String unitPic) {
        this.unitPic = unitPic;
        return this;
    }

    public void setUnitPic(String unitPic) {
        this.unitPic = unitPic;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public Unit unitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
        return this;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getUnitEmail() {
        return unitEmail;
    }

    public Unit unitEmail(String unitEmail) {
        this.unitEmail = unitEmail;
        return this;
    }

    public void setUnitEmail(String unitEmail) {
        this.unitEmail = unitEmail;
    }

    public ZonedDateTime getUnitCretime() {
        return unitCretime;
    }

    public Unit unitCretime(ZonedDateTime unitCretime) {
        this.unitCretime = unitCretime;
        return this;
    }

    public void setUnitCretime(ZonedDateTime unitCretime) {
        this.unitCretime = unitCretime;
    }

    public String getUnitCreid() {
        return unitCreid;
    }

    public Unit unitCreid(String unitCreid) {
        this.unitCreid = unitCreid;
        return this;
    }

    public void setUnitCreid(String unitCreid) {
        this.unitCreid = unitCreid;
    }

    public ZonedDateTime getUnitModtime() {
        return unitModtime;
    }

    public Unit unitModtime(ZonedDateTime unitModtime) {
        this.unitModtime = unitModtime;
        return this;
    }

    public void setUnitModtime(ZonedDateTime unitModtime) {
        this.unitModtime = unitModtime;
    }

    public String getUnitModid() {
        return unitModid;
    }

    public Unit unitModid(String unitModid) {
        this.unitModid = unitModid;
        return this;
    }

    public void setUnitModid(String unitModid) {
        this.unitModid = unitModid;
    }

    public String getUnitLogip() {
        return unitLogip;
    }

    public Unit unitLogip(String unitLogip) {
        this.unitLogip = unitLogip;
        return this;
    }

    public void setUnitLogip(String unitLogip) {
        this.unitLogip = unitLogip;
    }

    public ZonedDateTime getUnitSignDate() {
        return unitSignDate;
    }

    public Unit unitSignDate(ZonedDateTime unitSignDate) {
        this.unitSignDate = unitSignDate;
        return this;
    }

    public void setUnitSignDate(ZonedDateTime unitSignDate) {
        this.unitSignDate = unitSignDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unit)) {
            return false;
        }
        return id != null && id.equals(((Unit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Unit{" +
            "id=" + getId() +
            ", unitUcCode='" + getUnitUcCode() + "'" +
            ", unitCode='" + getUnitCode() + "'" +
            ", unitName='" + getUnitName() + "'" +
            ", unitAddr='" + getUnitAddr() + "'" +
            ", unitLongitude='" + getUnitLongitude() + "'" +
            ", unitLatitude='" + getUnitLatitude() + "'" +
            ", unitPic='" + getUnitPic() + "'" +
            ", unitPhone='" + getUnitPhone() + "'" +
            ", unitEmail='" + getUnitEmail() + "'" +
            ", unitCretime='" + getUnitCretime() + "'" +
            ", unitCreid='" + getUnitCreid() + "'" +
            ", unitModtime='" + getUnitModtime() + "'" +
            ", unitModid='" + getUnitModid() + "'" +
            ", unitLogip='" + getUnitLogip() + "'" +
            ", unitSignDate='" + getUnitSignDate() + "'" +
            "}";
    }
}
