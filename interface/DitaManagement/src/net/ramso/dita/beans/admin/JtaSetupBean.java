package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import net.ramso.dita.beans.config.ConfigData;
import net.ramso.dita.beans.config.ConfigDataTools;
import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.filesystem.FileSystemRepository;
import net.ramso.dita.repository.svn.RepositorySVN;
import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.Messages;
import net.ramso.utils.ReflectionHelper;

@ManagedBean
@ViewScoped
public class JtaSetupBean extends AbstractConfigEditor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139165432735932613L;
	public static final String CONNETIONKEY = "openjpa.ConnectionFactoryName";
	private String dataSource;
	private ArrayList<String> dataSources;

	@PostConstruct
	public void initBean() {
		name = "repository";
		key = ConfManager.FILEPREFIX + name;

		initProperties();

	}

	@Override
	public void save() {
		final Properties properties = new Properties();
		properties.setProperty(CONNETIONKEY, getDataSource());
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

	@Override
	public void initProperties() {
		final ConfManager conf = ConfManager.getInstance();
		final Properties pro = conf.getPropertiesFile(key);
		setDataSource(pro.getProperty(CONNETIONKEY));
		pro.remove(CONNETIONKEY);
		configs = ConfigDataTools.getFromProperties(pro);
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<String> getDataSources() {
		if (dataSources == null) {
			dataSources = new ArrayList<String>();
			try {
				final javax.naming.Context ic = new javax.naming.InitialContext();
				final NamingEnumeration<NameClassPair> list = ic
						.list("java:comp/env/jdbc");
				while (list.hasMore()) {
					final javax.naming.NameClassPair nc = list.next();
					try {
						final Object obj = ic.lookup("java:comp/env/jdbc/"
								+ nc.getName());
						if (obj instanceof javax.sql.DataSource) {
							dataSources.add("java:comp/env/jdbc/" + nc.getName());
						}
					} catch (final Exception e) {
					}
				}
			} catch (final Exception e) {
			}
			try {
				final javax.naming.Context ic = new javax.naming.InitialContext();
				final NamingEnumeration<NameClassPair> list = ic
						.list("java:comp/env");
				while (list.hasMore()) {
					final javax.naming.NameClassPair nc = list.next();
					try {
						final Object obj = ic.lookup("java:comp/env/"
								+ nc.getName());
						if (obj instanceof javax.sql.DataSource) {
							dataSources.add("java:comp/env/" + nc.getName());
						}
					} catch (final Exception e) {
					}
				}
			} catch (final Exception e) {
			}
			if (dataSources.isEmpty()) {
				dataSources.add("Not Data Source found");
			}
		}
		return dataSources;
	}
}
