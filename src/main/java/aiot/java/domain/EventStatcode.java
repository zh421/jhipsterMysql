package aiot.java.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.ZonedDateTime;

/**
 * A EventStatcode.
 */
@Entity
@Table(name = "event_statcode")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EventStatcode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "es_code", length = 60, nullable = false)
    private String esCode;

    @NotNull
    @Size(max = 60)
    @Column(name = "es_name", length = 60, nullable = false)
    private String esName;

    @NotNull
    @Column(name = "es_cretime", nullable = false)
    private ZonedDateTime esCretime;

    @NotNull
    @Size(max = 255)
    @Column(name = "es_creid", length = 255, nullable = false)
    private String esCreid;

    @NotNull
    @Column(name = "es_modtime", nullable = false)
    private ZonedDateTime esModtime;

    @NotNull
    @Size(max = 255)
    @Column(name = "es_modid", length = 255, nullable = false)
    private String esModid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEsCode() {
        return esCode;
    }

    public EventStatcode esCode(String esCode) {
        this.esCode = esCode;
        return this;
    }

    public void setEsCode(String esCode) {
        this.esCode = esCode;
    }

    public String getEsName() {
        return esName;
    }

    public EventStatcode esName(String esName) {
        this.esName = esName;
        return this;
    }

    public void setEsName(String esName) {
        this.esName = esName;
    }

    public ZonedDateTime getEsCretime() {
        return esCretime;
    }

    public EventStatcode esCretime(ZonedDateTime esCretime) {
        this.esCretime = esCretime;
        return this;
    }

    public void setEsCretime(ZonedDateTime esCretime) {
        this.esCretime = esCretime;
    }

    public String getEsCreid() {
        return esCreid;
    }

    public EventStatcode esCreid(String esCreid) {
        this.esCreid = esCreid;
        return this;
    }

    public void setEsCreid(String esCreid) {
        this.esCreid = esCreid;
    }

    public ZonedDateTime getEsModtime() {
        return esModtime;
    }

    public EventStatcode esModtime(ZonedDateTime esModtime) {
        this.esModtime = esModtime;
        return this;
    }

    public void setEsModtime(ZonedDateTime esModtime) {
        this.esModtime = esModtime;
    }

    public String getEsModid() {
        return esModid;
    }

    public EventStatcode esModid(String esModid) {
        this.esModid = esModid;
        return this;
    }

    public void setEsModid(String esModid) {
        this.esModid = esModid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventStatcode)) {
            return false;
        }
        return id != null && id.equals(((EventStatcode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EventStatcode{" +
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
