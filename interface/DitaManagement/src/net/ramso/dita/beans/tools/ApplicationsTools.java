package net.ramso.dita.beans.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import net.ramso.dita.beans.content.ContentItem;
import net.ramso.dita.repository.ContentException;
import net.ramso.dita.repository.RepositoryException;
import net.ramso.dita.repository.iFile;
import net.ramso.dita.repository.iFolder;
import net.ramso.dita.repository.content.ContentFactory;
import net.ramso.utils.LogManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ApplicationScoped
public class ApplicationsTools {
	private static ContentItem selectedContent = null;

	public static ContentItem getSelectedContent() {
		return ApplicationsTools.selectedContent;
	}

	public static void setSelectedContent(ContentItem contentItem) {
		ApplicationsTools.selectedContent = contentItem;
	}

	public void commit() {
		try {
			ContentFactory.getInstance().commit();
			ContentFactory.getInstance().refresh();
		} catch (final ContentException e) {
			ApplicationMessage.setError("Error added uploads file", e);
		} catch (final RepositoryException e) {
			ApplicationMessage.setError("Error added uploads file", e);
		}
		RequestContext.getCurrentInstance().closeDialog("");
	}

	public StreamedContent getImage() {
		return ApplicationsTools.selectedContent.getImage();
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (ApplicationsTools.getSelectedContent() == null) {
			LogManager.warn("Parent folder is null");
		} else {
			if (ApplicationsTools.getSelectedContent().getContent() instanceof iFolder) {
				try {
					final UploadedFile f1 = event.getFile();
					if (f1.getContentType().equals("application/zip")) {
						unZip(f1);
					} else {
						final iFile f = ContentFactory.getInstance()
								.createFile(
										((iFolder) ApplicationsTools
												.getSelectedContent()
												.getContent()).getPath()
												+ "/" + f1.getFileName());
						final InputStream is = f1.getInputstream();
						final byte[] b = IOUtils.toByteArray(is);
						f.setContent(b);
					}
				} catch (final IOException e) {
					ApplicationMessage.setError("Error in upload file", e);
				} catch (final ContentException e) {
					ApplicationMessage.setError("Error in upload file", e);
				} catch (final RepositoryException e) {
					ApplicationMessage.setError("Error in upload file", e);
				}
			}
		}
	}

	private void unZip(UploadedFile file) {
		try {
			final ZipInputStream in = new ZipInputStream(file.getInputstream());
			ZipEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					ContentFactory.getInstance().createFolder(
							((iFolder) ApplicationsTools.getSelectedContent()
									.getContent()).getPath()
									+ "/"
									+ entry.getName());
				} else {
					final ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int data = 0;
					while ((data = in.read()) != -1) {
						bao.write(data);
					}
					final iFile f = ContentFactory.getInstance().createFile(
							((iFolder) ApplicationsTools.getSelectedContent()
									.getContent()).getPath()
									+ "/"
									+ entry.getName());
					f.setContent(bao.toByteArray());
					bao.close();
				}
			}
			in.close();
		} catch (final Exception e) {
			ApplicationMessage.setError("Error importing zip file", e);
		}
	}
}