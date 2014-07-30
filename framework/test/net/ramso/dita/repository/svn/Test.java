package net.ramso.dita.repository.svn;

import java.util.ArrayList;
import java.util.Properties;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;

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
			SVNFolder folder = new SVNFolder(repo.getRepository(), content.getPath() + "/Test" );
			folder.setModify(true);
			content.addChild(folder);
			content.commit();
//			ArrayList<iContent> childs = content.getChilds();
//			String tabs = "";
//			displayChilds(tabs, childs);
			repo.disconnect();
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void displayChilds(String tabs, ArrayList<iContent> childs) throws ContentException {
		for (iContent iContent : childs) {
			System.out.println(tabs + iContent.getPath() + " - "
					+ iContent.getClass().getCanonicalName());
			if (iContent instanceof SVNFolder) {
				displayChilds(tabs + "-", iContent.getChilds());
			}
		}

	}

}
