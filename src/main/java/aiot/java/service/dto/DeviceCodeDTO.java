package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.DeviceCode} entity.
 */
public class DeviceCodeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String dviCode;

    @NotNull
    @Size(max = 60)
    private String dviName;

    @NotNull
    private ZonedDateTime dviCretime;

    @NotNull
    @Size(max = 255)
    private String dviCreid;

    @NotNull
    private ZonedDateTime dviModtime;

    @NotNull
    @Size(max = 255)
    private String dviModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDviCode() {
        return dviCode;
    }

    public void setDviCode(String dviCode) {
        this.dviCode = dviCode;
    }

    public String getDviName() {
        return dviName;
    }

    public void setDviName(String dviName) {
        this.dviName = dviName;
    }

    public ZonedDateTime getDviCretime() {
        return dviCretime;
    }

    public void setDviCretime(ZonedDateTime dviCretime) {
        this.dviCretime = dviCretime;
    }

    public String getDviCreid() {
        return dviCreid;
    }

    public void setDviCreid(String dviCreid) {
        this.dviCreid = dviCreid;
    }

    public ZonedDateTime getDviModtime() {
        return dviModtime;
    }

    public void setDviModtime(ZonedDateTime dviModtime) {
        this.dviModtime = dviModtime;
    }

    public String getDviModid() {
        return dviModid;
    }

    public void setDviModid(String dviModid) {
        this.dviModid = dviModid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceCodeDTO deviceCodeDTO = (DeviceCodeDTO) o;
        if (deviceCodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deviceCodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeviceCodeDTO{" +
            "id=" + getId() +
            ", dviCode='" + getDviCode() + "'" +
            ", dviName='" + getDviName() + "'" +
            ", dviCretime='" + getDviCretime() + "'" +
            ", dviCreid='" + getDviCreid() + "'" +
            ", dviModtime='" + getDviModtime() + "'" +
            ", dviModid='" + getDviModid() + "'" +
            "}";
    }
}
