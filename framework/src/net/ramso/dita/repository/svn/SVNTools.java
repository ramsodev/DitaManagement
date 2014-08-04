/**
 * 
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayInputStream;

import net.ramso.dita.repository.ContentException;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
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
		if (active) {
			if (editor != null) {
				editor.abortEdit();
			}
			active = false;
		}
		return null;
	}

	public static void newFolder(SVNRepository repository, String path)
			throws SVNException {
		moveFolder(repository, path, null);
	}

	public static void moveFolder(SVNRepository repository, String path,
			String from) throws SVNException {
		startCommit(repository);
		int o = openParents(path, -1);
		String f = null;
		if (from != null) {
			f = repository.getRepositoryPath(getName(from));
		}
		String t = getName(path);
		editor.addDir(t, f, l);
		editor.closeDir();
		for (int i = 0; i < o; i++) {
			editor.closeDir();
		}

	}

	public static void delete(SVNRepository repository, String path)
			throws SVNException {
		startCommit(repository);
		editor.deleteEntry(path, -1);
	}

	public static void newFile(SVNRepository repository, String path,
			byte[] content) throws SVNException {
		startCommit(repository);
		int o = openParents(path, -1);

		editor.addFile(path, null, -1);
		editor.applyTextDelta(path, null);
		SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(content), editor, true);
		editor.closeFile(path, checksum);
		for (int i = 0; i < o; i++) {
			editor.closeDir();
		}
	}

	public static void updateFile(SVNRepository repository, String path,
			byte[] content, byte[] oldcontent) throws SVNException {
		startCommit(repository);
		int o = openParents(path, -1);
		editor.openFile(path, -1);
		editor.applyTextDelta(path, null);
		SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(oldcontent), 0,
				new ByteArrayInputStream(content), editor, true);
		editor.closeFile(path, checksum);
		for (int i = 0; i < o; i++) {
			editor.closeDir();
		}

	}

	private static String getParentPath(String path) {
		return path.substring(0, path.lastIndexOf("/"));
	}

	private static String getName(String path) {
		return path.substring(path.indexOf("/") + 1);
	}

	private static int openParents(String path, int i) throws SVNException {
		int o = 1;
		String parent = getParentPath(path);
		if (!parent.trim().isEmpty()) {
			o += openParents(parent, i);
			editor.openDir(parent, i);
		} else {
			o = 0;
		}
		return o;
	}

	public static boolean exist(SVNRepository repository, String path)
			throws SVNException {
		SVNNodeKind nodeKind = repository.checkPath(path, -1);
		if (nodeKind == SVNNodeKind.NONE) {
			return false;
		}
		return true;
	}

}
