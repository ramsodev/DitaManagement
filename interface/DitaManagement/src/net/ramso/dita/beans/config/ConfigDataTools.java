/**
 * 
 */
package net.ramso.dita.beans.config;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @author ramso
 *
 */
public class ConfigDataTools {

	public static List<ConfigData> getFromProperties(Properties pro) {
		ArrayList<ConfigData> configs = new ArrayList<ConfigData>();
		Enumeration<?> names = pro.propertyNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			configs.add(new ConfigData(key, pro.getProperty(key)));
		}
		return configs;
	}

	public static List<ConfigData> getFromAttributes(
			Set<Entry<String, Object>> set) {
		ArrayList<ConfigData> configs = new ArrayList<ConfigData>();
		for (Entry<String, Object> entry : set) {
			String key = (String) entry.getKey().toString();
			String value = "";
			if (entry.getValue() != null) {
				value = entry.getValue().toString();
			}
			configs.add(new ConfigData(key, value));
		}
		return configs;
	}

}
