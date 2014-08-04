package net.ramso.dita.repository.svn;

import net.ramso.dita.content.ContentFactory;
import net.ramso.dita.repository.iFolder;
import net.ramso.utils.ApplicationConfiguration;

public class test2 {

	public test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ac.setupRepository();
		ContentFactory cFactory = new ContentFactory();
		try {
			
			 iFolder ps = cFactory.getProjects();
			 System.out.println(ps.getPath());
//			 Files.readAllBytes("");
//			Path path = FileSystems.getDefault().getPath(
//					"/home/ramso/Dropbox/Trabajo/sibbac/DDR/", "ddr.ditamap");
//			byte[] b = Files.readAllBytes(path);
//
//			Object obj = cFactory.getDitaDocument(b);
//			System.out.println(obj.getClass().getCanonicalName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
