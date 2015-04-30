package com.coderanch.certs;

/**
 * Maps the cert we are monitoring with the current URL
 * 
 * @author jeanne
 *
 */
public enum CertsToCheckEnum {

	/*
	 * Associate/Programmer 
	 */
	
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
	OCPJP_JAVA_8(
			"1Z0-809",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-809"),
	JAVA_7_UPGRADE(
			"1Z0-805",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-805"),
	OCAJP_JAVA_8(
			"1Z0-808",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-808"),
	OCJP_JAVA_5(
			"1Z0-853",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-853"),
	OCJP_JAVA_5_UPGRADE(
			"1Z0-854",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-854"),
	OCJP_JAVA_6_UPGRADE(
			"1Z0-852",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-852"),
	JAVA_8_UPGRADE(
			"1Z0-810",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-810"),
	JAVA_8_UPGRADE_FROM_PRE_7(
			"1Z0-813",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-813"),
			
    // ----------------------------------------------------	
			
	/*
	 * Architect 
	 */
	OCMJEA_5_PART_1(
			"1Z0-864",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-864"),
	OCMJEA_5_PART_2(
			"1Z0-865",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-865"),
	OCMJEA_5_PART_2_RESUBMIT(
			"1Z0-867",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-867"),
	OCMJEA_5_PART_3(
			"1Z0-866",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-866"),
	OCMJEA_6_PART_1(
			"1Z0-807",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-807"),
	OCMJEA_6_UPGRADE(
			"1Z0-868",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-868"),
	// ----------------------------------------------------
			
	/*
	 * JPA/EJB 
	 */
	JPA_6(
			"1Z0-898",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-898"),
	BUS_COMPONENT_5(
			"1Z0-860",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-860"),
	BUS_COMPONENT_5_UPGRADE(
			"1Z0-861",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-861"),
	EJB_6(
			"1Z0-895",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-895"),		
			
	// ----------------------------------------------------		
	/*
	 * Web 
	 */
	WEB_SERVICES_6(
			"1Z0-897",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-897"),
	WEB_SERVICES_5(
			"1Z0-862",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-862"),
	WEB_SERVICES_5_UPGRADE(
			"1Z0-863",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-863"),		
	WEB_COMPONENT_5(
			"1Z0-858",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-858"),
	WEB_COMPONENT_5_UPGRADE(
			"1Z0-859",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-859"),
	WEB_COMPONENT_6(
			"1Z0-899",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-899"),
	JSF_6(
			"1Z0-896",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-896"),		
			
	// ----------------------------------------------------		
	/*
	 * Mobile 
	 */
	MOBILE(
			"1Z0-869",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-869"),		
	// ----------------------------------------------------		
	/*
	 * Developer 
	 */
	DEVELOPER_6_ASSIGNMENT(
			"1Z0-855",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-855"),		
	DEVELOPER_6_ESSAY(
			"1Z0-856",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-856"),
	DEVELOPER_6_RESUBMISSION(
			"1Z0-857",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-857"),
	;
	
	
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
