package net.ramso.utils;

import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.content.ContentFactory;

public class ApplicationConfiguration {

	public ApplicationConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		ConfManager conf = ConfManager.getInstance();
		Properties P = conf.getPropertiesFile(RepositoryFactory.PREFIX);
		RepositoryFactory.config(conf
				.getPropertiesFile(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getPropertiesFile(ContentFactory.PREFIX));
	}

	public void setupRepository() {
		try {
			IRepository rf = RepositoryFactory.getRepositoryInstance();
			rf.connect();			
			rf.addChild(rf.getFolder(ContentFactory.projectRoot));
			rf.addChild( rf.getFolder(ContentFactory.templatesRoot));
			rf.addChild(rf.getFolder(ContentFactory.componentsRoot));
			rf.commit();
			rf.disconnect();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
