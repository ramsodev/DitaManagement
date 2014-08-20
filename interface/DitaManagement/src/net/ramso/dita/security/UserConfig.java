package net.ramso.dita.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserConfig {

	private String group;
	private String theme;
	private String user;

	public String getGroup() {
		return group;
	}

	public String getTheme() {
		return theme;
	}

	public String getUser() {
		return user;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
