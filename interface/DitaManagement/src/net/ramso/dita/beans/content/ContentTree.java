package net.ramso.dita.beans.content;

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
import net.ramso.utils.LogManager;
import net.ramso.utils.Messages;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class ContentTree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TreeNode root;
	private TreeNode selected;



	@PostConstruct
	public void init() {
		ContentFactory cf = new ContentFactory();
		try {
			root = new DefaultTreeNode("/", null);
			iFolder ps = cf.getProjects();
			DefaultTreeNode node = new DefaultTreeNode(new ContentItem(ps), root);
			selected=node;
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

	

	public TreeNode getSelected() {
		return selected;
	}

	public void setSelected(TreeNode selected) {
		this.selected = selected;
	}
	
	public void add(){
		
		
	}
	public void delete(){
		
	}
	public void refresh(){
		
	}
	
	public void onSelect(NodeSelectEvent event){
		System.out.println("Selected event:" + event.getTreeNode().getType());
		System.out.println(selected);
	}

}
