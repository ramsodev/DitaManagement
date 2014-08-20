package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
public class RepoSetupBean extends AbstractConfigEditor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139165432735932613L;
	public static final String FACTORYKEY = "repository.factory";
	public String factory;
	private String[] repositories;

	@PostConstruct
	public void initBean() {
		name = "repository";
		key = ConfManager.FILEPREFIX + name;

		initProperties();

	}

	@Override
	public void save() {
		final Properties properties = new Properties();
		properties.setProperty(FACTORYKEY, getFactory());
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
		setFactory(pro.getProperty(FACTORYKEY));
		pro.remove(FACTORYKEY);
		configs = ConfigDataTools.getFromProperties(pro);
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String[] getRepositories() {
		if (repositories == null) {
			repositories = new String[2];
			repositories[0] = FileSystemRepository.class.getName();
			repositories[1] = RepositorySVN.class.getName();
//			final List<Class<?>> processorCandidates = ReflectionHelper
//					.findClassesImpmenenting(IRepository.class,
//							IRepository.class.getPackage());
//			repositories = new String[processorCandidates.size()];
//			int i = 0;
//			for (Class clazz : processorCandidates) {
//				repositories[i] = clazz.getName(); 
//			}
		}
		return repositories;
	}
}
