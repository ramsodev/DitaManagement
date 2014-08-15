package net.ramso.dita.repository.svn;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.utils.ApplicationConfiguration;

public class test2 {

	public test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ac.setupRepository();
		ContentFactory cFactory = ContentFactory.getInstance();
		try {
			
			 iFolder ps = cFactory.getProjects();
			 cFactory.createFile(ps.getPath()+File.separator+"borrar1/borrar2/text2.txt");
			 cFactory.createFile(ps.getPath()+File.separator+"text2.txt");
			 cFactory.commit();
			 iFile f1 = cFactory.getFile(ps.getPath()+File.separator+"borrar1/borrar2/text2.txt");
			 f1.setDelete(true);
			 iFolder f2 = cFactory.getFolder((ps.getPath()+File.separator+"borrar1"));
			 f2.setDelete(true);
			 f1.commit();
			 f2.commit();
			 cFactory.disconnect();
//			 System.out.println(ps.getPath());
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
