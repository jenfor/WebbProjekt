package edu.chl.hajo.jsfs.auth;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**

 @author hajo
 */
@Named
@RequestScoped
public class AuthBean implements Serializable {

    private User user;
    @Getter
    @Setter
    @NotNull(message = "{common.notEmpty}")
    @Size(min = 3, max = 20, message = "{user.name}")
    private String username;
    @Getter
    @Setter
    @NotNull(message = "{common.notEmpty}")
    @Size(min = 3, max = 20, message = "{user.password}")
    private String password;
    @Getter
    private String loginMessage = "";

    // Handled by GlassFish file realm
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            // Only login in not logged in
            if (request.getUserPrincipal() != null) {
                return "login-success";
            }
            request.login(username, password);
            if (request.isUserInRole("productManager")) {
                user = new User(username);
                Logger.getAnonymousLogger().log(Level.INFO, "Successfully logged in {0}", username);
                return "login-success";
            } else {
                return "login-fail";
            }
        } catch (ServletException e) {
            // If login fail an exception is thrown

            // FacesMessage is a dysfunctional ... replace with simple property
            //FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Bad username or password.", null);
            //context.addMessage(null, m);
            loginMessage = "Bad username or password.";
            return "login-fail";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(AuthBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        user = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // Here it does work????
        return "logout-success";
    }

    public boolean isLoggedin() {
        return user != null;
    }
}
