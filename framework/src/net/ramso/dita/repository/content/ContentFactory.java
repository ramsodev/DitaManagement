/**
 *
 */
package net.ramso.dita.repository.content;

import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.Messages;

/**
 * @author jescudero
 *
 */
public class ContentFactory {
	private static ContentFactory cf;
	public static final String COMPONENTSLABEL = Messages
			.getString("ContentFactory.label.component"); //$NON-NLS-1$
	public static String componentsRoot = "/General/Componentes"; //$NON-NLS-1$
	public static final String PREFIX = "content"; //$NON-NLS-1$
	public static final String PROJECTLABEL = Messages
			.getString("ContentFactory.label.project"); //$NON-NLS-1$
	public static String projectRoot = "/project"; //$NON-NLS-1$
	public static final String TEMPLATESLABEL = Messages
			.getString("ContentFactory.label.template"); //$NON-NLS-1$
	public static String templatesRoot = "/General/templates"; //$NON-NLS-1$

	public static void config(Properties properties) {
		ContentFactory.projectRoot = properties
				.getProperty(ContentFactory.PREFIX + ".path.projects"); //$NON-NLS-1$
		ContentFactory.templatesRoot = properties
				.getProperty(ContentFactory.PREFIX + ".path.templates"); //$NON-NLS-1$
		ContentFactory.componentsRoot = properties
				.getProperty(ContentFactory.PREFIX + ".path.components"); //$NON-NLS-1$
	}

	public static ContentFactory getInstance() {
		if (ContentFactory.cf == null) {
			ContentFactory.cf = new ContentFactory();
		}
		return ContentFactory.cf;
	}

	private IRepository repository;

	/**
	 *
	 */
	protected ContentFactory() {
	}

	public void commit() throws ContentException, RepositoryException {
		getRepository().commit();
	}

	public iFile createFile(String name) throws ContentException,
	RepositoryException {
		final iFile f = getFile(name);
		getRepository().addChild(f);

		return f;
	}

	public iFolder createFolder(String name) throws ContentException,
	RepositoryException {
		final iFolder f = getFolder(name);
		getRepository().addChild(f);
		return f;
	}

	public iFolder createProject(String name) throws ContentException,
	RepositoryException {
		final iFolder f = getProject(name);
		getRepository().addChild(f);
		return f;
	}

	public void disconnect() throws RepositoryException {
		getRepository().disconnect();

	}

	public iFolder getComponents() throws ContentException, RepositoryException {
		return getRepository().getFolder(ContentFactory.componentsRoot);
	}

	public iFile getFile(String path) throws ContentException,
	RepositoryException {
		return getRepository().getFile(path);

	}

	public iFolder getFolder(String name) throws ContentException,
	RepositoryException {
		return getRepository().getFolder(name);
	}

	public iFolder getProject(String name) throws ContentException,
	RepositoryException {
		return getRepository().getFolder(
				ContentFactory.projectRoot + "/" + name); //$NON-NLS-1$

	}

	public iFolder getProjects() throws ContentException, RepositoryException {
		return getRepository().getFolder(ContentFactory.projectRoot);
	}

	protected IRepository getRepository() throws RepositoryException {
		if (repository == null) {
			repository = RepositoryFactory.getRepositoryInstance();
			repository.connect();
		}
		return repository;
	}

	public iFolder getTemplates() throws ContentException, RepositoryException {
		return getRepository().getFolder(ContentFactory.templatesRoot);
	}

	public void populate() throws RepositoryException, ContentException {
		final IRepository rf = RepositoryFactory.getRepositoryInstance();
		rf.connect();
		rf.addChild(rf.getFolder(ContentFactory.projectRoot));
		rf.addChild(rf.getFolder(ContentFactory.templatesRoot));
		rf.addChild(rf.getFolder(ContentFactory.componentsRoot));
		rf.commit();
		rf.disconnect();
	}

	public void refresh() throws RepositoryException {
		disconnect();
		repository.connect();
	}

}
