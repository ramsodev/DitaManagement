package net.ramso.utils;

import java.util.Properties;

import org.apache.commons.collections.functors.StringValueTransformer;
import org.tmatesoft.svn.core.SVNException;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.svn.SVNTools;

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
			String[] ps = ContentFactory.projectRoot.split("/");
			String pt= "/";
			for (String p : ps) {
				if (!p.trim().isEmpty()) {
					pt+=p;
					iFolder folder = rf.getFolder(pt);
					folder.commit();
				}
			}

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
