/**
 *
 */
package net.ramso.dita.repository.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import net.ramso.dita.repository.AbstractFile;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.Messages;

/**
 * @author ramso
 *
 */
public class FileSystemFile extends AbstractFile implements iFile {

	/**
	 *
	 */
	private static final long serialVersionUID = 8611813995985388136L;
	private byte[] content;
	private File file;

	public FileSystemFile(File file) {
		super();
		this.file = file;
		if (!file.exists()) {
			setNew(true);
			if (!file.getPath().startsWith(FileSystemRepository.ROOT)) {
				this.file = new File(FileSystemRepository.ROOT + File.separator
						+ file.getPath());
				if (this.file.exists()) {
					setNew(false);
				}
			}
		}
	}

	public FileSystemFile(String path) {
		this(new File(path));
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
				file.createNewFile();
				if ((getContent() == null) || (getContent().length < 1)) {

				} else {
					addToIndex();
					Files.write(file.toPath(), getContent(),
							StandardOpenOption.SYNC);
				}
			} else if (isDelete()) {
				removeFromIndex();
				file.delete();
			}
			if (isRename()) {
				removeFromIndex();
				final File file2 = new File(file.getParent(), name);
				file.renameTo(file2);
				addToIndex();
			}
		} catch (final FileNotFoundException e) {
			throw new ContentException(e);
		} catch (final IOException e) {
			throw new ContentException(e);
		} catch (final IndexException e) {
			throw new ContentException(e);
		}

		setModify(false);
		setNew(false);
		setDelete(false);
		rename(null);

	}

	@Override
	public byte[] getContent() throws ContentException {
		if (content == null) {
			content = new byte[0];
			try {
				content = Files.readAllBytes(file.toPath());
			} catch (final IOException e) {
				throw new ContentException(e);
			}
		}
		return content;
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

	@Override
	public String getStorageType() {

		return FileSystemRepository.TYPE;
	}

	@Override
	public String getVersion() throws ContentException {
		return "Last modification:" + new Date(file.lastModified());
	}

	@Override
	public void setContent(byte[] content) {
		this.content = content;
		type = null;
		setModify(true);
		size = null;
		type = null;
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
				throw new ContentException(Messages.getString(
						"FileSystemFile.exception.msg", path)); //$NON-NLS-1$
			}
			if (!file.exists()) {
				setNew(true);
			}
		} catch (final Exception e) {
			throw new ContentException(e);
		}

	}

	@Override
	public String toString() {
		return getPath();
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
		} catch (final IOException e) {
			throw new ContentException(e);
		}
		setModify(false);
		setNew(false);
		setDelete(false);
		rename(null);

	}

}
