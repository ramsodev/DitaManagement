/**
 * 
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import net.ramso.dita.repository.AbstractFile;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iFile;

/**
 * @author ramso
 *
 */
public class FileSystemFile extends AbstractFile implements iFile {

	private File file;
	private byte[] content;

	public FileSystemFile(File file) {
		super();
		this.file = file;
		if(!file.exists()){
			setNew(true);
		}
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
			if(!file.exists()){
				setNew(true);
			}
		} catch (Exception e) {
			throw new ContentException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.ramso.dita.repository.iContent#commit()
	 */
	@Override
	public void commit() throws ContentException {

		try {
			if (isModify() || isNew()) {
				Files.write(file.toPath(), getContent(),
						StandardOpenOption.SYNC);
			} else if (isDelete()) {
				file.delete();
			}
			if (isRename()) {
				File file2 = new File(file.getParent(), name);
				file.renameTo(file2);
			}
		} catch (FileNotFoundException e) {
			throw new ContentException(e);
		} catch (IOException e) {
			throw new ContentException(e);
		}

		setModify(false);
		setNew(false);
		setDelete(false);
		rename(null);

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
		setModify(false);
		setNew(false);
		setDelete(false);
		rename(null);

	}

	@Override
	public void setContent(byte[] content) {
		this.content = content;
		setModify(true);
	}

}
