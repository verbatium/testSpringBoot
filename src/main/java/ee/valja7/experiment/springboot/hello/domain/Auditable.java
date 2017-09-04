package ee.valja7.experiment.springboot.hello.domain;

import ee.valja7.experiment.springboot.hello.AuditListener;

import javax.persistence.*;
import java.sql.Timestamp;

@EntityListeners({AuditListener.class})
@MappedSuperclass
public abstract class Auditable<T> extends History<T>{

    public Auditable(IAuditable<T> original) {
        super(original);
    }

    public Auditable() {
        super();
    }
}
