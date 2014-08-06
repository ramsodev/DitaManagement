package net.ramso.dita.web.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.utils.ConfManager;

/**
 * Application Lifecycle Listener implementation class DitaManagementContextListener
 *
 */
@WebListener
public class DitaManagementContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DitaManagementContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	String path = arg0.getServletContext().getContextPath();
    	ConfManager conf = ConfManager.getInstance();
		RepositoryFactory.config(conf
				.getPropertiesFile(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getPropertiesFile(ContentFactory.PREFIX));
		
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
