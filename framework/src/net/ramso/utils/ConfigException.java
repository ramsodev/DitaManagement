/**
 * 
 */
package net.ramso.utils;

/**
 * @author jescudero
 *
 */
public class ConfigException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4246846583492469436L;

	/**
	 * 
	 */
	public ConfigException() {
	}

	/**
	 * @param message
	 */
	public ConfigException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConfigException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ConfigException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
