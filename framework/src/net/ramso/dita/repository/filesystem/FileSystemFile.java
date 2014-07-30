/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;

/**
 * @author ramso
 *
 */
public class FileSystemFile implements iFile {

	private File file;
	private boolean modify;
	private byte[] content;

	public FileSystemFile(File file) {
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getPath()
	 */
	@Override
	public String getPath() {
		return file.getPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) throws ContentException {
		try {
			file = new File(path);

			if (!file.isFile()) {
				throw new ContentException("The " + path + " is not a file");
			}
		} catch (Exception e) {
			throw new ContentException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#setModify(boolean)
	 */
	@Override
	public void setModify(boolean modify) {
		this.modify = modify;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#isModify()
	 */
	@Override
	public boolean isModify() {
		return modify;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#getChilds()
	 */
	@Override
	public ArrayList<iContent> getChilds() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.ramso.dita.repository.iContent#addChild(net.ramso.dita.repository
	 * .iContent)
	 */
	@Override
	public void addChild(iContent chid) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#sync()
	 */
	@Override
	public void sync() throws ContentException {
		commit();
		update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {
		try {
			Files.write(file.toPath(), getContent(), StandardOpenOption.SYNC);
		} catch (FileNotFoundException e) {
			throw new ContentException(e);
		} catch (IOException e) {
			throw new ContentException(e);
		}
		modify = false;

	}

	public byte[] getContent() {
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#update()
	 */
	@Override
	public void update() throws ContentException {
		try {
			setContent(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			throw new ContentException(e);
		}
		modify = false;

	}

	@Override
	public void setContent(byte[] content) {
		this.content = content;
		modify = true;
	}
	@Override
	public void rename(String name) throws ContentException {
		try {
			File file2 = new File(file.getParent(),name);
			file.renameTo(file2);
		} catch (Exception e) {
			throw new ContentException(e);
		}
		
	}

	@Override
	public void delete() throws ContentException {
		try {
			file.delete();
		} catch (Exception e) {
			throw new ContentException(e);
		}
		
	}

}
