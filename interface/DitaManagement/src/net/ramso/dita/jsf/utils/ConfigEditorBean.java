package net.ramso.dita.jsf.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;

import org.primefaces.event.CellEditEvent;

@ManagedBean
@SessionScoped
public class ConfigEditorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5161871701960982098L;
	private ArrayList<ConfigData> configs;

	@PostConstruct
	public void init() {
		System.out.println("inicia");
		ConfManager conf = ConfManager.getInstance();
		HashMap<String, Properties> properties = conf.getPropertiesFile();
		// Set<String> keys = properties.keySet();
		Properties pro = properties.get(ConfManager.FILEPREFIX+ConfManager.LOGPREFIX);

		configs = new ArrayList<ConfigData>();
		Enumeration<?> names = pro.propertyNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			configs.add(new ConfigData(key, pro.getProperty(key)));
		}

	}

	public ArrayList<ConfigData> getConfigs() {
		return configs;
	}

	public void onCellEdit(CellEditEvent event) {
		String oldValue = (String) event.getOldValue();
		String newValue = (String) event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void save() {
		Properties properties = new Properties();
		for (ConfigData configData : configs) {
			properties.setProperty(configData.getKey(), configData.getValue());
		}
		try {
			ConfManager.getInstance().update(ConfManager.FILEPREFIX+ConfManager.LOGPREFIX,properties);
		} catch (ConfigException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Properties Store",e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void add(){
		configs.add(new ConfigData("key", "Value"));
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Añadida", "key,value");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
