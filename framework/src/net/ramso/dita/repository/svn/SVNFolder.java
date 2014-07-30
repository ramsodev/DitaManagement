/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.util.ArrayList;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;

/**
 * @author ramso
 *
 */
public class SVNFolder extends SVNContent implements iFolder {

	

	/**
	 * 
	 */
	public SVNFolder() {
		// TODO Auto-generated constructor stub
	}

	
	public SVNFolder(SVNRepository repository, String relativePath) {
		super(repository, relativePath);
	}


	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.iContent#sync()
	 */
	@Override
	public void sync() throws ContentException {
		// TODO Auto-generated method stub

	}

	

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.iContent#delete()
	 */
	@Override
	public void delete() throws ContentException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.iFolder#rename(java.lang.String)
	 */
	@Override
	public void rename(String name) throws ContentException {
		// TODO Auto-generated method stub

	}

}
