/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.io.SVNRepository;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;

/**
 * @author ramso
 *
 */
public class SVNFile extends SVNContent implements iFile {

	private byte[] content;

	/**
	 * 
	 */
	public SVNFile() {
		// TODO Auto-generated constructor stub
	}

	public SVNFile(SVNRepository repository, String relativePath) {
		super(repository, relativePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent chid) {
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
		// TODO Auto-generated method stub

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#getContent()
	 */
	@Override
	public byte[] getContent() throws ContentException {
		if (content == null) {
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
	public void setContent(byte[] content) {
		this.content = content;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#rename(java.lang.String)
	 */
	@Override
	public void rename(String name) throws ContentException {
		// TODO Auto-generated method stub

	}

}
