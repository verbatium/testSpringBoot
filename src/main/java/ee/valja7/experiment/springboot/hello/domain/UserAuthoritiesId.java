package ee.valja7.experiment.springboot.hello.domain;

import java.io.Serializable;

public class UserAuthoritiesId implements Serializable {
    User user;
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
