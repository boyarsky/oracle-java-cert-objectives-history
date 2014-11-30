package com.coderanch.certs;

/**
 * Maps the cert we are monitoring with the current URL
 * 
 * @author jeanne
 *
 */
public enum CertsToCheckEnum {

	OCJA_JAVA_6(
			"1Z0-850",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-850"),
	OCPJP_JAVA_6(
			"1Z0-851",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-851"),
	OCAJP_JAVA_7(
			"1Z0-803",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-803"), 
	OCPJP_JAVA_7(
			"1Z0-804",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-804"),
	JAVA_7_UPGRADE(
			"1Z0-805",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-805"),
	OCAJP_JAVA_8(
			"1Z0-808",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-808"),
			
    // ----------------------------------------------------		
	OCMJEA_5_PART_1(
			"1Z0-864",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-864"),
	OCMJEA_5_PART_2(
			"1Z0-865",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-865"),
	OCMJEA_5_PART_3(
			"1Z0-866",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-866"),
	OCMJEA_6_PART_1(
			"1Z0-807",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-807"),
	OCMJEA_6_UPGRADE(
			"1Z0-868",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-868");
	
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

	public String getExamNumber() {
		return examNumber;
	}
}
