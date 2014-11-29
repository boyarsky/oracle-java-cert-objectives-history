package com.coderanch.certs;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

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

	private CertsToCheckEnum certToCheck;
	private WebDriver driver;

	public CheckForChangesIT(CertsToCheckEnum certToCheck) {
		this.certToCheck = certToCheck;
	}

	// ----------------------------------------------------

	@Before
	public void connect() {
		driver = new HtmlUnitDriver();
		driver.get(certToCheck.getUrl());
	}

	// ----------------------------------------------------

	@Test
	public void upToDate() {
		System.out.println(driver.getPageSource());
	}

}
