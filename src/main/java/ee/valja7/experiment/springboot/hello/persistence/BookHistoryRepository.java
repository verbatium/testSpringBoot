package ee.valja7.experiment.springboot.hello.persistence;

import ee.valja7.experiment.springboot.hello.domain.BookHistory;
import ee.valja7.experiment.springboot.hello.domain.HistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHistoryRepository extends JpaRepository<BookHistory,HistoryId>{
}
