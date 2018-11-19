package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import org.apache.commons.io.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.htmlunit.*;
import org.w3c.dom.*;

/**
 * Checks if test objectives have changed.
 * 
 * @author jeanne
 *
 */
public class CheckForChangesIT  {

	private CertsToCheckEnum certToCheck;

	// ----------------------------------------------------

	@ParameterizedTest
	@EnumSource(CertsToCheckEnum.class)
	public void upToDate(CertsToCheckEnum c) throws Exception {
	    assumeTrue(c != CertsToCheckEnum.OCPJP_JAVA_7, "OCP 7 exam returns odd HTML for topics list. It's going away in December though so not worth worrying about.");

        printHeader(c);
        certToCheck = c;
		// TODO remove
		if (certToCheck.getAjaxDataUrl().trim().isEmpty()) {
			throw new RuntimeException("need to finish test");
		}
		assertNotEquals("", certToCheck.getAjaxDataUrl(), "need to specifcy url");
		String url = certToCheck.getAjaxDataUrl();
		try (InputStream stream = new URL(url).openStream()) {
			List<String> lines = IOUtils.readLines(stream);
			assertEquals(1, lines.size(), "expecting only one line of input. If changes update this code");
			String currentData = convertToString(lines.get(0));
			assertSameAsExisting(currentData);
		}
	}

    private void printHeader(CertsToCheckEnum c) {
        System.out.println("Checking " + c.getExamNumber());
        System.out.println("URL " + c.getUrl());
        System.out.println("AJAX Data " + c.getAjaxDataUrl());
        System.out.println("-----------------------------------------");
        System.out.println("");
    }

    // ----------------------------------------------------

	/*
	 * Human readable version that we store/compare over time
	 */
	private String convertToString(String json) throws Exception {
		SingleExamPageParser parser = new SingleExamPageParser();
		return parser.convertToTextFormat(json);
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
