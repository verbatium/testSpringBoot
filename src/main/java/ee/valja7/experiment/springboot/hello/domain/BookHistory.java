package ee.valja7.experiment.springboot.hello.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "BookHistory")
@Table(name = "BookHistory")
@IdClass(HistoryId.class)
public class BookHistory extends History<User> implements IBook, Serializable {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    private String title;
    private Float price;
    private String description;
    private Integer pageCount;
    private Boolean illustrations;
    private Integer version;

    public BookHistory() {
        super();
    }

    @Id
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BookHistory(IBook original) {
        super(original);
        this.id = original.getId();
        this.description = original.getDescription();
        this.title = original.getTitle();
        this.illustrations = original.getIllustrations();
        this.pageCount = original.getPageCount();
        this.price = original.getPrice();
        this.version = original.getVersion();
    }

    @Id
    @Override
    public Long getId() {
        return id;
    }

    @Transient
    public boolean isNew() {
        return true;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getPageCount() {
        return pageCount;
    }

    @Override
    public Boolean getIllustrations() {
        return illustrations;
    }
}
