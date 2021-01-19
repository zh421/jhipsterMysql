package aiot.java.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link aiot.java.domain.EventStatcode} entity.
 */
public class EventStatcodeDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String esCode;

    @NotNull
    @Size(max = 60)
    private String esName;

    @NotNull
    private ZonedDateTime esCretime;

    @NotNull
    @Size(max = 255)
    private String esCreid;

    @NotNull
    private ZonedDateTime esModtime;

    @NotNull
    @Size(max = 255)
    private String esModid;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEsCode() {
        return esCode;
    }

    public void setEsCode(String esCode) {
        this.esCode = esCode;
    }

    public String getEsName() {
        return esName;
    }

    public void setEsName(String esName) {
        this.esName = esName;
    }

    public ZonedDateTime getEsCretime() {
        return esCretime;
    }

    public void setEsCretime(ZonedDateTime esCretime) {
        this.esCretime = esCretime;
    }

    public String getEsCreid() {
        return esCreid;
    }

    public void setEsCreid(String esCreid) {
        this.esCreid = esCreid;
    }

    public ZonedDateTime getEsModtime() {
        return esModtime;
    }

    public void setEsModtime(ZonedDateTime esModtime) {
        this.esModtime = esModtime;
    }

    public String getEsModid() {
        return esModid;
    }

    public void setEsModid(String esModid) {
        this.esModid = esModid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventStatcodeDTO eventStatcodeDTO = (EventStatcodeDTO) o;
        if (eventStatcodeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventStatcodeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventStatcodeDTO{" +
            "id=" + getId() +
            ", esCode='" + getEsCode() + "'" +
            ", esName='" + getEsName() + "'" +
            ", esCretime='" + getEsCretime() + "'" +
            ", esCreid='" + getEsCreid() + "'" +
            ", esModtime='" + getEsModtime() + "'" +
            ", esModid='" + getEsModid() + "'" +
            "}";
    }
}
