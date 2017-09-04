package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
public abstract class History<T> implements IAuditable<T> , IVersionable{
    private Timestamp createdDate;
    private T createdBy;
    private Timestamp lastModifiedDate;
    private T lastModifiedBy;


    public History(IAuditable<T> original) {
        this.createdBy=original.getCreatedBy();
        this.createdDate=original.getCreatedDate();
        this.lastModifiedBy=original.getLastModifiedBy();
        this.lastModifiedDate=original.getLastModifiedDate();
    }

    public History() {
    }

    @Override
    @Basic
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "createdBy")
    public T getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    @Basic
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "lastModifiedBy")
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
                '}';
    }
}
