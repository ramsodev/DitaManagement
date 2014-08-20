package net.ramso.utils;

public enum ApplicationStatus {
	CONFIGURED("setup.end"), SETUP_FINISH("setup.finish"), SETUP_PENDING(
			"setup.pending");

	private String statusCode;

	private ApplicationStatus(String s) {
		statusCode = s;
	}

	public String getStatus() {
		return statusCode;
	}

}