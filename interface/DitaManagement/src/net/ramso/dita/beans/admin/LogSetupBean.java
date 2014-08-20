package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.LogTypes;
import net.ramso.utils.Messages;

@ManagedBean
@ViewScoped
public class LogSetupBean extends AbstractConfigEditor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139165432735932613L;
	private String type;
	private String appname;
	private String filename;

	@PostConstruct
	public void initBean(){
		key = ConfManager.FILEPREFIX
				+ ConfManager.LOGPREFIX;
		name = Messages.getString(key);
		type = ConfManager.getInstance().getProperty(ConfManager.LOG_TYPE);
		appname = ConfManager.getInstance().getProperty(ConfManager.LOGPREFIX + "." + "name");
		filename = ConfManager.getInstance().getProperty(ConfManager.FILEPREFIX
				+ ConfManager.LOGPREFIX);
		initProperties();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String[] getLogTypes(){
		LogTypes[] ts = LogTypes.values();
		String[] values = new String[ts.length];
		for(int i =0;i<ts.length;i++){
			values[i] = ts[i].getType();
		}
		return values;
	}

	@Override
	public void save() {
		final Properties properties = new Properties();
		
			properties.setProperty(ConfManager.LOG_TYPE, getType());
			properties.setProperty(ConfManager.LOGPREFIX + "." + "name", getAppname());
		
		try {
			ConfManager.getInstance().update(ConfManager.LOGPREFIX, properties);
		} catch (final ConfigException e) {
			ApplicationMessage.setError(
					Messages.getString("ConfigEditorBean.message.save"), e); //$NON-NLS-1$
		}
		super.save();
	}
	
}
