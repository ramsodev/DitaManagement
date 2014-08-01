/**
 * 
 */
package net.ramso.dita.repository;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * @author ramso
 *
 */
public class RepositoryFactory {
	public static final String PREFIX = "repository";
	private static Properties properties = new Properties();

	/**
	 * Create and configure the repository class assigned in the configuration
	 * properties
	 * 
	 * @return
	 * @throws RepositoryException
	 */
	public static IRepository getRepositoryInstance()
			throws RepositoryException {

		IRepository repository = null;
		try {
			Class<?> c = Class.forName(properties
					.getProperty("repository.factory"));
			Constructor<?> cons = c.getConstructor();
			repository = (IRepository) cons.newInstance();
			repository.setup(properties);
		} catch (Exception e) {
			throw new RepositoryException(
					"Imposible implement the repository exception", e);
		}

		return repository;
	}

	public static void config(Properties prop) {
		properties = prop;
	}

}
