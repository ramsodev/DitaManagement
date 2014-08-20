package net.ramso.dita.repository.svn;

import net.ramso.dita.bookmap.Bookmap;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.utils.ApplicationConfiguration;

public class CopyOfTestjaxb {

	public CopyOfTestjaxb() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ContentFactory cFactory = ContentFactory.getInstance();
		try {
			iFile file = cFactory.getFile("/DDR/ddr.ditamap");
			Bookmap bookmap = (Bookmap) file.getDocument(); 
			bookmap.getAllAttributes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
