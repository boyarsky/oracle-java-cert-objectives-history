package com.coderanch.certs;

import java.util.*;
import java.util.stream.*;

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
	JAVA_8_UPGRADE(
			"1Z0-810",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-810"),
	JAVA_8_UPGRADE_FROM_PRE_7(
			"1Z0-813",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-813"),
	JAVA_FOUNDATIONAL(
			"1Z0-811",
			"https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-811"),
				
    // ----------------------------------------------------	
			
	/*
	 * Architect 
	 */
	OCMJEA_6_PART_1(
			"1Z0-807",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-807"),
	OCMJEA_ASSIGNMENT(
			"1Z0-865",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-865"),
	OCMJEA_ESSAY(
			"1Z0-866",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-866"),
	OCMJEA_RESUBMIT(
			"1Z0-867",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:1Z0-867"),
	
	// ----------------------------------------------------
			
	/*
	 * JPA/EJB 
	 */
	JPA_6(
			"1Z0-898",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-898"),
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
	WEB_COMPONENT_6(
			"1Z0-899",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-899"),
	JSF_6(
			"1Z0-896",
			"http://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=652&get_params=p_exam_id:1Z0-896"),		
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
	
	public static Set<String> getSetOfExamNumbers() {
		return Stream.of(CertsToCheckEnum.values())
				.map(CertsToCheckEnum::getExamNumber)
				.collect(Collectors.toSet());
	}
}
