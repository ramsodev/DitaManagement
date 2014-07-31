package net.ramso.dita.repository;

public interface iFile extends iContent {
	byte[] getContent() throws ContentException;
	void setContent(byte[] content) throws ContentException;
	void rename(String name) throws ContentException;
	String getRename();
	boolean isRename();
}
