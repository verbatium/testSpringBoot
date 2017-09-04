package ee.valja7.experiment.springboot.hello;

import ee.valja7.experiment.springboot.hello.domain.*;
import ee.valja7.experiment.springboot.hello.persistence.BookHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

public class AuditListener extends TransactionSynchronizationAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditListener.class);

    @Autowired
    BookHistoryRepository bookHistoryRepository;
    @PersistenceContext private EntityManager em;


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
        saveHistory((IBook) object, object.getVersion());
        LOGGER.info("new object saved {}", object);
    }

    @PreUpdate
    private void beforeUpdate(Auditable<User> object) {
        User user = getCurrentUser();
        LOGGER.info("{} Updated object: {}", user.getUsername(), object);
        Timestamp now = Timestamp.from(Instant.now());
        object.setLastModifiedBy(user);
        object.setLastModifiedDate(now);


        saveHistory((IBook) object, object.getVersion() + 1);

    }

    private void saveHistory(IBook object, int version) {
        BookHistory history = new BookHistory(object);
        history.setVersion(version);
        BookHistoryRepository bookHistoryRepository = BeanUtil.getBean(BookHistoryRepository.class);
        bookHistoryRepository.save(history);
    }

    @PostUpdate
    public void afterUpdate(Auditable<User> object) {
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