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
public class repository implements IRepository {
	SVNRepository repository = null;
	private String url;
	private String name;
	private String password;

	/**
	 * 
	 */
	public repository() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#setup(java.util.Properties)
	 */
	@Override
	public void setup(Properties properties) throws ContentException {
		url = "http://svn.svnkit.com/repos/svnkit/trunk/doc";
		name = "anonymous";
		password = "anonymous";

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
		SVNNodeKind nodeKind;
		try {
			nodeKind = repository.checkPath( "" ,  -1 );
			if ( nodeKind == SVNNodeKind.NONE ) {
	            throw new ContentException( "There is no entry at '" + url + "'." );
	        } else if ( nodeKind == SVNNodeKind.FILE ) {
	        	throw new ContentException( "The entry at '" + url + "' is a file while a directory was expected." );
	           
	        }
			Collection entries = repository.getDir( path, -1 , null , (Collection) null );
	        Iterator iterator = entries.iterator( );
	        while ( iterator.hasNext( ) ) {
	            SVNDirEntry entry = ( SVNDirEntry ) iterator.next( );
	            System.out.println( "/" + (path.equals( "" ) ? "" : path + "/" ) + entry.getName( ) + 
	                               " ( author: '" + entry.getAuthor( ) + "'; revision: " + entry.getRevision( ) + 
	                               "; date: " + entry.getDate( ) + ")" );
	            if ( entry.getKind() == SVNNodeKind.DIR ) {
	                listEntries( repository, ( path.equals( "" ) ) ? entry.getName( ) : path + "/" + entry.getName( ) );
	            }
	        }
			
		} catch (SVNException e) {
			throw new ContentException(e);
		}
        
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iContent getContent(String path) {
		// TODO Auto-generated method stub
		return null;
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
