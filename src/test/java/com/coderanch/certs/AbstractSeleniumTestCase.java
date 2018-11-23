package com.coderanch.certs;

import java.util.concurrent.TimeUnit;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractSeleniumTestCase {


    protected WebDriver driver;
    
    // ----------------------------------------------------

	@BeforeEach
	public final void connect() {
		driver = new JBrowserDriver(Settings.builder()
			.timezone(Timezone.AMERICA_NEWYORK)
			.userAgent(UserAgent.CHROME).build());

		// says 120 but is really 0
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}

	@AfterEach
	public final void closeDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

    /*
     * Check hidden text (textContent) instead of getText() because page
     * requires clicking/expansion to see the specific exams
     */
    protected String getHiddenText(WebElement e) {
        return e.getAttribute("textContent");
    }
    
}