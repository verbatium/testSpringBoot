package ee.valja7.experiment.springboot.hello.persistence;

import ee.valja7.experiment.springboot.hello.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
    User getByUsername(String name);
}
