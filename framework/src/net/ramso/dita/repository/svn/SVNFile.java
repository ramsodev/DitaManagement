/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayOutputStream;

import net.ramso.dita.repository.AbstractFile;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iFile;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.io.SVNRepository;

/**
 * @author ramso
 *
 */
public class SVNFile extends AbstractFile implements iFile {

	private byte[] content;
	private SVNRepository repository;
	private byte[] oldcontent;

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
		} catch (ContentException e) {
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
				SVNTools.newFile(repository, getPath(), getContent());
			}else if(isModify()){
				SVNTools.updateFile(repository, getPath(), getContent(), getOldcontent());
			}else if(isDelete()){
				SVNTools.delete(repository, getPath());
			}else if(isRename()){
				SVNTools.newFile(repository, getRename(), getContent());
				SVNTools.delete(repository, getPath());
				setPath(getRename());
			}
			
		} catch (SVNException e) {
			throw new ContentException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		content=null;
		oldcontent=null;
		setNew(false);
		rename(null);
		setModify(false);
		setDelete(false);
		getContent();
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#getContent()
	 */
	@Override
	public byte[] getContent() throws ContentException {
		if (content == null && !isNew()) {
			SVNProperties fileProperties = new SVNProperties();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				SVNNodeKind nodeKind = repository.checkPath(getPath(), -1);
				if (nodeKind == SVNNodeKind.NONE) {
					System.err.println("There is no entry at '" + getPath()
							+ "'.");
					System.exit(1);
				} else if (nodeKind == SVNNodeKind.DIR) {
					System.err.println("The entry at '" + getPath()
							+ "' is a directory while a file was expected.");
					System.exit(1);
				}
				repository.getFile(getPath(), -1, fileProperties, baos);
			} catch (SVNException svne) {
				throw new ContentException(
						"error while fetching the file contents and properties: "
								+ svne.getMessage());
			}
			content = baos.toByteArray();
		}
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#setContent(byte[])
	 */
	@Override
	public void setContent(byte[] content) throws ContentException {
		this.oldcontent = getContent();
		setModify(getOldcontent()!=null);		
		this.content = content;
	}

	private byte[] getOldcontent() {
		return oldcontent;
	}
	@Override
	public String toString() {
		return getPath();
	}

}
