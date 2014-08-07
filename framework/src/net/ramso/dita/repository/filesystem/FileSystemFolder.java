/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.ramso.dita.repository.AbstractFolder;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.Messages;

/**
 * @author ramso
 *
 */
public class FileSystemFolder extends AbstractFolder implements iFolder {

	private File folder;

	public FileSystemFolder(File file) {
		super();
		folder = file;
		if (!folder.exists()) {
			setNew(true);
		}
	}

	public FileSystemFolder(String root) {
		super();
		try {
			setPath(root);
		} catch (ContentException e) {
			e.printStackTrace();
		}
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
				throw new ContentException(Messages.getString("FileSystemFolder.exception.msg",path)); //$NON-NLS-1$
			}
			if (!folder.exists()) {
				setNew(true);
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
	public void commit() throws ContentException {
		try {
			if (isNew()) {
				folder.createNewFile();
			} else if (isRename()) {
				File file = new File(folder.getParent(), getRename());
				folder.renameTo(file);
			}
			super.commit();
		} catch (IOException e) {
			throw new ContentException(e);
		}

	}

}
