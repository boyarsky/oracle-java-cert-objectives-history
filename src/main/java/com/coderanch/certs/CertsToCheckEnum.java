package com.coderanch.certs;

import java.util.*;
import java.util.stream.*;

/**
 * Maps the cert we are monitoring with the current URL
 *
 * @author jeanne
 */
public enum CertsToCheckEnum {

    /*
     * OCA 7
     */
    OCAJP_JAVA_7("1Z0-803"),
    /*
     * OCP 7
     */
    OCPJP_JAVA_7("1Z0-804"),

    /*
     * OCP 8
     */
    OCPJP_JAVA_8("1Z0-809"),
    /*
     * Java 7 OCP upgrade
     */
    JAVA_7_UPGRADE("1Z0-805"),
    /*
     * OCA 8
     */
    OCAJP_JAVA_8("1Z0-808"),
    //"https://education.oracle.com/ccstoreui/v1/pages/java-se-8-programmer-i/pexam_1Z0-808?dataOnly=false&cacheableDataOnly=true&productTypesRequired=true"),
    /*
     * Java 7 to 8 OCP upgrade
     */
    JAVA_8_UPGRADE("1Z0-810"),
    /*
     * Java <=6 to 8 OCP upgrade
     */
    JAVA_8_UPGRADE_FROM_PRE_7("1Z0-813"),
    /*
     * Java (8) Foundational
     */
    JAVA_FOUNDATIONAL("1Z0-811"),

    // ----------------------------------------------------

    /*
     * OCMEA 6 - part 1
     */
    OCMJEA_6_PART_1("1Z0-807"),
    /*
     * OCMEA 6 - part 2
     */
    OCMJEA_ASSIGNMENT("1Z0-865"),
    /*
     * OCMEA 6 - part 3
     */
    OCMJEA_ESSAY("1Z0-866"),
    /*
     * OCMEA 6 - part 3 resubmit
     */
    OCMJEA_RESUBMIT("1Z0-867"),

    // ----------------------------------------------------

    /*
     * JPA 6
     */
    JPA_6("1Z0-898"),
    /*
     * EJB 6
     */
    EJB_6("1Z0-895"),
    /*
     * Java EE 7
     */
    JAVA_EE_7("1Z0-900"),

    // ----------------------------------------------------
    /*
     * Web Services 6
     */
    WEB_SERVICES_6("1Z0-897"),
    /*
     * Web Component 6
     */
    WEB_COMPONENT_6("1Z0-899"),
    /*
     * JSF 6
     */
    JSF_6("1Z0-896");
    // ----------------------------------------------------
    String examNumber;
    String url;
    // couldn't figure how to get this programmatically so looked up each one
    String ajaxDataUrl;

    private CertsToCheckEnum(String examNumber) {
        this.examNumber = examNumber;
    }

    /**
     * URL of exam home page using default URL format. If this format changes, need to override in each enum
     *
     * @return url
     */
    public String getUrl() {
        return "https://education.oracle.com/pls/web_prod-plq-dad/db_pages.getpage?page_id=5001&get_params=p_exam_id:" + examNumber;
    }

    /**
     * URL of exam data using default URL format. If this format changes, need to override in each enum
     *
     * @return url
     */
    public String getAjaxDataUrl() {
        return "https://education.oracle.com/ccstoreui/v1/pages/product/pexam_" + examNumber + "?dataOnly=false&cacheableDataOnly=true&productTypesRequired=true";
    }

    public String getExamNumber() {
        return examNumber;
    }

    public static Set<String> getSetOfExamNumbers() {
        return Stream.of(CertsToCheckEnum.values()).map(CertsToCheckEnum::getExamNumber).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return super.toString() + " (" + examNumber + ")";
    }
}
