package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class UserAuthoritiesId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "username")
    User user;
    @ManyToOne
    @JoinColumn(name = "authority")
    Authority authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
