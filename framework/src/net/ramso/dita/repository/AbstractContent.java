/**
 * 
 */
package net.ramso.dita.repository;

import java.util.ArrayList;

/**
 * @author ramso
 *
 */
public abstract class AbstractContent implements iContent {

	protected String path;
	protected boolean modify;
	protected boolean create;
	protected boolean delete;
	protected ArrayList<iContent> childs = null;

	/**
	 * 
	 * 
	 */
	public AbstractContent(String path) {
		try {
			setPath(path);
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AbstractContent() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getPath()
	 */
	@Override
	public String getPath() {

		return this.path;
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
	public void setModify(boolean change) {
		this.modify = change;

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
	 * @see net.ramso.dita.repository.iContent#setNew(boolean)
	 */
	@Override
	public void setNew(boolean change) {
		this.create = change;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#isNew()
	 */
	@Override
	public boolean isNew() {
		return create;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setDelete(boolean)
	 */
	@Override
	public void setDelete(boolean change) {
		this.delete = change;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#isDelete()
	 */
	@Override
	public boolean isDelete() {
		return delete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {
		for (iContent iContent : getChilds()) {
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
		childs = null;
		getChilds();
		setModify(false);

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent child) throws ContentException{
		if (childs == null) {
			childs = new ArrayList<iContent>();
		}
		setModify(true);
		childs.add(child);
	}

}
