package net.ramso.dita.repository;

import java.util.Properties;

public interface IRepository {
	void addChild(iContent child) throws ContentException;

	void commit() throws ContentException;

	void connect() throws RepositoryException;

	void disconnect() throws RepositoryException;

	iFile getFile(String path) throws ContentException;

	iFolder getFolder(String path) throws ContentException;

	iFolder getParent(String path) throws ContentException;

	iContent getRoot() throws ContentException;

	void setup(Properties properties) throws RepositoryException;

	void update() throws ContentException;

}
