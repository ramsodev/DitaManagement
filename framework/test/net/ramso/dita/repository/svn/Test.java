package net.ramso.dita.repository.svn;

import java.util.ArrayList;
import java.util.Properties;

import org.tmatesoft.svn.core.SVNException;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		RepositorySVN repo = new RepositorySVN();
		try {
			repo.setup(new Properties());
			repo.connect();
			iContent content = repo.getRootContent();
//			 createFolder(repo, content, "Test2");

			iFolder iContent = (iFolder) repo.getContent("/Test");

			// deleteFolder(repo, iContent, );
//			deleteFile(repo, iContent, iContent.getChilds().get(0));
//			modFiles(repo, iContent, (iFile) iContent.getChilds().get(0));
			 renameFolder(repo, content, iContent, "cambia");

			// }
			// }

			// String tabs = "";
			// displayChilds(tabs, childs);
			repo.disconnect();
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void deleteFile(RepositorySVN repo, iFolder iContent,
			iContent iContent2) throws ContentException, SVNException {
		iContent2.setDelete(true);
		iContent.commit();
		SVNTools.endCommit();

	}

	private static void modFiles(RepositorySVN repo, iFolder folder, iFile file)
			throws ContentException, SVNException {
		file.setContent("Nuevo texto".getBytes());
		folder.commit();
		SVNTools.endCommit();
	}

	private static void addFiles(RepositorySVN repo, iFolder folder, String name)
			throws ContentException {
		SVNFile file = new SVNFile(repo.getRepository(), folder.getPath() + "/"
				+ name);
		file.setNew(true);
		file.setContent("Texto de prueba".getBytes());
		folder.addChild(file);

	}

	private static void deleteFolder(RepositorySVN repo, iFolder iContent)
			throws ContentException, SVNException {
		iContent.setDelete(true);
		iContent.commit();
		SVNTools.endCommit();
	}

	private static void createFolder(RepositorySVN repo, iContent content,
			String name) throws ContentException, SVNException {
		SVNFolder folder = new SVNFolder(repo.getRepository(),
				content.getPath() + "/" + name);
		folder.setNew(true);
		content.addChild(folder);
		addFiles(repo, folder, "file1.txt");
		addFiles(repo, folder, "file2.txt");
		content.commit();
		SVNTools.endCommit();

	}

	private static void renameFolder(RepositorySVN repo, iContent content,
			iFolder folder, String name) throws ContentException, SVNException {

		folder.rename(content.getPath() + "/" + name);
		// content.addChild(folder);
		folder.commit();
		SVNTools.endCommit();
	}

	private static void displayChilds(String tabs, ArrayList<iContent> childs)
			throws ContentException {
		for (iContent iContent : childs) {
			System.out.println(tabs + iContent.getPath() + " - "
					+ iContent.getClass().getCanonicalName());
			if (iContent instanceof SVNFolder) {
				displayChilds(tabs + "-", iContent.getChilds());
			}
		}

	}

}
