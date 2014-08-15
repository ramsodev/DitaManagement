/**
 * 
 */
package net.ramso.dita.repository.content;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.RepositoryFactory;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.Messages;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @author jescudero
 *
 */
public class ContentFactory {
	public static final String PREFIX = "content"; //$NON-NLS-1$
	public static String projectRoot = "/project"; //$NON-NLS-1$
	public static String templatesRoot = "/General/templates"; //$NON-NLS-1$
	public static String componentsRoot = "/General/Componentes"; //$NON-NLS-1$
	private static ContentFactory cf;
	public static final String PROJECTLABEL = Messages
			.getString("ContentFactory.label.project"); //$NON-NLS-1$
	public static final String TEMPLATESLABEL = Messages
			.getString("ContentFactory.label.template"); //$NON-NLS-1$
	public static final String COMPONENTSLABEL = Messages
			.getString("ContentFactory.label.component"); //$NON-NLS-1$
	private IRepository repository;

	/**
	 * 
	 */
	protected ContentFactory() {
	}

	public iFile createFile(String name) throws ContentException,
			RepositoryException {
		iFile f = getFile(name);
		getRepository().addChild(f);
		
		return f;
	}

	public iFile getFile(String path) throws ContentException,
			RepositoryException {
		return getRepository().getFile(path);

	}

	public iFolder createFolder(String name) throws ContentException,
			RepositoryException {
		iFolder f = getFolder(name);
		getRepository().addChild(f);
		return f;
	}

	public iFolder getFolder(String name) throws ContentException,
			RepositoryException {
		return getRepository().getFolder(name);
	}

	public iFolder createProject(String name) throws ContentException,
			RepositoryException {
		iFolder f = getProject(name);
		getRepository().addChild(f);
		return f;
	}

	public iFolder getProject(String name) throws ContentException,
			RepositoryException {
		return getRepository().getFolder(projectRoot + "/" + name); //$NON-NLS-1$

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
			repository.connect();
		}
		return repository;
	}

	public static void config(Properties properties) {
		projectRoot = properties.getProperty(PREFIX + ".path.projects"); //$NON-NLS-1$
		templatesRoot = properties.getProperty(PREFIX + ".path.templates"); //$NON-NLS-1$
		componentsRoot = properties.getProperty(PREFIX + ".path.components"); //$NON-NLS-1$
	}

	

	public void commit() throws ContentException, RepositoryException {
		getRepository().commit();
	}

	public static ContentFactory getInstance() {
		if (cf == null) {
			cf = new ContentFactory();
		}
		return cf;
	}

	public void disconnect() throws RepositoryException {
		getRepository().disconnect();

	}

	public void populate() throws RepositoryException, ContentException {
		IRepository rf = RepositoryFactory.getRepositoryInstance();
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
