package ee.valja7.experiment.springboot.hello.service;

import ee.valja7.experiment.springboot.hello.domain.Book;
import ee.valja7.experiment.springboot.hello.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/service/books")
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }
    
    @RequestMapping(value="/service/book/{id}", method= RequestMethod.GET)
    public @ResponseBody
    Book getBookById(@PathVariable Long id) {
        return this.bookRepository.findOne(id);
    }
}
