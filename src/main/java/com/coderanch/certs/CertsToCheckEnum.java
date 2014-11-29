package com.coderanch.certs;

/**
 * Maps the cert we are monitoring with the current URL
 * @author jeanne
 *
 */
public enum CertsToCheckEnum {

	OCA_JAVA_8("1Z0-808",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-808");

	// ----------------------------------------------------
	String examNumber;
	String url;

	private CertsToCheckEnum(String examNumber, String url) {
		this.examNumber = examNumber;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
