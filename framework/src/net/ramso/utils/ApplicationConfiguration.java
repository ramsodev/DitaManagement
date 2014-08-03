package net.ramso.utils;

import java.util.Properties;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.RepositoryFactory;

public class ApplicationConfiguration {
	

	public ApplicationConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		ConfManager conf = ConfManager.getInstance();
		Properties P = conf.getPropertiesFile(RepositoryFactory.PREFIX);
		RepositoryFactory.config(conf.getPropertiesFile(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getPropertiesFile(ContentFactory.PREFIX));
	}

}
