package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Book.class)
public class Book_ {
    public static volatile SingularAttribute<Book, Integer> id;

    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Long> price;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, Integer> pagecount;
    public static volatile SingularAttribute<Book, Boolean> illustrations;
    public static volatile SingularAttribute<Book, String> createdby;
    public static volatile SingularAttribute<Book, String> lastmodifiedby;
    public static volatile SingularAttribute<Book, Timestamp> createddate;
    public static volatile SingularAttribute<Book, Timestamp> lastmodifieddate;
    public static volatile SingularAttribute<Book, Integer> version;
}
