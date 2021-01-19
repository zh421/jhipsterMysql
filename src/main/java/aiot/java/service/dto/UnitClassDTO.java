package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.UnitClass} entity.
 */
public class UnitClassDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String ucCode;

    @NotNull
    @Size(max = 60)
    private String ucName;

    @NotNull
    private ZonedDateTime ucCretime;

    @NotNull
    @Size(max = 255)
    private String ucCreid;

    @NotNull
    private ZonedDateTime ucModtime;

    @NotNull
    @Size(max = 255)
    private String ucModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUcCode() {
        return ucCode;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }

    public String getUcName() {
        return ucName;
    }

    public void setUcName(String ucName) {
        this.ucName = ucName;
    }

    public ZonedDateTime getUcCretime() {
        return ucCretime;
    }

    public void setUcCretime(ZonedDateTime ucCretime) {
        this.ucCretime = ucCretime;
    }

    public String getUcCreid() {
        return ucCreid;
    }

    public void setUcCreid(String ucCreid) {
        this.ucCreid = ucCreid;
    }

    public ZonedDateTime getUcModtime() {
        return ucModtime;
    }

    public void setUcModtime(ZonedDateTime ucModtime) {
        this.ucModtime = ucModtime;
    }

    public String getUcModid() {
        return ucModid;
    }

    public void setUcModid(String ucModid) {
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

        UnitClassDTO unitClassDTO = (UnitClassDTO) o;
        if (unitClassDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unitClassDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UnitClassDTO{" +
            "id=" + getId() +
            ", ucCode='" + getUcCode() + "'" +
            ", ucName='" + getUcName() + "'" +
            ", ucCretime='" + getUcCretime() + "'" +
            ", ucCreid='" + getUcCreid() + "'" +
            ", ucModtime='" + getUcModtime() + "'" +
            ", ucModid='" + getUcModid() + "'" +
            "}";
    }
}
