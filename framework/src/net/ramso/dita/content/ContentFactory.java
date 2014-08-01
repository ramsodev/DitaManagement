/**
 * 
 */
package net.ramso.dita.content;

import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.iFolder;

/**
 * @author jescudero
 *
 */
public class ContentFactory {

	public static String projectRoot = "/project";
	public static String templatesRoot = "/General/templates";
	public static String componentsRoot = "/General/Componentes";
	private IRepository repository;

	/**
	 * 
	 */
	public ContentFactory() {
	}

	public void createProject(String name) {

	}

	public iFolder getProject(String name) {
		return null;

	}

	public iFolder getProjects() throws ContentException, RepositoryException {
		return getRepository().getFolder(projectRoot);
	}

	public iFolder getTemplates() throws ContentException, RepositoryException {
		return getRepository().getFolder(templatesRoot);
	}

	public iFolder getComponents() throws ContentException, RepositoryException {
		return getRepository().getFolder(componentsRoot);
	}

	protected IRepository getRepository() throws RepositoryException {
		if (repository == null) {
			repository = RepositoryFactory.getRepositoryInstance();
		}
		return repository;
	}

	public static void config(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
