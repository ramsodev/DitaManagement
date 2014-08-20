/**
 *
 */
package net.ramso.dita.repository;


/**
 * @author ramso
 *
 */
public abstract class AbstractFolder extends AbstractContent implements iFolder {

	/**
	 *
	 */
	private static final long serialVersionUID = -8436973110239733672L;
	protected String name = null;

	/**
	 *
	 */
	public AbstractFolder() {
		super();
	}

	/**
	 * @param path
	 */
	public AbstractFolder(String path) {
		super(path);
	}

	@Override
	public String getRename() {
		return name;
	}

	@Override
	public boolean isRename() {
		return getRename() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iFile#rename(java.lang.String)
	 */
	@Override
	public void rename(String name) throws ContentException {
		this.name = name;

	}
}
