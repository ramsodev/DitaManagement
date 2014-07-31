/**
 * 
 */
package net.ramso.dita.repository;

import java.util.ArrayList;

/**
 * @author ramso
 *
 */
public abstract class AbstractFile extends AbstractContent implements iFile {

	protected String name = null;

	/**
	 * @param path
	 */
	public AbstractFile(String path) {
		super(path);
	}

	/**
	 * 
	 */
	public AbstractFile() {
		super();
	}

	/* 
	 * Allwais null, a file not have childs
	 *  
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() throws ContentException {
		return null;
	}

	/* 
	 * Make nothing
	 * @see net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository.iContent)
	 */
	@Override
	public void addChild(iContent child) throws ContentException {

	}


	/* (non-Javadoc)
	 * @see net.ramso.dita.repository.iFile#rename(java.lang.String)
	 */
	@Override
	public void rename(String name) throws ContentException {
		this.name  = name;

	}
	@Override
	public String getRename() {
		return name;
	}

	@Override
	public boolean isRename() {
		return getRename()!=null;
	}
}
