/**
 * 
 */
package net.ramso.dita.repository;

import java.lang.reflect.Constructor;
import java.util.Properties;

import net.ramso.utils.ConfManager;
import net.ramso.utils.Messages;

/**
 * @author ramso
 *
 */
public class RepositoryFactory {
	public static final String PREFIX = ConfManager.FILEPREFIX + "repository"; //$NON-NLS-1$
	private static Properties properties = new Properties();
	private static IRepository repository;

	/**
	 * Create and configure the repository class assigned in the configuration
	 * properties
	 * 
	 * @return
	 * @throws RepositoryException
	 */
	public static IRepository getRepositoryInstance()
			throws RepositoryException {
		if (repository == null) {
			try {
				Class<?> c = Class.forName(properties
						.getProperty("repository.factory")); //$NON-NLS-1$
				Constructor<?> cons = c.getConstructor();
				repository = (IRepository) cons.newInstance();
				repository.setup(properties);
			} catch (Exception e) {
				throw new RepositoryException(
						Messages.getString("RepositoryFactory.exception.msg"), e); //$NON-NLS-1$
			}
		}
		return repository;
	}

	public static void config(Properties prop) {
		properties = prop;
	}

}
