/**
 *
 */
package net.ramso.dita.repository.svn;

import java.io.ByteArrayInputStream;

import net.ramso.utils.Messages;

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

	private static boolean active = false;
	private static ISVNEditor editor = null;
	private static long l;

	public static SVNCommitInfo abort() throws SVNException {
		if (SVNTools.active) {
			if (SVNTools.editor != null) {
				SVNTools.editor.abortEdit();
			}
			SVNTools.active = false;
		}
		return null;
	}

	public static void delete(SVNRepository repository, String path)
			throws SVNException {
		SVNTools.startCommit(repository);
		SVNTools.editor.deleteEntry(path, -1);
	}

	public static SVNCommitInfo endCommit() throws SVNException {
		SVNCommitInfo info = null;
		if (SVNTools.active) {
			if (SVNTools.editor != null) {
				SVNTools.editor.closeDir();
				info = SVNTools.editor.closeEdit();
				SVNTools.editor = null;
			}
			SVNTools.active = false;
		}
		return info;

	}

	public static boolean exist(SVNRepository repository, String path)
			throws SVNException {
		final SVNNodeKind nodeKind = repository.checkPath(path, -1);
		if (nodeKind == SVNNodeKind.NONE) {
			return false;
		}
		return true;
	}

	public static ISVNEditor getEditor(SVNRepository repository)
			throws SVNException {
		SVNTools.l = repository.getLatestRevision();
		if (SVNTools.editor == null) {
			SVNTools.editor = repository.getCommitEditor(
					Messages.getString("SVNTools.commit.default.msg"), null); //$NON-NLS-1$
		}

		return SVNTools.editor;
	}

	private static String getName(String path) {
		return path.substring(path.indexOf("/") + 1); //$NON-NLS-1$
	}

	private static String getParentPath(String path) {
		return path.substring(0, path.lastIndexOf("/")); //$NON-NLS-1$
	}

	public static void moveFolder(SVNRepository repository, String path,
			String from) throws SVNException {
		SVNTools.startCommit(repository);
		final int o = SVNTools.openParents(path, -1);
		String f = null;
		if (from != null) {
			f = repository.getRepositoryPath(SVNTools.getName(from));
		}
		final String t = SVNTools.getName(path);
		SVNTools.editor.addDir(t, f, SVNTools.l);
		SVNTools.editor.closeDir();
		for (int i = 0; i < o; i++) {
			SVNTools.editor.closeDir();
		}

	}

	public static void newFile(SVNRepository repository, String path,
			byte[] content) throws SVNException {
		SVNTools.startCommit(repository);
		final int o = SVNTools.openParents(path, -1);

		SVNTools.editor.addFile(path, null, -1);
		SVNTools.editor.applyTextDelta(path, null);
		final SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		final String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(content), SVNTools.editor, true);
		SVNTools.editor.closeFile(path, checksum);
		for (int i = 0; i < o; i++) {
			SVNTools.editor.closeDir();
		}
	}

	public static void newFolder(SVNRepository repository, String path)
			throws SVNException {
		SVNTools.moveFolder(repository, path, null);
	}

	private static int openParents(String path, int i) throws SVNException {
		int o = 1;
		final String parent = SVNTools.getParentPath(path);
		if (!parent.trim().isEmpty()) {
			o += SVNTools.openParents(parent, i);
			SVNTools.editor.openDir(parent, i);
		} else {
			o = 0;
		}
		return o;
	}

	public static void startCommit(SVNRepository repository)
			throws SVNException {
		if (!SVNTools.active) {
			SVNTools.editor = SVNTools.getEditor(repository);
			SVNTools.editor.openRoot(-1);
			SVNTools.active = true;
		}
	}

	public static void updateFile(SVNRepository repository, String path,
			byte[] content, byte[] oldcontent) throws SVNException {
		SVNTools.startCommit(repository);
		final int o = SVNTools.openParents(path, -1);
		SVNTools.editor.openFile(path, -1);
		SVNTools.editor.applyTextDelta(path, null);
		final SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
		final String checksum = deltaGenerator.sendDelta(path,
				new ByteArrayInputStream(oldcontent), 0,
				new ByteArrayInputStream(content), SVNTools.editor, true);
		SVNTools.editor.closeFile(path, checksum);
		for (int i = 0; i < o; i++) {
			SVNTools.editor.closeDir();
		}

	}

}
