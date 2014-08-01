package net.ramso.utils;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.RepositoryFactory;

public class ApplicationConfiguration {
	

	public ApplicationConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		ConfManager conf = ConfManager.getInstance();
		RepositoryFactory.config(conf.getProperties(RepositoryFactory.PREFIX));
		ContentFactory.config(conf.getProperties("content"));
	}

}
