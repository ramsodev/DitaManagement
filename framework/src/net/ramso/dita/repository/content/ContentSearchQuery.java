package net.ramso.dita.repository.content;

import java.io.IOException;

import javax.annotation.PostConstruct;

import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.apache.tika.metadata.Metadata;

public class ContentSearchQuery {

	private static ContentSearchQuery contentSearch;
	private IndexSearcher searcher;

	protected ContentSearchQuery() {
		try {
			init();
		} catch (ConfigException e) {
			LogManager.error("[Content Search] fail initializing", e);
		}
	}

	public static ContentSearchQuery getInstance() {
		if (contentSearch == null) {
			contentSearch = new ContentSearchQuery();
		}
		return contentSearch;
	}

	@PostConstruct
	public void init() throws ConfigException {
		Directory index = ContentIndexer.getInstance().getIndexWriter()
				.getDirectory();
		try {
			IndexReader reader = DirectoryReader.open(index);
			searcher = new IndexSearcher(reader);
			LogManager.info("--------------Searcher initialized----------------");
		} catch (IOException e) {
			throw new ConfigException("Impossible to configure lucene", e);
		}

	}

	public ScoreDoc[] findFromAll(String text) throws IndexException {
		ScoreDoc[] hits = null;
		try {
			MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
					Version.LUCENE_4_9, new String[] { "Content Name", "path",
							"content", Metadata.CONTENT_TYPE, "Dita Type" },
					new StandardAnalyzer(Version.LUCENE_4_9));
			Query query = queryParser.parse(text);			
			hits = getSearcher().search(query, 25).scoreDocs;
		} catch (ParseException e) {
			throw new IndexException("Fail queryng", e);
		} catch (IOException e) {
			throw new IndexException("Fail queryng", e);
		}
		return hits;
	}

	public ScoreDoc[] findAbsoluteByPath(String path) throws IndexException {
		path = path.replace("/", "//");
		String searchTerm="Path:\""+path+"\"";
		ScoreDoc[] hits = null;
		try {
			QueryParser parser = new QueryParser(Version.LUCENE_4_9, "Path",
					new StandardAnalyzer(Version.LUCENE_4_9));
			Query query = parser.parse(searchTerm);
			hits = getSearcher().search(query, 1).scoreDocs;
		} catch (ParseException e) {
			throw new IndexException("Fail queryng", e);
		} catch (IOException e) {
			throw new IndexException("Fail queryng", e);
		}
		return hits;

	}

	public IndexSearcher getSearcher() {
		return searcher;
	}
}
