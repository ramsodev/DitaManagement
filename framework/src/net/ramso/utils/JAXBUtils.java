package net.ramso.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class JAXBUtils {

	private static JAXBUtils instance;
	public static JAXBContext jaxbContext;
	private static Logger logger = LoggerFactory.getLogger(JAXBUtils.class);

	public static void getAttributes(String element, String xsd) {
		final SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {

			sf.newSchema(new File("customer.xsd"));

		} catch (final SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getExtendedObjects(List<JAXBElement> objEls) {

		final List list = new ArrayList();

		for (final JAXBElement objEl : objEls) {
			list.add(objEl.getValue());
		}

		return list;
	}

	public static synchronized JAXBUtils getInstance(String pkgs) {
		if (JAXBUtils.instance == null) {
			JAXBUtils.instance = new JAXBUtils(pkgs);
		}

		return JAXBUtils.instance;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getObjects(List<JAXBElement> objEls) {
		final List list = new ArrayList();

		for (final JAXBElement objEl : objEls) {
			list.add(objEl.getValue());
		}

		return list;
	}

	public static void setInstanceNull() {
		if (JAXBUtils.instance != null) {
			JAXBUtils.instance = null;
		}
	}

	private JAXBUtils(String pkgs) {
		try {
			JAXBUtils.jaxbContext = JAXBContext.newInstance(pkgs);
		} catch (final Exception ex) {
			JAXBUtils.logger.error(
					Messages.getString("JAXBUtils.error.msg"), ex); //$NON-NLS-1$
		}
	}

	public Unmarshaller createUnmarshaller() throws JAXBException {
		return JAXBUtils.jaxbContext.createUnmarshaller();
	}

	public Node marshall(Object obj) throws JAXBException {
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = null;
		try {
			final DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
		} catch (final ParserConfigurationException ex) {
			throw new JAXBException(ex);
		}

		final Marshaller m = JAXBUtils.jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(obj, doc);
		return doc.getDocumentElement();
	}

	public void marshall(Object obj, OutputStream outStream)
			throws JAXBException {
		final Marshaller m = JAXBUtils.jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(obj, outStream);
	}

	public byte[] marshallToByteArr(Object obj) throws JAXBException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshall(obj, baos);
		return baos.toByteArray();
	}

	public void marshallToFile(Object obj, String path, String fileName)
			throws JAXBException, FileNotFoundException {
		try {
			final Writer writer = new FileWriter(path + File.separator
					+ fileName);
			final Marshaller marshaller = JAXBUtils.jaxbContext
					.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(obj, writer);
		} catch (final IOException e) {
			JAXBUtils.logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void marshallToFile(Object obj, String path, String fileName,
			String schemaLocation) throws JAXBException, FileNotFoundException {
		try {
			final Writer writer = new FileWriter(path + File.separator
					+ fileName);
			final Marshaller marshaller = JAXBUtils.jaxbContext
					.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					schemaLocation);
			marshaller.marshal(obj, writer);
			writer.close();
		} catch (final IOException e) {
			JAXBUtils.logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public String marshallToStr(Object obj) throws JAXBException {
		return new String(marshallToByteArr(obj));
	}

	public Object unmarshall(byte[] content) throws JAXBException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(content);
		return this.unmarshall(bais);
	}

	public Object unmarshall(File file) throws JAXBException {
		final Unmarshaller um = JAXBUtils.jaxbContext.createUnmarshaller();
		return um.unmarshal(file);
	}

	public Object unmarshall(InputStream xml) throws JAXBException {
		final Unmarshaller um = JAXBUtils.jaxbContext.createUnmarshaller();
		return um.unmarshal(xml);
	}

	public Object unmarshall(Node node) throws JAXBException {
		final Unmarshaller um = JAXBUtils.jaxbContext.createUnmarshaller();
		return um.unmarshal(node);
	}

	public Object unmarshall(String xml) throws JAXBException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(
				xml.getBytes());
		final Unmarshaller um = JAXBUtils.jaxbContext.createUnmarshaller();
		return um.unmarshal(bais);
	}

	public Object unmarshall(URL url) throws JAXBException {
		final Unmarshaller um = JAXBUtils.jaxbContext.createUnmarshaller();
		return um.unmarshal(url);
	}

	public Object unmarshallFromXmlPath(String xmlPath) throws JAXBException,
	IOException {

		try {
			ResourcesLocator resourcesLocator;
			resourcesLocator = new ResourcesLocator(xmlPath);
			final InputStream in = resourcesLocator.getInputStream();
			return unmarshall(in);
		} catch (final Exception e) {
			final File f = new File(xmlPath);
			return unmarshall(f);
		}
	}
}