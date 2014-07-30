/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.iContent;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author ramso
 *
 */
public class RepositorySVN implements IRepository {
	SVNRepository repository = null;
	public SVNRepository getRepository() {
		return repository;
	}

	public void setRepository(SVNRepository repository) {
		this.repository = repository;
	}

	private String url;
	private String name;
	private String password;

	/**
	 * 
	 */
	public RepositorySVN() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#setup(java.util.Properties)
	 */
	@Override
	public void setup(Properties properties) throws ContentException {
		url = "svn://192.168.1.2/proyectos";
		name = "ramso";
		password = "aurin";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#connect()
	 */
	@Override
	public void connect() throws ContentException {
		try {
			repository = SVNRepositoryFactory.create(SVNURL
					.parseURIDecoded(url));
			ISVNAuthenticationManager authManager = SVNWCUtil
					.createDefaultAuthenticationManager(name, password);
			repository.setAuthenticationManager(authManager);
		} catch (SVNException e) {
			throw new ContentException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#disconnect()
	 */
	@Override
	public void disconnect() {
		repository.closeSession();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getRootContent()
	 */
	@Override
	public iContent getRootContent() throws ContentException {
		return getContent("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iContent getContent(String path) throws ContentException {
		SVNNodeKind nodeKind;
		try {
			nodeKind = repository.checkPath(path, -1);
			if (nodeKind == SVNNodeKind.NONE) {
	            throw new ContentException("There is no entry at '" + url + "'.");
	        } else if (nodeKind == SVNNodeKind.FILE) {
	        	throw new ContentException("The entry at '" + url + "' is a file while a directory was expected.");
	        }
		} catch (SVNException e) {
			throw new ContentException(e);
		}
		SVNContent content = new SVNContent(repository, path);
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#sync()
	 */
	@Override
	public void sync() throws ContentException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#commit()
	 */
	@Override
	public void commit() throws ContentException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#update()
	 */
	@Override
	public void update() throws ContentException {
		// TODO Auto-generated method stub

	}

}
