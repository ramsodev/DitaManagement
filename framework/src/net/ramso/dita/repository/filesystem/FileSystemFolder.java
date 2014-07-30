/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.util.ArrayList;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;

/**
 * @author ramso
 *
 */
public class FileSystemFolder extends FileSystemContent implements iFolder {

	private File folder;

	public FileSystemFolder(File file) {
		super();
		folder = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getPath()
	 */
	@Override
	public String getPath() {
		return folder.getPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) throws ContentException {
		try {
			folder = new File(path);

			if (!folder.isDirectory()) {
				throw new ContentException("The " + path + " is not a folder");
			}
		} catch (Exception e) {
			throw new ContentException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() {
		if (childs == null) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					childs.add(new FileSystemFolder(file));
				} else {
					childs.add(new FileSystemFile(file));
				}
			}
		}
		return childs;
	}

	@Override
	public void rename(String name) throws ContentException {
		try {
			File file = new File(folder.getParent(),name);
			folder.renameTo(file);
		} catch (Exception e) {
			throw new ContentException(e);
		}
		
	}

}
