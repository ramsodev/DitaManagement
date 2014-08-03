package net.ramso.dita.repository.svn;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.ApplicationConfiguration;

public class test2 {

	public test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ContentFactory cFactory = new ContentFactory();
		try {
			iFolder ps = cFactory.getProjects();
			System.out.println(ps.getPath());
		} catch (ContentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
