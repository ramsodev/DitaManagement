/**
 *
 */
package net.ramso.dita.repository.content;

import java.io.ByteArrayInputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import net.ramso.dita.bookmap.Bookmap;
import net.ramso.dita.concept.Concept;
import net.ramso.dita.glosssentry.Glossentry;
import net.ramso.dita.glosssgroup.Glossgroup;
import net.ramso.dita.map.Map;
import net.ramso.dita.reference.Reference;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.task.Task;
import net.ramso.dita.topic.Topic;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * A tool class to convert the iFile in jaxb java beans
 *
 * @author ramso
 *
 */
public class DitaTools {
	/**
	 * Get the type of Dita document of the repository file
	 *
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @param content
	 * @return
	 * @throws ContentException
	 */
	public static DitaTypes getDataType(byte[] content) throws ContentException {
		try {
			final ByteArrayInputStream bais = new ByteArrayInputStream(content);
			final DocumentBuilderFactory dbFactory = DocumentBuilderFactory
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
			final Document doc = dBuilder.parse(bais);
			final DitaTypes type = DitaTypes.get(doc.getDoctype().getName());
			return type;
		} catch (final Exception e) {
			throw new ContentException(e);
		}

	}

	/**
	 * Unmarshall de jaxb object content in the iFile
	 *
	 * @param file
	 *            the repostiory file
	 * @return the jaxb bean
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @throws ContentException
	 *
	 */

	public static Object getDitaDocument(iFile file) throws ContentException {
		try {
			;
			final byte[] content = file.getContent();
			final DitaTypes type = DitaTools.getDataType(content);
			return DitaTools.getDitaDocument(file, type);
		} catch (final Exception e) {
			throw new ContentException(e);
		}

	}

	/**
	 * Unmarshall de jaxb object content in the iFile
	 *
	 * @param file
	 *            the repository file
	 * @param type
	 *            the type of document to unmarshall
	 * @return the jaxb bean
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @throws ContentException
	 */
	public static Object getDitaDocument(iFile file, DitaTypes type)
			throws ContentException {
		try {
			;
			final byte[] content = file.getContent();
			final JAXBContext jc = JAXBContext.newInstance(type.getPackage());
			final SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
					false);
			spf.setFeature("http://xml.org/sax/features/validation", false); //$NON-NLS-1$
			spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			final XMLReader xmlReader = spf.newSAXParser().getXMLReader();
			final ByteArrayInputStream bais = new ByteArrayInputStream(content);
			final InputSource inputSource = new InputSource(bais);
			final SAXSource source = new SAXSource(xmlReader, inputSource);

			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			return unmarshaller.unmarshal(source);

		} catch (final Exception e) {
			throw new ContentException(e);
		}

	}

	/**
	 * Get the type of Dita document of the repository file
	 *
	 * @see Bookmap
	 * @see Concept
	 * @see Glossgroup
	 * @see Glossentry
	 * @see Map
	 * @see Reference
	 * @see Task
	 * @see Topic
	 * @param content
	 * @return
	 * @throws ContentException
	 */
	public static DitaTypes getDitaType(iFile content) throws ContentException {
		return DitaTools.getDataType(content.getContent());
	}
}
