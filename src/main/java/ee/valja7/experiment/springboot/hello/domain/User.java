package ee.valja7.experiment.springboot.hello.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    private String username;
    @NotEmpty(message = "*Please provide your password")
    private String password;


    @Transient
    private Boolean accountNonExpired = true;
    @Transient
    private Boolean accountNonLocked = true;
    @Transient
    private Boolean credentialsNonExpired = true;
    @Transient
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserAuthorities", joinColumns = {
            @JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "authority",
                    nullable = false, updatable = false)})
    private Set<Authority> authorities = new HashSet();

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
