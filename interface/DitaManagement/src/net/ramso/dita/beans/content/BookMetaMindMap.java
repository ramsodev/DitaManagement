package net.ramso.dita.beans.content;

import java.util.List;

import net.ramso.dita.bookmap.Bookmeta;
import net.ramso.dita.bookmap.Data;
import net.ramso.dita.bookmap.DataAbout;
import net.ramso.dita.bookmap.Keyword;
import net.ramso.dita.bookmap.Linktext;
import net.ramso.dita.bookmap.Term;
import net.ramso.dita.bookmap.Text;
import net.ramso.dita.bookmap.Tm;

import org.primefaces.model.mindmap.MindmapNode;

public class BookMetaMindMap {

	private Bookmeta bookMeta;

	public BookMetaMindMap(Bookmeta bookmeta) {
		this.bookMeta = bookmeta;
	}

	public MindmapNode getNode() {
		DitaMindmapNode node = new DitaMindmapNode("Book Metadata", bookMeta,
				"6e9ebf", true);
		Linktext linktext = bookMeta.getLinktext();
		if (linktext != null) {
			getLinkText(linktext, node);
		}
		return node;
	}

	private MindmapNode getLinkText(Linktext linktext, DitaMindmapNode parent) {
		DitaMindmapNode node = getGeneralNode(linktext, "Link Text");
		getContent(linktext.getContent(), node);
		parent.addNode(node);
		return node;
	}

	private void getContent(List<Object> content, DitaMindmapNode parent) {
		for (Object cont : content) {
			if (cont instanceof Term) {
				Term data = (Term) cont;
				DitaMindmapNode node = getGeneralNode(data, "Term");
				getContent(data.getContent(), node);
				parent.addNode(node);
			} else if (cont instanceof Text) {
				Text data = (Text) cont;
				DitaMindmapNode node = getGeneralNode(data, "Text");
				getContent(data.getContent(), node);
				parent.addNode(node);
			} else if (cont instanceof Tm) {
				Tm data = (Tm) cont;
				DitaMindmapNode node = getGeneralNode(data, "Tm");
				getContent(data.getContent(), node);
				parent.addNode(node);
			} else if (cont instanceof Data) {
				Data data = (Data) cont;
				addData(data, parent);
			} else if (cont instanceof DataAbout) {
				DataAbout data = (DataAbout) cont;
				DitaMindmapNode node = getGeneralNode(data, "Data About");
				getContent(data.getDataOrDataAbout(), node);
				parent.addNode(node);
			} else if (cont instanceof Keyword) {
				Keyword data = (Keyword) cont;
				DitaMindmapNode node = getGeneralNode(data, "Keyword");
				getContent(data.getContent(), node);
				parent.addNode(node);
			} else {
				DitaMindmapNode node = getGeneralNode(cont, cont.getClass()
						.getSimpleName());
				parent.addNode(node);
			}
		}
	}

	private void addData(Data data, DitaMindmapNode parent) {
		DitaMindmapNode node = getGeneralNode(data, "Data");
		getContent(data.getContent(), node);
		parent.addNode(node);
	}

	private DitaMindmapNode getGeneralNode(Object object, String title) {
		DitaMindmapNode node = new DitaMindmapNode(title, object, "6e9ebf",
				true);
		return node;
	}

}
