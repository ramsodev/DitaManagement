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

import net.ramso.dita.repository.content.ContentIndexer;
import net.ramso.dita.repository.content.DitaTools;
import net.ramso.dita.repository.content.DitaTypes;
import net.ramso.dita.repository.content.IndexException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author ramso
 *
 */
public abstract class AbstractFile extends AbstractContent implements iFile {

	/**
	 *
	 */
	private static final long serialVersionUID = 3010949313704661215L;
	private DitaTypes ditaType;
	private HashMap<String, String> metadata;
	protected String name = null;
	protected String size;
	protected String type = null;

	/**
	 *
	 */
	public AbstractFile() {
		super();
	}

	/**
	 * @param path
	 */
	public AbstractFile(String path) {
		super(path);
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

	@Override
	public void addToIndex() throws ContentException, IndexException {
		try {
			final ByteArrayInputStream bai = new ByteArrayInputStream(
					getContent());
			final ContentHandler contenthandler = new BodyContentHandler(
					Integer.MAX_VALUE);
			final Metadata meta = new Metadata();
			meta.add(TikaMetadataKeys.RESOURCE_NAME_KEY, getPath());
			final AutoDetectParser parser = new AutoDetectParser();
			parser.parse(bai, contenthandler, meta);
			ContentIndexer.getInstance().add(getMetadata(),
					contenthandler.toString());
		} catch (final IOException e) {
			throw new ContentException(e);
		} catch (final SAXException e) {
			throw new ContentException(e);
		} catch (final TikaException e) {
			throw new ContentException(e);
		}

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
	public Object getDocument() throws ContentException {
		return DitaTools.getDitaDocument(this, getDitaType());
	}

	protected String getMagicMime() throws ContentException {
		final InputStream bais = new ByteArrayInputStream(getContent());
		String type = "";
		try {
			type = URLConnection.guessContentTypeFromStream(bais);
		} catch (final IOException e) {
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
	public Map<String, String> getMetadata() throws ContentException {
		if (metadata == null) {
			parseTykaMetadata();
		}
		return metadata;
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

	@Override
	public String getRename() {
		return name;
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
	public boolean isRename() {
		return getRename() != null;
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
			final ByteArrayInputStream bai = new ByteArrayInputStream(
					getContent());
			final ContentHandler contenthandler = new BodyContentHandler();
			final Metadata meta = new Metadata();
			meta.add(TikaMetadataKeys.RESOURCE_NAME_KEY, getPath());
			final AutoDetectParser parser = new AutoDetectParser();
			parser.parse(bai, contenthandler, meta);
			final String[] m = meta.names();
			for (final String ms : m) {
				if (!ms.equalsIgnoreCase(TikaMetadataKeys.RESOURCE_NAME_KEY)) {
					if (ms.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE)) {
						final String[] ts = meta.get(ms).split(";");
						if (ts.length > 0) {
							this.type = ts[0];
							if (type.contains("dita") && (ts.length > 1)) {
								ditaType=DitaTypes.TOPIC;
								String literal = ts[1].substring(ts[1]
										.lastIndexOf("=") + 1);
								ditaType = DitaTypes.get(literal);
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
		} catch (final IOException e) {
			throw new ContentException(e);
		} catch (final SAXException e) {
			throw new ContentException(e);
		} catch (final TikaException e) {
			type = getMagicMime();
			throw new ContentException(e);
		}
	}

	@Override
	public void removeFromIndex() throws ContentException, IndexException {
		ContentIndexer.getInstance().remove(getPath());
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
}
