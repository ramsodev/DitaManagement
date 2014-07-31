/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayInputStream;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;

/**
 * @author ramso
 *
 */
public class SVNTools {

	private static ISVNEditor editor = null;
	private static boolean active = false;
	private static long l;

	public static ISVNEditor getEditor(SVNRepository repository)
			throws SVNException {
		l = repository.getLatestRevision();
		if (editor == null) {
			editor = repository.getCommitEditor("Dita Manager commit", null);
		}

		return editor;
	}

	public static void startCommit(SVNRepository repository)
			throws SVNException {
		if (!active) {
			editor = getEditor(repository);
			editor.openRoot(-1);
			active = true;
		}
	}

	public static SVNCommitInfo endCommit() throws SVNException {
		SVNCommitInfo info = null;
		if (active) {
			if (editor != null) {
				editor.closeDir();
				info = editor.closeEdit();
				editor = null;
			}
			active = false;
		}
		return info;

	}

	public static SVNCommitInfo abort() throws SVNException {
		if (editor != null) {
			editor.abortEdit();
		}
		return endCommit();
	}

	public static void newFolder(SVNRepository repository, String path)
			throws SVNException {
		moveFolder(repository, path, null);
	}

	public static void moveFolder(SVNRepository repository, String path,
			String from) throws SVNException {
		startCommit(repository);
		String f = null;
		if (from != null) {
			f = repository.getRepositoryPath(getName(from));
		}
		String t = getName(path);
		// long l = repository.getLatestRevision();
		editor.addDir(t, f, l);
		editor.closeDir();

	}

	public static void delete(SVNRepository repository, String path)
			throws SVNException {
		startCommit(repository);
		editor.deleteEntry(path, -1);
	}

	public static void newFile(SVNRepository repository, String path,
			byte[] content) throws SVNException {
		startCommit(repository);
		editor.openDir(getParentPath(path), -1);
		editor.addFile(path, null, -1);
		editor.applyTextDelta(path, null);
		SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(content), editor, true);
		editor.closeFile(path, checksum);
		editor.closeDir();
	}

	public static void updateFile(SVNRepository repository, String path,
			byte[] content, byte[] oldcontent) throws SVNException {
		startCommit(repository);
		editor.openDir(getParentPath(path), -1);
		editor.openFile(path, -1);
		editor.applyTextDelta(path, null);
		SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(oldcontent), 0,
				new ByteArrayInputStream(content), editor, true);
		editor.closeFile(path, checksum);
		editor.closeDir();

	}

	private static String getParentPath(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}

	private static String getName(String path) {
		return path.substring(path.indexOf("/") + 1);
	}

}
