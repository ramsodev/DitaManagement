/**
 * 
 */
package net.ramso.dita.repository;

import java.util.ArrayList;

/**
 * @author ramso
 *
 */
public abstract class AbstractFolder extends AbstractContent implements iFolder {

	protected String name = null;

	/**
	 * @param path
	 */
	public AbstractFolder(String path) {
		super(path);
	}

	/**
	 * 
	 */
	public AbstractFolder() {
		super();
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
