package net.ramso.utils;
public enum ApplicationStatus {
	SETUP_PENDING("setup.pending"), SETUP_FINISH("setup.finish"), CONFIGURED("setup.end");
 
	private String statusCode;
 
	private ApplicationStatus(String s) {
		statusCode = s;
	}
 
	public String getStatus() {
		return statusCode;
	}
 
}