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
import net.ramso.utils.LogManager;
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

	@PostConstruct
	public void init() {
		ContentFactory cf = ContentFactory.getInstance();
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

		} catch (ContentException e) {
			LogManager.error(
					Messages.getString("ContentTree.content.exception"), e); //$NON-NLS-1$
		} catch (RepositoryException e) {
			LogManager.error(
					Messages.getString("ContentTree.content.exception"), e); //$NON-NLS-1$
		}
	}

	private void addchilds(TreeNode parent, ArrayList<iContent> childs)
			throws ContentException {
		if (childs != null) {
			for (iContent child : childs) {
				ContentItem item = new ContentItem(child);
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

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelected() {
		return selected;
	}

	public void setSelected(TreeNode selected) {
		this.selected = selected;
	}

	public void add() {

	}

	public void delete() {
		getSelectedData().getContent().setDelete(true);
		try {
			getSelectedData().getContent().commit();
			refresh();
		} catch (ContentException e) {
			ApplicationMessage.setError("Delete content fail", e);
		}

	}

	public void refresh() {
		try {
			ContentFactory.getInstance().refresh();
			init();
		} catch (RepositoryException e) {
			ApplicationMessage.setError("Refresh tree content fail", e);
		}

	}

	public ContentItem getSelectedData() {
		if (getSelected() != null) {
			return (ContentItem) getSelected().getData();
		}
		return null;
	}

	public TreeNode getByPath(String path, boolean all) {
		System.out.println(getRoot().getChildCount());
		return getByPath(getRoot().getChildren(), path, all);
	}

	public TreeNode getByPath(List<TreeNode> childs, String path, boolean all) {
		for (TreeNode treeNode : childs) {
			ContentItem node = (ContentItem) treeNode.getData();
			if (node.getContent() instanceof iFolder) {
				if(all){
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

}
