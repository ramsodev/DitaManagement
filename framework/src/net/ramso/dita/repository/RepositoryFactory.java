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

	public static void config(Properties prop) {
		RepositoryFactory.properties = prop;
	}

	/**
	 * Create and configure the repository class assigned in the configuration
	 * properties
	 *
	 * @return
	 * @throws RepositoryException
	 */
	public static IRepository getRepositoryInstance()
			throws RepositoryException {
		if (RepositoryFactory.repository == null) {
			try {
				final Class<?> c = Class.forName(RepositoryFactory.properties
						.getProperty("repository.factory")); //$NON-NLS-1$
				final Constructor<?> cons = c.getConstructor();
				RepositoryFactory.repository = (IRepository) cons.newInstance();
				RepositoryFactory.repository
						.setup(RepositoryFactory.properties);
			} catch (final Exception e) {
				throw new RepositoryException(
						Messages.getString("RepositoryFactory.exception.msg"), e); //$NON-NLS-1$
			}
		}
		return RepositoryFactory.repository;
	}

}
