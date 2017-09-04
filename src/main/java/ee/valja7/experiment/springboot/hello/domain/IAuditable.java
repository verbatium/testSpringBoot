package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

public interface IAuditable<T> {
    Timestamp getCreatedDate();
    T getCreatedBy();
    Timestamp getLastModifiedDate();
    T getLastModifiedBy();
}
