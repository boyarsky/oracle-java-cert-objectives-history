package com.coderanch.certs;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import javax.xml.parsers.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.*;

@RunWith(Parameterized.class)
public class CheckForChangesIT {

	@Parameters
	public static List<CertsToCheckEnum[]> suite() {
		List<CertsToCheckEnum[]> result = new ArrayList<>();
		for (CertsToCheckEnum element : CertsToCheckEnum.values()) {
			result.add(new CertsToCheckEnum[] { element });
		}
		return result;
	}

	// ----------------------------------------------------

	private static final String XML_URL = "http://education.oracle.com/pls/web_prod-plq-dad/oucertapp_bo.getExamData?p_exam_id=";

	private CertsToCheckEnum certToCheck;
	private InputStream stream;
	private Document doc;

	public CheckForChangesIT(CertsToCheckEnum certToCheck) {
		this.certToCheck = certToCheck;
	}

	// ----------------------------------------------------

	@Before
	public void connect() throws Exception {
		String url = XML_URL + certToCheck.getExamNumber();
		stream = new URL(url).openStream();
	}

	@After
	public void close() {
		if (stream != null) {
			try {
				stream.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	// ----------------------------------------------------

	@Test
	public void upToDate() throws Exception {
		parseDocument();
		String currentData = convertToString();
		assertSameAsExisting(currentData);
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
		assertEquals("must be exactly one tag named " + tag + " for "
				+ certToCheck + " in " + certToCheck.getExamNumber(), 1,
				nodeList.getLength());
		Node cdata = nodeList.item(0).getFirstChild();
		String value = cdata.getNodeValue();
		System.out.println(value);
		value = value.replaceAll("^\\s*<p>", "");
		value = value.replaceAll("</p>\\s+", "");
		value = value.replaceAll("<img[^>]*>", "");
		value = value.replaceAll("<a[^>]*>", "");
		value = value.replaceAll("</a>", "");
		value = value.replaceAll("&nbsp;", "");
		
		System.out.println("---------------------------");
		System.out.println(value);
		return value.trim();
	}
	
	private String getCDataForTagWithoutDivs(String tag) {
		String value = getCDataForTag(tag);
		value = value.replaceAll("<div[^>]*>", "");
		value = value.replaceAll("</div>", "");
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
	 * Fail if file not present (new cert) or something has changed since last
	 * run.
	 */
	private void assertSameAsExisting(String actual) throws Exception {
		Path path = Paths.get("src/main/resources/"
				+ certToCheck.getExamNumber() + ".txt");
		assertTrue(path + " does not exist for " + certToCheck
				+ ". Please create it with contents: \n" + actual,
				Files.exists(path));
		String expected = new String(Files.readAllBytes(path));
		assertEquals(
				"Oracle has updated the cert "
						+ certToCheck
						+ ". Please update "
						+ path
						+ " with the new contents and publicize if a significant change: \n"
						+ actual, expected, actual);
	}
}
