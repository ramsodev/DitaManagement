/**
 * 
 */
package net.ramso.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gestion la configuración de una aplicación
 * 
 * @author jescudero
 * @author cjrequena
 * 
 */

public class ConfigurationManager {
	/**
	 * Nombre del fichero de configuración
	 */
	public static final String PROPERTIESNAME = "configuration.properties";

	/**
	 * Propiedad del fichero de configuración del log
	 */
	public static final String FILE_CONF_LOG = "file.conf.log";

	/**
	 * Tipo de log a usar en la aplicación
	 */
	public static final String LOG_TYPE = "log.type";

	/**
	 * Propiedad del Directorio de salida
	 */
	public static final String DIR_OUT = "dir.out";
	/**
	 * Propiedad del Directorio de entrada
	 */
	public static final String DIR_IN = "dir.in";

	/**
	 * Propiedad del path del fichero de configuración para base de datos
	 */
	public static final String FILE_CONF_DB = "file.conf.db";
	/**
	 * Propiedad que indica el nombre de unidad a configurar
	 */
	public static final String DB_UNIT_NAME = "db.unit.name";
	/**
	 * Propiedad del path del fichero de confiruación para la conversión de
	 * caracteres
	 */
	public static final String FILE_CONF_RC = "file.conf.replace.characters";

	/**
	 * Tipos admitido de log para configurar
	 * 
	 * @author jescudero
	 * 
	 */
	public enum LOG_TYPES {
		jdk, log4j
	};

	final static protected Properties defaults = new Properties();
	final static protected Properties properties = new Properties(defaults);

	/**
	 * Metodo de inicialición del gestor de configuraciones. Configura el log y
	 * la base de datos en base a los parametros recibidos
	 * 
	 * @param path
	 *            del directorio donde esta el fichero de configuración
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void init(String path) throws FileNotFoundException,
			IOException {

		if (load(new FileReader(path + PROPERTIESNAME))) {
			System.out.println("Configuration roperties loaded correctly");
		} else {
			System.out.println("Error on load configuration properties");
		}

		// Cargar el logger
		LOG_TYPES logtype = LOG_TYPES.valueOf(getProperty(LOG_TYPE));
		switch (logtype) {
		case jdk:
			System.setProperty("java.util.logging.config.file",
					getProperty(FILE_CONF_LOG));
			break;

		default:
			break;
		}
		LogManager.info("El sistema de log ha sido configurado");
		PersistanceManager.init(getProperty(FILE_CONF_DB),
				getProperty(DB_UNIT_NAME));
		LogManager.info("Gestor de Base de datos inicializado");
//		TextUtils.init(getProperty(FILE_CONF_RC));
		LogManager.info("Aplicación inicializada");
	}

	@SuppressWarnings("null")
	final static boolean load(FileReader file) {
		boolean result = true;
		FileInputStream propsFile = null;
		try {
			properties.load(file);
		} catch (Exception e) {
			result = false;
			// Ignoramos las
		} finally {
			try {
				propsFile.close();
			} catch (Exception e) {
				// ignoramos
			}
		}
		return result;
	}

	final static boolean load(File file) {
		boolean result = true;
		FileInputStream propsFile = null;
		try {
			propsFile = new FileInputStream(file);
			properties.load(propsFile);
		} catch (Exception e) {
			result = false;
			// Ignoramos las
		} finally {
			try {
				propsFile.close();
			} catch (Exception e) {
				// ignoramos
			}
		}
		return result;
	}

	final static boolean load(InputStream propsFile) {
		boolean result = true;
		// FileInputStream propsFile = null;
		try {
			// propsFile = new FileInputStream(file);
			properties.load(propsFile);
		} catch (Exception e) {
			result = false;
			// Ignoramos las
		} finally {
			try {
				propsFile.close();
			} catch (Exception e) {
				// ignoramos
			}
		}
		return result;
	}

	final static public void putdefault(Object key, Object value) {
		defaults.put(key, value);
	}

	/** Obtiene una Propiedad como un String si no Existe devuelve "" */
	final public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/** Obtiene una Propiedad como un String sino su valor por defecto */
	final public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	/**
	 * Obtiene la propiedad y la convierte a boolean si la propiedad no existe
	 * retorna false
	 */
	final static public boolean getBooleanProperty(String propName) {
		return getBooleanProperty(propName, false);
	}

	/**
	 * Obtiene la propiedad y la convierte a boolean si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public boolean getBooleanProperty(String propName,
			boolean defaultValue) {
		try {
			return Boolean.valueOf(
					properties.getProperty(propName,
							Boolean.toString(defaultValue))).booleanValue();
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a byte si la propiedad no existe
	 * retorna (byte)0
	 */
	final static public byte getByteProperty(String propName) {
		return getByteProperty(propName, (byte) 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a byte si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public byte getByteProperty(String propName, byte defaultValue) {
		try {
			return Byte.parseByte(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a int si la propiedad no existe
	 * retorna (int)0
	 */
	final static public int getIntProperty(String propName) {
		return getIntProperty(propName, 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a int si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public int getIntProperty(String propName, int defaultValue) {
		try {
			return Integer.parseInt(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a long si la propiedad no existe
	 * retorna (long)0
	 */
	final static public long getLongProperty(String propName) {
		return getLongProperty(propName, 0L);
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public long getLongProperty(String propName, long defaultValue) {
		try {
			return Long.parseLong(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe
	 * retorna (short)0
	 */
	final static public short getShortProperty(String propName) {
		return getShortProperty(propName, (short) 0);
	}

	/**
	 * Obtiene la propiedad y la convierte a short si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public short getShortProperty(String propName,
			short defaultValue) {
		try {
			return Short.parseShort(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe
	 * retorna (short)0
	 */
	final static public double getDoubleProperty(String propName) {
		return getDoubleProperty(propName, 0.0);
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public double getDoubleProperty(String propName,
			double defaultValue) {
		try {
			return Double.parseDouble(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe
	 * retorna (short)0
	 */
	final static public float getFloatProperty(String propName) {
		return getFloatProperty(propName, (float) 0.0);
	}

	/**
	 * Obtiene la propiedad y la convierte a double si la propiedad no existe
	 * retorna defaultValue
	 */
	final static public float getFloatProperty(String propName,
			float defaultValue) {
		try {
			return Float.parseFloat(properties.getProperty(propName));
		} catch (Exception ex) {
			return defaultValue;
		}
	}
}
