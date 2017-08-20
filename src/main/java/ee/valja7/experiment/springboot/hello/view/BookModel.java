package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.Book;
import ee.valja7.experiment.springboot.hello.persistence.BookRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "model", eager = true)
@RequestScoped
public class BookModel {

    @ManagedProperty(value = "#{bookRepository}")
    BookRepository bookRepository;

    public Long getCurrentBookId() {
        return currentBookId;
    }

    public void setCurrentBookId(Long id) {
        this.currentBookId = id;
    }

    private Long currentBookId;

    private Book book = new Book();


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String doCreateBook() {
        Book newBook = this.bookRepository.save(this.book);

        FacesContext.getCurrentInstance().addMessage("errors",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
                        "The book " + book.getTitle() + " has been created with id=" + newBook.getId()));
        book = new Book();
        return "index.xhtml";
    }

    public void doFindBookById() {
        book = bookRepository.findOne(currentBookId);
        book.setIllustrations(!Boolean.TRUE.equals(book.getIllustrations()));
        bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }

}
