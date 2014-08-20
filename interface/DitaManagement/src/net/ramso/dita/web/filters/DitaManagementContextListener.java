package net.ramso.dita.web.filters;

import java.io.File;
import java.io.IOException;

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
import net.ramso.utils.PersistanceManager;

public class DitaManagementContextListener implements ServletContextListener {

	/**
	 * Constructor
	 *
	 */
	public DitaManagementContextListener() {
		super();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ContentFactory.getInstance().disconnect();
			ContentIndexer.getInstance().close();
		} catch (final RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		final String absoluteDiskPath = arg0.getServletContext().getRealPath(
				ConfManager.PROPERTIESDIR);
		final ConfManager conf = ConfManager.getInstance(absoluteDiskPath);
		try {
			PersistanceManager.init(ConfManager.ABSOLUTEDISKPATH
					+ File.separator + conf.getProperty(ConfManager.JPA),
					"dita.jpa");
		} catch (final IOException e) {
			LogManager.error("Not posible setup the JPA access", e);
		}
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
			} catch (final RepositoryException e) {
				LogManager.error(
						"Not posible to create the repository structure", e);
			} catch (final ContentException e) {
				LogManager.error(
						"Not posible to create the repository structure", e);
			} catch (final ConfigException e) {
				LogManager.error(
						"Not posible to change the application status", e);
			}
		}
	}

}
