/**
 * 
 */
package net.ramso.utils;

/**
 * @author ramso
 *
 */
public enum TypesOfDocuments {
	BOOKMAP ("bookmap", "net.ramso.dita.bookmap");
	private String name;
	private String pkg;

	TypesOfDocuments(String name, String pkg){
		this.name = name;
		this.pkg = pkg;
	}

	public String getKey() {
		
		return name;
	}

}
