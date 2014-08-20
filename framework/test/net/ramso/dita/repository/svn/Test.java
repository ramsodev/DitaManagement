package net.ramso.dita.repository.svn;

import java.util.ArrayList;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.IRepository;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.utils.ApplicationConfiguration;

import org.tmatesoft.svn.core.SVNException;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ContentFactory cFactory = ContentFactory.getInstance();

		try {

			
//			iContent content = repo.getRoot();
			// createFolder(repo, content, "Test");
			iFile f = cFactory.getFile("/dita/projects/DDR/sibbac.dita");
			System.out.println(f.getMime());
			// iFolder iContent = (iFolder) repo.getContent("/Test");
			//
			// deleteFolder(repo, iContent);
//			iFolder iContent = (iFolder) repo.getFolder("/Test");
			// deleteFolder(repo, iContent);
			// iContent = (iFolder) repo.getContent("/Test2");
			// deleteFolder(repo, iContent);
			// deleteFile(repo, iContent, iContent.getChilds().get(0));
			// modFiles(repo, iContent, (iFile) iContent.getChilds().get(0));
//			renameFolder(repo, content, iContent, "cambia");

			// }
			// }

			// String tabs = "";
			// displayChilds(tabs, childs);
			cFactory.disconnect();
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private static void deleteFile(IRepository repo, iFolder iContent,
			iContent iContent2) throws ContentException, SVNException {
		iContent2.setDelete(true);
		iContent.commit();
		SVNTools.endCommit();

	}

	private static void modFiles(IRepository repo, iFolder folder, iFile file)
			throws ContentException, SVNException {
		file.setContent("Nuevo texto".getBytes());
		folder.commit();
		SVNTools.endCommit();
	}

	private static void addFiles(IRepository repo, iFolder folder, String name)
			throws ContentException {
		SVNFile file = new SVNFile(((RepositorySVN) repo).getRepository(),
				folder.getPath() + "/" + name);
		file.setNew(true);
		file.setContent("Texto de prueba".getBytes());
		folder.addChild(file);

	}

	private static void deleteFolder(IRepository repo, iFolder iContent)
			throws ContentException, SVNException {
		iContent.setDelete(true);
		iContent.commit();
		SVNTools.endCommit();
	}

	private static void createFolder(IRepository repo, iContent content,
			String name) throws ContentException, SVNException {
		SVNFolder folder = new SVNFolder(
				((RepositorySVN) repo).getRepository(), content.getPath() + "/"
						+ name);
		folder.setNew(true);
		content.addChild(folder);
		addFiles(repo, folder, "file1.txt");
		addFiles(repo, folder, "file2.txt");
		content.commit();
		SVNTools.endCommit();

	}

	private static void renameFolder(IRepository repo, iContent content,
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
