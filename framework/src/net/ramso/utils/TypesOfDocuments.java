/**
 *
 */
package net.ramso.utils;

/**
 * @author ramso
 *
 */
public enum TypesOfDocuments {
	BOOKMAP("bookmap", "net.ramso.dita.bookmap"); //$NON-NLS-1$ //$NON-NLS-2$
	private String name;

	TypesOfDocuments(String name, String pkg) {
		this.name = name;
	}

	public String getKey() {

		return name;
	}

}
