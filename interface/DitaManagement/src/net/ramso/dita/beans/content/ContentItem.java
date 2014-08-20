/**
 *
 */
package net.ramso.dita.beans.content;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.ramso.dita.beans.config.ConfigData;
import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.DitaTypes;
import net.ramso.utils.LogManager;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @author ramso
 *
 */

public class ContentItem implements Serializable, Comparable<ContentItem> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private iContent content;
	private DitaTypes ditaType;
	private String icon;
	private String iconClose;
	private String iconOpen;
	String[] icons = { "bin", "bookmap", "class", "css", "csv", "dita", "doc",
			"docx", "gz", "gzip", "html", "jar", "js", "jsp", "log", "map",
			"obj", "odb", "odc", "odf", "odg", "odi", "odp", "ods", "odt",
			"odx", "pdf", "ppt", "pptx", "ps", "pub", "py", "rar", "sql",
			"sql4", "svg", "tar", "tiff", "txt", "xhtml", "xls", "xlsx", "xml",
	"zip" };
	private ArrayList<ConfigData> metadata;
	private String name;

	private String type;

	public ContentItem() {

	}

	/**
	 *
	 */
	public ContentItem(iContent content) {
		this.content = content;
		name = content.getPath().substring(
				content.getPath().lastIndexOf("/") + 1);
		if (content instanceof iFolder) {
			iconOpen = "ui-icon-folder-open";
			iconClose = "ui-icon-folder-collapsed";
			icon = "ui-icon-folder-collapsed";
			type = "folder";
			createFolderMetadata((iFolder) content);
		} else {
			final iFile f = (iFile) content;
			createFileMetadata(f);
			type = "resource";
			icon = "ui-icon-document-b";
			ditaType = null;
			try {
				if (f.getMime().contains("dita")) {
					ditaType = f.getDitaType();
					if (ditaType != null) {
						type = "document";
						icon = "ui-icon-document";
						metadata.add(new ConfigData("Dita type", ditaType
								.getLiteral()));
						switch (ditaType.getValue()) {
						case DitaTypes.BOOKMAP_VALUE:
							icon = "ui-icon-script";
							break;
						case DitaTypes.MAP_VALUE:
							icon = "ui-icon-script";
							break;
						default:
							icon = "ui-icon-document";
							break;
						}
					}
				} else if (f.getMime().startsWith("image")) {
					icon = "ui-icon-image";
				}
			} catch (final ContentException e) {
				LogManager
						.warn("Create Content Item failed retrieving Mime of "
								+ content.getPath());
			}

		}
	}

	@Override
	public int compareTo(ContentItem o) {
		return getContent().getPath().compareTo(o.getContent().getPath());
	}

	private void createFileMetadata(iFile file) {
		try {
			final Map<String, String> meta = file.getMetadata();
			metadata = new ArrayList<ConfigData>();
			for (final Entry<String, String> entry : meta.entrySet()) {
				metadata.add(new ConfigData(entry.getKey(), entry.getValue()));
			}
		} catch (final ContentException e) {
			LogManager.warn("[Content Item] Fail Creating File Metadata of "
					+ file.getPath());
		}

	}

	private void createFolderMetadata(iFolder folder) {
		try {
			metadata = new ArrayList<ConfigData>();
			metadata.add(new ConfigData("Folder Name", getName()));
			metadata.add(new ConfigData("Path", folder.getPath()));
			metadata.add(new ConfigData("Content", ""
					+ folder.getChilds().size()));
			metadata.add(new ConfigData("Storage Type", folder.getStorageType()));
			metadata.add(new ConfigData("Revision", folder.getVersion()));
		} catch (final ContentException e) {
			LogManager.warn("[Content Item] Fail Creating Metadata folder of "
					+ folder.getPath(), e);
		}
	}

	public ArrayList<ContentItem> getChilds() {
		final ArrayList<ContentItem> childs = new ArrayList<ContentItem>();
		if (getContent() instanceof iFolder) {
			final iFolder f = (iFolder) getContent();
			try {
				final ArrayList<iContent> cs = f.getChilds();
				for (final iContent c : cs) {
					childs.add(new ContentItem(c));
				}
			} catch (final ContentException e) {
				ApplicationMessage.setError("[ContentItem]" + getPath()
						+ " fail retrieving childs ", e);
			}
		}
		return childs;
	}

	public iContent getContent() {
		return content;
	}

	public String getIcon() {
		return icon;
	}

	public String getIconClose() {
		return iconClose;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public StreamedContent getImage() {
		iFile f = null;
		if (type.equals("folder")) {
			return new DefaultStreamedContent();
		} else {
			f = (iFile) getContent();
		}
		DefaultStreamedContent stream = new DefaultStreamedContent();
		try {
			stream = new DefaultStreamedContent(new ByteArrayInputStream(
					f.getContent()), f.getMime(), getName());
		} catch (final ContentException e) {
			LogManager.warn("[ContentItem] Fail Streaming image " + getPath());
		}
		return stream;
	}

	public List<ConfigData> getMetadata() {
		return metadata;
	}

	public String getName() {
		return name;
	}

	public String getPath() {

		return getContent().getPath();
	}

	public String getSvg() {
		final String sufix = ".svg";
		if (content instanceof iFolder) {
			return "folder.svg";
		} else {
			final String ext = getPath().substring(
					getPath().lastIndexOf(".") + 1);
			if (Arrays.asList(icons).contains(ext)) {
				return ext + sufix;
			} else if (ditaType != null) {
				if (ditaType.equals(DitaTypes.BOOKMAP)) {
					return "bookmap" + sufix;
				} else if (ditaType.equals(DitaTypes.MAP)) {
					return "map" + sufix;
				} else {
					return "dita" + sufix;
				}
			}
			final iFile f = (iFile) content;
			try {
				if (f.getMime().startsWith("image")) {
					return "image" + sufix;
				}
			} catch (final ContentException e) {
				LogManager.warn("Get mime type fail for " + getPath());
			}
		}
		return "resource.svg";
	}

	public String getText() {
		try {
			return new String(((iFile) getContent()).getContent());
		} catch (final ContentException e) {
			ApplicationMessage.setError("[ContentItem]" + getPath()
					+ " File content error", e);
		}
		return "";
	}

	public String getType() {
		return type;
	}

	public boolean render(int render) {
		iFile f = null;
		try {
			if (type.equals("folder")) {
				return false;
			} else {
				f = (iFile) getContent();
			}
			switch (render) {
			case 0:
				if (f.getMime().contains("xml") || f.getMime().contains("text")) {
					return true;
				}
				break;
			case 1:
				if (f.getMime().startsWith("image")) {
					return true;
				}
			case 2:
				if ((ditaType != null) && ditaType.equals(DitaTypes.BOOKMAP)) {
					return true;
				}
			default:
				break;
			}
		} catch (final Exception e) {
			ApplicationMessage.setWarn("[ContentItem] Render fails for "
					+ getPath(), e);
		}
		return false;
	}

	public void setContent(iContent content) {
		this.content = content;
	}

}
