package ee.valja7.experiment.springboot.hello.domain;

import ee.valja7.experiment.springboot.hello.AuditListener;

import javax.persistence.*;

@Entity
@EntityListeners({AuditListener.class})
@Table(name = "Books")
public class Book extends Auditable<User> {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private Float price;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Integer pageCount;
    @Column(nullable = true)
    private Boolean illustrations;

    public Book() {
        super();
    }

    public Book(Book book) {
        super(book);
        this.id = book.id;
        this.description = book.description;
        this.illustrations = book.illustrations;
        this.pageCount = book.pageCount;
        this.price = book.price;
        this.title = book.title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }
}
