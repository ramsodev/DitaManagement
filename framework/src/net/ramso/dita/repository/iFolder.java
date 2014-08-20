/**
 *
 */
package net.ramso.dita.repository;

import java.io.Serializable;

/**
 * @author ramso
 *
 */
public interface iFolder extends iContent, Serializable {

	String getRename();

	boolean isRename();

	void rename(String name) throws ContentException;
}
