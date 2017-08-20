package ee.valja7.experiment.springboot.hello.view;

import ee.valja7.experiment.springboot.hello.domain.Authority;
import ee.valja7.experiment.springboot.hello.domain.User;
import ee.valja7.experiment.springboot.hello.persistence.UserAuthoritiesRepository;
import ee.valja7.experiment.springboot.hello.persistence.UsersRepository;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("userModel")
@ViewScoped
public class UserModel extends CrudModel<User, String, UsersRepository> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserModel.class);
    @Inject
    UserAuthoritiesRepository userAuthoritiesRepository;

    private String newPassword;
    private boolean refreshAuthoritiesViaUserauthoritiesByAuthority;
    // rendering of add button
    private boolean showAddAuthoritiesDialog = false;

    public UserModel() {
        this.clazz = User.class;
    }

    @PostConstruct
    public void init() {
        setLazyModel(new UserLazyModel(getRepository()));
    }

    public boolean isNew() {
        return this.currentItem.getUsername() == null;
    }

    public String getNewPassword() {
        return "";
    }

    public void setNewPassword(String newPassword) {
        this.currentItem.setPassword(newPassword);
    }

    @Transactional
    public void addAuthorityToUser(Authority authority) {
        User one = getRepository().getOne(currentItem.getUsername());
        one.getAuthorities().add(authority);
        getRepository().save(one);
    }

    public void activateRenderAddAuthoritiesViaUserauthoritiesByAuthority() {
        showAddAuthoritiesDialog = true;
    }

    public void desactivateRenderAddAuthoritiesViaUserauthoritiesByAuthority() {
        showAddAuthoritiesDialog = false;
    }

    public void affectAndDesactivateRenderAuthoritiesViaUserauthoritiesByAuthority() {
        //usersService.addAuthoritiesViaUserauthoritiesByAuthority(currentItem, authoritiesViaUserauthoritiesByAuthority_);
        populateauthoritiesViaUserauthoritiesByAuthority();
    }

    public void disaffectAndDesactivateRenderAuthoritiesViaUserauthoritiesByAuthority() {
        //usersService.removeAuthoritiesViaUserauthoritiesByAuthority(selectedUsers, authoritiesViaUserauthoritiesByAuthority_);
        populateauthoritiesViaUserauthoritiesByAuthority();
    }

    private void populateauthoritiesViaUserauthoritiesByAuthority() {
        if (currentItem.getUsername() != null) {
            refreshAuthoritiesViaUserauthoritiesByAuthority = false;
            User mask = new User();
            mask.setUsername(currentItem.getUsername());
            //mask = modelnModelService.getManyToManyList(mask, "authoritiesViaUserauthoritiesByAuthority");
            //authoritiesViaUserauthoritiesByAuthority = new ArrayList(mask.getAuthoritiesViaUserauthoritiesByAuthority());
        }
    }


    public Boolean getShowAddAuthoritiesDialog() {
        return showAddAuthoritiesDialog;
    }

    public void setShowAddAuthoritiesDialog(Boolean value) {
        showAddAuthoritiesDialog = value;
    }


    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteListener(ActionEvent event) {
        try {
            if (currentItem.getUsername() != null) {
                getRepository().delete(currentItem);
                FacesMessage msg = new FacesMessage("item deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                System.out.println("no resources found");
            }
            currentItem = new User();
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage("PersistenceErrorOccured");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    public List<Authority> getAuthorities() {

        List<AuthorityView> grantedAuthorities = userAuthoritiesRepository.findByUser(currentItem);
        List<Authority> collect = grantedAuthorities.stream()
                .map(AuthorityView::getAuthority)
                .collect(Collectors.toList());
        return collect;
    }
}
