package net.ramso.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class ConfManager {

	public static String ABSOLUTEDISKPATH = null;
	public static String APPNAME = ""; //$NON-NLS-1$
	private static ConfManager conf;
	public static final String FILEPREFIX = "file.conf."; //$NON-NLS-1$
	public static final String INDEX = "application.index.dir";//$NON-NLS-1$
	public static final String JPA = "file.conf.jpa";
	/**
	 * Tipo de log a usar en la aplicación
	 */
	public static final String LOG_TYPE = "log.type"; //$NON-NLS-1$
	public static final String LOGPREFIX = "log"; //$NON-NLS-1$
	public static final String PROPERTIESDIR = "resources/conf"; //$NON-NLS-1$
	/**
	 * Nombre del fichero de configuración
	 */
	public static final String PROPERTIESNAME = "configuration.properties"; //$NON-NLS-1$
	public static String STATUS = "application.status"; //$NON-NLS-1$

	public static ConfManager getInstance() {
		if (ConfManager.conf == null) {
			ConfManager.conf = new ConfManager();
			ConfManager.conf.init();
			ConfManager.conf.initLogging();
			LogManager.info(Messages.getString(
					"ConfManager.log.initialized", ConfManager.APPNAME)); //$NON-NLS-1$
		}
		return ConfManager.conf;
	}

	public static ConfManager getInstance(String absoluteDiskPath) {
		ConfManager.ABSOLUTEDISKPATH = absoluteDiskPath;
		return ConfManager.getInstance();
	}

	private Properties properties;

	private final HashMap<String, Properties> propertiesFiles = new HashMap<String, Properties>();

	protected ConfManager() {

	}

	public String getIndexDir() {
		return properties.getProperty(ConfManager.INDEX);
	}

	private OutputStream getOutputProperties(String path)
			throws ConfigException {
		OutputStream out = null;
		try {
			final ResourcesLocator locator = new ResourcesLocator(path);
			out = locator.getOutputStream();

		} catch (final IOException e) {
			LogManager
					.info(Messages
							.getString(
									"ConfManager.exception.properfies.file.notfound.classpath", path)); //$NON-NLS-1$
			try {
				out = new FileOutputStream(new File(
						ConfManager.ABSOLUTEDISKPATH + File.separator + path));
			} catch (final FileNotFoundException e1) {
				LogManager
						.error(Messages
								.getString(
										"ConfManager.exception.properfies.file.notfound.update", path), e1);//$NON-NLS-1$
				throw new ConfigException(e1);
			}

		}
		return out;
	}

	protected Properties getProperties(String name) {
		final Properties p = new Properties();
		try {
			final ResourcesLocator locator = new ResourcesLocator(name);

			p.load(locator.getInputStream());
		} catch (final Exception e) {
			try {
				p.load(new FileInputStream(new File(
						ConfManager.ABSOLUTEDISKPATH + File.separator + name)));
			} catch (final IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return p;
	}

	public Properties getPropertiesFile(String key) {
		return propertiesFiles.get(key);
	}

	public Set<String> getPropertiesKeys() {
		return propertiesFiles.keySet();
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getStatus() {
		return properties.getProperty(ConfManager.STATUS,
				ApplicationStatus.SETUP_PENDING.getStatus());
	}

	protected void init() {
		if (ConfManager.ABSOLUTEDISKPATH == null) {
			ConfManager.ABSOLUTEDISKPATH = ConfManager.PROPERTIESDIR;
		}
		properties = getProperties(ConfManager.PROPERTIESNAME);
		final Enumeration<?> names = properties.propertyNames();
		while (names.hasMoreElements()) {
			final String key = (String) names.nextElement();
			if (key.startsWith(ConfManager.FILEPREFIX)) {
				propertiesFiles.put(key,
						getProperties(properties.getProperty(key)));
			} else {
				final String k = key.substring(0, key.indexOf(".")); //$NON-NLS-1$
				Properties p = new Properties();
				if (propertiesFiles.containsKey(k)) {
					p = propertiesFiles.get(k);
				}
				p.put(key, properties.getProperty(key));
				propertiesFiles.put(k, p);
			}
		}
	}

	public void initLogging() {
		ConfManager.APPNAME = getProperty(ConfManager.LOGPREFIX + "." + "name"); //$NON-NLS-1$ //$NON-NLS-2$
		if (LogTypes.JDK.getType().equals(getProperty(ConfManager.LOG_TYPE))) {
			System.setProperty("java.util.logging.config.file", //$NON-NLS-1$
					ConfManager.ABSOLUTEDISKPATH
							+ File.separator
							+ getProperty(ConfManager.FILEPREFIX
									+ ConfManager.LOGPREFIX));
		}
	}

	public void setStatus(ApplicationStatus status) throws ConfigException {
		final Date today = new Date(System.currentTimeMillis());
		final String comments = today + "Application status changed";
		properties.setProperty(ConfManager.STATUS, status.getStatus());
		try {
			final OutputStream out = getOutputProperties(ConfManager.PROPERTIESNAME);
			if (out != null) {
				properties.store(out, comments);
				init();
			} else {
				throw new ConfigException(
						Messages.getString(
								"ConfManager.exception.properfies.file.notfound", ConfManager.STATUS)); //$NON-NLS-1$
			}
		} catch (final IOException e) {
			throw new ConfigException(e);
		}
	}

	public void update(String key, Properties pro) throws ConfigException {
		final Date today = new Date(System.currentTimeMillis());
		final String comments = today
				+ Messages.getString("ConfManager.comment.properties.modify"); //$NON-NLS-1$
		OutputStream out = null;
		if (key.startsWith(ConfManager.FILEPREFIX)) {
			final String path = properties.getProperty(key);
			out = getOutputProperties(path);
		} else {
			final Enumeration<?> names = pro.propertyNames();
			while (names.hasMoreElements()) {
				final String k = (String) names.nextElement();
				properties.setProperty(k, pro.getProperty(k));
			}
			pro = properties;
			out = getOutputProperties(ConfManager.PROPERTIESNAME);
		}
		if (out != null) {
			try {
				pro.store(out, comments);
				init();
			} catch (final IOException e) {
				throw new ConfigException(e);
			}
		} else {
			throw new ConfigException(Messages.getString(
					"ConfManager.exception.properfies.file.notfound", key)); //$NON-NLS-1$
		}

	}
}
