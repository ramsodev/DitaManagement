package net.ramso.dita.beans.config;

import java.io.Serializable;

public class ConfigData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1920543273513112610L;
	private String key;
	private String value;

	public ConfigData(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
