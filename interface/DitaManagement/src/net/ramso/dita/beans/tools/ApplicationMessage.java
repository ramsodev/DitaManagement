package net.ramso.dita.beans.tools;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.ramso.utils.LogManager;

public class ApplicationMessage {

	public static void setError(String msg, Exception e) {
		LogManager.error(msg, e);
		String msgd = "";
		if (e != null) {
			msgd = e.getLocalizedMessage();
		}
		final FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, msgd);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}

	public static void setInfo(String msg) {
		ApplicationMessage.setInfo(msg, "");
	}

	public static void setInfo(String msg, String details) {
		LogManager.info(msg);
		final FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, details);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}

	public static void setWarn(String msg, Exception e) {
		LogManager.warn(msg, e);
		String msgd = "";
		if (e != null) {
			msgd = e.getLocalizedMessage();
		}
		final FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN,
				msg, msgd);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}

}
