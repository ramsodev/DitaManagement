/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;

/**
 * @author ramso
 *
 */
public class FileSystemRepository implements IRepository {

	public static final String TYPE = "Local Filesystem";
	static String ROOT;
	private FileSystemFolder content;
	private boolean add;

	/**
	 * 
	 */
	public FileSystemRepository() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#setup(java.util.Properties)
	 */
	@Override
	public void setup(Properties properties) {
		ROOT = properties.getProperty("filesystem.root"); //$NON-NLS-1$

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#connect()
	 */
	@Override
	public void connect() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#disconnect()
	 */
	@Override
	public void disconnect() {
		content=null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getRootContent()
	 */
	@Override
	public iContent getRoot() {

		if (content == null) {
			content = new FileSystemFolder(ROOT);
		}
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iFolder getFolder(String path) {
		return new FileSystemFolder(path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#commit()
	 */
	@Override
	public void commit() throws ContentException {
		getRoot().commit();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#update()
	 */
	@Override
	public void update() throws ContentException {
		getRoot().update();

	}

	@Override
	public iFile getFile(String path) throws ContentException {
		return new FileSystemFile(path);
	}

	@Override
	public iFolder getParent(String path) throws ContentException {
		String parent = path.substring(0, path.lastIndexOf("/")); //$NON-NLS-1$
		if (parent.trim().isEmpty()) {
			return (iFolder) getRoot();
		}
		return getFolder(parent);
	}

	@Override
	public void addChild(iContent child) throws ContentException {
		String relativePath = getRelativePath(child.getPath());		
		if (!new File(child.getPath()).exists()) {
//			getRoot().addChild(child);
			String parent = relativePath.substring(0,
					relativePath.lastIndexOf("/")); //$NON-NLS-1$
			int idx = parent.indexOf("/"); //$NON-NLS-1$
			iContent content = getRoot();
			while (true) {
				int idx2 = parent.indexOf("/", idx + 1); //$NON-NLS-1$
				if (idx2 == -1) {
					idx2 = parent.length();
				}
				String element = parent.substring(0, idx2);
				idx = parent.indexOf("/", idx + 1); //$NON-NLS-1$
				add = true;
				iContent c = getContent(content, element);
				if (add) {
					content.addChild(c);
				}
				content = c;
				if (idx == -1)
					break;
			}
			content.addChild(child);
		}
	}

	private iContent getContent(iContent content2, String path) {
		ArrayList<iContent> cs = content.getChilds();
		for (iContent c : cs) {
			if (getRelativePath(c.getPath()).equals(path)) {
				add = false;
				return c;
			}
		}
		add = true;
		return getFolder(path);
	}
	
	private String getRelativePath(String path){
		String relativePath = path;
		if (relativePath.startsWith(ROOT)) {
			relativePath = path.substring(ROOT.length()-1);
		}
		return relativePath;
	}

}
