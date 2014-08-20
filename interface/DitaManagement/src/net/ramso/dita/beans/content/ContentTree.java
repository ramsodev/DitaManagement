package net.ramso.dita.beans.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.dita.beans.tools.ApplicationsTools;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.utils.Messages;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class ContentTree implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private TreeNode root;
	private TreeNode selected;

	public void add() {

	}

	private void addchilds(TreeNode parent, ArrayList<iContent> childs)
			throws ContentException {
		if (childs != null) {
			for (final iContent child : childs) {
				final ContentItem item = new ContentItem(child);
				TreeNode node = null;
				if (!item.getType().equals("folder")) {
					node = new DefaultTreeNode(item.getType(), item, parent);
				} else {
					node = new DefaultTreeNode(item, parent);
				}
				addchilds(node, child.getChilds());
			}
		}

	}

	public void delete() {
		getSelectedData().getContent().setDelete(true);
		try {
			getSelectedData().getContent().commit();
			refresh();
		} catch (final ContentException e) {
			ApplicationMessage.setError("Delete content fail", e);
		}

	}

	public TreeNode getByPath(List<TreeNode> childs, String path, boolean all) {
		for (final TreeNode treeNode : childs) {
			final ContentItem node = (ContentItem) treeNode.getData();
			if (node.getContent() instanceof iFolder) {
				if (all) {
					if (path.equals(((iFolder) node.getContent()).getPath())) {
						return treeNode;
					}
				}
				if (path.startsWith(((iFolder) node.getContent()).getPath())) {
					return getByPath(treeNode.getChildren(), path, all);
				}
			} else {
				if (path.equals(((iFile) node.getContent()).getPath())) {
					return treeNode;
				}
			}
		}
		return null;
	}

	public TreeNode getByPath(String path, boolean all) {
		return getByPath(getRoot().getChildren(), path, all);
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelected() {
		return selected;
	}

	public ContentItem getSelectedData() {
		if (getSelected() != null) {
			return (ContentItem) getSelected().getData();
		}
		return null;
	}

	@PostConstruct
	public void init() {
		final ContentFactory cf = ContentFactory.getInstance();
		try {
			root = new DefaultTreeNode("/", null);
			iFolder ps = cf.getProjects();
			DefaultTreeNode node = new DefaultTreeNode(new ContentItem(ps),
					root);
			selected = node;
			ApplicationsTools.setSelectedContent((ContentItem) node.getData());
			addchilds(node, ps.getChilds());
			ps = cf.getTemplates();
			node = new DefaultTreeNode(new ContentItem(ps), root);
			addchilds(node, ps.getChilds());
			ps = cf.getComponents();
			node = new DefaultTreeNode(new ContentItem(ps), root);
			addchilds(node, ps.getChilds());

		} catch (final ContentException e) {
			ApplicationMessage.setError(
					Messages.getString("ContentTree.content.exception"), e); //$NON-NLS-1$
		} catch (final RepositoryException e) {
			ApplicationMessage.setError(
					Messages.getString("ContentTree.content.exception"), e); //$NON-NLS-1$
		}
	}

	public void refresh() {
		try {
			ContentFactory.getInstance().refresh();
			init();
		} catch (final RepositoryException e) {
			ApplicationMessage.setError("Refresh tree content fail", e);
		}

	}

	public void setSelected(TreeNode selected) {
		this.selected = selected;
	}

}
