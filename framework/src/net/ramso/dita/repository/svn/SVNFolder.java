/**
 *
 */
package net.ramso.dita.repository.svn;

import java.util.ArrayList;
import java.util.Collection;

import net.ramso.dita.repository.AbstractFolder;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFolder;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;

/**
 * @author ramso
 *
 */
public class SVNFolder extends AbstractFolder implements iFolder {

	/**
	 *
	 */
	private static final long serialVersionUID = -2435994501221318847L;
	private SVNRepository repository;
	private String version;

	/**
	 *
	 */
	public SVNFolder() {
		// TODO Auto-generated constructor stub
	}

	public SVNFolder(SVNRepository repository, String path) {
		this.repository = repository;
		try {
			setNew(false);
			rename(null);
			setModify(false);
			setDelete(false);
			setPath(path);
			try {
				if (!SVNTools.exist(repository, path)) {
					setNew(true);
				}
			} catch (final SVNException e) {
				e.printStackTrace();
			}

		} catch (final ContentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() throws ContentException {
		try {
			if (isDelete()) {
				SVNTools.delete(repository, getPath());
			} else {
				if (isNew()) {
					SVNTools.newFolder(repository, getPath());
				}
				super.commit();
				if (isRename()) {
					SVNTools.moveFolder(repository, getRename(), getPath());
					SVNTools.delete(repository, getPath());
					setPath(getRename());

				}
			}
		} catch (final SVNException e) {
			try {
				SVNTools.abort();
			} catch (final SVNException e1) {
				throw new ContentException(e);
			}
			throw new ContentException(e);
		}

		setNew(false);
		rename(null);
		setModify(false);
		setDelete(false);
		version = null;
	}

	@Override
	public ArrayList<iContent> getChilds() throws ContentException {
		if (childs == null) {
			childs = new ArrayList<iContent>();
			if (isNew()) {
				return childs;
			}
			Collection<SVNDirEntry> entries;
			try {
				entries = repository.getDir(getPath(), -1, null,
						(Collection) null);
				for (final SVNDirEntry entry : entries) {
					if (entry.getKind() == SVNNodeKind.DIR) {
						childs.add(new SVNFolder(repository, getPath() + "/" //$NON-NLS-1$
								+ entry.getRelativePath()));
					} else if (entry.getKind() == SVNNodeKind.FILE) {
						childs.add(new SVNFile(repository, getPath() + "/" //$NON-NLS-1$
								+ entry.getRelativePath()));
					}
				}
			} catch (final SVNException e) {
				throw new ContentException(e);
			}
		}
		return childs;
	}

	@Override
	public String getStorageType() {
		return RepositorySVN.TYPE;
	}

	@Override
	public String getVersion() throws ContentException {
		if (version == null) {
			Collection<SVNDirEntry> entries;
			try {
				entries = repository.getDir(getPath(), -1, null,
						(Collection) null);
				for (final SVNDirEntry entry : entries) {
					version = "Last Revision: " + entry.getRevision()
							+ " Message: " + entry.getCommitMessage();
				}
			} catch (final SVNException e) {
				throw new ContentException(e);
			}
		}
		return version;
	}

	@Override
	public String toString() {
		return getPath();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		childs = null;
		getChilds();
		setNew(false);
		rename(null);
		setModify(false);
		setDelete(false);
		version = null;

	}

}
