/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.util.ArrayList;
import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;

import org.apache.commons.collections.functors.StringValueTransformer;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @author ramso
 *
 */
public class RepositorySVN implements IRepository {
	SVNRepository repository = null;
	private SVNURL url;
	private ISVNAuthenticationManager authManager;
	private iFolder root = null;
	private boolean add;

	public SVNRepository getRepository() {
		return repository;
	}

	public void setRepository(SVNRepository repository) {
		this.repository = repository;
	}

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
	public void setup(Properties properties) throws RepositoryException {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
		String protocol = properties.getProperty("svn.protocol");
		String host = properties.getProperty("svn.host");
		int port = new Integer(properties.getProperty("svn.port", "3690"))
				.intValue();
		String path = properties.getProperty("svn.path");
		String user = properties.getProperty("svn.user");
		String password = properties.getProperty("svn.password");
		try {
			url = SVNURL.create(protocol, null, host, port, path, false);
			authManager = SVNWCUtil.createDefaultAuthenticationManager(user,
					password);
		} catch (SVNException e) {
			throw new RepositoryException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#connect()
	 */
	@Override
	public void connect() throws RepositoryException {
		try {
			if (repository == null) {
				repository = SVNRepositoryFactory.create(url);
				repository.setAuthenticationManager(authManager);
			}
		} catch (SVNException e) {
			throw new RepositoryException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#disconnect()
	 */
	@Override
	public void disconnect() {
		try {
			SVNTools.endCommit();
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.closeSession();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getRootContent()
	 */
	@Override
	public iContent getRoot() throws ContentException {
		if (root == null) {
			root = getFolder("");
		}
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iFolder getFolder(String path) throws ContentException {
		SVNNodeKind nodeKind;
		boolean create = false;
		try {
			nodeKind = repository.checkPath(path, -1);
			if (nodeKind == SVNNodeKind.NONE) {
				create = true;
			} else if (nodeKind == SVNNodeKind.FILE) {
				throw new ContentException("The entry at '" + url
						+ "' is a file while a directory was expected.");
			}
		} catch (SVNException e) {
			throw new ContentException(e);
		}

		SVNFolder content = new SVNFolder(repository, path);
		// content.setNew(create);
		// if(create){
		// getParent(path).addChild(content);
		// }else if(!path.isEmpty()){
		// getContentFromRoot(path).addChild(content);
		// }
		return content;
	}

	private static String getParentPath(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}

	private static String getName(String path) {
		return path.substring(path.lastIndexOf("/"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#commit()
	 */
	@Override
	public void commit() throws ContentException {
		getRoot().commit();
		try {
			SVNTools.endCommit();
		} catch (SVNException e) {
			throw new ContentException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.IRepository#update()
	 */
	@Override
	public void update() throws ContentException {
		root = null;
		getRoot().update();

	}

	@Override
	public iFile getFile(String path) throws ContentException {
		SVNNodeKind nodeKind;
		boolean create = false;
		try {
			nodeKind = repository.checkPath(path, -1);
			if (nodeKind == SVNNodeKind.NONE) {
				create = true;
			} else if (nodeKind == SVNNodeKind.DIR) {
				throw new ContentException("The entry at '" + url
						+ "' is a file while a directory was expected.");
			}
		} catch (SVNException e) {
			throw new ContentException(e);
		}
		SVNFile content = new SVNFile(repository, path);
		content.setNew(create);
		return content;
	}

	public iFolder getParent(String path) throws ContentException {
		String parent = path.substring(0, path.lastIndexOf("/"));
		if (parent.trim().isEmpty()) {
			return (iFolder) getRoot();
		}
		return getFolder(parent);
	}

	public void addChild(iContent child) throws ContentException {
		try {
			if (!SVNTools.exist(repository, child.getPath())) {
				String parent = child.getPath().substring(0,
						child.getPath().lastIndexOf("/"));
				int idx = parent.indexOf("/");
				iContent content = getRoot();
				while (true) {
					int idx2 = parent.indexOf("/", idx + 1);
					if (idx2 == -1) {
						idx2 = parent.length();
					}
					String element = parent.substring(0, idx2);
					idx = parent.indexOf("/", idx + 1);
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
		} catch (SVNException e) {
			throw new ContentException(e);
		}
	}

	private iContent getContent(iContent content, String path)
			throws ContentException {
		
		ArrayList<iContent> cs = content.getChilds();
		for (iContent c : cs) {
			if (c.getPath().equals(path)) {
				add = false;
				return c;
			}
		}
		add = true;
		return getFolder(path);
	}
}
