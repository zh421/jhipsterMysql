package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A DevicePatternIntro.
 */
@Entity
@Table(name = "device_pattern_intro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DevicePatternIntro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "devicepattern_id", length = 100, nullable = false)
    private String devicepatternId;

    @NotNull
    @Size(max = 100)
    @Column(name = "devicepattern_code", length = 100, nullable = false)
    private String devicepatternCode;

    @Size(max = 255)
    @Column(name = "memo", length = 255)
    private String memo;

    @NotNull
    @Size(max = 140)
    @Column(name = "creuser", length = 140, nullable = false)
    private String creuser;

    @NotNull
    @Column(name = "cretime", nullable = false)
    private ZonedDateTime cretime;

    @NotNull
    @Size(max = 140)
    @Column(name = "moduser", length = 140, nullable = false)
    private String moduser;

    @NotNull
    @Column(name = "modtime", nullable = false)
    private ZonedDateTime modtime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevicepatternId() {
        return devicepatternId;
    }

    public DevicePatternIntro devicepatternId(String devicepatternId) {
        this.devicepatternId = devicepatternId;
        return this;
    }

    public void setDevicepatternId(String devicepatternId) {
        this.devicepatternId = devicepatternId;
    }

    public String getDevicepatternCode() {
        return devicepatternCode;
    }

    public DevicePatternIntro devicepatternCode(String devicepatternCode) {
        this.devicepatternCode = devicepatternCode;
        return this;
    }

    public void setDevicepatternCode(String devicepatternCode) {
        this.devicepatternCode = devicepatternCode;
    }

    public String getMemo() {
        return memo;
    }

    public DevicePatternIntro memo(String memo) {
        this.memo = memo;
        return this;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreuser() {
        return creuser;
    }

    public DevicePatternIntro creuser(String creuser) {
        this.creuser = creuser;
        return this;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public ZonedDateTime getCretime() {
        return cretime;
    }

    public DevicePatternIntro cretime(ZonedDateTime cretime) {
        this.cretime = cretime;
        return this;
    }

    public void setCretime(ZonedDateTime cretime) {
        this.cretime = cretime;
    }

    public String getModuser() {
        return moduser;
    }

    public DevicePatternIntro moduser(String moduser) {
        this.moduser = moduser;
        return this;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }

    public ZonedDateTime getModtime() {
        return modtime;
    }

    public DevicePatternIntro modtime(ZonedDateTime modtime) {
        this.modtime = modtime;
        return this;
    }

    public void setModtime(ZonedDateTime modtime) {
        this.modtime = modtime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DevicePatternIntro)) {
            return false;
        }
        return id != null && id.equals(((DevicePatternIntro) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DevicePatternIntro{" +
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
