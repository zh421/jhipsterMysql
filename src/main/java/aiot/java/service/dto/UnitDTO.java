package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.Unit} entity.
 */
public class UnitDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String unitUcCode;

    @NotNull
    @Size(max = 50)
    private String unitCode;

    @NotNull
    @Size(max = 60)
    private String unitName;

    @NotNull
    @Size(max = 255)
    private String unitAddr;

    @NotNull
    @Size(max = 10)
    private String unitLongitude;

    @NotNull
    @Size(max = 10)
    private String unitLatitude;

    @NotNull
    @Size(max = 50)
    private String unitPic;

    @NotNull
    @Size(max = 20)
    private String unitPhone;

    @NotNull
    @Size(max = 255)
    private String unitEmail;

    @NotNull
    private ZonedDateTime unitCretime;

    @NotNull
    @Size(max = 255)
    private String unitCreid;

    @NotNull
    private ZonedDateTime unitModtime;

    @NotNull
    @Size(max = 255)
    private String unitModid;

    @NotNull
    @Size(max = 30)
    private String unitLogip;

    @NotNull
    private ZonedDateTime unitSignDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitUcCode() {
        return unitUcCode;
    }

    public void setUnitUcCode(String unitUcCode) {
        this.unitUcCode = unitUcCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddr() {
        return unitAddr;
    }

    public void setUnitAddr(String unitAddr) {
        this.unitAddr = unitAddr;
    }

    public String getUnitLongitude() {
        return unitLongitude;
    }

    public void setUnitLongitude(String unitLongitude) {
        this.unitLongitude = unitLongitude;
    }

    public String getUnitLatitude() {
        return unitLatitude;
    }

    public void setUnitLatitude(String unitLatitude) {
        this.unitLatitude = unitLatitude;
    }

    public String getUnitPic() {
        return unitPic;
    }

    public void setUnitPic(String unitPic) {
        this.unitPic = unitPic;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getUnitEmail() {
        return unitEmail;
    }

    public void setUnitEmail(String unitEmail) {
        this.unitEmail = unitEmail;
    }

    public ZonedDateTime getUnitCretime() {
        return unitCretime;
    }

    public void setUnitCretime(ZonedDateTime unitCretime) {
        this.unitCretime = unitCretime;
    }

    public String getUnitCreid() {
        return unitCreid;
    }

    public void setUnitCreid(String unitCreid) {
        this.unitCreid = unitCreid;
    }

    public ZonedDateTime getUnitModtime() {
        return unitModtime;
    }

    public void setUnitModtime(ZonedDateTime unitModtime) {
        this.unitModtime = unitModtime;
    }

    public String getUnitModid() {
        return unitModid;
    }

    public void setUnitModid(String unitModid) {
        this.unitModid = unitModid;
    }

    public String getUnitLogip() {
        return unitLogip;
    }

    public void setUnitLogip(String unitLogip) {
        this.unitLogip = unitLogip;
    }

    public ZonedDateTime getUnitSignDate() {
        return unitSignDate;
    }

    public void setUnitSignDate(ZonedDateTime unitSignDate) {
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

        UnitDTO unitDTO = (UnitDTO) o;
        if (unitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UnitDTO{" +
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
