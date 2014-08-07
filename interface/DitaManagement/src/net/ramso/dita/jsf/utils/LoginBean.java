package net.ramso.dita.jsf.utils;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import net.ramso.utils.LogManager;

import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}
	private static final long serialVersionUID = -2152389656664659476L;
	private static final String DEFAULTTHEME = "glass-x";
	private String nombre;
	private String clave;
	private boolean logeado = false;
	private String theme = "glass-x";

	public String getTheme() {
		if(theme == null){
			theme = DEFAULTTHEME;
		}
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean estaLogeado() {
		return logeado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		if (nombre != null && nombre.equals("admin") && clave != null
				&& clave.equals("admin")) {
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@",
					nombre);
			LogManager.info("Acceso del usuario " + nombre + " autorizado");
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Credenciales no válidas");
			LogManager.warn("Intento de acceso erroneo para usuario " + nombre);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado)
			continuar("home.xhtml");
//			context.addCallbackParam("view", "home.xhtml");
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
		continuar("login.xhtml");
		LogManager.info("El usuario " + nombre + " sale de la aplicación");
	}
	public void continuar(String page) {
		FacesMessage msg = null;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(page);
		} catch (IOException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
					"Error al acceder a la aplicación");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			LogManager.error("Imposible acceder a la aplicación ",e);
		}
	}
}