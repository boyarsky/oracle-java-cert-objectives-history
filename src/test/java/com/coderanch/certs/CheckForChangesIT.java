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

	private String getCDataForTag(String tag) {
		NodeList nodeList = doc.getElementsByTagName(tag);
		assertEquals("must be exactly one tag named " + tag, 1,
				nodeList.getLength());
		Node cdata = nodeList.item(0).getFirstChild();
		return cdata.getNodeValue();
	}

	/*
	 * Human readable version that we store/compare over time
	 */
	private String convertToString() {
		StringBuilder data = new StringBuilder();

		data.append("Duration: " + getCDataForTag("DURATION"));

		System.out.println(data);
		return data.toString();
	}

}
