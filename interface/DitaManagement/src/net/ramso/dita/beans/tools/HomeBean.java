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

	private boolean black;

	private boolean config;

	private TreeNode content;
	@ManagedProperty("#{contentTree}")
	private ContentTree contentTree;
	private boolean file;
	private boolean folder;
	private boolean index;
	private ArrayList<String[]> results;
	private String search;

	private ContentItem selected;

	private String selectedResult;

	private String type;
	private boolean upload;

	public void add(ActionEvent event) {
		setType("upload");
		final Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("contentHeight", 500);
		options.put("closable", true);
		RequestContext.getCurrentInstance().openDialog("upload", options, null);

	}

	public void find() {
		setType("search");
		try {
			results = new ArrayList<String[]>();
			final ScoreDoc[] r = ContentSearchQuery.getInstance().findFromAll(
					search);
			for (final ScoreDoc scoreDoc : r) {
				final Document d = ContentSearchQuery.getInstance()
						.getSearcher().doc(scoreDoc.doc);
				final String re = d.get("Path");
				if (re != null) {
					final String[] res = new String[2];
					res[0] = re;
					String c = d.get("content");
					if (c == null) {
						c = "";
					}
					if (c.length() > 500) {
						c = c.substring(0, 500);
					}
					res[1] = c;
					results.add(res);
				} else {
					LogManager
					.warn("The index containg documents with null path");
				}
			}
		} catch (final IndexException e) {
			ApplicationMessage.setError("Search fail", e);
		} catch (final IOException e) {
			ApplicationMessage.setError("Search fail", e);
		}
	}

	public TreeNode getContent() {
		return content;
	}

	public ContentTree getContentTree() {
		return contentTree;
	}

	public ArrayList<String[]> getResults() {
		return results;
	}

	public String getSearch() {
		return search;
	}

	public ContentItem getSelected() {
		return selected;
	}

	public String getSelectedResult() {
		return selectedResult;
	}

	public String getType() {
		return type;
	}

	@PostConstruct
	public void init() {
		setType("");
	}

	public boolean isBlack() {
		return black;
	}

	public boolean isConfig() {
		return config;
	}

	public boolean isFile() {
		return file;
	}

	public boolean isFolder() {
		return folder;
	}

	public boolean isIndex() {
		return index;
	}

	public boolean isUpload() {
		return upload;
	}

	public void onSelect(NodeSelectEvent event) {
		setType(event.getTreeNode().getType());
		ApplicationsTools.setSelectedContent((ContentItem) event.getTreeNode()
				.getData());
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}

	public void setContent(TreeNode content) {
		this.content = content;
	}

	public void setContentTree(ContentTree contentTree) {
		this.contentTree = contentTree;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public void setResults(ArrayList<String[]> results) {
		this.results = results;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Select para detalle de folder
	 *
	 * @param selected
	 */
	public void setSelected(ContentItem selected) {
		setType(selected.getType());
		final List<TreeNode> childs = getContentTree().getSelected()
				.getChildren();
		final TreeNode sel = contentTree.getByPath(childs, selected.getPath(),
				true);
		if (sel != null) {
			getContentTree().setSelected(sel);
			ApplicationsTools.setSelectedContent(selected);
		}
		this.selected = selected;
	}

	public void setSelectedResult(String selectedResult) {
		final TreeNode sel = contentTree.getByPath(selectedResult, false);
		if (sel != null) {
			setType(sel.getType());
			getContentTree().setSelected(sel);
			ApplicationsTools.setSelectedContent((ContentItem) sel.getData());
			selected = (ContentItem) sel.getData();
		}
		this.selectedResult = selectedResult;
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

	public void setUpload(boolean upload) {
		this.upload = upload;
	}
}
