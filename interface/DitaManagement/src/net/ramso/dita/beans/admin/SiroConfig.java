/**
 *
 */
package net.ramso.dita.beans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.ramso.dita.beans.config.ConfigData;
import net.ramso.dita.beans.config.ConfigDataTools;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.io.ResourceUtils;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class SiroConfig implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3367481503738660834L;
	private Ini ini;
	private ArrayList<ConfigData> values;

	/**
	 *
	 */
	public SiroConfig() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ConfigData> getValues() {
		return values;
	}

	@PostConstruct
	public void Init() {
		ini = new Ini();
		final String path = (FacesContext.getCurrentInstance()
				.getExternalContext()).getRealPath("WEB-INF/shiro.ini");
		ini.loadFromPath(ResourceUtils.FILE_PREFIX + path);

		System.out.println(ini.toString());
		values = new ArrayList<ConfigData>();
		final Set<Entry<String, Section>> set = ini.entrySet();
		for (final Entry<String, Section> entry : set) {
			entry.getValue().entrySet();
			values.addAll(ConfigDataTools.getFromShiroIni(entry.getValue()
					.entrySet()));
		}

	}

	public void setValues(ArrayList<ConfigData> values) {
		this.values = values;
	}

}
