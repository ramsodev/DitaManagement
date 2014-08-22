package net.ramso.dita.repository.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.apache.tika.metadata.HttpHeaders;

public class ContentSearchQuery {

	private static ContentSearchQuery contentSearch;

	public static ContentSearchQuery getInstance() {
		if (ContentSearchQuery.contentSearch == null) {
			ContentSearchQuery.contentSearch = new ContentSearchQuery();
		}
		return ContentSearchQuery.contentSearch;
	}

	private IndexSearcher searcher;

	protected ContentSearchQuery() {
		try {
			init();
		} catch (final ConfigException e) {
			LogManager.error("[Content Search] fail initializing", e);
		}
	}

	public ScoreDoc[] findAbsoluteByPath(String path) throws IndexException {
		path = path.replace("/", "//");
		final String searchTerm = "Path:\"" + path + "\"";
		ScoreDoc[] hits = null;
		try {
			final QueryParser parser = new QueryParser(Version.LUCENE_4_9,
					"Path", new StandardAnalyzer(Version.LUCENE_4_9));
			final Query query = parser.parse(searchTerm);
			hits = getSearcher().search(query, 1).scoreDocs;
		} catch (final ParseException e) {
			throw new IndexException("Fail queryng", e);
		} catch (final IOException e) {
			throw new IndexException("Fail queryng", e);
		}
		return hits;

	}

	public ScoreDoc[] findFromAll(String text) throws IndexException {
		return findFromAll(text, 100);
	}

	public ScoreDoc[] findFromAll(String text, int number)
			throws IndexException {
		ScoreDoc[] hits = null;
		try {
			final MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
					Version.LUCENE_4_9, new String[] { "Content Name", "path",
							"content", "MIME Type", "Dita Type" },
					new StandardAnalyzer(Version.LUCENE_4_9));
			final Query query = queryParser.parse(text);
			hits = getSearcher().search(query, number).scoreDocs;
		} catch (final ParseException e) {
			throw new IndexException("Fail queryng", e);
		} catch (final IOException e) {
			throw new IndexException("Fail queryng", e);
		}
		return hits;
	}

	public IndexSearcher getSearcher() {
		return searcher;
	}

	@PostConstruct
	public void init() throws ConfigException {
		final Directory index = ContentIndexer.getInstance().getIndexWriter()
				.getDirectory();
		try {
			final IndexReader reader = DirectoryReader.open(index);
			searcher = new IndexSearcher(reader);
			LogManager
					.info("--------------Searcher initialized----------------");
		} catch (final IOException e) {
			throw new ConfigException("Impossible to configure lucene", e);
		}

	}

	public IndexStatistis getStatistics() throws IndexException {
		Bits liveDocs;
		IndexStatistis statistis = new IndexStatistis();
		try {
			liveDocs = MultiFields.getLiveDocs(getSearcher().getIndexReader());
			String field = "MIME Type";
			TermsEnum termEnum = MultiFields.getTerms(
					getSearcher().getIndexReader(), field).iterator(null);
			BytesRef bytesRef;
			HashMap<String, Number> terms = new HashMap<String, Number>();
			while ((bytesRef = termEnum.next()) != null) {
				if (termEnum.seekExact(bytesRef)) {
					DocsEnum docsEnum = termEnum.docs(liveDocs, null);
					if (docsEnum != null) {
						int doc;
						while ((doc = docsEnum.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
							String key = bytesRef.utf8ToString();
							Integer c = new Integer(0);
							if (terms.containsKey(key)) {
								c = (Integer) terms.get(key);
							}
							c = c + 1;
							terms.put(key, c);
						}
					}
				}
			}
			statistis.setMime(terms);
			field = "DITA Type";
			termEnum = MultiFields.getTerms(getSearcher().getIndexReader(),
					field).iterator(null);
			terms = new HashMap<String, Number>();
			while ((bytesRef = termEnum.next()) != null) {
				if (termEnum.seekExact(bytesRef)) {
					DocsEnum docsEnum = termEnum.docs(liveDocs, null);
					if (docsEnum != null) {
						int doc;
						while ((doc = docsEnum.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
							String key = bytesRef.utf8ToString();
							Integer c = new Integer(0);
							if (terms.containsKey(key)) {
								c = (Integer) terms.get(key);
							}
							c = c + 1;
							terms.put(key, c);
						}
					}
				}
			}
			statistis.setDita(terms);
		} catch (IOException e) {
			throw new  IndexException("Statistics failed",e);
		}
		return statistis;
	}
}
