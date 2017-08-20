package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.Authority;
import ee.valja7.experiment.springboot.hello.persistence.AuthoritiesRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AuthorityModel extends CrudModel<Authority, String, AuthoritiesRepository> {
    public AuthorityModel() {
        this.clazz = Authority.class;
    }

    @PostConstruct
    public void init() {
        this.setLazyModel(new AuthorityLazyModel(getRepository()));
    }
}
