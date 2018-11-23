package com.coderanch.certs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Checks if any exams were added in the Java domain (or removed). If so, fail
 * test so know to add/remove text file to src/main/resources
 * 
 * @author jeanne
 *
 */
public class RetiringExamsIT extends AbstractSeleniumTestCase {

	private static final String EXAM_LIST_URL = "https://education.oracle.com/oracle-certification-exams-list";

	private Set<String> examNumbersFromOracle;
	private Set<String> examNumbersTested;

	// ----------------------------------------------------

	@Test
	public void retired() throws Exception {
        driver.get(EXAM_LIST_URL);

        WebElement div = driver.findElement(By.id("Rexams"));
        WebElement tableBody = div.findElement(By.tagName("tbody"));

        Set<RetiredExam> oracleRetiredExamList = tableBody.findElements(By.tagName("tr"))
                .stream()
                .map(this::createRetiredExamObject)
                // only care about the Java ones in this program
                .filter(RetiredExam::isExamInCertsToCheckEnum)
                // omit if already retired
                .filter(r -> ! r.isExpired())
                .collect(Collectors.toSet());

        Set<RetiredExam> knownRetirements = RetiredExam.parseFromCsv("src/main/resources/pending-retirement.csv");

        oracleRetiredExamList.forEach(r -> assertKnownRetirement(r, knownRetirements));

        assertEquals(0, knownRetirements.size(),
                "Exam has been removed from Oracle's retirement list: " + knownRetirements);
	}

    private void assertKnownRetirement(RetiredExam retirement, Set<RetiredExam> knownRetirements) {
        String message = "Please update pending-retirements.csv and announce at CodeRanch.";
        String examNumber = retirement.getExamNumber();
        Optional<RetiredExam> optional = knownRetirements
               .stream()
               .filter(r -> r.getExamNumber().equals(examNumber))
               .findAny();
	   RetiredExam actual = optional.orElseThrow(() -> new IllegalStateException(
                   examNumber + " is now retired. " + message));
	   assertEquals(retirement.getExpiration(), actual.getExpiration(), examNumber
               + " has new expiration date. " + message);

	   knownRetirements.removeIf(r -> r.getExamNumber().equals(examNumber));
    }


    private RetiredExam createRetiredExamObject(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        String examText = getHiddenText(cells.get(0));
        String retires = getHiddenText(cells.get(1));
        return RetiredExam.parseFromWeb(examText, retires);
    }

}
