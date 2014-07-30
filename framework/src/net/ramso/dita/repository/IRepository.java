package net.ramso.dita.repository;

import java.util.Properties;

public interface IRepository {
	void setup(Properties properties) throws ContentException;
	void connect() throws ContentException;
	void disconnect();
	iContent getRootContent() throws ContentException;
	iContent getContent(String path);
	void sync() throws ContentException;
	void commit() throws ContentException;
	void update() throws ContentException;
	
	

}
