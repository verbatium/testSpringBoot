package ee.valja7.experiment.springboot.hello.persistence;

import ee.valja7.experiment.springboot.hello.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
