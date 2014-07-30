/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.util.ArrayList;
import java.util.Collection;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;

/**
 * @author ramso
 *
 */
public class SVNContent implements iContent {

	protected SVNRepository repository;
	private String path;
	private boolean modify;
	private ArrayList<iContent> childs = null;

	/**
	 * 
	 */
	public SVNContent() {
		// TODO Auto-generated constructor stub
	}

	public SVNContent(SVNRepository repository, String path) {
		this.repository = repository;
		try {
			setPath(path);
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) throws ContentException {
		this.path = path;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setModify(boolean)
	 */
	@Override
	public void setModify(boolean modify) {
		this.modify = modify;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#isModify()
	 */
	@Override
	public boolean isModify() {
		return modify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() throws ContentException {
		if (childs == null) {
			childs = new ArrayList<iContent>();
			Collection<SVNDirEntry> entries;
			try {

				entries = repository.getDir(path, -1, null, (Collection) null);
				for (SVNDirEntry entry : entries) {
					if (entry.getKind() == SVNNodeKind.DIR) {
						childs.add(new SVNFolder(repository, getPath() + "/"
								+ entry.getRelativePath()));
					} else if (entry.getKind() == SVNNodeKind.FILE) {
						childs.add(new SVNFile(repository, entry
								.getRelativePath()));
					}
				}
			} catch (SVNException e) {
				throw new ContentException(e);
			}
		}
		return childs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent child) {
		if (childs == null) {
			childs = new ArrayList<iContent>();
		}
		childs.add(child);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#sync()
	 */
	@Override
	public void sync() throws ContentException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {
		if (modify) {
			try {
				System.out.println(getPath());
				String logMessage = "log message";
				ISVNEditor editor = repository.getCommitEditor(logMessage,
						null /* locks */, true /* keepLocks */, null /* mediator */);
				repository.checkout(-1, "check01", false, editor);

			} catch (SVNException e) {
				throw new ContentException(e);
			}
		}
		for (iContent iContent : childs) {
			iContent.commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#delete()
	 */
	@Override
	public void delete() throws ContentException {
		// TODO Auto-generated method stub

	}

}
