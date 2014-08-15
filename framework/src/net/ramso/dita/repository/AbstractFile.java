/**
 * 
 */
package net.ramso.dita.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import net.ramso.dita.repository.content.ContentIndexer;
import net.ramso.dita.repository.content.DitaTools;
import net.ramso.dita.repository.content.DitaTypes;
import net.ramso.dita.repository.content.IndexException;

/**
 * @author ramso
 *
 */
public abstract class AbstractFile extends AbstractContent implements iFile {

	protected String name = null;
	protected String type = null;
	protected String size;
	private DitaTypes ditaType;
	private HashMap<String, String> metadata;

	/**
	 * @param path
	 */
	public AbstractFile(String path) {
		super(path);
	}

	/**
	 * 
	 */
	public AbstractFile() {
		super();
	}

	/*
	 * Allwais null, a file not have childs
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() throws ContentException {
		return null;
	}

	/*
	 * Make nothing
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent child) throws ContentException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#rename(java.lang.String)
	 */
	@Override
	public void rename(String name) throws ContentException {
		this.name = name;

	}

	@Override
	public String getRename() {
		return name;
	}

	@Override
	public boolean isRename() {
		return getRename() != null;
	}

	@Override
	public String getMime() throws ContentException {
		if (type == null) {
			parseTykaMetadata();
			if (type == null) {
				type = getMagicMime();
			}
		}
		return type;
	}
	
	protected String getMagicMime() throws ContentException{
		InputStream bais = new ByteArrayInputStream(getContent());
		String type = "";
		try {
			type = URLConnection.guessContentTypeFromStream(bais);
		} catch (IOException e) {
			throw new ContentException(e);
		}
		if (type == null) {
			if (getPath().endsWith("txt")) {
				type = "text/plain";
			} else {
				type = "application/octet-stream";
			}
		}
		return type;
		
	}

	@Override
	public String getSize() throws ContentException {
		if (size == null) {
			int s = getContent().length;
			String sufix = "Bytes";
			if (s > 1024) {
				s = s / 1024;
				sufix = "KB";
				if (s > 1024) {
					s = s / 1024;
					sufix = "MB";
				}
				if (s > 1024) {
					s = s / 1024;
					sufix = "GB";
				}
			}
			size = s + " " + sufix;
		}
		return size;
	}

	@Override
	public Object getDocument() throws ContentException {
		return DitaTools.getDitaDocument(this, getDitaType());
	}

	@Override
	public DitaTypes getDitaType() throws ContentException {
		if (ditaType == null) {
			parseTykaMetadata();
			if (ditaType == null) {
				ditaType = DitaTools.getDataType(getContent());
			}
		}
		return ditaType;
	}

	@Override
	public Map<String, String> getMetadata() throws ContentException {
		if (metadata == null) {
			parseTykaMetadata();
		}
		return metadata;
	}

	@Override
	public void addToIndex() throws ContentException, IndexException {
		try {
			ByteArrayInputStream bai = new ByteArrayInputStream(getContent());
			ContentHandler contenthandler = new BodyContentHandler(
					Integer.MAX_VALUE);
			Metadata meta = new Metadata();
			meta.add(Metadata.RESOURCE_NAME_KEY, getPath());
			AutoDetectParser parser = new AutoDetectParser();
			parser.parse(bai, contenthandler, meta);
			ContentIndexer.getInstance().add(getMetadata(),
					contenthandler.toString());
		} catch (IOException e) {
			throw new ContentException(e);
		} catch (SAXException e) {
			throw new ContentException(e);
		} catch (TikaException e) {
			throw new ContentException(e);
		}

	}

	@Override
	public void removeFromIndex() throws ContentException, IndexException {
		ContentIndexer.getInstance().remove(getPath());
	}

	protected void parseTykaMetadata() throws ContentException {
		boolean addMeta = false;
		if (metadata == null) {
			metadata = new HashMap<String, String>();
			addMeta = true;
			metadata.put("Content Name",
					getPath().substring(getPath().lastIndexOf("/") + 1));
			metadata.put("Path", getPath());
			metadata.put("Storage Type", getStorageType());
			metadata.put("Revision", getVersion());
			metadata.put("Size", getSize());
		}
		try {
			ByteArrayInputStream bai = new ByteArrayInputStream(getContent());
			ContentHandler contenthandler = new BodyContentHandler();
			Metadata meta = new Metadata();
			meta.add(Metadata.RESOURCE_NAME_KEY, getPath());
			AutoDetectParser parser = new AutoDetectParser();
			parser.parse(bai, contenthandler, meta);
			String[] m = meta.names();
			for (String ms : m) {
				if (!ms.equalsIgnoreCase(Metadata.RESOURCE_NAME_KEY)) {
					if (ms.equalsIgnoreCase(Metadata.CONTENT_TYPE)) {
						String[] ts = meta.get(ms).split(";");
						if (ts.length > 0) {
							type = ts[0];
							if (type.contains("dita") && ts.length > 1) {
								ditaType = DitaTypes.get(ts[1].substring(ts[1]
										.lastIndexOf("=") + 1));
							}
						} else {
							type = null;
							ditaType = null;
						}
					} else {
						metadata.put(ms, meta.get(ms));
					}
				}
			}
			if (addMeta) {
				metadata.put("MIME Type", getMime());
				if (ditaType != null) {
					metadata.put("DITA Type", getDitaType().getLiteral());
				}
			}
		} catch (IOException e) {
			throw new ContentException(e);
		} catch (SAXException e) {
			throw new ContentException(e);
		} catch (TikaException e) {
			type = getMagicMime();
			throw new ContentException(e);
		}
	}
}
