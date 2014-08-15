package net.ramso.utils;

public enum LogTypes {
	JDK("jdk"), LOG4JAVA("log4j");

	private String logType;

	private LogTypes(String s) {
		logType = s;
	}

	public String getType() {
		return logType;
	}
}
