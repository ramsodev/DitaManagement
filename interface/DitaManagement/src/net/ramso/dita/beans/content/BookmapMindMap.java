package net.ramso.dita.beans.content;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.beans.tools.ApplicationMessage;
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

import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.MindmapNode;

@ManagedBean
@ViewScoped
public class BookmapMindMap implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4846397193676004229L;

	private Bookmap bookmap;

	private iFile file;

	private MindmapNode root;

	private MindmapNode selectedNode;

	public BookmapMindMap() {
	}

	private void addChaptersNodes(List<Chapter> chaps, MindmapNode parent) {
		for (final Chapter chapter : chaps) {
			final String href = chapter.getHref();
			final DitaMindmapNode node = new DitaMindmapNode(href, chapter,
					"82c542", true);
			addChilds(chapter.getTopicref(), node);
			parent.addNode(node);
		}
	}

	private void addChilds(List<Object> topicrefs, DitaMindmapNode parent) {
		for (final Object object : topicrefs) {
			if (object instanceof Chapter) {
				final Chapter chapter = (Chapter) object;
				final String href = chapter.getHref();
				final DitaMindmapNode node = new DitaMindmapNode(href, chapter,
						"82c542", true);
				addChilds(chapter.getTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topicref) {
				final Topicref topicRef = (Topicref) object;
				final String href = topicRef.getHref();
				final DitaMindmapNode node = new DitaMindmapNode(href,
						topicRef, "fce24f", true);
				addChilds(topicRef.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topicgroup) {
				final Topicgroup topicGroup = (Topicgroup) object;

				final DitaMindmapNode node = new DitaMindmapNode("Topic Group",
						topicGroup, "fce24f", true);
				addChilds(topicGroup.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Topichead) {
				final Topichead topicHead = (Topichead) object;
				final String title = topicHead.getNavtitle();
				final DitaMindmapNode node = new DitaMindmapNode(title,
						topicHead, "fce24f", true);
				addChilds(topicHead.getNavrefOrAnchorOrTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Navref) {
				final Navref navref = (Navref) object;
				final String mapref = navref.getMapref();
				final DitaMindmapNode node = new DitaMindmapNode(mapref,
						navref, "3399ff", true);
				parent.addNode(node);
			} else if (object instanceof Anchor) {
				final Anchor anchor = (Anchor) object;
				final String id = anchor.getId();
				final DitaMindmapNode node = new DitaMindmapNode(id, anchor,
						"fce24f", true);
				parent.addNode(node);
			} else if (object instanceof Booklist) {
				final Booklist booklist = (Booklist) object;
				final DitaMindmapNode node = new DitaMindmapNode("Book List",
						booklist, "82c542", true);
				parent.addNode(node);
			} else if (object instanceof Notices) {
				final Notices notices = (Notices) object;
				final DitaMindmapNode node = new DitaMindmapNode("Notices",
						notices, "fce24f", true);
				addChilds(notices.getTopicref(), node);
				parent.addNode(node);
			} else if (object instanceof Dedication) {
				final Dedication dedication = (Dedication) object;
				final DitaMindmapNode node = new DitaMindmapNode("Dedication",
						dedication, "3399ff", true);
				parent.addNode(node);
			}
		}
	}

	private void addPartNodes(List<Part> parts, MindmapNode parent) {
		for (final Part part : parts) {
			final String href = part.getHref();
			final DitaMindmapNode node = new DitaMindmapNode(href, part,
					"6e9ebf", true);
			addChilds(part.getTopicrefOrAnchorrefOrKeydef(), node);
			parent.addNode(node);
		}
	}

	public MindmapNode getRoot() {
		final iContent f = ApplicationsTools.getSelectedContent().getContent();
		if ((file == null)
				|| (!file.getPath().equals(f.getPath()) && (f instanceof iFile))) {
			file = (iFile) f;
			setContent(file);
		}
		return root;
	}

	public MindmapNode getSelectedNode() {
		return selectedNode;
	}

	public void onNodeDblselect(SelectEvent event) {
		selectedNode = (MindmapNode) event.getObject();
	}

	public void onNodeSelect(SelectEvent event) {
		final MindmapNode node = (MindmapNode) event.getObject();
		if (node.getChildren().isEmpty()) {

		}
	}

	public void setContent(iFile content) {
		try {
			bookmap = (Bookmap) content.getDocument();
			root = new DitaMindmapNode("bookmap", bookmap, "FFCC00", false);
			final Frontmatter frontmatter = bookmap.getFrontmatter();
			DitaMindmapNode node = new DitaMindmapNode("Front Matter",
					frontmatter, "6e9ebf", true);
			setSelectedNode(node);
			addChilds(frontmatter.getBooklistsOrNoticesOrDedication(), node);
			root.addNode(node);
			final List<Part> parts = bookmap.getPart();
			if ((parts != null) && (parts.size() > 0)) {
				addPartNodes(parts, root);
			} else {
				final List<Chapter> chaps = bookmap.getChapter();
				addChaptersNodes(chaps, root);
			}
			final Backmatter backmatter = bookmap.getBackmatter();
			if (backmatter != null) {
				node = new DitaMindmapNode("Back Matter", backmatter, "6e9ebf",
						true);
				addChilds(backmatter.getBooklistsOrNoticesOrDedication(), node);
				root.addNode(node);
			}
		} catch (final ContentException e) {
			ApplicationMessage.setError(
					"Fail creating Mindmap for " + content.getPath(), e);
		}
	}

	public void setSelectedNode(MindmapNode selectedNode) {
		this.selectedNode = selectedNode;
	}
}