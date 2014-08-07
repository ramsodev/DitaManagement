/**
 * 
 */
package net.ramso.dita.beans.content;

import java.io.Serializable;

import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;

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

	/**
	 * 
	 */
	public ContentItem(iContent content) {
		this.content = content;
		if (content instanceof iFolder) {
			this.iconOpen = "ui-icon-folder-open";
			this.iconClose = "ui-icon-folder-collapsed";
			this.type = "folder";
		} else {
			this.type = "document";
			this.icon = "ui-icon-document";
			if (content.getPath().endsWith("dita")
					|| content.getPath().endsWith("xml")) {

			} else if (content.getPath().endsWith("map")) {
				this.icon = "ui-icon-script";
			} else if (content.getPath().endsWith("png")
					|| content.getPath().endsWith("jpg")) {
				this.icon = "ui-icon-image";
				this.type = "resource";
			} else {
				this.type = "resource";
				this.icon = "ui-icon-document-b";
			}

		}
		this.name = content.getPath().substring(
				content.getPath().lastIndexOf("/") + 1);
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

}
