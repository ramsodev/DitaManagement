package net.ramso.dita.jsf.content;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.LogManager;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean

public class ContentTree {

	private String name;
	private TreeNode root;

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
				root = new DefaultTreeNode(ps.getPath());
				addchilds(root, ps.getChilds());
			} else if (name.equals(ContentFactory.TEMPLATESLABEL)) {
				iFolder ps = cf.getTemplates();
				root = new DefaultTreeNode(ps.getPath());
				addchilds(root, ps.getChilds());
			} else if (name.equals(ContentFactory.COMPONENTSLABEL)) {
				iFolder ps = cf.getComponents();
				root = new DefaultTreeNode(ps.getPath());
				addchilds(root, ps.getChilds());
			}else{
				root = new DefaultTreeNode("Vacio");
			}
		} catch (ContentException e) {
			LogManager.error("Error al crear los arboles de contenido", e);
		} catch (RepositoryException e) {
			LogManager.error("Error al crear los arboles de contenido", e);
		}
	}

	private void addchilds(TreeNode parent, ArrayList<iContent> childs)
			throws ContentException {
		if (childs != null) {
			for (iContent child : childs) {
				TreeNode node = new DefaultTreeNode(child.getPath(), parent);
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

}
