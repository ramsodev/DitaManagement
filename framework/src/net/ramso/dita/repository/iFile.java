package net.ramso.dita.repository;

import java.io.Serializable;

public interface iFile extends iContent, IDitaContent, Serializable {
	byte[] getContent() throws ContentException;

	String getMime() throws ContentException;

	String getRename();

	String getSize() throws ContentException;

	boolean isRename();

	void rename(String name) throws ContentException;

	void setContent(byte[] content) throws ContentException;
}
