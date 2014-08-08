package net.ramso.dita.beans.tools;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.model.TreeNode;

@ManagedBean
public class HomeBean {
	private String type;

	private boolean upload;
	private boolean black;
	private boolean folder;
	private boolean file;
	private TreeNode content;

	@PostConstruct
	public void init() {
		setType("");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		upload=false;
		file=false;
		folder=false;
		black=false;
		if (type.equals("upload")) {
			upload = true;
			
		}else if(type.equals("file")){
			file = true;
			
		}else if(type.equals("folder")){
			folder=true;
		}
		else {
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
	}

	public TreeNode getContent() {
		return content;
	}

	public void setContent(TreeNode content) {
		System.out.println(content);
		this.content = content;
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
	
	

}
