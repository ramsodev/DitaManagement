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
	void setModify(boolean modify);
	boolean isModify();
	ArrayList<iContent> getChilds() throws ContentException;
	void addChild(iContent chid);
	void sync() throws ContentException;
	void commit() throws ContentException;
	void update() throws ContentException;
	void delete() throws ContentException;

}
