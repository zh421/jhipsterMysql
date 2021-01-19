package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.SensorCode} entity.
 */
public class SensorCodeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String scCode;

    @NotNull
    @Size(max = 60)
    private String scName;

    @NotNull
    private ZonedDateTime scCretime;

    @NotNull
    @Size(max = 255)
    private String scCreid;

    @NotNull
    private ZonedDateTime scModtime;

    @NotNull
    @Size(max = 255)
    private String scModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScCode() {
        return scCode;
    }

    public void setScCode(String scCode) {
        this.scCode = scCode;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public ZonedDateTime getScCretime() {
        return scCretime;
    }

    public void setScCretime(ZonedDateTime scCretime) {
        this.scCretime = scCretime;
    }

    public String getScCreid() {
        return scCreid;
    }

    public void setScCreid(String scCreid) {
        this.scCreid = scCreid;
    }

    public ZonedDateTime getScModtime() {
        return scModtime;
    }

    public void setScModtime(ZonedDateTime scModtime) {
        this.scModtime = scModtime;
    }

    public String getScModid() {
        return scModid;
    }

    public void setScModid(String scModid) {
        this.scModid = scModid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SensorCodeDTO sensorCodeDTO = (SensorCodeDTO) o;
        if (sensorCodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sensorCodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SensorCodeDTO{" +
            "id=" + getId() +
            ", scCode='" + getScCode() + "'" +
            ", scName='" + getScName() + "'" +
            ", scCretime='" + getScCretime() + "'" +
            ", scCreid='" + getScCreid() + "'" +
            ", scModtime='" + getScModtime() + "'" +
            ", scModid='" + getScModid() + "'" +
            "}";
    }
}
