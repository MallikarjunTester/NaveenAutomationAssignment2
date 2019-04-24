package base.lab.naveenautomation;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentTest;

public class DesiredCapsManager   {

	
	
	public DesiredCapabilities capabilities;

	public WebDriver getDesiredCapabilities(String browser,WebDriver driver) {

		// Set DesiredCapabilities
		capabilities = new DesiredCapabilities();
		
		switch (browser.trim()) {

		case "firefox":
			driver  = setFirefoxCapabilities(capabilities,driver);
			return driver;

		case "chrome":
			driver = setChromeCapabilities(capabilities,driver);
			return driver;


		}
		return driver;
		

	}

	// setFirefoxCapabilities
	private WebDriver setFirefoxCapabilities(DesiredCapabilities capabilities,WebDriver driver) {
		
		if(Base.platfromName.equalsIgnoreCase("windows")) {
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
		// Accept Untrusted Certificates
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		
		// Use No Proxy Settings
		profile.setPreference("network.proxy.type", 0);
		profile.setPreference("browser.download.folderList", 2);
		
		// Set Firefox profile to capabilities
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		 driver = new FirefoxDriver(capabilities);
		
		} else if(Base.platfromName.equalsIgnoreCase("mac"))  {
			
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
			 driver = new FirefoxDriver(capabilities);
		}

		return driver;
	}

	// setChromeCapabilities
	private WebDriver setChromeCapabilities(DesiredCapabilities capabilities,WebDriver driver) {
		if(Base.platfromName.equalsIgnoreCase("windows")) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("window-size=1920,800");
			chromeOptions.addArguments("disable-infobars");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			driver = new ChromeDriver(chromeOptions);

		} else if(Base.platfromName.equalsIgnoreCase("mac"))  {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("window-size=1920,800");
			chromeOptions.addArguments("disable-infobars");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			driver = new ChromeDriver(chromeOptions);
		}
		return driver;
	}

	// Set IE Capabilities
	private void setIECapabilities(DesiredCapabilities capabilities) {

		System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(CapabilityType.BROWSER_NAME, "IE");
		options.setCapability("ignoreProtectedModeSettings", true);
		options.setCapability("ie.forceCreateProcessApi", true);
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

	}


}
