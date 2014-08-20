package net.ramso.dita.security;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import net.ramso.utils.LogManager;
import net.ramso.utils.Messages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.WebUtils;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private static final String DEFAULTTHEME = "glass-x";

	private static final long serialVersionUID = -2152389656664659476L;

	private String clave;
	private boolean logeado = false;
	private String nombre;
	private boolean remember;
	private String theme = "glass-x";

	public void continuar(String page) {
		FacesMessage msg = null;
		final ExternalContext externalContext = FacesContext
				.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(page);
		} catch (final IOException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					Messages.getString("LoginBean.message.error.login"), //$NON-NLS-1$
					Messages.getString("LoginBean.message.error.login.detail")); //$NON-NLS-1$
			FacesContext.getCurrentInstance().addMessage(null, msg);
			LogManager.error(
					Messages.getString("LoginBean.log.message.error.login"), e); //$NON-NLS-1$
		}
	}

	public boolean estaLogeado() {
		return logeado;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTheme() {
		if (theme == null) {
			theme = LoginBean.DEFAULTTHEME;
		}
		return theme;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public boolean isRemember() {
		return remember;
	}

	public void login(ActionEvent actionEvent) {
		SecurityUtils.getSubject()
		.login(new UsernamePasswordToken(getNombre(), getClave(),
				isRemember()));
		WebUtils.getAndClearSavedRequest((ServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest());
		logeado = SecurityUtils.getSubject().isAuthenticated();
		final RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (logeado) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					Messages.getString("LoginBean.message.welcome"), //$NON-NLS-1$
					nombre);
			LogManager.info(Messages.getString(
					"LoginBean.log.message.access", nombre)); //$NON-NLS-1$
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					Messages.getString("LoginBean.message.error"), //$NON-NLS-1$
					Messages.getString("LoginBean.message.error.details")); //$NON-NLS-1$
			LogManager.warn(Messages.getString(
					"LoginBean.log.message.error", nombre)); //$NON-NLS-1$
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado) {
			continuar("home.xhtml");
		}
	}

	public void logout() {
		SecurityUtils.getSubject().logout();
		final HttpSession session = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		logeado = false;
		continuar("login.xhtml");
		LogManager.info(Messages.getString(
				"LoginBean.log.message.logout", nombre)); //$NON-NLS-1$
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}