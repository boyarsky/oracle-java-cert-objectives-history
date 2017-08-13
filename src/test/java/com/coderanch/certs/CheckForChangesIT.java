package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;

import javax.xml.parsers.*;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.w3c.dom.*;

public class CheckForChangesIT {

	private static final String XML_URL = "http://education.oracle.com/pls/web_prod-plq-dad/oucertapp_bo.getExamData?p_exam_id=";

	private CertsToCheckEnum certToCheck;
	private InputStream stream;
	private Document doc;

	// ----------------------------------------------------

	@ParameterizedTest
	@EnumSource(CertsToCheckEnum.class)
	public void upToDate(CertsToCheckEnum c) throws Exception {
		certToCheck = c;
		String url = XML_URL + certToCheck.getExamNumber();
		try (InputStream stream = new URL(url).openStream()) {
			parseDocument();
			String currentData = convertToString();
			assertSameAsExisting(currentData);
		}
	}

	// ----------------------------------------------------

	private void parseDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(stream);
	}

	/*
	 * Remove p, img and a tags along with whitespace if present
	 */
	private String getCDataForTag(String tag) {
		NodeList nodeList = doc.getElementsByTagName(tag);
		assertEquals(1, nodeList.getLength(), "must be exactly one tag named " + tag + " for "
				+ certToCheck + " in " + certToCheck.getExamNumber());
		Node cdata = nodeList.item(0).getFirstChild();
		String value = cdata.getNodeValue();
		value = value.replaceAll("^\\s*<p>", "");
		value = value.replaceAll("</p>\\s+", "");
		value = value.replaceAll("<img[^>]*>", "");
		value = value.replaceAll("<a[^>]*>", "");
		value = value.replaceAll("</a>", "");
		value = value.replaceAll("&nbsp;", "");
		value = value.replaceAll("Exam topics in Spanish", "");
		return value.trim();
	}

	private String getCDataForTagWithoutDivs(String tag) {
		String value = getCDataForTag(tag);
		value = value.replaceAll("<div[^>]*>", "");
		value = value.replaceAll("</div>", "");
		value = value.replaceAll("<p>", "");
		return value.trim();
	}

	/*
	 * Human readable version that we store/compare over time
	 */
	private String convertToString() throws Exception {
		StringBuilder data = new StringBuilder();

		data.append("Duration: " + getCDataForTagWithoutDivs("DURATION") + "\n");
		data.append("# Questions: " + getCDataForTagWithoutDivs("NUMBER_OF_QUESTIONS")
				+ "\n");
		data.append("Passing Score: " + getCDataForTagWithoutDivs("PASSING_SCORE") + "\n");
		data.append("US exam cost: " + getCDataForTagWithoutDivs("PRICE") + "\n");
		data.append("\n");

		TopicListParser parser = new TopicListParser(getCDataForTag("TOPICS"));
		data.append("Topics:\n" + parser.convertToTextFormat());

		return data.toString();
	}

	// ----------------------------------------------------

	/*
	 * Fail if file not present (new cert) or something has changed since last run.
	 */
	private void assertSameAsExisting(String actual) throws Exception {
		Path path = Paths.get("src/main/resources/"
				+ certToCheck.getExamNumber() + ".txt");
		assertTrue(Files.exists(path), path + " does not exist for " + certToCheck
				+ ". Please create it with contents: \n" + actual);
		String expected = new String(Files.readAllBytes(path));
		assertEquals(expected, actual, "Oracle has updated the cert "
				+ certToCheck
				+ ". Please update "
				+ path
				+ " with the new contents and publicize if a significant change: \n"
				+ actual);
	}
}
