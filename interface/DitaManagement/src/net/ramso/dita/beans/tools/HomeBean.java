package net.ramso.dita.beans.tools;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import net.ramso.dita.beans.content.ContentItem;
import net.ramso.dita.beans.content.ContentTree;
import net.ramso.dita.repository.content.ContentSearchQuery;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.LogManager;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5720135148595515399L;

	@ManagedProperty("#{contentTree}")
	private ContentTree contentTree;

	private String type;

	private boolean upload;
	private boolean black;
	private boolean folder;
	private boolean file;
	private TreeNode content;
	private ContentItem selected;
	private String search;

	private boolean config;

	private boolean index;

	private ArrayList<String> results;
	private String selectedResult;

	@PostConstruct
	public void init() {
		setType("");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		upload = false;
		file = false;
		folder = false;
		black = false;
		config = false;
		index = false;
		if (type.equals("upload")) {
			upload = true;
		} else if (type.equals("document") || type.equals("resource")) {
			file = true;
		} else if (type.equals("folder") || type.equals("default")) {
			folder = true;
		} else if (type.equals("config")) {
			config = true;
		} else if (type.equals("search")) {
			index = true;
		} else {
			black = true;
		}
		this.type = type;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public void add(ActionEvent event) {
		setType("upload");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("contentHeight", 500);
		options.put("closable", true);
		RequestContext.getCurrentInstance().openDialog("upload", options, null);

	}

	public void onSelect(NodeSelectEvent event) {
		setType(event.getTreeNode().getType());
		ApplicationsTools.setSelectedContent((ContentItem) event.getTreeNode()
				.getData());
	}

	public TreeNode getContent() {
		return content;
	}

	public void setContent(TreeNode content) {
		this.content = content;
	}

	public ContentItem getSelected() {
		return selected;
	}

	/**
	 * Select para detalle de folder
	 * 
	 * @param selected
	 */
	public void setSelected(ContentItem selected) {
		setType(selected.getType());
		List<TreeNode> childs = getContentTree().getSelected().getChildren();
		TreeNode sel = contentTree.getByPath(childs, selected.getPath(), true);
		if (sel != null) {
			getContentTree().setSelected(sel);
			ApplicationsTools.setSelectedContent(selected);
		}
		this.selected = selected;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public boolean isConfig() {
		return config;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}

	public ContentTree getContentTree() {
		return contentTree;
	}

	public void setContentTree(ContentTree contentTree) {
		this.contentTree = contentTree;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void find() {
		setType("search");
		try {
			results = new ArrayList<String>();
			ScoreDoc[] r = ContentSearchQuery.getInstance().findFromAll(search);
			System.out.println("Resultados " + r.length);
			for (ScoreDoc scoreDoc : r) {
				Document d = ContentSearchQuery.getInstance().getSearcher()
						.doc(scoreDoc.doc);
				String re = d.get("Path");
				if (re != null) {
					results.add(re);
				} else {
					LogManager.warn("The index containg documents with null path");
				}
			}
		} catch (IndexException e) {
			ApplicationMessage.setError("Search fail", e);
		} catch (IOException e) {
			ApplicationMessage.setError("Search fail", e);
		}
	}

	public boolean isIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public ArrayList<String> getResults() {
		return results;
	}

	public void setResults(ArrayList<String> results) {
		this.results = results;
	}

	public String getSelectedResult() {
		return selectedResult;
	}

	public void setSelectedResult(String selectedResult) {
		TreeNode sel = contentTree.getByPath(selectedResult, false);
		if (sel != null) {
			setType(sel.getType());		
			getContentTree().setSelected(sel);
			ApplicationsTools.setSelectedContent((ContentItem) sel.getData());
			this.selected = (ContentItem) sel.getData();
		}		
		this.selectedResult = selectedResult;
	}
}
