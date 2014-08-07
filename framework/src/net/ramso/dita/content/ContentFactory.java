/**
 * 
 */
package net.ramso.dita.content;

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
	public static final String PROJECTLABEL = Messages.getString("ContentFactory.label.project"); //$NON-NLS-1$
	public static final String TEMPLATESLABEL = Messages.getString("ContentFactory.label.template"); //$NON-NLS-1$
	public static final String COMPONENTSLABEL = Messages.getString("ContentFactory.label.component"); //$NON-NLS-1$
	private IRepository repository;

	/**
	 * 
	 */
	public ContentFactory() {
	}

	public iFolder createProject(String name) throws ContentException, RepositoryException {
		iFolder f = getProject(name);
		getRepository().addChild(f);
		return f;
	}

	public iFolder getProject(String name) throws ContentException, RepositoryException {
		return getRepository().getFolder(projectRoot+"/"+name); //$NON-NLS-1$

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

	public Object getDitaDocument(iFile file) throws ContentException {
		try {
			// return utils.unmarshall(content);
			byte[] content = file.getContent();
			System.out.println(getDataType(content));
			JAXBContext jc = JAXBContext.newInstance("net.ramso.dita.bookmap"); //$NON-NLS-1$
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
					false);
			spf.setFeature("http://xml.org/sax/features/validation", false); //$NON-NLS-1$
			spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			XMLReader xmlReader = spf.newSAXParser().getXMLReader();
			// XMLFilterImpl xmlFilter = new XMLNamespaceFilter(xmlReader);
			ByteArrayInputStream bais = new ByteArrayInputStream(content);
			InputSource inputSource = new InputSource(bais);
			SAXSource source = new SAXSource(xmlReader, inputSource);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			return unmarshaller.unmarshal(source);

		} catch (Exception e) {
			throw new ContentException(e);
		}

	}

	public String getDataType(byte[] content) throws ContentException {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(content);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			dbFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
							false);
			dbFactory.setFeature("http://xml.org/sax/features/validation", //$NON-NLS-1$
					false);
			dbFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(bais);
			
			return doc.getDoctype().getName();
		} catch (Exception e) {
			throw new ContentException(e);
		}

	}

}
