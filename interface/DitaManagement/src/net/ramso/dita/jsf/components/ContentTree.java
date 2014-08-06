package net.ramso.dita.jsf.components;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;

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

	@PostConstruct
	public void init() {
		ContentFactory cf = new ContentFactory();
		try {
			iFolder ps = cf.getProjects();
			root = new DefaultTreeNode(ps.getPath());
			addchilds(root, ps.getChilds());
		} catch (ContentException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	private void addchilds(TreeNode parent, ArrayList<iContent> childs)
			throws ContentException {
		for (iContent child : childs) {
			TreeNode node = new DefaultTreeNode(child.getPath(), parent);
			addchilds(node, child.getChilds());
		}

	}

	public TreeNode getRoot() {
		return root;
	}
}