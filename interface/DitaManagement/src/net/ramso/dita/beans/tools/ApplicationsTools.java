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
import org.primefaces.model.mindmap.MindmapNode;

@ManagedBean
@ApplicationScoped
public class ApplicationsTools {
	private static ContentItem selectedContent = null;

	public StreamedContent getImage() {
		return selectedContent.getImage();
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (getSelectedContent() == null) {
			LogManager.warn("Parent folder is null");
		} else {
			if (getSelectedContent().getContent() instanceof iFolder) {
				try {
					UploadedFile f1 = event.getFile();
					if (f1.getContentType().equals("application/zip")) {
						unZip(f1);
					} else {
						iFile f = ContentFactory.getInstance().createFile(
								((iFolder) getSelectedContent().getContent())
										.getPath() + "/" + f1.getFileName());
						InputStream is = f1.getInputstream();
						byte[] b = IOUtils.toByteArray(is);
						f.setContent(b);
					}
				} catch (IOException e) {
					ApplicationMessage.setError("Error in upload file", e);
				} catch (ContentException e) {
					ApplicationMessage.setError("Error in upload file", e);
				} catch (RepositoryException e) {
					ApplicationMessage.setError("Error in upload file", e);
				}
			}
		}
	}

	public void commit() {
		try {
			ContentFactory.getInstance().commit();
			ContentFactory.getInstance().refresh();
		} catch (ContentException e) {
			ApplicationMessage.setError("Error added uploads file", e);
		} catch (RepositoryException e) {
			ApplicationMessage.setError("Error added uploads file", e);
		}
		RequestContext.getCurrentInstance().closeDialog("");
	}

	public static ContentItem getSelectedContent() {
		return selectedContent;
	}

	public static void setSelectedContent(ContentItem contentItem) {
		ApplicationsTools.selectedContent = contentItem;
	}

	private void unZip(UploadedFile file) {
		try {
			ZipInputStream in = new ZipInputStream(file.getInputstream());
			ZipEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					iFolder f = ContentFactory.getInstance().createFolder(
							((iFolder) getSelectedContent().getContent())
									.getPath() + "/" + entry.getName());
				} else {
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					int data = 0;
					while ((data = in.read()) != -1) {
						bao.write(data);
					}
					iFile f = ContentFactory.getInstance().createFile(
							((iFolder) getSelectedContent().getContent())
									.getPath() + "/" + entry.getName());
					f.setContent(bao.toByteArray());
					bao.close();
				}
			}
			in.close();
		} catch (Exception e) {
			ApplicationMessage.setError("Error importing zip file", e);
		}
	}
}