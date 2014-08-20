/**
 *
 */
package net.ramso.dita.repository;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author ramso
 *
 */
public interface iContent extends Serializable {
	void addChild(iContent child) throws ContentException;

	void commit() throws ContentException;

	ArrayList<iContent> getChilds() throws ContentException;

	String getPath();

	String getStorageType();

	String getVersion() throws ContentException;

	boolean isDelete();

	boolean isModify();

	boolean isNew();

	void setDelete(boolean change);

	void setModify(boolean change);

	void setNew(boolean change);

	void setPath(String path) throws ContentException;

	void update() throws ContentException;
}
