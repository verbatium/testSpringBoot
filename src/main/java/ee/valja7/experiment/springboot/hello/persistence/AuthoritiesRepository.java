package ee.valja7.experiment.springboot.hello.persistence;

import ee.valja7.experiment.springboot.hello.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, String> {
}
