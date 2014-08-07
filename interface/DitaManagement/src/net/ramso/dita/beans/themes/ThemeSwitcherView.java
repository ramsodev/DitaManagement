package net.ramso.dita.beans.themes;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class ThemeSwitcherView {

	private Theme theme;

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	private List<Theme> themes;

	@ManagedProperty("#{themeService}")
	private ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		
		return themes;
	}

	public void setService(ThemeService service) {
		this.service = service;
	}

	public  Theme currentTheme() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String param = externalContext.getInitParameter("primefaces.THEME");
		if (param != null) {
			for (Theme t : getThemes()) {
				if(t.getName().equals(param)){
					theme = t;
					return t;
				}
			}
			
		}
		return getThemes().get(0);
	}
}