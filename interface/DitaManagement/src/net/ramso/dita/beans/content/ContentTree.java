package net.ramso.dita.beans.content;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.LogManager;
import net.ramso.utils.Messages;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
public class ContentTree {

	private String name;
	private TreeNode root;
	private TreeNode[] selected;

	public ContentTree(String name) {
		this.name = name;
		init();
	}

	@PostConstruct
	public void init() {
		ContentFactory cf = new ContentFactory();
		try {
			if (name.equals(ContentFactory.PROJECTLABEL)) {
				iFolder ps = cf.getProjects();
				root = new DefaultTreeNode(new ContentItem(ps), null);
				addchilds(root, ps.getChilds());
			} else if (name.equals(ContentFactory.TEMPLATESLABEL)) {
				iFolder ps = cf.getTemplates();
				root = new DefaultTreeNode(new ContentItem(ps), null);
				addchilds(root, ps.getChilds());
			} else if (name.equals(ContentFactory.COMPONENTSLABEL)) {
				iFolder ps = cf.getComponents();
				root = new DefaultTreeNode(new ContentItem(ps), null);
				addchilds(root, ps.getChilds());
			} else {
				root = new DefaultTreeNode(
						Messages.getString("ContentTree.null.node")); //$NON-NLS-1$
			}
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
				}else{
					node = new DefaultTreeNode(item, parent);
				}
				addchilds(node, child.getChilds());
			}
		}

	}

	public TreeNode getRoot() {
		return root;
	}

	public String getName() {
		return name;
	}

	public TreeNode[] getSelected() {
		return selected;
	}

	public void setSelected(TreeNode[] selected) {
		this.selected = selected;
	}
	
	public void add(){
		
	}
	public void delete(){
		
	}
	public void refresh(){
		
	}

}
