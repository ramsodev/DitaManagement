/**
 * 
 */
package net.ramso.dita.repository.filesystem;

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

	private String root;
	private FileSystemFolder content;

	/**
	 * 
	 */
	public FileSystemRepository() {
	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#setup(java.util.Properties)
	 */
	@Override
	public void setup(Properties properties) {
		root = properties.getProperty("filesystem.root");

	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#connect()
	 */
	@Override
	public void connect() {

	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#disconnect()
	 */
	@Override
	public void disconnect() {

	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#getRootContent()
	 */
	@Override
	public iContent getRoot() {
		
		if(content==null){
			content = new FileSystemFolder(root);
		}
		return content;
	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iFolder getFolder(String path) {
		return new FileSystemFolder(path);
	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.IRepository#commit()
	 */
	@Override
	public void commit() throws ContentException {
		getRoot().commit();

	}

	/* (non-Javadoc)
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

}
