/**
 *
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayOutputStream;
import java.util.Collection;

import net.ramso.dita.repository.AbstractFile;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.Messages;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.io.SVNFileRevision;
import org.tmatesoft.svn.core.io.SVNRepository;

/**
 * @author ramso
 *
 */
public class SVNFile extends AbstractFile implements iFile {

	/**
	 *
	 */
	private static final long serialVersionUID = 4653490913079178947L;
	private byte[] content;
	private byte[] oldcontent;
	private SVNRepository repository;
	private String version;

	/**
	 *
	 */
	public SVNFile() {
		// TODO Auto-generated constructor stub
	}

	public SVNFile(SVNRepository repository, String path) {
		this.repository = repository;
		try {
			setNew(false);
			rename(null);
			setModify(false);
			setDelete(false);
			setPath(path);
		} catch (final ContentException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {
		try {
			if (isNew()) {
				addToIndex();
				SVNTools.newFile(repository, getPath(), getContent());
			} else if (isModify()) {
				SVNTools.updateFile(repository, getPath(), getContent(),
						getOldcontent());
			} else if (isDelete()) {
				removeFromIndex();
				SVNTools.delete(repository, getPath());
			} else if (isRename()) {
				removeFromIndex();
				SVNTools.newFile(repository, getRename(), getContent());
				SVNTools.delete(repository, getPath());
				setPath(getRename());
				addToIndex();
			}
		} catch (final SVNException e) {
			throw new ContentException(e);
		} catch (final IndexException e) {
			throw new ContentException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.iFile#getContent()
	 */
	@Override
	public byte[] getContent() throws ContentException {
		if ((content == null) && !isNew()) {
			final SVNProperties fileProperties = new SVNProperties();
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				final SVNNodeKind nodeKind = repository
						.checkPath(getPath(), -1);
				if (nodeKind == SVNNodeKind.NONE) {
					throw new ContentException(Messages.getString(
							"SVNFile.exception.notfound", getPath())); //$NON-NLS-1$
				} else if (nodeKind == SVNNodeKind.DIR) {
					throw new ContentException(Messages.getString(
							"SVNFile.exception.notfile", getPath())); //$NON-NLS-1$
				}
				repository.getFile(getPath(), -1, fileProperties, baos);
			} catch (final SVNException svne) {
				throw new ContentException(Messages.getString(
						"SVNFile.exception.msg", svne.getMessage()));//$NON-NLS-1$
			}
			content = baos.toByteArray();
		}
		return content;
	}

	private byte[] getOldcontent() {
		return oldcontent;
	}

	@Override
	public String getStorageType() {
		return RepositorySVN.TYPE;
	}

	@Override
	public String getVersion() throws ContentException {
		if (version == null) {
			long v = 0;
			Collection<SVNFileRevision> revisions;

			try {
				final long l = repository.getLatestRevision();
				revisions = repository.getFileRevisions(getPath(), null, 0, l);
				for (final SVNFileRevision entry : revisions) {
					if (v < entry.getRevision()) {
						v = entry.getRevision();
					}
				}
				version = "Last Revision: " + v;
			} catch (final SVNException e) {
				throw new ContentException(e);
			}
		}
		return version;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ramso.dita.repository.iFile#setContent(byte[])
	 */
	@Override
	public void setContent(byte[] content) throws ContentException {
		oldcontent = getContent();
		setModify(getOldcontent() != null);
		this.content = content;
		type = null;
		size = null;
		version = null;
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
		content = null;
		oldcontent = null;
		version = null;
		size = null;
		type = null;
		setNew(false);
		rename(null);
		setModify(false);
		setDelete(false);
		getContent();

	}

}
