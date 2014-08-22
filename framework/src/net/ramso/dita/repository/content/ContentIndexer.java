package net.ramso.dita.repository.content;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.TikaMetadataKeys;

public class ContentIndexer {

	private static ContentIndexer contentIndexer;
	private static String indexPath;

	public static void configure(String path) {
		ContentIndexer.indexPath = path;
	}

	public static ContentIndexer getInstance() {
		if (ContentIndexer.contentIndexer == null) {
			ContentIndexer.contentIndexer = new ContentIndexer();
		}
		return ContentIndexer.contentIndexer;
	}

	private IndexWriter indexWriter;

	protected ContentIndexer() {
		try {
			init();
		} catch (final ConfigException e) {
			LogManager.error("[Content Indexer] fail initializing", e);
		}
	}

	public void add(Map<String, String> metadata, String content)
			throws IndexException {
		final Document doc = new Document();
		for (final Entry<String, String> entry : metadata.entrySet()) {
			if (entry.getKey().equals("MIME Type")) {
				doc.add(new StringField(entry.getKey(), entry.getValue(),
						Field.Store.YES));
			} else {
				doc.add(new TextField(entry.getKey(), entry.getValue(),
						Field.Store.YES));
			}
		}
		if (content != null) {
			doc.add(new TextField("content", content, Field.Store.YES));
		}
		try {
			indexWriter.addDocument(doc);
		} catch (final IOException e) {
			throw new IndexException("Fail to add to index", e);
		}
	}

	public void clean() throws IndexException {
		try {
			indexWriter.deleteAll();
			indexWriter.commit();
		} catch (final IOException e) {
			throw new IndexException("Fail to clean the index", e);
		}
	}

	public void close() throws IndexException {
		try {
			indexWriter.deleteUnusedFiles();
			indexWriter.commit();
			indexWriter.close();
		} catch (final IOException e) {
			throw new IndexException("Fail closing the index", e);
		}
	}

	public IndexWriter getIndexWriter() {
		return indexWriter;
	}

	@PostConstruct
	public void init() throws ConfigException {
		if (ContentIndexer.indexPath != null) {
			final StandardAnalyzer analyzer = new StandardAnalyzer(
					Version.LUCENE_4_9);
			Directory index;
			try {
				index = new SimpleFSDirectory(
						new File(ContentIndexer.indexPath));
				final IndexWriterConfig config = new IndexWriterConfig(
						Version.LUCENE_4_9, analyzer);

				indexWriter = new IndexWriter(index, config);
				LogManager
						.info("-------------Indexer Initialized----------------\n----"
								+ indexWriter.numDocs() + " Documents indexed");

			} catch (final IOException e) {
				throw new ConfigException("Impossible to configure lucene", e);
			}
		} else {
			throw new ConfigException("The path of the index is null");
		}
	}

	public void remove(String name) throws IndexException {
		try {
			final QueryParser parser = new QueryParser(Version.LUCENE_4_9,
					"Path", new StandardAnalyzer(Version.LUCENE_4_9));
			final Query query = parser.parse(name);
			getIndexWriter().deleteDocuments(query);
		} catch (final ParseException e) {
			throw new IndexException("Fail to delete in index", e);
		} catch (final IOException e) {
			throw new IndexException("Fail to delete in index", e);
		}

	}

}
