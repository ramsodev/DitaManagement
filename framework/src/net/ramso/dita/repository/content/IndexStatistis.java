package net.ramso.dita.repository.content;

import java.util.Map;

public class IndexStatistis {

	private Map<String, Number> mime;
	private Map<String, Number> dita;

	public Map<String, Number> getMime() {
		return mime;
	}

	public void setMime(Map<String, Number> mime) {
		this.mime = mime;
	}

	public Map<String, Number> getDita() {
		return dita;
	}

	public void setDita(Map<String, Number> dita) {
		this.dita = dita;
	}

}
