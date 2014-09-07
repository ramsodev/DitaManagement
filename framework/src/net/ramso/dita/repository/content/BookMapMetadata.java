package net.ramso.dita.repository.content;

import java.util.List;

import net.ramso.dita.bookmap.Audience;
import net.ramso.dita.bookmap.Author;
import net.ramso.dita.bookmap.Authorinformation;
import net.ramso.dita.bookmap.Bookchangehistory;
import net.ramso.dita.bookmap.Bookid;
import net.ramso.dita.bookmap.Bookmap;
import net.ramso.dita.bookmap.Bookmeta;
import net.ramso.dita.bookmap.Bookrights;
import net.ramso.dita.bookmap.Category;
import net.ramso.dita.bookmap.Critdates;
import net.ramso.dita.bookmap.Data;
import net.ramso.dita.bookmap.Exportanchors;
import net.ramso.dita.bookmap.Keyword;
import net.ramso.dita.bookmap.Linktext;
import net.ramso.dita.bookmap.Metadata;
import net.ramso.dita.bookmap.Othermeta;
import net.ramso.dita.bookmap.Permissions;
import net.ramso.dita.bookmap.Prodinfo;
import net.ramso.dita.bookmap.Publisherinformation;
import net.ramso.dita.bookmap.Resourceid;
import net.ramso.dita.bookmap.Searchtitle;
import net.ramso.dita.bookmap.Shortdesc;
import net.ramso.dita.bookmap.Source;

public class BookMapMetadata {
	
	public void process(){
		Bookmap bookMap = new Bookmap();
		Bookmeta bookMeta = bookMap.getBookmeta();
		setAudience(bookMeta.getAudience());
		SetAuthor(bookMeta.getAuthor());
		setChangeHistory(bookMeta.getBookchangehistory());
		setRights(bookMeta.getBookrights());
		setCategory(bookMeta.getCategory());
		setKeywords(bookMeta.getKeywords());
		setMetadata(bookMeta.getMetadata());
		setOtherMeta(bookMeta.getOthermeta());
		setProdInfo(bookMeta.getProdinfo());
		setLinkText(bookMeta.getLinktext());
		setSearchTitle(bookMeta.getSearchtitle());
		setShortDescription(bookMeta.getShortdesc());
		setSource(bookMeta.getSource());
		setPublisherInformation(bookMeta.getPublisherinformation());
		setCritDates(bookMeta.getCritdates());
		setPermissions(bookMeta.getPermissions());
		setResourceid(bookMeta.getResourceid());
		setBookId(bookMeta.getBookid());
		setData(bookMeta.getData());
	}

	private void setData(List<Data> data) {
		// TODO Auto-generated method stub
		
	}

	private void setBookId(Bookid bookid) {
		// TODO Auto-generated method stub
		
	}

	private void setResourceid(List<Resourceid> resourceid) {
		// TODO Auto-generated method stub
		
	}

	private void setPermissions(Permissions permissions) {
		// TODO Auto-generated method stub
		
	}

	private void setCritDates(Critdates critdates) {
		// TODO Auto-generated method stub
		
	}

	private void setPublisherInformation(
			Publisherinformation publisherinformation) {
		// TODO Auto-generated method stub
		
	}

	private void setSource(Source source) {
		// TODO Auto-generated method stub
		
	}

	private void setShortDescription(Shortdesc shortdesc) {
		// TODO Auto-generated method stub
		
	}

	private void setSearchTitle(Searchtitle searchtitle) {
		// TODO Auto-generated method stub
		
	}

	private void setLinkText(Linktext linktext) {
		// TODO Auto-generated method stub
		
	}

	private void setProdInfo(List<Prodinfo> prodinfo) {
		// TODO Auto-generated method stub
		
	}

	private void setOtherMeta(List<Othermeta> othermeta) {
		// TODO Auto-generated method stub
		
	}

	private void setMetadata(List<Metadata> metadata) {
		// TODO Auto-generated method stub
		
	}

	private void setKeywords(List<Object> keywords) {
		if(keywords instanceof Keyword){
			
		} else if (keywords instanceof Exportanchors){
			
		}
		
	}

	private void setCategory(List<Category> category) {
		// TODO Auto-generated method stub
		
	}

	private void setRights(List<Bookrights> bookrights) {
		// TODO Auto-generated method stub
		
	}

	private void setChangeHistory(List<Bookchangehistory> bookchangehistory) {
		// TODO Auto-generated method stub
		
	}

	private void SetAuthor(List<Object> author) {
		if(author instanceof Author){
			
		}else if(author instanceof Authorinformation){
			
		}
		
	}

	private void setAudience(List<Audience> audience) {
		for (Audience audience2 : audience) {
			
		}
		
	}

}
