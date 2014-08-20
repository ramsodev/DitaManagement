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

	public static List<ConfigData> getFromAttributes(
			Set<Entry<String, Object>> set) {
		final ArrayList<ConfigData> configs = new ArrayList<ConfigData>();
		for (final Entry<String, Object> entry : set) {
			final String key = entry.getKey().toString();
			String value = "";
			if (entry.getValue() != null) {
				value = entry.getValue().toString();
			}
			configs.add(new ConfigData(key, value));
		}
		return configs;
	}

	public static List<ConfigData> getFromProperties(Properties pro) {
		final ArrayList<ConfigData> configs = new ArrayList<ConfigData>();
		final Enumeration<?> names = pro.propertyNames();
		while (names.hasMoreElements()) {
			final String key = (String) names.nextElement();
			configs.add(new ConfigData(key, pro.getProperty(key)));
		}
		return configs;
	}

	public static ArrayList<ConfigData> getFromShiroIni(
			Set<Entry<String, String>> set) {
		final ArrayList<ConfigData> configs = new ArrayList<ConfigData>();
		for (final Entry<String, String> entry : set) {
			configs.add(new ConfigData(entry.getKey(), entry.getValue()));
		}
		return configs;
	}

}
