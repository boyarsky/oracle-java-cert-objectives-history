package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Checks the public page still uses the same AJAX URL since CheckForChangesIT
 * uses the AJAX url directly. (This test doesn't really need Selenium; used it
 * because originally was planning to do the whole task in Selenium).
 * 
 * @author jeanne
 *
 */
public class SanityCheckAjaxUrlUnchangedIT extends AbstractSeleniumTestCase {

	@Test
	public void ajaxUrl() {
		// has the data in JSON (or HTML inside the JSON)
		String url = "https://education.oracle.com/ccstoreui/v1/pages/java-se-8-programmer-i/pexam_1Z0-808?dataOnly=false&cacheableDataOnly=true&productTypesRequired=true";
		driver.get(url);

		String source = driver.getPageSource();
		assertTrue(source.contains("Java Basics"),
				url + " no longer uses same page for AJAX data; please check program");
	}

}
