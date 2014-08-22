/**
 *
 */
package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.iContent;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.dita.repository.content.ContentIndexer;
import net.ramso.dita.repository.content.ContentSearchQuery;
import net.ramso.dita.repository.content.IndexException;
import net.ramso.utils.ConfManager;
import net.ramso.utils.ConfigException;
import net.ramso.utils.LogManager;
import net.ramso.utils.Messages;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class IndexSetupBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5361041953471506726L;

	private String path;

	private int numDocs;

	private int refconunts;

	private HorizontalBarChartModel mime;
	private HorizontalBarChartModel dita;

	@PostConstruct
	public void init() {
		setPath(ConfManager.getInstance().getProperty(ConfManager.INDEX));
		setNumDocs(ContentSearchQuery.getInstance().getSearcher()
				.getIndexReader().numDocs());
		setRefconunts(ContentSearchQuery.getInstance().getSearcher()
				.getIndexReader().getRefCount());
		try {
			setMime(ContentSearchQuery.getInstance().getStatistics().getMime());
			setDita(ContentSearchQuery.getInstance().getStatistics().getDita());
		} catch (IndexException e) {
			LogManager.error("[Index Setup]", e);
		}

	}

	public void save() {
		final Properties properties = new Properties();
		properties.setProperty(ConfManager.INDEX, getPath());
		try {
			ConfManager.getInstance().update(ConfManager.LOGPREFIX, properties);
		} catch (final ConfigException e) {
			ApplicationMessage.setError(
					Messages.getString("ConfigEditorBean.message.save"), e); //$NON-NLS-1$
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getNumDocs() {
		return numDocs;
	}

	public void setNumDocs(int numDocs) {
		this.numDocs = numDocs;
	}

	public int getRefconunts() {
		return refconunts;
	}

	public void setRefconunts(int refconunts) {
		this.refconunts = refconunts;
	}

	public void reIndex() {
		try {
			ContentFactory cFactory = ContentFactory.getInstance();
			long ini = System.currentTimeMillis();
			LogManager.info("Reindexing starting");
			ContentIndexer.getInstance().clean();
			LogManager.info("Cleaning index in "
					+ (System.currentTimeMillis() - ini) + " miliseconds");
			iFolder p = cFactory.getProjects();
			indexFolder(p);
			p = cFactory.getTemplates();
			indexFolder(p);
			p = cFactory.getComponents();
			indexFolder(p);
			LogManager.info("Reindexing end with "
					+ ContentIndexer.getInstance().getIndexWriter().numDocs()
					+ " documents indexing in "
					+ (System.currentTimeMillis() - ini) + " miliseconds");
			ApplicationMessage.setInfo("Reindexing end", "Documents indexing "
					+ ContentIndexer.getInstance().getIndexWriter().numDocs());
			ContentIndexer.getInstance().close();
		} catch (Exception e) {
			ApplicationMessage.setError("Reindexing fail", e);
		}
	}

	public void clean() {
		try {
			ContentIndexer.getInstance().clean();
			ApplicationMessage.setInfo("Index cleaning");
		} catch (IndexException e) {
			ApplicationMessage.setError("[Index cleaning]", e);
		}
	}

	private void indexFolder(iFolder folder) throws ContentException {
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

	private void setDita(Map<String, Number> data) {
		dita = new HorizontalBarChartModel();
		ChartSeries serie = new ChartSeries();
		serie.setLabel("Mime Types");
		for (Entry<String, Number> entry : data.entrySet()) {
			serie.set(entry.getKey(), entry.getValue());
		}
		dita.setTitle("Dita Types in Search Index ");
		dita.setLegendPosition("e");
		dita.addSeries(serie);
		dita.setShadow(true);
		dita.getAxis(AxisType.X).setLabel("Dita Types");
		dita.getAxis(AxisType.Y).setLabel("Number of Documents");
		dita.setShowDatatip(true);
		dita.setShowPointLabels(true);
	}

	private void setMime(Map<String, Number> data) {
		mime = new HorizontalBarChartModel();
		ChartSeries serie = new ChartSeries();
		serie.setLabel("Mime Types");
		for (Entry<String, Number> entry : data.entrySet()) {
			serie.set(entry.getKey(), entry.getValue());
		}
		mime.setTitle("Types of Documents in Search Index");
		mime.setLegendPosition("e");
		mime.addSeries(serie);
		mime.setShadow(true);
		mime.getAxis(AxisType.X).setLabel("Mime Types");
		mime.getAxis(AxisType.Y).setLabel("Number of Documents");
		mime.setShowDatatip(true);
		mime.setShowPointLabels(true);
	}

	public HorizontalBarChartModel getMime() {
		if (mime == null) {
			mime = new HorizontalBarChartModel();
		}
		return mime;
	}

	public HorizontalBarChartModel getDita() {
		if (dita == null) {
			dita = new HorizontalBarChartModel();
		}
		return dita;
	}

}
