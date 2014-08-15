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

	/**
	 * Nombre del fichero de configuración
	 */
	public static final String PROPERTIESNAME = "configuration.properties"; //$NON-NLS-1$
	public static final String PROPERTIESDIR = "resources/conf"; //$NON-NLS-1$
	public static final String FILEPREFIX = "file.conf."; //$NON-NLS-1$
	/**
	 * Tipo de log a usar en la aplicación
	 */
	public static final String LOG_TYPE = "log.type"; //$NON-NLS-1$
	public static final String LOGPREFIX = "log"; //$NON-NLS-1$
	public static final String INDEX ="application.index.dir";//$NON-NLS-1$
	public static String APPNAME = ""; //$NON-NLS-1$
	public static String STATUS = "application.status"; //$NON-NLS-1$
	private static String ABSOLUTEDISKPATH = null;
	private static ConfManager conf;
	private HashMap<String, Properties> propertiesFiles = new HashMap<String, Properties>();
	private Properties properties;

	protected ConfManager() {

	}

	protected void init() {
		if (ABSOLUTEDISKPATH == null) {
			ABSOLUTEDISKPATH = PROPERTIESDIR;
		}
		this.properties = getProperties(PROPERTIESNAME);
		Enumeration<?> names = properties.propertyNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			if (key.startsWith(FILEPREFIX)) {
				propertiesFiles.put(key,
						getProperties(properties.getProperty(key)));

			} else {
				String k = key.substring(0, key.indexOf(".")); //$NON-NLS-1$
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
		APPNAME = getProperty(LOGPREFIX + "." + "name"); //$NON-NLS-1$ //$NON-NLS-2$
		if (LogTypes.JDK.getType().equals(getProperty(LOG_TYPE))) {
			System.setProperty("java.util.logging.config.file", //$NON-NLS-1$
					getProperty(FILEPREFIX + LOGPREFIX));
		}
	}

	protected Properties getProperties(String name) {
		Properties p = new Properties();
		try {
			ResourcesLocator locator = new ResourcesLocator(name);

			p.load(locator.getInputStream());
		} catch (Exception e) {
			try {
				p.load(new FileInputStream(new File(ABSOLUTEDISKPATH
						+ File.separator + name)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return p;
	}

	public static ConfManager getInstance() {
		if (conf == null) {
			conf = new ConfManager();
			conf.init();
			conf.initLogging();
			LogManager.info(Messages.getString(
					"ConfManager.log.initialized", APPNAME)); //$NON-NLS-1$
		}
		return conf;
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

	public static ConfManager getInstance(String absoluteDiskPath) {
		ABSOLUTEDISKPATH = absoluteDiskPath;
		return getInstance();
	}

	public void update(String key, Properties pro) throws ConfigException {
		Date today = new Date(System.currentTimeMillis());
		String comments = today
				+ Messages.getString("ConfManager.comment.properties.modify"); //$NON-NLS-1$
		OutputStream out = null;
		if (key.startsWith(FILEPREFIX)) {
			String path = properties.getProperty(key);
			out = getOutputProperties(path);
		} else {
			Enumeration<?> names = pro.propertyNames();
			while (names.hasMoreElements()) {
				String k = (String) names.nextElement();
				properties.setProperty(k, pro.getProperty(k));
			}
			pro = properties;
			out = getOutputProperties(PROPERTIESNAME);
		}
		if (out != null) {
			try {
				pro.store(out, comments);
				init();
			} catch (IOException e) {
				throw new ConfigException(e);
			}
		} else {
			throw new ConfigException(Messages.getString(
					"ConfManager.exception.properfies.file.notfound", key)); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}

	private OutputStream getOutputProperties(String path)
			throws ConfigException {
		OutputStream out = null;
		try {
			ResourcesLocator locator = new ResourcesLocator(path);
			out = locator.getOutputStream();

		} catch (IOException e) {
			LogManager
					.info(Messages
							.getString(
									"ConfManager.exception.properfies.file.notfound.classpath", path)); //$NON-NLS-1$
			try {
				out = new FileOutputStream(new File(ABSOLUTEDISKPATH
						+ File.separator + path));
			} catch (FileNotFoundException e1) {
				LogManager
						.error(Messages
								.getString(
										"ConfManager.exception.properfies.file.notfound.update", path), e1);//$NON-NLS-1$
				throw new ConfigException(e1);
			}

		}
		return out;
	}

	public String getStatus() {
		return properties.getProperty(STATUS,
				ApplicationStatus.SETUP_PENDING.getStatus());
	}

	public void setStatus(ApplicationStatus status) throws ConfigException {
		Date today = new Date(System.currentTimeMillis());
		String comments = today + "Application status changed";
		properties.setProperty(STATUS, status.getStatus());
		try {
			OutputStream out = getOutputProperties(PROPERTIESNAME);
			if (out != null) {
				properties.store(out, comments);
				init();
			} else {
				throw new ConfigException(
						Messages.getString(
								"ConfManager.exception.properfies.file.notfound", STATUS)); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (IOException e) {
			throw new ConfigException(e);
		}
	}
	
	public String getIndexDir() {
		return properties.getProperty(INDEX);
	}
}
