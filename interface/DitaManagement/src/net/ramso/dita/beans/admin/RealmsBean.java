/**
 *
 */
package net.ramso.dita.beans.admin;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class RealmsBean implements Serializable {

	public static final String BASICREALMKEY = "basic";
	public static final String JDBCREALMKEY = "jdbcRealm";
	public static final String LDAPREALMKEY = "ldapRealm";
	/**
	 *
	 */
	private static final long serialVersionUID = 5361041953471506726L;
	@ManagedProperty("#{adminBean}")
	private AdminBean adminBean;
	private boolean jdbc;

	@ManagedProperty("#{jDBCRealmBean}")
	private JDBCRealmBean jdbcRealmBean;

	private boolean ldap;
	private String type;

	private String[] types = { RealmsBean.JDBCREALMKEY,
			RealmsBean.LDAPREALMKEY, RealmsBean.BASICREALMKEY };

	public AdminBean getAdminBean() {
		return adminBean;
	}

	public JDBCRealmBean getJdbcRealmBean() {
		return jdbcRealmBean;
	}

	public String getType() {
		return type;
	}

	public String[] getTypes() {
		return types;
	}

	@PostConstruct
	public void init() {
		jdbc = false;
		ldap = false;
		final Ini ini = adminBean.getIni();
		final Section main = ini.getSection("main");
		if (main.containsKey(RealmsBean.JDBCREALMKEY)) {
			setType(RealmsBean.JDBCREALMKEY);
		} else if (main.containsKey(RealmsBean.LDAPREALMKEY)) {
			setType(RealmsBean.LDAPREALMKEY);
		} else {
			setType(RealmsBean.BASICREALMKEY);
		}
	}

	public boolean isJdbc() {
		System.out.println("Es jdbc " + jdbc);
		return jdbc;
	}

	public boolean isLdap() {
		return ldap;
	}

	public void save() {
		try {
			final PrintWriter writer = new PrintWriter(
					"/home/ramso/temp_files/siro.ini", "UTF-8");
			final Collection<Section> sections = adminBean.getIni()
					.getSections();
			for (final Section section : sections) {
				writer.println("[" + section.getName().trim() + "]");
				final Set<Entry<String, String>> values = section.entrySet();
				for (final Entry<String, String> entry : values) {
					writer.println(entry.getKey() + "=" + entry.getValue());
				}
				if (section.getName().equals("main")) {
					if (type.equals(RealmsBean.JDBCREALMKEY)) {
						final Set<Entry<String, String>> ps = jdbcRealmBean
								.getProperties();
						for (final Entry<String, String> entry : ps) {
							writer.println(entry.getKey() + "="
									+ entry.getValue());
						}
					}
				}
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}

	public void setJdbc(boolean jdbc) {
		this.jdbc = jdbc;
	}

	public void setJdbcRealmBean(JDBCRealmBean jdbcRealmBean) {
		this.jdbcRealmBean = jdbcRealmBean;
	}

	public void setLdap(boolean ldap) {
		this.ldap = ldap;
	}

	public void setType(String type) {
		System.out.println("Tipo: " + type);
		jdbc = false;
		ldap = false;
		if (type.equals(RealmsBean.JDBCREALMKEY)) {
			jdbc = true;
		} else if (type.equals(RealmsBean.LDAPREALMKEY)) {
			ldap = true;
		}
		this.type = type;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

}
