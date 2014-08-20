package net.ramso.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {

	public static Logger logger = LoggerFactory.getLogger(ConfManager.APPNAME);

	public static void debug(String message) {
		LogManager.logger.info(message);
	}

	public static void error(String message, Throwable cause) {
		LogManager.logger.error(message, cause);
	}

	public static void info(String message) {
		LogManager.logger.info(message);
	}

	public static void warn(String message) {
		LogManager.logger.warn(message);

	}

	public static void warn(String message, Throwable cause) {
		LogManager.logger.warn(message, cause);
	}
}
