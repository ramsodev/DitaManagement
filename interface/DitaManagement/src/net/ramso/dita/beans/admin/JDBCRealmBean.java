/**
 *
 */
package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import org.apache.shiro.config.Ini;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class JDBCRealmBean implements Serializable {
	public static final String DATASOURCEKEY = "jdbcRealm.dataSource";
	public static final String QUERYKEY = "jdbcRealm.authenticationQuery";
	/**
	 *
	 */
	private static final long serialVersionUID = -8203808743829735230L;
	@ManagedProperty("#{adminBean}")
	private AdminBean adminBean;
	private String dataSource;
	private ArrayList<String> dataSources;
	private String query;
	private String queryRoles;

	// jdbcRealm=org.apache.shiro.realm.jdbc.JdbcRealm
	//
	// jdbcRealm.authenticationQuery = select user_pass from users where
	// user_name = ?
	// jdbcRealm.userRolesQuery = select role_name from user_roles where
	// user_name = ?

	// ds = com.mysql.jdbc.jdbc2.optional.MysqlDataSource
	// ds.serverName = 192.168.0.75
	// ds.port = 3306
	// ds.user = test
	// ds.databaseName = appfuse
	// jdbcRealm.dataSource = $ds
	// ds = org.apache.shiro.jndi.JndiObjectFactory
	// ds.requiredType = javax.sql.DataSource
	// ds.resourceName = java:/comp/env/oracle/pportal_dev
	//
	// # JDBC realm config
	// jdbcRealm = org.apache.shiro.realm.jdbc.JdbcRealm
	// jdbcRealm.permissionsLookupEnabled = true
	// jdbcRealm = org.apache.shiro.realm.jdbc.JdbcRealm
	// jdbcRealm.dataSource = $ds

	public AdminBean getAdminBean() {
		return adminBean;
	}

	public String getDataSource() {
		return dataSource;
	}

	public ArrayList<String> getDataSources() {
		if (dataSources == null) {
			dataSources = new ArrayList<String>();
			try {
				final javax.naming.Context ic = new javax.naming.InitialContext();
				final NamingEnumeration<NameClassPair> list = ic
						.list("java:comp/env/jdbc");
				while (list.hasMore()) {
					final javax.naming.NameClassPair nc = list.next();
					try {
						final Object obj = ic.lookup("java:comp/env/jdbc/"
								+ nc.getName());
						if (obj instanceof javax.sql.DataSource) {
							dataSources.add("java:comp/env/jdbc/" + nc.getName());
						}
					} catch (final Exception e) {
					}
				}
			} catch (final Exception e) {
			}
			try {
				final javax.naming.Context ic = new javax.naming.InitialContext();
				final NamingEnumeration<NameClassPair> list = ic
						.list("java:comp/env");
				while (list.hasMore()) {
					final javax.naming.NameClassPair nc = list.next();
					try {
						final Object obj = ic.lookup("java:comp/env/"
								+ nc.getName());
						if (obj instanceof javax.sql.DataSource) {
							dataSources.add("java:comp/env/" + nc.getName());
						}
					} catch (final Exception e) {
					}
				}
			} catch (final Exception e) {
			}
			if (dataSources.isEmpty()) {
				dataSources.add("Not Data Source found");
			}
		}
		return dataSources;
	}

	public Set<Entry<String, String>> getProperties() {
		final HashMap<String, String> p = new HashMap<String, String>();

		p.put("ds", "org.apache.shiro.jndi.JndiObjectFactory");
		p.put("ds.requiredType", "javax.sql.DataSource");
		p.put("ds.resourceName", "java:/comp/env/" + getDataSource().trim());
		p.put("jdbcRealm", "org.apache.shiro.realm.jdbc.JdbcRealm");
		p.put("jdbcRealm.permissionsLookupEnabled", "true");
		p.put("jdbcRealm", "org.apache.shiro.realm.jdbc.JdbcRealm");
		p.put("jdbcRealm.dataSource", "$ds");
		return p.entrySet();
	}

	public String getQuery() {
		return query;
	}

	public String getQueryRoles() {
		return queryRoles;
	}

	@PostConstruct
	public void init() {
		final Ini ini = adminBean.getIni();
		query = ini.getSectionProperty("main", JDBCRealmBean.QUERYKEY);
		dataSource = ini
				.getSectionProperty("main", JDBCRealmBean.DATASOURCEKEY);
	}

	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataSources(ArrayList<String> dataSources) {
		this.dataSources = dataSources;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setQueryRoles(String queryRoles) {
		this.queryRoles = queryRoles;
	}
}
