package net.ramso.dita.beans.admin;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import net.ramso.dita.beans.config.ConfigData;
import net.ramso.dita.beans.config.ConfigDataTools;
import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.Messages;

public abstract class AbstractConfigEditor {
	
	protected List<ConfigData> configs;

	protected String key;
	protected String name;
	protected List<ConfigData> selected;

	public void add() {
		configs.add(new ConfigData("", ""));
		ApplicationMessage
		.setInfo(
				Messages.getString("ConfigEditorBean.message.add"), Messages.getString("ConfigEditorBean.message.add.detail")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	public void delete() {
		ApplicationMessage
		.setInfo(
				Messages.getString("ConfigEditorBean.message.delete"), selected.size() + Messages.getString("ConfigEditorBean.message.delete.detail")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	

	public List<ConfigData> getConfigs() {
		if (configs == null) {
			initProperties();
		}
		return configs;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public List<ConfigData> getSelected() {
		return selected;
	}

	public void initProperties() {
		final ConfManager conf = ConfManager.getInstance();
		final Properties pro = conf.getPropertiesFile(key);
		configs = ConfigDataTools.getFromProperties(pro);
	}

	public void refresh() {
		initProperties();
		ApplicationMessage
		.setInfo(
				Messages.getString("ConfigEditorBean.message.refresh"), Messages.getString("ConfigEditorBean.message.refresh.detail")); //$NON-NLS-1$ //$NON-NLS-2$

	}

	public void save() {
		final Properties properties = new Properties();
		for (final ConfigData configData : configs) {
			properties.setProperty(configData.getKey(), configData.getValue());
		}
		try {
			ConfManager.getInstance().update(key, properties);
		} catch (final ConfigException e) {
			ApplicationMessage.setError(
					Messages.getString("ConfigEditorBean.message.save"), e); //$NON-NLS-1$
		}
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public void setSelected(List<ConfigData> selected) {
		this.selected = selected;
	}


}
