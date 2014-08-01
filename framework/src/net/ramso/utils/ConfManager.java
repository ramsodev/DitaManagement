package net.ramso.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ConfManager {

	/**
	 * Nombre del fichero de configuración
	 */
	public static final String PROPERTIESNAME = "configuration.properties";
	public static final String PROPERTIESDIR = "./conf";
	public static final String FILEPREFIX = "file.conf.";
	/**
	 * Tipo de log a usar en la aplicación
	 */
	public static final String LOG_TYPE = "log.type";
	public static final String LOGPREFIX = "log";
	public static String APPNAME = "";
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
		this.properties = getProperties(PROPERTIESNAME);
		Enumeration<?> names = properties.propertyNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			if (key.startsWith(FILEPREFIX)) {
				propertiesFiles.put(key.substring(FILEPREFIX.length()),
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
		Properties properties = new Properties();
		try {
			ResourcesLocator locator = new ResourcesLocator(PROPERTIESNAME);

			properties.load(locator.getInputStream());
		} catch (Exception e) {
			try {
				properties.load(new FileInputStream(new File(PROPERTIESDIR
						+ File.separator + PROPERTIESNAME)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return properties;
	}

	public static ConfManager getInstance() {
		ConfManager conf = new ConfManager();
		conf.init();
		conf.initLogging();
		LogManager.info("Logging for " + APPNAME
				+ " application is initialized");
		return conf;
	}

	public Properties getPropertiesFile(String key) {
		return propertiesFiles.get(key);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
