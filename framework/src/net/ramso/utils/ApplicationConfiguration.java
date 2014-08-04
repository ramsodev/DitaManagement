package net.ramso.utils;

import java.util.ArrayList;
import java.util.Properties;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.svn.RepositorySVN;
import net.ramso.dita.repository.svn.SVNFolder;
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

			iFolder folder = rf.getFolder(ContentFactory.projectRoot);
			iFolder folder2 = rf.getFolder(ContentFactory.templatesRoot);
			iFolder folder3 = rf.getFolder(ContentFactory.componentsRoot);
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

	private void createFolder(IRepository repo, iContent content, String name)
			throws ContentException, SVNException {
		iFolder folder = repo.getFolder(name);
		// new SVNFolder(((RepositorySVN)repo).getRepository(),
		// content.getPath() + "/" + name);
		// if (folder.isNew()) {
		//
		// repo.getRoot().addChild(folder);
		// }
		iContent a = repo.getRoot();
		ArrayList<iContent> c = a.getChilds();
		repo.commit();
		// SVNTools.endCommit();

	}
}
