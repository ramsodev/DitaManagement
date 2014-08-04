package net.ramso.dita.repository;

import java.util.Properties;

public interface IRepository {
	void setup(Properties properties) throws RepositoryException;
	void connect() throws RepositoryException;
	void disconnect()throws RepositoryException;
	iContent getRoot() throws ContentException;
	iFolder getFolder(String path) throws ContentException;
	iFile getFile(String path) throws ContentException;
	void commit() throws ContentException;
	void update() throws ContentException;
	iFolder getParent(String path) throws ContentException;
	void addChild(iContent child) throws ContentException;
	

}
