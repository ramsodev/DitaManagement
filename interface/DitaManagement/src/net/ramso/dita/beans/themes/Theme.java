package net.ramso.dita.beans.themes;

public class Theme {

	private String displayName;

	private int id;

	private String name;

	public Theme() {
	}

	public Theme(int id, String displayName, String name) {
		this.id = id;
		this.displayName = displayName;
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}