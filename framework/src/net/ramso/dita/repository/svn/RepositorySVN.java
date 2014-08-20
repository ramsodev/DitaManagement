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
import net.ramso.utils.Messages;

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
	public static final String TYPE = "SVN";
	private boolean add;
	private ISVNAuthenticationManager authManager;
	SVNRepository repository = null;
	private iFolder root = null;
	private SVNURL url;

	/**
	 *
	 */
	public RepositorySVN() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addChild(iContent child) throws ContentException {
		try {
			if (!SVNTools.exist(repository, child.getPath())) {
				final String parent = child.getPath().substring(0,
						child.getPath().lastIndexOf("/")); //$NON-NLS-1$
				int idx = parent.indexOf("/"); //$NON-NLS-1$
				iContent content = getRoot();
				while (true) {
					int idx2 = parent.indexOf("/", idx + 1); //$NON-NLS-1$
					if (idx2 == -1) {
						idx2 = parent.length();
					}
					final String element = parent.substring(0, idx2);
					idx = parent.indexOf("/", idx + 1); //$NON-NLS-1$
					add = true;
					final iContent c = getContent(content, element);
					if (add) {
						content.addChild(c);
					}
					content = c;
					if (idx == -1) {
						break;
					}
				}
				content.addChild(child);
			}
		} catch (final SVNException e) {
			throw new ContentException(e);
		}
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
		} catch (final SVNException e) {
			throw new ContentException(e);
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
		} catch (final SVNException e) {
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
		} catch (final SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.closeSession();

	}

	private iContent getContent(iContent content, String path)
			throws ContentException {

		final ArrayList<iContent> cs = content.getChilds();
		for (final iContent c : cs) {
			if (c.getPath().equals(path)) {
				add = false;
				return c;
			}
		}
		add = true;
		return getFolder(path);
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
				throw new ContentException(Messages.getString(
						"RepositorySVN.exception.msg", url)); //$NON-NLS-1$
			}
		} catch (final SVNException e) {
			throw new ContentException(e);
		}
		final SVNFile content = new SVNFile(repository, path);
		content.setNew(create);
		return content;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.IRepository#getContent(java.lang.String)
	 */
	@Override
	public iFolder getFolder(String path) throws ContentException {
		SVNNodeKind nodeKind;
		try {
			nodeKind = repository.checkPath(path, -1);
			if (nodeKind == SVNNodeKind.NONE) {
			} else if (nodeKind == SVNNodeKind.FILE) {
				throw new ContentException(Messages.getString(
						"RepositorySVN.exception.msg", url)); //$NON-NLS-1$
			}
		} catch (final SVNException e) {
			throw new ContentException(e);
		}

		final SVNFolder content = new SVNFolder(repository, path);
		// content.setNew(create);
		// if(create){
		// getParent(path).addChild(content);
		// }else if(!path.isEmpty()){
		// getContentFromRoot(path).addChild(content);
		// }
		return content;
	}

	@Override
	public iFolder getParent(String path) throws ContentException {
		final String parent = path.substring(0, path.lastIndexOf("/")); //$NON-NLS-1$
		if (parent.trim().isEmpty()) {
			return (iFolder) getRoot();
		}
		return getFolder(parent);
	}

	public SVNRepository getRepository() {
		return repository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.IRepository#getRootContent()
	 */
	@Override
	public iContent getRoot() throws ContentException {
		if (root == null) {
			root = getFolder(""); //$NON-NLS-1$
		}
		return root;
	}

	public void setRepository(SVNRepository repository) {
		this.repository = repository;
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
		final String protocol = properties.getProperty("svn.protocol"); //$NON-NLS-1$
		final String host = properties.getProperty("svn.host"); //$NON-NLS-1$
		final int port = new Integer(properties.getProperty("svn.port", "3690")) //$NON-NLS-1$ //$NON-NLS-2$
		.intValue();
		final String path = properties.getProperty("svn.path"); //$NON-NLS-1$
		final String user = properties.getProperty("svn.user"); //$NON-NLS-1$
		final String password = properties.getProperty("svn.password"); //$NON-NLS-1$
		try {
			url = SVNURL.create(protocol, null, host, port, path, false);
			authManager = SVNWCUtil.createDefaultAuthenticationManager(user,
					password);
		} catch (final SVNException e) {
			throw new RepositoryException(e);
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
}
