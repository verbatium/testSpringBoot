package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.*;

@Entity(name="Book")
@Table(name = "Books")
public class Book extends Auditable<User> implements IBook
{
    private Long id;
    private String title;
    private Float price;
    private String description;
    private Integer pageCount;
    private Boolean illustrations;
    protected Integer version;

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Book() {
    }

    public Book(IAuditable<User> original) {
        super(original);
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    @Column(nullable = true)
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    @Column(nullable = true)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Column(nullable = true)
    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    @Column(nullable = true)
    public Boolean getIllustrations() {
        return illustrations;
    }
    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", illustrations=" + illustrations +
                ", Version = " + version +
                ", Auditable=" + super.toString() +
                '}';
    }
}
