package ee.valja7.experiment.springboot.hello.domain;

import javax.persistence.*;

@Entity
@IdClass(UserAuthoritiesId.class)
@Table(name = "UserAuthorities")
public class UserAuthorities {
    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    User user;
    @Id
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
