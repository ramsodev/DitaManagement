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

public class ConfManager {

	/**
	 * Nombre del fichero de configuración
	 */
	public static final String PROPERTIESNAME = "configuration.properties";
	public static final String PROPERTIESDIR = "resources/conf";
	public static final String FILEPREFIX = "file.conf.";
	/**
	 * Tipo de log a usar en la aplicación
	 */
	public static final String LOG_TYPE = "log.type";
	public static final String LOGPREFIX = "log";
	public static String APPNAME = "";
	private static String ABSOLUTEDISKPATH = null;
	private static ConfManager conf;
	private HashMap<String, Properties> propertiesFiles = new HashMap<String, Properties>();
	private Properties properties;

	/**
	 * Tipos admitido de log para configurar
	 * 
	 * @author jescudero
	 * 
	 */
	public enum LOG_TYPES {
		jdk, log4j
	};

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
				String k = key.substring(0, key.indexOf("."));
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
		APPNAME = getProperty(LOGPREFIX + "." + "name");
		LOG_TYPES logtype = LOG_TYPES.valueOf(getProperty(LOG_TYPE));
		switch (logtype) {
		case jdk:
			System.setProperty("java.util.logging.config.file",
					getProperty(FILEPREFIX + LOGPREFIX));
			break;

		default:
			break;
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
			LogManager.info("Logging for " + APPNAME
					+ " application is initialized");
		}
		return conf;
	}

	public Properties getPropertiesFile(String key) {
		return propertiesFiles.get(key);
	}

	public HashMap<String, Properties> getPropertiesFile() {
		return propertiesFiles;
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
		String comments = today + " Modify in application";
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
			out = getOutputProperties(PROPERTIESNAME);
		}
		if (out != null) {
			try {
				pro.store(out, comments);
			} catch (IOException e) {
				throw new ConfigException(e);
			}
		} else {
			throw new ConfigException("Property file for " + key + "not found");
		}

	}

	private OutputStream getOutputProperties(String path)
			throws ConfigException {
		OutputStream out = null;
		try {
			ResourcesLocator locator = new ResourcesLocator(path);
			out = locator.getOutputStream();

		} catch (IOException e) {
			LogManager.info("Property file" + path
					+ "No localizado en el entorno");
			try {
				out = new FileOutputStream(new File(ABSOLUTEDISKPATH
						+ File.separator + path));
			} catch (FileNotFoundException e1) {
				LogManager
						.error("Property file"
								+ path
								+ "No localizado, imposible actualizar las propiedades",
								e1);
				throw new ConfigException(e1);
			}

		}
		return out;
	}
}
