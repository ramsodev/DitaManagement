package net.ramso.dita.beans.themes;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class ThemeSwitcherView {

	@ManagedProperty("#{themeService}")
	private ThemeService service;

	private Theme theme;

	private List<Theme> themes;

	public Theme currentTheme() {
		final ExternalContext externalContext = FacesContext
				.getCurrentInstance().getExternalContext();
		final String param = externalContext
				.getInitParameter("primefaces.THEME");
		if (param != null) {
			for (final Theme t : getThemes()) {
				if (t.getName().equals(param)) {
					theme = t;
					return t;
				}
			}

		}
		return getThemes().get(0);
	}

	public Theme getTheme() {
		return theme;
	}

	public List<Theme> getThemes() {

		return themes;
	}

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public void setService(ThemeService service) {
		this.service = service;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}