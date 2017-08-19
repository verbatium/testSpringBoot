package ee.valja7.experiment.springboot.hello.persistence;

import ee.valja7.experiment.springboot.hello.domain.Authority;
import ee.valja7.experiment.springboot.hello.domain.User;
import ee.valja7.experiment.springboot.hello.domain.UserAuthorities;
import ee.valja7.experiment.springboot.hello.domain.UserAuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAuthoritiesRepository extends JpaRepository<UserAuthorities, UserAuthoritiesId> {
    @Query("select ua.authority from UserAuthorities ua where ua.user=:user")
    List<Authority> getAuthoritiesByUser(@Param("user") User user);
}
