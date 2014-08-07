package net.ramso.dita.web.filters;


import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.utils.ConfManager;


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
		String absoluteDiskPath = arg0.getServletContext().getRealPath(ConfManager.PROPERTIESDIR);
		ConfManager conf = ConfManager.getInstance(absoluteDiskPath);
		Properties P = conf.getPropertiesFile(RepositoryFactory.PREFIX);
		RepositoryFactory.config(conf
				.getPropertiesFile(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getPropertiesFile(ContentFactory.PREFIX));
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}

