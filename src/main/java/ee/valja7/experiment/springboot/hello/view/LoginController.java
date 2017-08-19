package ee.valja7.experiment.springboot.hello.view;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@ManagedBean
public class LoginController {

    public void doLogin() throws ServletException, IOException {
        redirect("/appLogin");
    }

    public void doLogout() throws ServletException, IOException {
        redirect("/appLogout");
    }

    private void redirect(String address) throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher(address);
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
    }
}
