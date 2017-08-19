package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.User;
import ee.valja7.experiment.springboot.hello.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown username or password");
        }
        user.getAuthorities().size();
        return user;
    }
}
