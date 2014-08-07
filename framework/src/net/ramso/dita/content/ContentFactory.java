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

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @author jescudero
 *
 */
public class ContentFactory {
	public static final String PREFIX = "content";
	public static String projectRoot = "/project";
	public static String templatesRoot = "/General/templates";
	public static String componentsRoot = "/General/Componentes";
	public static final String PROJECTLABEL = "Projects";
	public static final String TEMPLATESLABEL = "Templates";
	public static final String COMPONENTSLABEL = "Components";
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
		return getRepository().getFolder(projectRoot+"/"+name);

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
		projectRoot = properties.getProperty(PREFIX + ".path.projects");
		templatesRoot = properties.getProperty(PREFIX + ".path.templates");
		componentsRoot = properties.getProperty(PREFIX + ".path.components");
	}

	public Object getDitaDocument(iFile file) throws ContentException {
		try {
			// return utils.unmarshall(content);
			byte[] content = file.getContent();
			System.out.println(getDataType(content));
			JAXBContext jc = JAXBContext.newInstance("net.ramso.dita.bookmap");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);
			spf.setFeature("http://xml.org/sax/features/validation", false);
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
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);
			dbFactory.setFeature("http://xml.org/sax/features/validation",
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
