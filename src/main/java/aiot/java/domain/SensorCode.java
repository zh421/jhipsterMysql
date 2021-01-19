package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A SensorCode.
 */
@Entity
@Table(name = "sensor_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SensorCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "sc_code", length = 60, nullable = false)
    private String scCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "sc_name", length = 60, nullable = false)
    private String scName;

    @NotNull
    @Column(name = "sc_cretime", nullable = false)
    private ZonedDateTime scCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "sc_creid", length = 255, nullable = false)
    private String scCreid;

    @NotNull
    @Column(name = "sc_modtime", nullable = false)
    private ZonedDateTime scModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "sc_modid", length = 255, nullable = false)
    private String scModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScCode() {
        return scCode;
    }

    public SensorCode scCode(String scCode) {
        this.scCode = scCode;
        return this;
    }

    public void setScCode(String scCode) {
        this.scCode = scCode;
    }

    public String getScName() {
        return scName;
    }

    public SensorCode scName(String scName) {
        this.scName = scName;
        return this;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public ZonedDateTime getScCretime() {
        return scCretime;
    }

    public SensorCode scCretime(ZonedDateTime scCretime) {
        this.scCretime = scCretime;
        return this;
    }

    public void setScCretime(ZonedDateTime scCretime) {
        this.scCretime = scCretime;
    }

    public String getScCreid() {
        return scCreid;
    }

    public SensorCode scCreid(String scCreid) {
        this.scCreid = scCreid;
        return this;
    }

    public void setScCreid(String scCreid) {
        this.scCreid = scCreid;
    }

    public ZonedDateTime getScModtime() {
        return scModtime;
    }

    public SensorCode scModtime(ZonedDateTime scModtime) {
        this.scModtime = scModtime;
        return this;
    }

    public void setScModtime(ZonedDateTime scModtime) {
        this.scModtime = scModtime;
    }

    public String getScModid() {
        return scModid;
    }

    public SensorCode scModid(String scModid) {
        this.scModid = scModid;
        return this;
    }

    public void setScModid(String scModid) {
        this.scModid = scModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorCode)) {
            return false;
        }
        return id != null && id.equals(((SensorCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SensorCode{" +
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
