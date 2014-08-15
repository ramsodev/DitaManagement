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
	private String iconOpen;
	private String iconClose;
	private String type;
	private String name;
	private String icon;
	private ArrayList<ConfigData> metadata;
	private DitaTypes ditaType;

	String[] icons = { "bin", "bookmap", "class", "css", "csv", "dita", "doc",
			"docx", "gz", "gzip", "html", "jar", "js", "jsp", "log", "map",
			"obj", "odb", "odc", "odf", "odg", "odi", "odp", "ods", "odt",
			"odx", "pdf", "ppt", "pptx", "ps", "pub", "py", "rar", "sql",
			"sql4", "svg", "tar", "tiff", "txt", "xhtml", "xls", "xlsx", "xml",
			"zip" };

	public ContentItem() {

	}

	/**
	 * 
	 */
	public ContentItem(iContent content) {
		this.content = content;
		this.name = content.getPath().substring(
				content.getPath().lastIndexOf("/") + 1);
		if (content instanceof iFolder) {
			this.iconOpen = "ui-icon-folder-open";
			this.iconClose = "ui-icon-folder-collapsed";
			this.icon = "ui-icon-folder-collapsed";
			this.type = "folder";
			createFolderMetadata((iFolder) content);
		} else {
			iFile f = (iFile) content;
			createFileMetadata(f);
			this.type = "resource";
			this.icon = "ui-icon-document-b";
			this.ditaType = null;
			try {
				if (f.getMime().contains("dita")) {
					ditaType = f.getDitaType();
					if (ditaType != null) {
						this.type = "document";
						this.icon = "ui-icon-document";
						metadata.add(new ConfigData("Dita type", ditaType
								.getLiteral()));
						switch (ditaType.getValue()) {
						case DitaTypes.BOOKMAP_VALUE:
							this.icon = "ui-icon-script";
							break;
						case DitaTypes.MAP_VALUE:
							this.icon = "ui-icon-script";
							break;
						default:
							this.icon = "ui-icon-document";
							break;
						}
					}
				} else if (f.getMime().startsWith("image")) {
					this.icon = "ui-icon-image";
				}
			} catch (ContentException e) {
				LogManager.warn("Create Content Item failed retrieving Mime of " +content.getPath());
			}

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
		} catch (ContentException e) {
			LogManager.warn("[Create Folder Metadata ", e);
		}
	}

	private void createFileMetadata(iFile file) {
		try {
			Map<String, String> meta = file.getMetadata();
			metadata = new ArrayList<ConfigData>();
			for (Entry<String, String> entry : meta.entrySet()) {
				metadata.add(new ConfigData(entry.getKey(), entry.getValue()));
			}
		} catch (ContentException e) {
			LogManager.warn("[Content Item] Create File Metadata of " + file.getPath());
		}

	}

	@Override
	public int compareTo(ContentItem o) {
		return this.getContent().getPath().compareTo(o.getContent().getPath());
	}

	public iContent getContent() {
		return content;
	}

	public void setContent(iContent content) {
		this.content = content;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public List<ConfigData> getMetadata() {
		return metadata;
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
		} catch (ContentException e) {
			LogManager.warn("[ContentItem] Stream image", e);
		}
		return stream;
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
				if (ditaType != null && ditaType.equals(DitaTypes.BOOKMAP)) {
					return true;
				}
			default:
				break;
			}
		} catch (Exception e) {
			ApplicationMessage.setWarn("[ContentItem] Render fails", e);
		}
		return false;
	}

	public String getPath() {

		return getContent().getPath();
	}

	public String getText() {
		try {
			return new String(((iFile) getContent()).getContent());
		} catch (ContentException e) {
			ApplicationMessage.setError("File content error", e);
		}
		return "";
	}

	public ArrayList<ContentItem> getChilds() {
		ArrayList<ContentItem> childs = new ArrayList<ContentItem>();
		if (getContent() instanceof iFolder) {
			iFolder f = (iFolder) getContent();
			try {
				ArrayList<iContent> cs = f.getChilds();
				for (iContent c : cs) {
					childs.add(new ContentItem(c));
				}
			} catch (ContentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return childs;
	}

	public String getSvg() {
		String sufix = ".svg";
		if (content instanceof iFolder) {
			return "folder.svg";
		} else {
			String ext = getPath().substring(getPath().lastIndexOf(".") + 1);
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
			iFile f = (iFile) content;
			try {
				if (f.getMime().startsWith("image")) {
					return "image" + sufix;
				}
			} catch (ContentException e) {
				LogManager.warn("Get mime type fail", e);
			}
		}
		return "resource.svg";
	}

}
