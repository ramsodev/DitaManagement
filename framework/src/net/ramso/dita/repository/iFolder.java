/**
 * 
 */
package net.ramso.dita.repository;

/**
 * @author ramso
 *
 */
public interface iFolder extends iContent {

	void rename(String name) throws ContentException;
}
