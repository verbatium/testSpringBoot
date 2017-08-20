package ee.valja7.experiment.springboot.hello.domain;

import ee.valja7.experiment.springboot.hello.AuditListener;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners({AuditListener.class})
public abstract class Auditable<T> {
    private Timestamp createdDate;
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private T createdBy;
    private Timestamp lastModifiedDate;
    @ManyToOne
    @JoinColumn(name = "lastModifiedBy")
    private T lastModifiedBy;

    public Auditable() {
    }

    protected Auditable(Auditable<T> book) {
        this.version = book.version;
        this.createdBy = book.createdBy;
        this.lastModifiedBy = book.lastModifiedBy;
        this.createdDate = book.createdDate;
        this.lastModifiedDate = book.lastModifiedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Version
    @Column(name = "version")
    private Integer version;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public T getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public T getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(T lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "Auditable{" +
                "createdDate=" + createdDate +
                ", createdBy=" + createdBy +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", version=" + version +
                '}';
    }
}
