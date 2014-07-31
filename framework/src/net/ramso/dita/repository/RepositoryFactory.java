/**
 * 
 */
package net.ramso.dita.repository;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * @author ramso
 *
 */
public class RepositoryFactory {
	/**
	 * Create and configure the repository class asigned in the configuration properties
	 * 
	 * @return
	 * @throws RepositoryException
	 */
	public static IRepository getRepositoryInstance() throws RepositoryException{
		Properties p = new Properties();
		IRepository repository = null;
		try {
			p.load(new FileInputStream(new File("./conf/repository.properties")));
			Class<?> c = Class.forName(p.getProperty("repository.factory"));
			Constructor<?> cons = c.getConstructor();
			 repository = (IRepository) cons.newInstance();
			 repository.setup(p);
		} catch (Exception e) {
			throw new RepositoryException("Imposible implement the repository exception", e);
		} 
		
		return repository;
	}

}
