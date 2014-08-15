package net.ramso.dita.beans.content;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.beans.tools.ApplicationsTools;
import net.ramso.dita.bookmap.Anchor;
import net.ramso.dita.bookmap.Backmatter;
import net.ramso.dita.bookmap.Booklist;
import net.ramso.dita.bookmap.Bookmap;
import net.ramso.dita.bookmap.Chapter;
import net.ramso.dita.bookmap.Dedication;
import net.ramso.dita.bookmap.Frontmatter;
import net.ramso.dita.bookmap.Navref;
import net.ramso.dita.bookmap.Notices;
import net.ramso.dita.bookmap.Part;
import net.ramso.dita.bookmap.Topicgroup;
import net.ramso.dita.bookmap.Topichead;
import net.ramso.dita.bookmap.Topicref;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.utils.LogManager;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.MindmapNode;

@ManagedBean
@ViewScoped
public class BookmapMindMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4846397193676004229L;

	private MindmapNode root;

	private MindmapNode selectedNode;

	private Bookmap bookmap;
	
	private iFile file;

	public BookmapMindMap() {
	}

	public MindmapNode getRoot() {
		 iContent f = ApplicationsTools.getSelectedContent().getContent();
		if (this.file == null || (!this.file.getPath().equals(f.getPath()) && f instanceof iFile)){
			file=(iFile) f;
			setContent(file);
		}		
		return root;
	}

	public MindmapNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(MindmapNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void onNodeDblselect(SelectEvent event) {
		this.selectedNode = (MindmapNode) event.getObject();
	}

	public void onNodeSelect(SelectEvent event) {
		MindmapNode node = (MindmapNode) event.getObject();
		if (node.getChildren().isEmpty()) {

		}
	}
	
	public void setContent(iFile content) {
		try {
			bookmap = (Bookmap) content.getDocument();
			root = new DitaMindmapNode("bookmap", bookmap, "FFCC00", false);
			Frontmatter frontmatter = bookmap.getFrontmatter();
			DitaMindmapNode node = new DitaMindmapNode("Front Matter",
					frontmatter, "6e9ebf", true);
			setSelectedNode(node);
			addChilds(frontmatter.getBooklistsOrNoticesOrDedication(), node);
			root.addNode(node);
			List<Part> parts = bookmap.getPart();
			if (parts != null && parts.size() > 0) {
				addPartNodes(parts, root);
			} else {
				List<Chapter> chaps = bookmap.getChapter();
				addChaptersNodes(chaps, root);
			}
			Backmatter backmatter = bookmap.getBackmatter();
			if (backmatter != null) {
				node = new DitaMindmapNode("Back Matter", backmatter,
						"6e9ebf", true);
				addChilds(backmatter.getBooklistsOrNoticesOrDedication(), node);
				root.addNode(node);
			}			
		} catch (ContentException e) {
			LogManager.error("Fail creating Mindmap", e);
		}
	}

	private void addChaptersNodes(List<Chapter> chaps, MindmapNode parent) {
		for (Chapter chapter : chaps) {
			String href = chapter.getHref();
			DitaMindmapNode node = new DitaMindmapNode(href, chapter,
					"82c542", true);
			addChilds(chapter.getTopicref(), node);
			parent.addNode(node);			
		}
	}

	private void addChilds(List<Object> topicrefs, DitaMindmapNode parent) {
		for (Object object : topicrefs) {
			if (object instanceof Chapter) {
				Chapter chapter = (Chapter) object;
				String href = chapter.getHref();
				DitaMindmapNode node = new DitaMindmapNode(href, chapter,
						"82c542", true);
				addChilds(chapter.getTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topicref) {
				Topicref topicRef = (Topicref) object;
				String href = topicRef.getHref();
				DitaMindmapNode node = new DitaMindmapNode(href,
						topicRef, "fce24f", true);
				addChilds(topicRef.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topicgroup) {
				Topicgroup topicGroup = (Topicgroup) object;

				DitaMindmapNode node = new DitaMindmapNode("Topic Group",
						topicGroup, "fce24f", true);
				addChilds(topicGroup.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topichead) {
				Topichead topicHead = (Topichead) object;
				String title = topicHead.getNavtitle();
				DitaMindmapNode node = new DitaMindmapNode(title,
						topicHead, "fce24f", true);
				addChilds(topicHead.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Navref) {
				Navref navref = (Navref) object;
				String mapref = navref.getMapref();
				DitaMindmapNode node = new DitaMindmapNode(mapref,
						navref, "3399ff", true);
				parent.addNode(node);
			} else if (object instanceof Anchor) {
				Anchor anchor = (Anchor) object;
				String id = anchor.getId();
				DitaMindmapNode node = new DitaMindmapNode(id, anchor,
						"fce24f", true);
				parent.addNode(node);
			} else if (object instanceof Booklist) {
				Booklist booklist = (Booklist) object;
				DitaMindmapNode node = new DitaMindmapNode("Book List",
						booklist, "82c542", true);
				parent.addNode(node);
			} else if (object instanceof Notices) {
				Notices notices = (Notices) object;
				DitaMindmapNode node = new DitaMindmapNode("Notices",
						notices, "fce24f", true);
				addChilds(notices.getTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Dedication) {
				Dedication dedication = (Dedication) object;
				DitaMindmapNode node = new DitaMindmapNode("Dedication",
						dedication, "3399ff", true);
				parent.addNode(node);
			}
		}
	}

	private void addPartNodes(List<Part> parts, MindmapNode parent) {
		for (Part part : parts) {
			String href = part.getHref();
			DitaMindmapNode node = new DitaMindmapNode(href, part,
					"6e9ebf", true);
			addChilds(part.getTopicrefOrAnchorrefOrKeydef(), node);
			parent.addNode(node);
		}
	}
}