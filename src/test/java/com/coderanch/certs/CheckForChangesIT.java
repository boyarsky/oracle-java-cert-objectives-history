package com.coderanch.certs;

import static org.junit.Assert.*;

import java.io.*;
import java.net.*;
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
		List<CertsToCheckEnum[]> result = new ArrayList<CertsToCheckEnum[]>();
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

	}

	private void parseDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(stream);
	}

	/**
	 * Remove
	 * <p>
	 * tags and whitespace if present
	 * 
	 * @param tag
	 * @return
	 */
	private String getCDataForTag(String tag) {
		NodeList nodeList = doc.getElementsByTagName(tag);
		assertEquals("must be exactly one tag named " + tag, 1,
				nodeList.getLength());
		Node cdata = nodeList.item(0).getFirstChild();
		String value = cdata.getNodeValue();
		value = value.replaceAll("^\\s*<p>", "");
		value = value.replaceAll("</p>\\s+", "");
		return value.trim();
	}

	/*
	 * Human readable version that we store/compare over time
	 */
	private String convertToString() throws Exception {
		StringBuilder data = new StringBuilder();

		data.append("Duration: \t" + getCDataForTag("DURATION") + "\n");
		data.append("# Questions: \t" + getCDataForTag("NUMBER_OF_QUESTIONS")
				+ "\n");
		data.append("Passing Score: \t" + getCDataForTag("PASSING_SCORE")
				+ "\n");
		data.append("US exam cost: \t" + getCDataForTag("PRICE") + "\n");
		data.append("\n");

		TopicListParser parser = new TopicListParser(getCDataForTag("TOPICS"));
		data.append("Topics:\n" + parser.convertToTextFormat());

		System.out.println(data);
		return data.toString();
	}

}
