package net.ramso.dita.beans.tools;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.ramso.utils.LogManager;

public class ApplicationMessage {

	public static void setError(String msg, Exception e) {
		LogManager.error(msg, e);
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
				e.getLocalizedMessage());
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}

	public static void setWarn(String msg, Exception e) {
		LogManager.warn(msg, e);
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg,
				e.getLocalizedMessage());
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}
	
	public static void setInfo(String msg) {
		LogManager.info(msg);
		FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}

}
