package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface IBook extends IAuditable<User>, IVersionable{

    Long getId();

    String getTitle();

    Float getPrice();

    String getDescription();

    Integer getPageCount();

    Boolean getIllustrations();
}
