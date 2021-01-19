package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A UnitClass.
 */
@Entity
@Table(name = "unit_class")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UnitClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "uc_code", length = 60, nullable = false)
    private String ucCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "uc_name", length = 60, nullable = false)
    private String ucName;

    @NotNull
    @Column(name = "uc_cretime", nullable = false)
    private ZonedDateTime ucCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "uc_creid", length = 255, nullable = false)
    private String ucCreid;

    @NotNull
    @Column(name = "uc_modtime", nullable = false)
    private ZonedDateTime ucModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "uc_modid", length = 255, nullable = false)
    private String ucModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUcCode() {
        return ucCode;
    }

    public UnitClass ucCode(String ucCode) {
        this.ucCode = ucCode;
        return this;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }

    public String getUcName() {
        return ucName;
    }

    public UnitClass ucName(String ucName) {
        this.ucName = ucName;
        return this;
    }

    public void setUcName(String ucName) {
        this.ucName = ucName;
    }

    public ZonedDateTime getUcCretime() {
        return ucCretime;
    }

    public UnitClass ucCretime(ZonedDateTime ucCretime) {
        this.ucCretime = ucCretime;
        return this;
    }

    public void setUcCretime(ZonedDateTime ucCretime) {
        this.ucCretime = ucCretime;
    }

    public String getUcCreid() {
        return ucCreid;
    }

    public UnitClass ucCreid(String ucCreid) {
        this.ucCreid = ucCreid;
        return this;
    }

    public void setUcCreid(String ucCreid) {
        this.ucCreid = ucCreid;
    }

    public ZonedDateTime getUcModtime() {
        return ucModtime;
    }

    public UnitClass ucModtime(ZonedDateTime ucModtime) {
        this.ucModtime = ucModtime;
        return this;
    }

    public void setUcModtime(ZonedDateTime ucModtime) {
        this.ucModtime = ucModtime;
    }

    public String getUcModid() {
        return ucModid;
    }

    public UnitClass ucModid(String ucModid) {
        this.ucModid = ucModid;
        return this;
    }

    public void setUcModid(String ucModid) {
        this.ucModid = ucModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitClass)) {
            return false;
        }
        return id != null && id.equals(((UnitClass) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UnitClass{" +
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
