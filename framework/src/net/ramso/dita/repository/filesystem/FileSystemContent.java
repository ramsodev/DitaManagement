/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.util.ArrayList;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;

/**
 * @author ramso
 *
 */
public class FileSystemContent implements iContent {

	private String path;
	private boolean modify;
	protected ArrayList<iContent> childs = null;

	public FileSystemContent(String root) {
		try {
			setPath(root);
		} catch (ContentException e) {
		}
	}

	public FileSystemContent() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) throws ContentException {
		this.path = path;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setModify(boolean)
	 */
	@Override
	public void setModify(boolean modify) {
		this.modify = modify;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#isModify()
	 */
	@Override
	public boolean isModify() {
		return modify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() {
		if (childs == null) {
			File f = new File(getPath());
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						childs.add(new FileSystemFolder(file));
					} else {
						childs.add(new FileSystemFile(file));
					}
				}
			}
		}

		return childs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent child) {
		if (childs == null) {
			childs = new ArrayList<iContent>();
		}
		setModify(true);
		childs.add(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#sync()
	 */
	@Override
	public void sync() throws ContentException {
		commit();
		update();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {

		for (iContent iContent : childs) {
			if (iContent.isModify()) {
				iContent.commit();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		childs = null;
		getChilds();
		setModify(false);

	}

	@Override
	public void delete() throws ContentException {
		for (iContent iContent : childs) {
			iContent.delete();
		}
		
	}

}
