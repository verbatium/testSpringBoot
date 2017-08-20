package ee.valja7.experiment.springboot.hello;

import ee.valja7.experiment.springboot.hello.domain.Auditable;
import ee.valja7.experiment.springboot.hello.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


public class AuditListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    private void beforeInsert(Auditable<User> object) {
        User user = getCurrentUser();
        LOGGER.info("{} Created new object: {}", user.getUsername(), object);
        Timestamp now = Timestamp.from(Instant.now());
        object.setCreatedBy(user);
        object.setCreatedDate(now);
    }

    @PostPersist
    private void afterInsert(Auditable<User> object) {
        LOGGER.info("new object saved {}", object);
    }

    @PreUpdate
    private void beforeUpdate(Auditable<User> object) {
        User user = getCurrentUser();
        LOGGER.info("{} Updated object: {}", user.getUsername(), object);
        Timestamp now = Timestamp.from(Instant.now());
        object.setLastModifiedBy(user);
        object.setLastModifiedDate(now);
    }

    @PostUpdate
    private void afterUpdate(Auditable<User> object) {
        LOGGER.info("{} Updated object after update: {}", object);
    }

    @PreRemove
    private void beforeDelete(Object object) {
        User user = getCurrentUser();
        LOGGER.info("{} Deleted object: {}", user.getUsername(), object);
    }

    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal;
    }

}