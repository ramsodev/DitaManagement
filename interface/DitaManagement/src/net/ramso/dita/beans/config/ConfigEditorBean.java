package net.ramso.dita.beans.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.Messages;

@ManagedBean
@SessionScoped
public class ConfigEditorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5161871701960982098L;
	private ArrayList<ConfigData> configs;

	private List<ConfigData> selected;
	private String name;
	private String key;

	public void init() {
		ConfManager conf = ConfManager.getInstance();
		Properties pro = conf.getPropertiesFile(key);
		configs = new ArrayList<ConfigData>();
		Enumeration<?> names = pro.propertyNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			configs.add(new ConfigData(key, pro.getProperty(key)));
		}

	}

	public ArrayList<ConfigData> getConfigs() {
		if (configs == null) {
			init();
		}
		return configs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		name=Messages.getString(key);
		this.key = key;
		configs=null;
	}

	public List<ConfigData> getSelected() {
		return selected;
	}

	public void setSelected(List<ConfigData> selected) {
		this.selected = selected;
	}

	public void save() {
		Properties properties = new Properties();
		for (ConfigData configData : configs) {
			properties.setProperty(configData.getKey(), configData.getValue());
		}
		try {
			ConfManager.getInstance().update(name, properties);
		} catch (ConfigException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("ConfigEditorBean.message.save"), e.getMessage()); //$NON-NLS-1$
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void add() {
		configs.add(new ConfigData("", ""));
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Messages.getString("ConfigEditorBean.message.add"), Messages.getString("ConfigEditorBean.message.add.detail")); //$NON-NLS-1$ //$NON-NLS-2$
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void refresh() {
		init();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Messages.getString("ConfigEditorBean.message.refresh"), Messages.getString("ConfigEditorBean.message.refresh.detail")); //$NON-NLS-1$ //$NON-NLS-2$
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void delete() {
		configs.removeAll(selected);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				Messages.getString("ConfigEditorBean.message.delete"), selected.size() + Messages.getString("ConfigEditorBean.message.delete.detail")); //$NON-NLS-1$ //$NON-NLS-2$
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
