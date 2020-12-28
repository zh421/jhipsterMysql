package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.DevicePatternIntro} entity.
 */
public class DevicePatternIntroDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 100)
    private String devicepatternId;

    @NotNull
    @Size(max = 100)
    private String devicepatternCode;

    @Size(max = 255)
    private String memo;

    @NotNull
    @Size(max = 140)
    private String creuser;

    @NotNull
    private ZonedDateTime cretime;

    @NotNull
    @Size(max = 140)
    private String moduser;

    @NotNull
    private ZonedDateTime modtime;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevicepatternId() {
        return devicepatternId;
    }

    public void setDevicepatternId(String devicepatternId) {
        this.devicepatternId = devicepatternId;
    }

    public String getDevicepatternCode() {
        return devicepatternCode;
    }

    public void setDevicepatternCode(String devicepatternCode) {
        this.devicepatternCode = devicepatternCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public ZonedDateTime getCretime() {
        return cretime;
    }

    public void setCretime(ZonedDateTime cretime) {
        this.cretime = cretime;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }

    public ZonedDateTime getModtime() {
        return modtime;
    }

    public void setModtime(ZonedDateTime modtime) {
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

        DevicePatternIntroDTO devicePatternIntroDTO = (DevicePatternIntroDTO) o;
        if (devicePatternIntroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), devicePatternIntroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DevicePatternIntroDTO{" +
            "id=" + getId() +
            ", devicepatternId='" + getDevicepatternId() + "'" +
            ", devicepatternCode='" + getDevicepatternCode() + "'" +
            ", memo='" + getMemo() + "'" +
            ", creuser='" + getCreuser() + "'" +
            ", cretime='" + getCretime() + "'" +
            ", moduser='" + getModuser() + "'" +
            ", modtime='" + getModtime() + "'" +
            "}";
    }
}
