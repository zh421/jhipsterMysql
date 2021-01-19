package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A DeviceCode.
 */
@Entity
@Table(name = "device_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeviceCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "dvi_code", length = 60, nullable = false)
    private String dviCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "dvi_name", length = 60, nullable = false)
    private String dviName;

    @NotNull
    @Column(name = "dvi_cretime", nullable = false)
    private ZonedDateTime dviCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "dvi_creid", length = 255, nullable = false)
    private String dviCreid;

    @NotNull
    @Column(name = "dvi_modtime", nullable = false)
    private ZonedDateTime dviModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "dvi_modid", length = 255, nullable = false)
    private String dviModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDviCode() {
        return dviCode;
    }

    public DeviceCode dviCode(String dviCode) {
        this.dviCode = dviCode;
        return this;
    }

    public void setDviCode(String dviCode) {
        this.dviCode = dviCode;
    }

    public String getDviName() {
        return dviName;
    }

    public DeviceCode dviName(String dviName) {
        this.dviName = dviName;
        return this;
    }

    public void setDviName(String dviName) {
        this.dviName = dviName;
    }

    public ZonedDateTime getDviCretime() {
        return dviCretime;
    }

    public DeviceCode dviCretime(ZonedDateTime dviCretime) {
        this.dviCretime = dviCretime;
        return this;
    }

    public void setDviCretime(ZonedDateTime dviCretime) {
        this.dviCretime = dviCretime;
    }

    public String getDviCreid() {
        return dviCreid;
    }

    public DeviceCode dviCreid(String dviCreid) {
        this.dviCreid = dviCreid;
        return this;
    }

    public void setDviCreid(String dviCreid) {
        this.dviCreid = dviCreid;
    }

    public ZonedDateTime getDviModtime() {
        return dviModtime;
    }

    public DeviceCode dviModtime(ZonedDateTime dviModtime) {
        this.dviModtime = dviModtime;
        return this;
    }

    public void setDviModtime(ZonedDateTime dviModtime) {
        this.dviModtime = dviModtime;
    }

    public String getDviModid() {
        return dviModid;
    }

    public DeviceCode dviModid(String dviModid) {
        this.dviModid = dviModid;
        return this;
    }

    public void setDviModid(String dviModid) {
        this.dviModid = dviModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceCode)) {
            return false;
        }
        return id != null && id.equals(((DeviceCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeviceCode{" +
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
