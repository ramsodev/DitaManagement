package net.ramso.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogManager {
	
	public static Logger logger = LoggerFactory.getLogger(ConfManager.APPNAME);

	public static void info(String message){
		logger.info(message);
	}
	
	public static void warn(String message, Throwable cause){
		logger.warn(message, cause);
	}
	
	public static void error(String message,  Throwable cause){
		logger.error(message, cause);
	}
	
	public static void debug(String message){
		logger.info(message);
	}
}
