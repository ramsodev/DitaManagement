/**
 * 
 */
package net.ramso.dita.repository;

import java.util.ArrayList;

/**
 * @author ramso
 *
 */
public interface iContent {
	String getPath();
	void setPath(String path) throws ContentException;
	void setModify(boolean change);
	boolean isModify();
	void setNew(boolean change);
	boolean isNew();
	void setDelete(boolean change);
	boolean isDelete();
	ArrayList<iContent> getChilds() throws ContentException;
	void addChild(iContent child)throws ContentException;
	void commit() throws ContentException;
	void update() throws ContentException;
	String getStorageType();
	String getVersion() throws ContentException;
}
