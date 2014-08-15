package net.ramso.dita.web.filters;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.dita.repository.content.ContentIndexer;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.ApplicationStatus;
import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;

public class DitaManagementContextListener implements ServletContextListener {

	/**
	 * Constructor
	 * 
	 */
	public DitaManagementContextListener() {
		super();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String absoluteDiskPath = arg0.getServletContext().getRealPath(
				ConfManager.PROPERTIESDIR);
		ConfManager conf = ConfManager.getInstance(absoluteDiskPath);
		Properties P = conf.getPropertiesFile(RepositoryFactory.PREFIX);
		RepositoryFactory.config(conf
				.getPropertiesFile(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getPropertiesFile(ContentFactory.PREFIX));
		String indexdir = conf.getIndexDir();
		if (!indexdir.startsWith("/")) {
			indexdir = arg0.getServletContext().getRealPath(indexdir);
		}
		ContentIndexer.configure(indexdir);
		if (conf.getStatus().equalsIgnoreCase(
				ApplicationStatus.SETUP_PENDING.getStatus())) {
			try {
				ContentFactory.getInstance().populate();
				conf.setStatus(ApplicationStatus.CONFIGURED);
			} catch (RepositoryException e) {
				LogManager.error(
						"Not posible to create the repository structure", e);
			} catch (ContentException e) {
				LogManager.error(
						"Not posible to create the repository structure", e);
			} catch (ConfigException e) {
				LogManager.error(
						"Not posible to change the application status", e);
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ContentFactory.getInstance().disconnect();
			ContentIndexer.getInstance().close();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
