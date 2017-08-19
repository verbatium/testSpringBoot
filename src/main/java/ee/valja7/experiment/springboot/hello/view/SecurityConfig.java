package ee.valja7.experiment.springboot.hello.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("configureGlobal");
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configure");
        http.authorizeRequests()
                .antMatchers("/resources/**", "/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()  //login configuration
                .loginPage("/appLogin").permitAll()
                .loginProcessingUrl("/appLogin").permitAll()
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/appLogin")
                .successHandler(loginSuccessHandler())
                .failureHandler(failureHandler())
                .and().logout()    //logout configuration
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/appLogin").permitAll()
                .logoutSuccessHandler(logoutSuccessHandler())
        ;

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    //our beans for handlers
    @Bean
    public SimpleUrlLogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new LoginFailureHandler();
    }
}