package net.ramso.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * A class to locate resources, retrieve their contents, and determine their
 * last modified time. To find the resource the class searches the CLASSPATH
 * first, then ResourceLocator.class.getResource("/" + name). If the
 * ResourceLocator finds a "file:" URL, the file path will be treated as a file.
 * Otherwise, the path is treated as a URL and has limited last modified info.
 */
public class ResourcesLocator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private File file;
	private URL url;

	public ResourcesLocator(String name) throws IOException {
		this.name = name;
		SecurityException exception = null;

		try {
			// Search using the CLASSPATH. If found, "file" is set and the call
			// returns true. A SecurityException might bubble up.
			if (tryClasspath(name)) {
				return;
			}
		} catch (SecurityException e) {
			exception = e; // Save for later.
		}

		try {
			// Search using the classloader getResource( ). If found as a file,
			// "file" is set; if found as a URL, "url" is set.
			if (tryLoader(name)) {
				return;
			}
		} catch (SecurityException e) {
			exception = e; // Save for later.
		}

		// If you get here, something went wrong. Report the exception.
		String msg = ""; //$NON-NLS-1$
		if (exception != null) {
			msg = ": " + exception; //$NON-NLS-1$
		}

		throw new IOException(
				"ResourceLocator '" //$NON-NLS-1$
						+ name
						+ "' could not be found in " //$NON-NLS-1$
						+ "the CLASSPATH (" //$NON-NLS-1$
						+ System.getProperty("java.class.path") //$NON-NLS-1$
						+ "), nor could it be located by the classloader responsible for the " //$NON-NLS-1$
						+ "web application (WEB-INF/classes)" + msg); //$NON-NLS-1$
	}

	/**
	 * Returns the resource name, as passed to the constructor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns an input stream to read the resource contents
	 */
	public InputStream getInputStream() throws IOException {
		if (file != null) {
			return new BufferedInputStream(new FileInputStream(file));
		} else if (url != null) {
			return new BufferedInputStream(url.openStream());
		}
		return null;
	}

	/**
	 * Returns when the resource was last modified. If the resource was found
	 * using a URL, this method will work only if the URL connection supports
	 * last modified information. If there's no support, Long.MAX_VALUE is
	 * returned. Perhaps this should return -1, but you should return MAX_VALUE
	 * on the assumption that if you can't determine the time, it's maximally
	 * new.
	 */
	public long lastModified() {
		if (file != null) {
			return file.lastModified();
		} else if (url != null) {
			try {
				return url.openConnection().getLastModified(); // Hail Mary
			} catch (IOException e) {
				return Long.MAX_VALUE;
			}
		}
		return 0; // can't happen
	}

	/**
	 * Returns the directory containing the resource, or null if the resource
	 * isn't directly available on the filesystem. This value can be used to
	 * locate the configuration file on disk, or to write files in the same
	 * directory.
	 */
	public String getDirectory() {
		if (file != null) {
			return file.getParent();
		} else if (url != null) {
			return null;
		}
		return null;
	}

	// Returns true if found
	private boolean tryClasspath(String filename) {
		String classpath = System.getProperty("java.class.path"); //$NON-NLS-1$
		String[] paths = split(classpath, File.pathSeparator);
		file = searchDirectories(paths, filename);
		return (file != null);
	}

	private static File searchDirectories(String[] paths, String filename) {
		SecurityException exception = null;
		for (int i = 0; i < paths.length; i++) {
			try {
				File file = new File(paths[i], filename);
				if (file.exists() && !file.isDirectory()) {
					return file;
				}
			} catch (SecurityException e) {
				exception = e;
			}
		}
		
		if (exception != null) {
			throw exception;
		} else {
			return null;
		}
	}

	
	private static String[] split(String str, String delim) {
		Vector<String> v = new Vector<String>();
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while (tokenizer.hasMoreTokens()) {
			v.addElement(tokenizer.nextToken());
		}
		String[] ret = new String[v.size()];
		v.copyInto(ret);
		return ret;
	}

	
	private boolean tryLoader(String name) {
		name = "/" + name; //$NON-NLS-1$
		URL res = ResourcesLocator.class.getResource(name);
		if (res == null) {
			return false;
		}
		File resFile = urlToFile(res);
		if (resFile != null) {
			file = resFile;
		} else {
			url = res;
		}
		return true;
	}

	private static File urlToFile(URL res) {
		String externalForm = res.toExternalForm();
		if (externalForm.startsWith("file:")) { //$NON-NLS-1$
			return new File(externalForm.substring(5));
		}
		return null;
	}

	public String toString() {
		return "[ResourceLocator: File: " + file + " URL: " + url + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public OutputStream getOutputStream() throws FileNotFoundException {
		if (file != null) {
			return new  FileOutputStream(file);
		} 
		return null;
	}
}