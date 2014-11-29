package com.coderanch.certs;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

/**
 * Checks the public page still uses the same AJAX URL since CheckForChangesIT
 * uses the AJAX url directly. (This test doesn't really need Selenium; used it
 * because originally was planning to do the whole task in Selenium).
 * 
 * @author jeanne
 *
 */
public class SanityCheckAjaxUrlUnchangedIT {

	private WebDriver driver;

	// ----------------------------------------------------

	@Before
	public void connect() {
		driver = new HtmlUnitDriver();
	}

	// ----------------------------------------------------

	@Test
	public void ajaxUrl() {
		String url = CertsToCheckEnum.OCA_JAVA_8.getUrl();
		driver.get(url);
		String source = driver.getPageSource();
		assertTrue(
				url
						+ " no longer uses same page for AJAX data; please check program",
				source.contains("var url=\"/pls/web_prod-plq-dad/oucertapp_bo.getExamData\";"));
	}

}
