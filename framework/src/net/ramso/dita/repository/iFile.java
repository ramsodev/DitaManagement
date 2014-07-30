package net.ramso.dita.repository;

public interface iFile extends iContent {
	byte[] getContent();
	void setContent(byte[] content);
	void rename(String name) throws ContentException;
}
