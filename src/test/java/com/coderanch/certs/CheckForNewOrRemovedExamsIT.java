package com.coderanch.certs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import com.machinepublishers.jbrowserdriver.*;

/**
 * Checks if any exams were added in the Java domain (or removed). If so, fail
 * test so know to add/remove text file to src/main/resources
 * 
 * @author jeanne
 *
 */
public class CheckForNewOrRemovedExamsIT {

	private static final String EXAM_LIST_URL = "https://education.oracle.com/oracle-certification-exams-list";

	private WebDriver driver;
	private Set<String> examNumbersFromOracle;
	private Set<String> examNumbersTested;

	// ----------------------------------------------------

	@BeforeEach
	public void connect() {
		driver = new JBrowserDriver(Settings.builder().userAgent(UserAgent.CHROME).build());

		// says 120 but is really 0
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}

	// ----------------------------------------------------

	@Test
	public void listsInSync() throws Exception {
		setCurrentExamListFromOracle();
		setExamNumbersTested();

		Set<String> testedButRemovedFromOracleList = new HashSet<>(
				examNumbersTested);
		testedButRemovedFromOracleList.removeAll(examNumbersFromOracle);

		Set<String> onOracleListButNotTested = new HashSet<>(
				examNumbersFromOracle);
		onOracleListButNotTested.removeAll(examNumbersTested);

		testedButRemovedFromOracleList = normalizeBetaExamNumbers(testedButRemovedFromOracleList);
		onOracleListButNotTested = normalizeBetaExamNumbers(onOracleListButNotTested);

		assertEquals(0, testedButRemovedFromOracleList.size(), testedButRemovedFromOracleList
				+ "\nare checked for currency in this test project, "
				+ "\nbut no longer on Oracle's list. "
				+ "\nNext step: remove from enum so no longer check");

		assertEquals(0, onOracleListButNotTested.size(), onOracleListButNotTested
				+ "\nare on Oracle's list, but not tested in this project, "
				+ "\nNext step: add to enum so start checking");
	}

	/*
	 * normalize to change 1Z1 to 1Z0 for the exam prefix
	 */
	private Set<String> normalizeBetaExamNumbers(Set<String> testedButRemovedFromOracleList) {
		return testedButRemovedFromOracleList.stream().map(s -> s.replaceFirst("^1Z1", "1Z0"))
				.collect(Collectors.toSet());
	}

	private void setExamNumbersTested() {
		examNumbersTested = CertsToCheckEnum.getSetOfExamNumbers();
	}

	private void setCurrentExamListFromOracle() throws Exception {
		examNumbersFromOracle = new HashSet<>();
		driver.get(EXAM_LIST_URL);
	
		WebElement javaHeader = getJavaHeader();
		WebElement javaSection = javaHeader.findElement(By.xpath("./.."));
		
		List<WebElement> examListElements = javaSection.findElements(By.tagName("li"));
		examNumbersFromOracle = examListElements.stream()
			.map(this::getHiddenText)
			// each list item in format examName | examNumber
			.map(str -> str.replaceFirst("^.*\\|", ""))
			.map(String::trim)
			.collect(Collectors.toSet());
	}

	private WebElement getJavaHeader() {
		List<WebElement> elements = driver.findElements(By.tagName("h5"));
		List<WebElement> matches = elements
			.stream()
			.filter(e -> "Java".equals(getHiddenText(e)))
			.collect(Collectors.toList());
		assertEquals(1 , matches.size(), "should be exactly one java header section");
		return matches.get(0);
	}

	/*
	* Check hidden text (textContent) instead of getText() becuase page
	* requires clicking/expansion to see the specific exams
	*/
	private String getHiddenText(WebElement e) {
		return e.getAttribute("textContent");
	}
}
