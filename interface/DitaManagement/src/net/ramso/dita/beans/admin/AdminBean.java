package net.ramso.dita.beans.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.config.Ini;
import org.apache.shiro.io.ResourceUtils;

@ManagedBean
@ViewScoped
public class AdminBean implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -5720135148595515399L;

	private boolean config;

	private Ini ini;

	private boolean realm;

	private boolean roles;

	private String type;

	private boolean users;

	private boolean log;
	
	private boolean repo;
	
	private boolean jta;
	
	private boolean index;

	public Ini getIni() {
		return ini;
	}

	public String getType() {
		return type;
	}

	@PostConstruct
	public void init() {
		setType("");
		ini = new Ini();
		final String path = (FacesContext.getCurrentInstance()
				.getExternalContext()).getRealPath("WEB-INF/shiro.ini");
		ini.loadFromPath(ResourceUtils.FILE_PREFIX + path);
	}

	public boolean isConfig() {
		return config;
	}

	public boolean isRealm() {
		return realm;
	}

	public boolean isRoles() {
		return roles;
	}

	public boolean isUsers() {
		return users;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}

	public void setIni(Ini ini) {
		this.ini = ini;
	}

	public void setRealm(boolean realm) {
		this.realm = realm;
	}

	public void setRoles(boolean roles) {
		this.roles = roles;
	}

	public void setType(String type) {
		realm = false;
		config = false;
		users = false;
		setRoles(false);
		setLog(false);
		setRepo(false);
		setJta(false);
		setIndex(false);
		if (type.equals("config")) {
			config = true;
		} else if (type.equals("realm")) {
			realm = true;
		} else if (type.equals("users")) {
			users = true;
		} else if (type.equals("roles")) {
			setRoles(true);
		} else if (type.equals("log")){
			log=true;
		} else if(type.equals("repo")){
			repo=true;
		} else if(type.equals("jta")){
			jta=true;
		}else if(type.equals("index")){
			index=true;
		}
		this.type = type;
	}

	public void setUsers(boolean users) {
		this.users = users;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public boolean isRepo() {
		return repo;
	}

	public void setRepo(boolean repo) {
		this.repo = repo;
	}

	public boolean isJta() {
		return jta;
	}

	public void setJta(boolean jta) {
		this.jta = jta;
	}

	public boolean isIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

}
