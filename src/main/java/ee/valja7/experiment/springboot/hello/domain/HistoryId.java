package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.io.Serializable;

public class HistoryId implements Serializable {
    @Id
    Long id;
    @Id
    Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public HistoryId(Long id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public HistoryId() {
    }
}
