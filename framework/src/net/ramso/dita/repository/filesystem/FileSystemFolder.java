/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
			if (!folder.getPath().startsWith(FileSystemRepository.ROOT)) {
				this.folder = new File(FileSystemRepository.ROOT + File.separator
						+ folder.getPath());
				if (this.folder.exists()) {
					setNew(false);
				}
			}

		}
	}

	public FileSystemFolder(String path) {
		this(new File(path));
		
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
			if (!folder.exists()) {
				setNew(true);

			} else if (!folder.isDirectory()) {
				throw new ContentException(Messages.getString(
						"FileSystemFolder.exception.msg", path)); //$NON-NLS-1$
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
			childs = new ArrayList<iContent>();
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						FileSystemFolder f = new FileSystemFolder(file);
						childs.add(f);
					} else {
						childs.add(new FileSystemFile(file));
					}
				}
			}
		}
		return childs;
	}

	@Override
	public void commit() throws ContentException {
		try {
			if (isNew()) {
				folder.mkdirs();
			} else if (isRename()) {
				File file = new File(folder.getParent(), getRename());
				folder.renameTo(file);
			}else if(isDelete()){
				if(!folder.delete()){
					throw new ContentException("The folder " + getPath() + " not delete");
				}
			}
			super.commit();
		} catch (SecurityException e) {
			throw new ContentException(e);
		}

	}

	@Override
	public String getStorageType() {

		return FileSystemRepository.TYPE;
	}

	@Override
	public String getVersion() throws ContentException {
		return "Last modification:" + new Date(folder.lastModified());
	}

	@Override
	public String toString() {
		return getPath();
	}

}
