package net.ramso.dita.repository.svn;

import java.util.ArrayList;

import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.dita.repository.content.ContentIndexer;
import net.ramso.dita.repository.content.ContentSearchQuery;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.ApplicationConfiguration;
import net.ramso.utils.ConfManager;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;

public class Testjaxb {

	public Testjaxb() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationConfiguration ac = new ApplicationConfiguration();
		ac.init();
		ContentIndexer.configure("/home/ramso/temp_files/index/");
		ContentFactory cFactory = ContentFactory.getInstance();
		try {
			System.out.println("Indexar");
			System.out.println("------Existen "
					+ ContentIndexer.getInstance().getIndexWriter().numDocs()
					+ " -------------");
//			iFolder p = cFactory.getProjects();
			// indexFolder(p);
//			ScoreDoc[] r = ContentSearchQuery
//					.getInstance()
//					.findAbsoluteByPath(
//							"/home/ramso/temp_files/dita/projects/db2/SAMPLE__SYSCAT_views_Chapter.dita");
			//
//			for (ScoreDoc scoreDoc : r) {
//				int docId = scoreDoc.doc;
//				Document d = ContentSearchQuery.getInstance().getSearcher()
//						.doc(docId);
//
//				System.out.println("-----------Resultados----------");
//
//				System.out.println("-----------Path----------");
//				System.out.println(d.get("Path"));
//			}
//			r = ContentSearchQuery
//					.getInstance()
//					.findFromAll(
//							"image*");
//			int i = 0;
//			for (ScoreDoc scoreDoc : r) {
//				int docId = scoreDoc.doc;
//				Document d = ContentSearchQuery.getInstance().getSearcher()
//						.doc(docId);
//
//				System.out.println("-----------Resultados ALL----------");
//
//				System.out.println("-----------Path----------");
//				System.out.println(i++ + "-" + d.get("Path"));
//			}
			ContentSearchQuery.getInstance().getStatistics();
			System.out.println("------Total index "
					+ ContentIndexer.getInstance().getIndexWriter().numDocs()
					+ " -------------");
			System.out.println("salir");
			ContentIndexer.getInstance().close();
			System.exit(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void indexFolder(iFolder folder) throws ContentException {
		ArrayList<iContent> childs = folder.getChilds();
		System.out.println(folder.getPath());
		for (iContent iContent : childs) {
			if (iContent instanceof iFolder) {
				indexFolder((iFolder) iContent);
			} else {
				System.out.println(iContent.getPath());
				try {
					((iFile) iContent).addToIndex();
				} catch (ContentException | IndexException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}
