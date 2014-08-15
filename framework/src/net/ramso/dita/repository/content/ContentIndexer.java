package net.ramso.dita.repository.content;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class ContentIndexer {

	private static String indexPath;
	private static ContentIndexer contentIndexer;
	private IndexWriter indexWriter;

	protected ContentIndexer() {
		try {
			init();
		} catch (ConfigException e) {
			LogManager.error("[Content Indexer] fail initializing", e);
		}
	}

	public static ContentIndexer getInstance() {
		if (contentIndexer == null) {
			contentIndexer = new ContentIndexer();
		}
		return contentIndexer;
	}

	public static void configure(String path) {
		indexPath = path;
	}

	@PostConstruct
	public void init() throws ConfigException {
		if (indexPath != null) {
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
			Directory index;
			try {
				index = new SimpleFSDirectory(new File(indexPath));
				IndexWriterConfig config = new IndexWriterConfig(
						Version.LUCENE_4_9, analyzer);

				indexWriter = new IndexWriter(index, config);
				LogManager
						.info("-------------Indexer Initialized----------------\n----"
								+ indexWriter.numDocs() + " Documents indexed");

			} catch (IOException e) {
				throw new ConfigException("Impossible to configure lucene", e);
			}
		} else {
			throw new ConfigException("The path of the index is null");
		}
	}

	public void close() throws IndexException {
		try {
			indexWriter.deleteUnusedFiles();
			indexWriter.commit();
			indexWriter.close();
		} catch (IOException e) {
			throw new IndexException("Fail closing the index", e);
		}
	}

	public void clean() throws IndexException {
		try {
			indexWriter.deleteAll();
			indexWriter.commit();
		} catch (IOException e) {
			throw new IndexException("Fail to clean the index", e);
		}
	}

	public void add(Map<String, String> metadata, String content)
			throws IndexException {
		Document doc = new Document();
		for (Entry<String, String> entry : metadata.entrySet()) {
			doc.add(new TextField(entry.getKey(), entry.getValue(),
					Field.Store.YES));
		}
		if (content != null) {
			doc.add(new TextField("content", content, Field.Store.YES));
		}
		try {
			indexWriter.addDocument(doc);
		} catch (IOException e) {
			throw new IndexException("Fail to add to index", e);
		}
	}

	public void remove(String name) throws IndexException {
		try {
			QueryParser parser = new QueryParser(Version.LUCENE_4_9, "Path",
					new StandardAnalyzer(Version.LUCENE_4_9));
			Query query = parser.parse(name);
			getIndexWriter().deleteDocuments(query);
		} catch (ParseException e) {
			throw new IndexException("Fail to delete in index", e);
		} catch (IOException e) {
			throw new IndexException("Fail to delete in index", e);
		}

	}

	public IndexWriter getIndexWriter() {
		return indexWriter;
	}

}
